package ui;

import javax.swing.JFrame;

import metadata.OntologyCatalog;

public class EntryForm extends JFrame {

	private static final long serialVersionUID = 7857601432524291863L;
	
	public OntologyCatalog ontCatalog;

	public EntryForm (String sOntology, OntologyCatalog oCatalog) {
		ontCatalog = oCatalog;
		initComponents ();
		System.out.println("New Entry Form for " + sOntology);
		initComponents ();
	}
	
	private void initComponents () {
		System.out.println(ontCatalog.getSize());
	}
}
