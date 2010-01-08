/**
 * code related to the user inteface / presentation
 */
package ui;

import java.util.Iterator;

import metadata.MyOntology;
import metadata.OntologyCatalog;

/**
 * @author dgalas
 *
 */
public class UIMgr {

	private OntologyCatalog ontCatalog;
	
	private void buildOntologyPickerUI () {
		//select ontology
		Iterator <String> itOntNames = ontCatalog.getOntNames();
		while (itOntNames.hasNext())
			System.out.println("Ontology available: " + itOntNames.next() );
		//upon selection...
		buildForm("Annotations");
	}
	
	private void buildForm (String sOntSelected) {
		MyOntology ontSelected = ontCatalog.getOntModels(sOntSelected);
		System.out.println("Ontology selected: " + ontSelected.getsOntName());
	}
	
	private void setOntCatalog (OntologyCatalog ontCatalog) {
		this.ontCatalog = ontCatalog;
	}
	
	public UIMgr (OntologyCatalog ontCatalog) {
		setOntCatalog (ontCatalog);
		buildOntologyPickerUI ();
	}
}
