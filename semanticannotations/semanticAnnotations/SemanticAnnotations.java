package semanticAnnotations;

import metadata.MyResourceFactory;

public class SemanticAnnotations {

	private static MyResourceFactory rscFactory;
	
	/**
	 * Invokes the Resource Factory loader
	 */
	public static void loadMetadataDefinitions () {
		rscFactory = new MyResourceFactory();
		if (rscFactory.isRscFactoryReady())
			System.out.println("Resource Factory ready!");
	}
	
	public static void launchUI () {
		
	}

	/**
	 * Cleanup method, setting objects to null before shutting down the system
	 */
	private static void cleanup() {
		rscFactory = null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		loadMetadataDefinitions();
		launchUI();
		cleanup();
	}
}
