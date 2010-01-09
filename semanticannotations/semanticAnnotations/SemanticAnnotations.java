/**
 * 
 */
package semanticAnnotations;

import metadata.OntologyCatalog;
import ui.UI;

/***
 * 
 * @author daniel
 *
 */
public class SemanticAnnotations {

	private static OntologyCatalog ontCatalog;
	
	/**
	 * Invokes the Ontology Catalog loader
	 */
	public static void loadMetadataDefinitions () {
		ontCatalog = new OntologyCatalog ();
		if (ontCatalog.isOntCatalogReady ())
			System.out.println("Ontology Catalog ready!");
	}
	
	/**
	 * Prepares the user interface
	 */
	public static void launchUI () {
		new UI().setVisible(true);
	}

	/**
	 * Cleanup method, setting objects to null before shutting down the system
	 */
	private static void cleanup () {
		ontCatalog = null;
	}

	public static OntologyCatalog getOntologyCatalog () {
		return ontCatalog;
	}
	
	/**
	 * @param args
	 */
	public static void main (String[] args) {
		//startup
		loadMetadataDefinitions ();
		launchUI ();
		//set objects to null
		cleanup ();
	}
}
