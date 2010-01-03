package utils;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

/***
 * 
 * @author daniel
 *
 */
public class RDFUtils {

	/**
	 * 
	 * @param sResource
	 * @param sProperty
	 * @param sEncoding
	 * @param sValue
	 * @return
	 */
	public static Model addEncodedResource (String sResource,
											String sProperty,
											String sEncoding,
											String sValue) {
		String sXSIns = "http://www.w3.org/2001/XMLSchema-instance#";
		Model m = getNewModel();
		Resource r1 = m.createResource(sResource);
		Resource r2 = m.createResource()
						.addProperty (m.createProperty(sXSIns + "value"),
										sValue)
						.addProperty (m.createProperty(sXSIns + "type"),
										sEncoding);
		r1.addProperty(m.createProperty(sProperty), r2);
		return m;
	}

	/**
	 * 
	 * @param sResource
	 * @param sProperty
	 * @param sValue
	 * @return
	 */
	public static Model addEncodedResource (String sResource,
											String sProperty,
											String sValue) {
		Model m = getNewModel();
		Resource r = m.createResource(sResource);
		r.addProperty(m.createProperty(sProperty), sValue);
		return m;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Model getNewModel () {
		return ModelFactory.createDefaultModel();
	}
}
