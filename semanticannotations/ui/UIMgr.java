/**
 * code related to the user inteface / presentation
 */
package ui;

import metadata.MyOntology;
import metadata.OntologyCatalog;
import semanticAnnotations.SemanticAnnotations;

/**
 * @author dgalas
 *
 */
public class UIMgr {

	private void buildOntologyPickerUI () {
		new OntologyPicker().setVisible(true);
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
		buildOntologyPickerUI ();
	}
}
