package ui;

import javax.swing.JFrame;

public class EntryForm extends JFrame {

	private static final long serialVersionUID = 7857601432524291863L;

	public EntryForm (String sOntology) {
		initComponents ();
		System.out.println("New Entry Form for " + sOntology);
	}
	
	private void initComponents () {
	}
}
