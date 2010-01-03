package metadata;

import org.w3c.dom.NodeList;

import com.hp.hpl.jena.ontology.OntModel;

/***
 * 
 * @author daniel
 *
 */
public class MyOntology {

	private String sOntName;
	private OntModel omModel;

	/**
	 * Getter method for MyOntology's alias
	 * @return the ontology alias as a String
	 */
	public String getsOntName () {
		return sOntName;
	}

	/**
	 * Setter method for MyOntology's alias 
	 * @param sOntName the ontology alias as a String
	 */
	private void setsOntName (String sOntName) {
		this.sOntName = sOntName;
	}
	
	private void loadOntModel (NodeList nlDefs) {
		
	}
	
	/**
	 * 
	 * @param sOntName	Ontology alias
	 * @param nlDefs	List of Models for this ontology
	 */
	public MyOntology (String sOntName, NodeList nlDefs) {
		System.out.println("Loading " + sOntName + " ontology");
		setsOntName(sOntName);
		loadOntModel(nlDefs);
	}
}
