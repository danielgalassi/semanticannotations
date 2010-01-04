/**
 * code related to the user inteface / presentation
 */
package ui;

import metadata.OntologyCatalog;

/**
 * @author dgalas
 *
 */
public class UIMgr {

	private OntologyCatalog ontCatalog;
	
	private void buildWelcomeFrame () {
		//select ontology
		for (int i=0; i<ontCatalog.getSize(); i++)
			System.out.println(ontCatalog.getOntNames().get(i));
		//upon selection...
		buildForm();
	}
	
	private void buildForm () {
		
	}
	
	private void setOntCatalog (OntologyCatalog ontCatalog) {
		this.ontCatalog = ontCatalog;
	}
	
	public UIMgr (OntologyCatalog ontCatalog) {
		setOntCatalog (ontCatalog);
		buildWelcomeFrame ();
	}
}
