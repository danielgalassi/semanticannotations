package ui;

import java.awt.Component;
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

import semanticAnnotations.SemanticAnnotations;

/***
 * 
 * @author daniel
 *
 */
public class OptionsFrame extends JFrame {

	private HashMap <String, JButton> hmJBOntologies;

	private static final long serialVersionUID = 1L;

	public OptionsFrame() {
		initComponents();
	}

	private void initComponents() {

		Iterator <String> itOntNames = SemanticAnnotations
										.getOntologyCatalog()
										.getOntNames();
		Iterator <Entry<String, JButton>> itJBOntologies = null;
		ParallelGroup pgOntologies = null;
		SequentialGroup sgOntologies = null;
		Map.Entry<String, JButton> meJBOntologies = null;
		JButton jButton = null;
		String sOntology;

		hmJBOntologies = new HashMap <String, JButton> ();

		while (itOntNames.hasNext()) {
			sOntology = itOntNames.next();
			System.out.println("Ontology available: " + sOntology);
			jButton = new JButton();
			jButton.setIcon(new ImageIcon("rdf.jpg"));
			jButton.setText(sOntology);
			jButton.setIconTextGap(10);
			jButton.setName(sOntology);
			jButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					jOntActionPerformed(evt);
				}
			});
			hmJBOntologies.put(sOntology, jButton);
		}

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setName("Ontology picker...");

		//prepare layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		pgOntologies = layout.createParallelGroup(GroupLayout
				.Alignment
				.LEADING);

		itJBOntologies = hmJBOntologies.entrySet().iterator();
		//prepare parallel group
		while (itJBOntologies.hasNext()) {
			meJBOntologies = (Map.Entry<String,JButton>)itJBOntologies.next();
			pgOntologies.addComponent((JButton) meJBOntologies.getValue(),
												GroupLayout.DEFAULT_SIZE, 212,
												Short.MAX_VALUE);
		};
		//attach parallel group to horizontal layout
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(35).addGroup(pgOntologies)
						.addContainerGap(41, Short.MAX_VALUE)));
		
		sgOntologies = layout.createSequentialGroup();
		itJBOntologies = hmJBOntologies.entrySet().iterator();
		//prepare sequential group
		while (itJBOntologies.hasNext()) {
			meJBOntologies = (Map.Entry<String,JButton>)itJBOntologies.next();
			sgOntologies.addGap(20)
						.addComponent((JButton) meJBOntologies.getValue(),
								GroupLayout.PREFERRED_SIZE, 65, 
								GroupLayout.PREFERRED_SIZE)
								.addContainerGap(10 * hmJBOntologies.size()
										, Short.MAX_VALUE);
		}
		//attach sequential group to vertical layout
		layout.setVerticalGroup(layout.createParallelGroup(
					GroupLayout.Alignment.LEADING).addGroup(sgOntologies));

		layout.linkSize(SwingConstants.VERTICAL, new Component[] {});
		//layout.linkSize(SwingConstants.VERTICAL, new Component[] {jButton1, jButton2});
		setVisible(true);
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
