package utils;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class RDFUtils {

	/**
	 * 
	 * @param sResource
	 * @param sProperty
	 * @param sEncoding
	 * @param sValue
	 * @return
	 */
	public static Model addEncodedResource(String sResource,
										String sProperty,
										String sEncoding,
										String sValue) {
		Model m = ModelFactory.createDefaultModel();
		Resource r1 = m.createResource(sResource);
		Resource r2 = m.createResource()
						.addProperty(m.createProperty("http://www.w3.org/2001/XMLSchema-instance#value"), sValue)
						.addProperty(m.createProperty("http://www.w3.org/2001/XMLSchema-instance#type"), sEncoding);
		r1.addProperty(m.createProperty(sProperty), r2);
		return m;
	}
}
