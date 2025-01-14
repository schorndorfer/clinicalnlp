package clinicalnlp.dict.ae

import clinicalnlp.dict.*
import clinicalnlp.dsl.DSL
import clinicalnlp.types.DictMatch
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.io.Resources
import groovy.util.logging.Log4j
import opennlp.tools.tokenize.TokenizerME
import org.apache.commons.io.Charsets
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.tcas.Annotation
import org.codehaus.groovy.control.CompilerConfiguration
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.Resource

@Log4j
class DictAnnotatorImpl {
    private DictModel dictModel
    private Script postScript

    void initialize(String dictionaryPath,
                    String dictionaryType,
                    TokenizerME tokenizer,
                    Boolean caseInsensitive,
                    String initScriptFile,
                    String postScriptFile) {

        ObjectMapper mapper = new ObjectMapper()
        DefaultResourceLoader loader = new DefaultResourceLoader(ClassLoader.getSystemClassLoader());
        Resource schemaResource = loader.getResource(dictionaryPath)
        AbstractionSchema schema = mapper.readValue(schemaResource.inputStream, AbstractionSchema);
        this.dictModel = DictModelFactory.make(dictionaryType, schema, tokenizer, caseInsensitive)

        if (postScriptFile) {
            CompilerConfiguration config = new CompilerConfiguration()
            config.setScriptBaseClass("clinicalnlp.dsl.DSL")
            GroovyShell shell = new GroovyShell(config)
            this.postScript = shell.parse(Resources.toString(
                    Resources.getResource(postScriptFile),
                    org.apache.commons.io.Charsets.UTF_8))
            if (initScriptFile) {
                Script bindingsScript = shell.parse(Resources.toString(
                    Resources.getResource(initScriptFile), Charsets.UTF_8))
                this.postScript.setBinding(new Binding(bindingsScript.run()))
            }
        }
    }

    void process(JCas jcas,
                 Boolean caseInsensitive,
                 Float tolerance,
                 Integer maxDistance,
                 String containerClassName,
                 String tokenClassName) {

        Class<Annotation> ContainerClass = Class.forName(containerClassName)
        Class<Annotation> TokenClass = Class.forName(tokenClassName)

        jcas.select(type:ContainerClass).each { Annotation container ->
            Collection<Annotation> anns = jcas.select(type:TokenClass,
                    filter:DSL.coveredBy(container))
            Collection<CharSequence> tokens = new ArrayList<>()
            anns.each { Annotation ann ->
                tokens << (caseInsensitive ? ann.coveredText.toLowerCase() : ann.coveredText)
            }
            this.dictModel.matches(tokens, tolerance, maxDistance).each { TokenMatch m ->
                Collection<Annotation> matched = new ArrayList<>()
                for (int i = m.begin; i <= m.end; i++) {
                    matched << anns.get(i)
                }
                jcas.create(type:DictMatch,
                    begin:matched[0].begin,
                    end:matched[matched.size()-1].end,
                    canonical:m.value.canonical,
                    code:m.value.code,
                    vocabulary:m.value.vocab,
                    container:container,
                    matchedTokens:matched,
                    score:m.score
                )
            }
        }

        // execute the post script, if specified
        if (this.postScript) {
            this.postScript.setProperty('jcas', jcas)
            this.postScript.run()
        }
    }
}
