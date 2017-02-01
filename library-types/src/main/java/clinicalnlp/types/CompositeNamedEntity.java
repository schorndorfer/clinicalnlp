

/* First created by JCasGen Tue Jan 31 21:25:28 CST 2017 */
package clinicalnlp.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Jan 31 21:25:28 CST 2017
 * XML source: /var/folders/k0/jcxw1d05549c48zgccrbj_q40000gp/T/leoTypeDescription_7b137e80-296a-485d-88dc-f9f994b57aa65542413090411876594.xml
 * @generated */
public class CompositeNamedEntity extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(CompositeNamedEntity.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected CompositeNamedEntity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public CompositeNamedEntity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public CompositeNamedEntity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public CompositeNamedEntity(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: concepts

  /** getter for concepts - gets array of concepts
   * @generated
   * @return value of the feature 
   */
  public FSArray getConcepts() {
    if (CompositeNamedEntity_Type.featOkTst && ((CompositeNamedEntity_Type)jcasType).casFeat_concepts == null)
      jcasType.jcas.throwFeatMissing("concepts", "clinicalnlp.types.CompositeNamedEntity");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((CompositeNamedEntity_Type)jcasType).casFeatCode_concepts)));}
    
  /** setter for concepts - sets array of concepts 
   * @generated
   * @param v value to set into the feature 
   */
  public void setConcepts(FSArray v) {
    if (CompositeNamedEntity_Type.featOkTst && ((CompositeNamedEntity_Type)jcasType).casFeat_concepts == null)
      jcasType.jcas.throwFeatMissing("concepts", "clinicalnlp.types.CompositeNamedEntity");
    jcasType.ll_cas.ll_setRefValue(addr, ((CompositeNamedEntity_Type)jcasType).casFeatCode_concepts, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for concepts - gets an indexed value - array of concepts
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public NamedEntityMention getConcepts(int i) {
    if (CompositeNamedEntity_Type.featOkTst && ((CompositeNamedEntity_Type)jcasType).casFeat_concepts == null)
      jcasType.jcas.throwFeatMissing("concepts", "clinicalnlp.types.CompositeNamedEntity");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CompositeNamedEntity_Type)jcasType).casFeatCode_concepts), i);
    return (NamedEntityMention)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CompositeNamedEntity_Type)jcasType).casFeatCode_concepts), i)));}

  /** indexed setter for concepts - sets an indexed value - array of concepts
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setConcepts(int i, NamedEntityMention v) { 
    if (CompositeNamedEntity_Type.featOkTst && ((CompositeNamedEntity_Type)jcasType).casFeat_concepts == null)
      jcasType.jcas.throwFeatMissing("concepts", "clinicalnlp.types.CompositeNamedEntity");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((CompositeNamedEntity_Type)jcasType).casFeatCode_concepts), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((CompositeNamedEntity_Type)jcasType).casFeatCode_concepts), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    