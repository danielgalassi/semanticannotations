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

import utils.RDFUtils;

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
import com.hp.hpl.jena.util.iterator.Filter;

public class semanticannotations {

	public static void getCommentsForContributor() {
		/*
		<rdf:Description rdf:about="http://purl.org/dc/terms/contributor">
		<rdfs:label xml:lang="en-US">Contributor</rdfs:label>
		<rdfs:comment xml:lang="en-US">An entity responsible for making contributions to the resource.</rdfs:comment>
		<dcterms:description xml:lang="en-US">Examples of a Contributor include a person, an organization, or a service. Typically, the name of a Contributor should be used to indicate the entity.</dcterms:description>
		<rdfs:isDefinedBy rdf:resource="http://purl.org/dc/terms/"/>
		<dcterms:issued>2008-01-14</dcterms:issued>
		<dcterms:modified>2008-01-14</dcterms:modified>
		<rdf:type rdf:resource="http://www.w3.org/1999/02/22-rdf-syntax-ns#Property"/>
		<dcterms:hasVersion rdf:resource="http://dublincore.org/usage/terms/history/#contributorT-001"/>
		<rdfs:range rdf:resource="http://purl.org/dc/terms/Agent"/>
		<rdfs:subPropertyOf rdf:resource="http://purl.org/dc/elements/1.1/contributor"/>
		</rdf:Description>
		*/
		final String ns = "http://purl.org/dc/terms/";
		final String co = "contributor";
		String propNs = "http://www.w3.org/2000/01/rdf-schema#";
		String propNm = "comment";
		
		//opening an ontology model
		final OntModel m = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		InputStream in = FileManager.get().open("dcterms.rdf");
		m.read(in, null);
		Property p = m.createProperty(propNs+propNm);
		
		ExtendedIterator ri = m.listResourcesWithProperty(p)
							.filterKeep(new Filter<Resource>() {
								@Override
								public boolean accept(Resource o)
								{return ((Resource)o).toString().equals("http://purl.org/dc/terms/contributor");
										/*m.createProperty("http://www.w3.org/2000/01/rdf-schema#about"),
										ns+co
										);*/}
						});
		Resource r = null;
		if (ri.hasNext())
			while (ri.hasNext()) {
				r = (Resource)ri.next();
				//r = ri.next();
				System.out.println(r.getProperty(p));
				System.out.println(r.listProperties(p).next().getLiteral().getLexicalForm());
			}
		else
			System.out.println("No Resources Available");
		
		propNs = null;
		propNm = null;
	}

	public static void getTooltips(String filename, final String ns, final String property) {
		final OntModel m = ModelFactory.createOntologyModel( OntModelSpec.RDFS_MEM);
		InputStream in = FileManager.get().open(filename);
		m.read(in, null);
		
		ExtendedIterator ri = m.listResourcesWithProperty(m.createProperty("http://www.w3.org/2000/01/rdf-schema#comment"))
			.filterKeep(new Filter <Resource> () {
							@Override
							public boolean accept(Resource o) {
								return ((Resource)o).hasProperty(m.createProperty(ns + property), "");
							}});
		/*
		new Filter() {
			public boolean accept(Object o)
				{return ((Resource)o).hasProperty(RDF.type, "swrl#Imp");}
		}
		*/
		
		System.out.println(ri.hasNext());
		/*
		Resource r = null;
		NodeIterator ni = null;
		RDFNode n = null;

		Filter <Resource> f = null;
		
		while (ri.hasNext()) {
			r = ri.nextResource();
			System.out.println(r);
			ni = m.listObjectsOfProperty(r, m.createProperty(ns + property));
		    	while (ni.hasNext()) {
		    		n = ni.nextNode();
		    		if (n.asNode().getLiteralLanguage().equals("en-US"))
		    			//System.out.println(n.asNode().getLiteralValue());
		    			System.out.println("RDFS Comment: " + n.asNode().getLiteralLexicalForm());
		    	}
		}*/
		
	}

	public static Model loadModelFromFile (String sFile) {
		Model m = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(sFile);
		m.read(in, null);
		return m;
	}

	public static void exampleA() {
		Model m = loadModelFromFile("dcelements.rdf");

		ResIterator rs = m.listSubjects();
		StmtIterator si = null, si2 = null;
		Statement s = null;
		Resource r = null;
		while (rs.hasNext()) {
			r = rs.next();
			System.out.println("\n" + r);
			//si = r.listProperties();
			si2 = r.listProperties(m.createProperty("http://purl.org/dc/terms/description"));
			while (si2.hasNext())
				System.out.println("   Description\t" + si2.next().getObject().asNode().getLiteralValue());
			si2 = r.listProperties(m.createProperty("http://www.w3.org/2000/01/rdf-schema#label"));
			while (si2.hasNext())
				System.out.println("   Label\t" + si2.next().getObject().asNode().getLiteralValue());

			/*while (si.hasNext()) {
				s = si.next();
				System.out.println("-->\t" +
						s.getPredicate() + "\t" +
						s.getObject());
			}*/
		}
	}
	
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
	    
	    m.write(System.out);
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

	public static void dcOthers() {
		final Model mTerms = loadModelFromFile("dcterms.rdf");
		StmtIterator siTerms = mTerms.listStatements();
		ExtendedIterator <Statement> siT2 = siTerms.filterKeep(new Filter<Statement>() {
			@Override
			public boolean accept(Statement st)
			{return (st.getPredicate().equals(mTerms.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type")) &&
					!(st.getObject().toString().equals("http://www.w3.org/2000/01/rdf-schema#Class") ||
						st.getObject().toString().equals("http://www.w3.org/1999/02/22-rdf-syntax-ns#Property")));}
			});
		while (siT2.hasNext())
			System.out.println(siT2.next().getSubject());
		System.out.println();
	}

	public static void dcClasses() {
		final Model mTerms = loadModelFromFile("dcterms.rdf");
		StmtIterator siTerms = mTerms.listStatements();
		ExtendedIterator <Statement> siT2 = siTerms.filterKeep(new Filter<Statement>() {
			@Override
			public boolean accept(Statement st)
			{return (st.getPredicate().equals(mTerms.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type")) &&
					st.getObject().toString().equals("http://www.w3.org/2000/01/rdf-schema#Class"));}
			});
		while (siT2.hasNext())
			System.out.println(siT2.next().getSubject());
		System.out.println();
	}

	public static void dcSubPropertyOf(final String s) {
		final Model mTerms = loadModelFromFile("dcterms.rdf");
		StmtIterator siTerms = mTerms.listStatements();
		ExtendedIterator <Statement> siT2 = siTerms.filterKeep(new Filter<Statement>() {
			@Override
			public boolean accept(Statement st)
			{return (st.getPredicate().equals(mTerms.createProperty("http://www.w3.org/2000/01/rdf-schema#subPropertyOf"))
					&& st.getObject().toString().equals(s)
					);
			}
			});
		while (siT2.hasNext())
			System.out.println("\t" + siT2.next().getSubject());
		System.out.println();
	}
	
	public static void dcSKOS(final String sSubject) {
		final Model dc = loadModelFromFile("dcelements.rdf");
		StmtIterator si = dc.listStatements();
		ExtendedIterator <Statement> si2 = si.filterKeep(new Filter<Statement>() {
			@Override
			public boolean accept(Statement st)
			{return (st.getPredicate().equals(dc.createProperty("http://www.w3.org/2004/02/skos/core#note")) &&
					st.getSubject().toString().equals(sSubject));
			}
			});
		Statement s = null;
		while (si2.hasNext()) {
			s = si2.next();
			System.out.println(s.getSubject() + "\n\t" + 
					s.getObject().asNode().getLiteralValue() + "\n\t" + 
					s.getObject().asNode().getLiteralLanguage());
		}
	}
	
	private static void dcBigPic() {
		Model dcE = loadModelFromFile("dcelements.rdf");
		Model dcT = loadModelFromFile("dcterms.rdf");
		StmtIterator dcTerms = dcT.listStatements();
		StmtIterator dcElem = dcE.listStatements();
		Statement sTerm = null;

		//ResIterator dcRes = dcT.listResourcesWithProperty(dcT.createProperty("http://www.w3.org/2000/01/rdf-schema#subPropertyOf"));
		ResIterator dcRes = dcE.listSubjects();
		Resource dcR = null;

		/*
		while (dcElem.hasNext())
			System.out.println(dcElem.next());
		while (dcTerms.hasNext()) {
			sTerm = dcTerms.next();
			System.out.println(sTerm);
		}
		*/

		while (dcRes.hasNext()) {
			dcR = dcRes.next();
			System.out.println(dcR);
			dcSubPropertyOf(dcR.toString());
		}

		System.out.println("DC Classes");
		dcClasses();

		System.out.println("DC Others");
		dcOthers();

		/*Statement s = null;
		while (dcTerms.hasNext()) {
			s = dcTerms.next();
			System.out.println(s.getSubject() + "\t" + 
								s.getPredicate() + "\t" +
								s.getObject());
		}*/
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

		Property p = ant.createProperty("http://www.w3.org/2000/10/annotation-ns#body");

		r.addProperty(ant.createProperty("http://www.w3.org/2000/10/annotation-ns#body"),
				"BUENA idea!",
				Iso639.find("spa").twoCharCode);

		r.addLiteral(ant.createProperty("http://www.w3.org/2000/10/annotation-ns#body"), 123.0);

		ant.write(System.out);
	}

	public static void main(String[] args) {
		System.out.println("\nAnnnotea example:");
		annotea1();
		/*
		System.out.println("Dublin Core example:");
		dcm1();
		
		System.out.println("\nDublin Core element set:");
		dcm2();
		
		System.out.println("\nModel X:");
		model1();
		
		System.out.println("\nDublin Core Ontology example:");
		dcom1();
		dcom2();
		System.out.println("Example RDF:");
		example1();
		*/
		Iso639 a = null;
		System.out.println("Found?\t" + a.find("es").name);
		getTooltips("dcterms.rdf", "http://purl.org/dc/terms/", "type");
		//getTooltips("dcterms.rdf", "http://www.w3.org/2000/01/rdf-schema#", "comment");
		System.out.println("\nSubjects:");
		exampleA();
		System.out.println("\n");
		getCommentsForContributor();
		//dcBigPic();
		
		System.out.println("dc skos");
		dcSKOS("http://purl.org/dc/elements/1.1/identifier");

		Model model = loadModelFromFile("example.rdf");
		//m.write(System.out);
		//MyResource mr = new MyResource();
		String dcterms = "http://purl.org/dc/terms/";
		String dctermsdate = dcterms + "date";
		String xsi = "http://www.w3.org/2001/XMLSchema-instance#";
		String xsitype = xsi + "type";
		/*Model model = ModelFactory.createDefaultModel();
		model.setNsPrefix("dcterms", dcterms);
		model.setNsPrefix("xsi", xsi);*/
		Model m2 = RDFUtils.addEncodedResource("http://example.org/123",
												"http://purl.org/dc/terms/date",
												"dcterms:W3CDTF",
												"2222-1-1");
		model.add(m2);
		model.write(System.out);
	}
}
