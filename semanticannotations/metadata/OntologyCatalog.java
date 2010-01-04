/**
 * code related to metadata handling
 */
package metadata;

import java.io.File;
import java.util.ArrayList;

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

	private ArrayList <MyOntology> alOntModels;
	private int iSize;
	private boolean bOntCatalogReady = false;
	
	/**
	 * Provides a list of ontology model names
	 * @return OntModel names in an ArrayList
	 */
	public ArrayList<String> getOntNames () {
		ArrayList <String> alOntNames = new ArrayList <String> ();
		for (int i=0; i<alOntModels.size(); i++)
			alOntNames.add(alOntModels.get(i).getsOntName());
		return alOntNames;
	}
	
	/**
	 * Loads all the ontologies specified in the config.xml file
	 */
	private void loadModels () {
		Document fConfig = XMLUtils.File2Document(new File("config.xml"));
		NodeList nlCfg = fConfig.getElementsByTagName("RDFList");
		Node nRsc = null;
		iSize = nlCfg.getLength();
		
		if (iSize > 0) {
			alOntModels = new ArrayList <MyOntology> ();
			System.out.println("Loading " + iSize + " top-level definitions");
			for (int i=0; i < iSize; i++) {
				nRsc = nlCfg.item(i);
				alOntModels.add(new MyOntology(nRsc.getAttributes()
													.getNamedItem("type")
													.getNodeValue(),
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
