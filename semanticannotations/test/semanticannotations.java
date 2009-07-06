package test;

/*
http://jena.sourceforge.net/tutorial/RDF_API/index.html#ch-Statements
http://jena.sourceforge.net/javadoc/index.html
http://dublincore.org/documents/dcmi-terms/
http://dublincore.org/documents/dces/
http://dublincore.org/schemas/rdfs/
http://dublincore.org/documents/dc-rdf/

*/
import com.hp.hpl.jena.rdf.arp.lang.Iso639;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class semanticannotations {
	
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

	private static void annotea1() {
		Model ant = ModelFactory.createDefaultModel();
		//ant.setNsPrefix("annotea", "http://www.w3.org/2000/10/annotation-ns");
		
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
		System.out.println("Dublin Core example:");
		dcm1();
		System.out.println("\n\nAnnnotea example:");
		annotea1();
	}
}
