package semanticAnnotations;

import metadata.MyResourceFactory;

public class SemanticAnnotations {

	private static MyResourceFactory rscFactory;
	
	public static void loadMetadataDefinitions () {
		rscFactory = new MyResourceFactory();
	}
	
	public static void launchUI () {
		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		loadMetadataDefinitions();
		launchUI();
	}

}
