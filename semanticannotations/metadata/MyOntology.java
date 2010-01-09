package metadata;

import java.util.HashMap;

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
	private HashMap <String, OntModel> hmOntology;

	/**
	 * 
	 * @param sOntAlias ontology key
	 * @return an ontology
	 */
	public OntModel getOntology (String sOntAlias) {
		return hmOntology.get(sOntAlias);
	}
	
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
	
	/**
	 * Loads all the ontologies
	 * @param nlDefs config.xml node list
	 */
	private void loadOntModel (NodeList nlDefs) {
		String sOntAlias;
		String sOntFile;
		Node nDef = null;
		hmOntology = new HashMap <String, OntModel> ();
		System.out.print("Loading definitions\t");
		for (int i=0; i<nlDefs.getLength(); i++) {
			nDef = nlDefs.item(i);
			if (nDef.getNodeName().equals("RDFvoc")) {
				sOntFile = nDef.getChildNodes().item(0).getNodeValue();
				sOntAlias = nDef.getAttributes()
								.getNamedItem("name")
								.getNodeValue();
				//adding new OntModel to the Ontologies catalog
				hmOntology.put (sOntAlias,
							RDFUtils.loadOntModelFromFile(sOntFile + ".rdf"));
			}
		}
		System.out.println();
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
