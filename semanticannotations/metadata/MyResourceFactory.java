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
public class MyResourceFactory {

	private ArrayList <MyOntology> alOntModels;
	private boolean bRscFactoryReady = false;
	
	/**
	 * 
	 */
	private void loadModels () {
		Document fConfig = XMLUtils.File2Document(new File("config.xml"));
		NodeList nlCfg = fConfig.getElementsByTagName("RDFList");
		Node nRsc = null;
		int iDefs = nlCfg.getLength();
		
		if (iDefs > 0) {
			alOntModels = new ArrayList <MyOntology> ();
			System.out.println("Loading " + iDefs + " top-level definitions");
			for (int i=0; i < iDefs; i++) {
				nRsc = nlCfg.item(i);
				alOntModels.add(new MyOntology(nRsc.getAttributes()
													.getNamedItem("type")
													.getNodeValue(),
												nRsc.getChildNodes()));
			}
		}
		bRscFactoryReady = true;
	}

	/**
	 * @return the bRscFactoryReady
	 */
	public boolean isRscFactoryReady() {
		return bRscFactoryReady;
	}

	/**
	 * 
	 */
	public MyResourceFactory () {
		loadModels();
	}
}
