package ui;

import javax.swing.JFrame;

import metadata.MyOntology;

public class EntryForm extends JFrame {

	private static final long serialVersionUID = 7857601432524291863L;
	
	public MyOntology ontPicked;

	public EntryForm (MyOntology ontology) {
		ontPicked = ontology;
		initComponents ();
		System.out.println("New Entry Form for " + ontPicked.getsOntName());
		initComponents ();
	}
	
	private void initComponents () {
		System.out.println(ontPicked.getsOntName());
	}
}
