package semanticAnnotations;

import ui.UIMgr;
import metadata.OntologyCatalog;

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
		new UIMgr (ontCatalog);
	}

	/**
	 * Cleanup method, setting objects to null before shutting down the system
	 */
	private static void cleanup () {
		ontCatalog = null;
	}
	
	/**
	 * @param args
	 */
	public static void main (String[] args) {
		//startup
		loadMetadataDefinitions();
		launchUI();
		//set objects to null
		cleanup();
	}
}
