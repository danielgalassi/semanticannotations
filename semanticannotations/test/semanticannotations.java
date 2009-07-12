package test;

/*
http://jena.sourceforge.net/tutorial/RDF_API/index.html#ch-Statements
http://jena.sourceforge.net/javadoc/index.html
http://dublincore.org/documents/dcmi-terms/
http://dublincore.org/documents/dces/
http://dublincore.org/schemas/rdfs/
http://dublincore.org/documents/dc-rdf/

 */
import java.io.InputStream;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.arp.lang.Iso639;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class semanticannotations {

	private static void example1() {
		Model m = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open("example.rdf");
		m.read(in, null);
		
		ResIterator rs = m.listResourcesWithProperty(m.createProperty("http://purl.org/dc/terms/title"));
		Resource r = null;
		StmtIterator si = null;
		Statement s = null;
		while (rs.hasNext()) {
			r = rs.nextResource();
			System.out.println("Resource: " + r);
			si = r.listProperties();
			while (si.hasNext()) {
				s = si.next();
				System.out.println("Statement: " + s);
				System.out.println(s.getLanguage());
			}
		}
		
		Resource source = m.getResource("http://example.org/123");//("http://purl.org/dc/terms/title");
		NodeIterator ni = m.listObjectsOfProperty(source, m.createProperty("http://purl.org/dc/terms/title"));
		RDFNode n = null;
	    if (ni != null)
	    	while (ni.hasNext()) {
	    		n = ni.nextNode();
	    		if (n.asNode().getLiteralLanguage().equals("es"))
	    			//System.out.println(n.asNode().getLiteralValue());
	    			System.out.println("Titulo en español: " + n.asNode().getLiteralLexicalForm());
	    	}
	}

	private static void dcom2() {
		OntModel dcom = ModelFactory.createOntologyModel( OntModelSpec.RDFS_MEM);

		InputStream in = FileManager.get().open("dcterms.rdf");

		dcom.read(in, null);

		ResIterator ri = dcom.listResourcesWithProperty(dcom.createProperty("http://www.w3.org/2000/01/rdf-schema#comment"));
		Resource r = null;
		NodeIterator ni = null;
		RDFNode n = null;
		
		while (ri.hasNext()) {
			r = ri.nextResource();
			System.out.println(r);
			ni = dcom.listObjectsOfProperty(r, dcom.createProperty("http://www.w3.org/2000/01/rdf-schema#comment"));
		    	while (ni.hasNext()) {
		    		n = ni.nextNode();
		    		if (n.asNode().getLiteralLanguage().equals("en-US"))
		    			//System.out.println(n.asNode().getLiteralValue());
		    			System.out.println("RDFS Comment: " + n.asNode().getLiteralLexicalForm());
		    	}
		}
	}

	private static void dcom1() {
		OntModel dcom = ModelFactory.createOntologyModel( OntModelSpec.RDFS_MEM);

		InputStream in = FileManager.get().open("dcterms.rdf");

		dcom.read(in, null);

		ExtendedIterator <OntProperty> p = dcom.listOntProperties();
		Statement s1 = null;
		Statement or = null;
		OntProperty op = null;

		while (p.hasNext()) {
			op = p.next();
			System.out.println(op);

			System.out.println("\trdfs:comment = " + op.getComment("en"));

			s1 = op.getProperty(dcom.createProperty(dcom.getNsPrefixURI("skos"), "note"));
			try {
				//or = s1.getResource().getProperty(dcom.createProperty(dcom.getNsPrefixURI("skos"), "note"));
				or = s1.getProperty(dcom.createProperty(dcom.getNsPrefixURI("skos"), "note"));
			} catch (Exception e) {
				or = dcom.createStatement(dcom.createResource(), dcom.createProperty("hello"), "en");
			}
			if (s1 != null)
				System.out.println("\tskos:note = " + s1);
			System.out.println("\tskos:note2= " + or);

		}
		//dcom.write(System.out);
	}

	private static void dcm2() {
		Model dcm = ModelFactory.createDefaultModel();

		InputStream in = FileManager.get().open("dcelements.rdf");
		if (in == null) {
			System.out.println("File Not Found");
			return;
		}
		//read the RDF/XML file
		dcm.read(in, null);

		System.out.println("\n\n");
		StmtIterator iter = dcm.listStatements();
		while (iter.hasNext())
			System.out.println(iter.nextStatement());

		System.out.println("\n\n");
		ResIterator ri = dcm.listSubjects();
		while (ri.hasNext())
			System.out.println("Resource: " + ri.nextResource());

		System.out.println("\n\nskos:notes");
		iter = dcm.listStatements();
		Statement st = null;
		int i = 0;
		while (iter.hasNext()) {
			st = iter.nextStatement();
			System.out.println(i++ + "	" + st.toString());
			if (st.getPredicate().equals(dcm.createProperty(dcm.getNsPrefixURI("skos"), "note")))
				System.out.println("skos:note = " + st.getObject());
			if (st.getPredicate().equals(dcm.createProperty(dcm.getNsPrefixURI("dcterms"), "description")))
				System.out.println("dcterms:description = " + st.getObject());
		}

		System.out.println("\n\n");
		//dcm.write(System.out);
	}

	/*
	<rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
			xmlns:dcterms="http://purl.org/dc/terms/">

		<rdf:Description rdf:about="http://example.org/123">
			<dcterms:title xml:lang="en">Learning Biology</dcterms:title>
			</rdf:Description>
	</rdf:RDF>
	 */
	private static void dcm1() {
		Model dcm = ModelFactory.createDefaultModel();
		dcm.setNsPrefix("dcterms", "http://purl.org/dc/terms/");

		Resource r = dcm.createResource("http://example.org/123");

		r.addProperty(dcm.createProperty("http://purl.org/dc/terms/title"),
				"Learning Biology",
				Iso639.find("eng").twoCharCode);

		r.addProperty(dcm.createProperty("http://purl.org/dc/terms/title"),
				"Aprendiendo Biología",
				Iso639.find("spa").twoCharCode);

		dcm.write(System.out);
	}

	private static void model1() {
		Model m = ModelFactory.createDefaultModel();
		m.setNsPrefix("test", "http://my.example.com/");

		Property p = m.createProperty(m.getNsPrefixURI("test"), "myToken");

		System.out.println(p);

		p = null;
		m = null;
	}

	private static void annotea1() {
		Model ant = ModelFactory.createDefaultModel();

		Resource r = ant.createResource("http://amazon.com/RDF Primer");

		r.addProperty(ant.createProperty("http://www.w3.org/2000/10/annotation-ns#context"),
				"Chapter 1",
				Iso639.find("eng").twoCharCode);

		r.addProperty(ant.createProperty("http://www.w3.org/2000/10/annotation-ns#body"),
				"wonderful idea!",
				Iso639.find("eng").twoCharCode);

		r.addProperty(ant.createProperty("http://www.w3.org/2000/10/annotation-ns#body"),
				"BUENA idea!",
				Iso639.find("spa").twoCharCode);

		ant.write(System.out);
	}

	public static void main(String[] args) {
		/*
		System.out.println("Dublin Core example:");
		dcm1();
		System.out.println("\nAnnnotea example:");
		annotea1();
		System.out.println("\nDublin Core element set:");
		dcm2();
		System.out.println("\nModel X:");
		model1();
		System.out.println("\nDublin Core Ontology example:");
		dcom1();
		*/
		dcom2();

		//System.out.println("Example RDF:");
		//example1();
	}
}
