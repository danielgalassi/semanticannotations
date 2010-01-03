package metadata;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import utils.XMLUtils;

public class MyResourceFactory {

	private void loadModels () {
		Document fConfig = XMLUtils.File2Document(new File("config.xml"));
		NodeList nlCfg = fConfig.getElementsByTagName("RDFList");
		NodeList nlVoc = fConfig.getElementsByTagName("RDFvoc");
		ArrayList <MyResource> alRscs = new ArrayList <MyResource>();
		String sResAlias;
		int iResources = nlCfg.getLength();
		
		System.out.println(iResources + " top-level RDF definitions found");
		for (int i=0; i<nlCfg.getLength(); i++) {
			sResAlias = nlCfg.item(i)
						.getAttributes()
						.getNamedItem("type")
						.getNodeValue();
			alRscs.add(new MyResource(sResAlias, nlCfg.item(i).getChildNodes()));
		}
		
		System.out.println("RDFVocs:");
		for (int i=0; i<nlVoc.getLength(); i++) {
			System.out.print("main?=" + nlVoc.item(i).getAttributes().getNamedItem("main").getNodeValue());
			System.out.println("\t" + nlVoc.item(i).getParentNode().getAttributes().getNamedItem("type").getNodeValue() +
								"\t" + nlVoc.item(i).getFirstChild().getNodeValue());
			//System.out.println("\t" + nlVoc.item(i).getFirstChild().getNodeValue());
		}
	}
	
	public MyResourceFactory () {
		loadModels();
	}
}
