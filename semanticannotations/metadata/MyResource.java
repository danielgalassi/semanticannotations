package metadata;

import java.util.ArrayList;

import org.w3c.dom.NodeList;

import com.hp.hpl.jena.rdf.model.Model;

public class MyResource {

	private String sRscAlias;
	ArrayList <Model> mRsc;
	
	public String getAlias () {
		return sRscAlias;
	}
	
	public void setAlias (String sAlias) {
		sRscAlias = sAlias;
	}
	
	public MyResource (String sAlias, NodeList nlDefs) {
		setAlias(sAlias);
		System.out.println("New Resource Created\t" + sRscAlias);
	}
}
