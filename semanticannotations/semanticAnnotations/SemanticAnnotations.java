/**
 * 
 */
package semanticAnnotations;

import metadata.OntologyCatalog;
import ui.SplashScreen;
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
	 * 
	 */
	private static void launchSplashScreen() {
		
	}

	/**
	 * Prepares the user interface
	 */
	public static void launchUI () {
		UI u = new UI();
		u.setVisible(true);
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
		//show splash screen
		SplashScreen sscrn = new SplashScreen ();
		sscrn.setVisible(true);
		//startup
		loadMetadataDefinitions ();
		//5 extra seconds before closing the splash screen
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		};
		sscrn.setVisible(false);
		sscrn = null;
		//show user interface
		launchUI ();
		//set objects to null
		cleanup ();
	}
}
