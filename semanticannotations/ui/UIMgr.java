/**
 * code related to the user inteface / presentation
 */
package ui;

import java.util.Iterator;

import metadata.MyOntology;
import metadata.OntologyCatalog;
import semanticAnnotations.SemanticAnnotations;

/**
 * @author dgalas
 *
 */
public class UIMgr {

	private void buildOntologyPickerUI () {
		//select ontology
		Iterator <String> itOntNames = SemanticAnnotations
										.getOntologyCatalog()
										.getOntNames();
		while (itOntNames.hasNext())
			System.out.println("Ontology available: " + itOntNames.next() );
		//upon selection...
		buildForm("Annotations");
	}
	
	private void buildForm (String sOntSelected) {
		MyOntology ontSelected = SemanticAnnotations
									.getOntologyCatalog()
									.getOntModels(sOntSelected);
		System.out.println("Ontology selected: " + ontSelected.getsOntName());
	}
	
	public UIMgr (OntologyCatalog ontCatalog) {
		System.out.println(SemanticAnnotations.getOntologyCatalog().getSize());
		buildOntologyPickerUI ();
	}
}
