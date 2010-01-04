package metadata;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utils.RDFUtils;

import com.hp.hpl.jena.ontology.OntModel;

/***
 * 
 * @author daniel
 *
 */
public class MyOntology {

	private String sOntName;
	private ArrayList <OntModel> omModel;

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
		String sOntFile;
		Node nDef = null;
		omModel = new ArrayList <OntModel> ();
		System.out.println("Loading ontologies");
		for (int i=0; i<nlDefs.getLength(); i++) {
			nDef = nlDefs.item(i);
			if (nDef.getNodeName().equals("RDFvoc")) {
				sOntFile = nDef.getChildNodes().item(0).getNodeValue();
				System.out.println("\t" + sOntFile);
				//adding new OntModel to the Ontologies catalog
				omModel.add(RDFUtils.loadOntModelFromFile(sOntFile + ".rdf"));
			}
		}
	}
	
	/**
	 * 
	 * @param sOntName	Ontology alias
	 * @param nlDefs	List of Models for this ontology
	 */
	public MyOntology (String sOntName, NodeList nlDefs) {
		System.out.println("Loading '" + sOntName + "' ontology");
		setsOntName(sOntName);
		loadOntModel(nlDefs);
	}
}
