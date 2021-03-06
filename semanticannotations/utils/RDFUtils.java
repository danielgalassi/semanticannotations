/**
 * code related to help / utility routines
 */
package utils;

import java.io.InputStream;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;

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
	 * Generates a default new Model
	 * @return model
	 */
	public static Model getNewModel () {
		return ModelFactory.createDefaultModel();
	}
	
	/**
	 * Loads a resource from a file
	 * @param sFile resource file
	 * @return model
	 */
	public static Model loadModelFromFile (String sFile) {
		System.out.print("\t" + sFile);
		Model m = getNewModel();
		InputStream in = FileManager.get().open(sFile);
		m.read(in, null);
		return m;
	}

	/**
	 * Loads an Ontology from a file
	 * @param sFile ontology file
	 * @return ontology model
	 */
	public static OntModel loadOntModelFromFile (String sFile) {
		System.out.print ("\t" + sFile);
		OntModel om = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		InputStream in = FileManager.get().open(sFile);
		om.read(in, null);
		return om;	
	}
}
