package utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XMLUtils {

	/**
	 * Reify a DOM document from a file
	 * @param filename
	 * @return DOM document
	 */
	public static Document File2Document(File filename) {
		DocumentBuilderFactory dBFXML = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBXML = null;
		Document docXML = null;

		try {
			docBXML = dBFXML.newDocumentBuilder();
			docXML = docBXML.parse(filename.toURI().toString());
		} catch(Exception e) {
			e.printStackTrace();
		}

		docXML.setXmlStandalone(true);
		return docXML;
	}
}
