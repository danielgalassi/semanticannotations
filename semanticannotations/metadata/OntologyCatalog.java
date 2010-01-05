/**
 * code related to metadata handling
 */
package metadata;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utils.XMLUtils;

/***
 * 
 * @author daniel
 *
 */
public class OntologyCatalog {

	private HashMap <String, MyOntology> hmOntModels;
	private int iSize;
	private boolean bOntCatalogReady = false;
	
	/**
	 * 
	 * @param sOntName
	 * @return
	 */
	public MyOntology getOntModels (String sOntName) {
		return hmOntModels.get(sOntName);
	}
	
	/**
	 * Provides a list of ontology names
	 * @return ontology names
	 */
	public Iterator <String> getOntNames () {
		return (hmOntModels.keySet().iterator());
	}
	
	/**
	 * Loads all the ontologies specified in the config.xml file
	 */
	private void loadModels () {
		Document fConfig = XMLUtils.File2Document(new File("config.xml"));
		NodeList nlCfg = fConfig.getElementsByTagName("RDFList");
		Node nRsc = null;
		String sOntName;
		iSize = nlCfg.getLength();
		
		if (iSize > 0) {
			hmOntModels = new HashMap <String, MyOntology> ();
			System.out.println("Loading " + iSize + " top-level definitions");
			for (int i=0; i < iSize; i++) {
				nRsc = nlCfg.item(i);
				sOntName = nRsc.getAttributes()
								.getNamedItem("type")
								.getNodeValue();
				hmOntModels.put	(sOntName, 
								new MyOntology (sOntName, 
												nRsc.getChildNodes()));
			}
		}
		bOntCatalogReady = true;
	}

	/**
	 * Provides the state of the Ontology Catalog
	 * @return the bOntCatalogReady
	 */
	public boolean isOntCatalogReady () {
		return bOntCatalogReady;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return iSize;
	}

	/**
	 * 
	 */
	public OntologyCatalog () {
		loadModels();
	}
}
