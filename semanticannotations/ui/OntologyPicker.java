package ui;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import semanticAnnotations.SemanticAnnotations;

/***
 * 
 * @author daniel
 *
 */
public class OntologyPicker extends JFrame {

	private HashMap <String, JButton> hmJBOntologs;

	private static final long serialVersionUID = 1L;

	public OntologyPicker() {
		initComponents();
	}

	private void initComponents() {
		Iterator <String> itOntNames = SemanticAnnotations
		.getOntologyCatalog()
		.getOntNames();
		JButton[] aJButtons = new JButton[SemanticAnnotations
		                                  .getOntologyCatalog()
		                                  .getSize()];
		JButton jbOntology = null;
		Iterator <Entry<String, JButton>> itJBOntologs = null;
		ParallelGroup pgOntologs = null;
		SequentialGroup sgOntologs = null;
		Map.Entry<String, JButton> meJBOntologs = null;
		String sOntology;
		int i = 0;
		
		hmJBOntologs = new HashMap <String, JButton> ();
		
		while (itOntNames.hasNext()) {
			sOntology = itOntNames.next();
			System.out.println("Ontology available: " + sOntology);
			jbOntology = new JButton();
			jbOntology.setMnemonic(sOntology.charAt(0));
			jbOntology.setIcon(new ImageIcon("tentacle.jpg"));
			jbOntology.setText(sOntology);
			jbOntology.setIconTextGap(15);
			jbOntology.setName(sOntology);
			jbOntology.setBorder(new SoftBevelBorder ( BevelBorder.RAISED ));
			jbOntology.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jOntActionPerformed(evt);
				}
			});
			hmJBOntologs.put(sOntology, jbOntology);
		}
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setName("OntologyPickerFrame");
		setTitle("Ontology Picker");
		setIconImage(Toolkit.getDefaultToolkit().getImage("rdf.jpg"));
		
		//prepare layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		//prepare alignment groups
		pgOntologs = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		sgOntologs = layout.createSequentialGroup();
		itJBOntologs = hmJBOntologs.entrySet().iterator();
		while (itJBOntologs.hasNext()) {
			meJBOntologs = (Map.Entry<String,JButton>)itJBOntologs.next();
			pgOntologs.addComponent((JButton) meJBOntologs.getValue(), GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE);
			sgOntologs.addGap(15).addComponent((JButton) meJBOntologs.getValue(), GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addContainerGap(20, Short.MAX_VALUE);
			aJButtons[i++] = meJBOntologs.getValue();
		};
		//attach parallel group to horizontal layout
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(35).addGroup(pgOntologs)
						.addContainerGap(35, Short.MAX_VALUE)));
		//attach sequential group to vertical layout
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(sgOntologs));
		//end of alignment groups
		
		layout.linkSize(SwingConstants.VERTICAL, aJButtons);
		
		pack();
	}

	private void jOntActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println ("Ontology Picked!!");
	}

	/**
	 * @param args the command line arguments
	 *//*
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OptionsFrame().setVisible(true);
            }
        });
    }*/
}
