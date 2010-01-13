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
public class UI extends JFrame {

	private static final long serialVersionUID = -6440471635274136209L;
	
	private HashMap <String, JButton> hmJBOnts;
	
	public UI() {
		initComponents();
		setLocation(200, 300);
	}

	public JButton addJButton (String sTitle) {
		JButton jbOntology = new JButton();
		jbOntology.setMnemonic(sTitle.charAt(0));
		jbOntology.setIcon(new ImageIcon("tentacle.jpg"));
		jbOntology.setText(sTitle);
		jbOntology.setName(sTitle);
		jbOntology.setIconTextGap(15);
		jbOntology.setBorder(new SoftBevelBorder ( BevelBorder.RAISED ));
		jbOntology.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jOntActionPerformed(evt);
			}
		});
		hmJBOnts.put(sTitle, jbOntology);
		return jbOntology;
	}
	
	private void initComponents () {
		Iterator <String> itOntNames = SemanticAnnotations
		.getOntologyCatalog()
		.getOntNames();
		JButton[] aJButtons = new JButton[SemanticAnnotations
		                                  .getOntologyCatalog()
		                                  .getSize()+1];
		Iterator <Entry<String, JButton>> itJBOnts = null;
		ParallelGroup pgOnts = null;
		SequentialGroup sgOnts = null;
		Map.Entry<String, JButton> meJBOnts = null;
		int i = 0;
		
		hmJBOnts = new HashMap <String, JButton> ();
		
		while (itOntNames.hasNext()) {
			addJButton(itOntNames.next());
		}
		addJButton("Remember");
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setName("OntologyPickerFrame");
		setTitle("Ontology Picker");
		setIconImage(Toolkit.getDefaultToolkit().getImage("rdf.jpg"));
		
		//prepare layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		//prepare alignment groups
		pgOnts = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		sgOnts = layout.createSequentialGroup();
		itJBOnts = hmJBOnts.entrySet().iterator();
		while (itJBOnts.hasNext()) {
			meJBOnts = (Map.Entry<String,JButton>)itJBOnts.next();
			pgOnts.addComponent((JButton) meJBOnts.getValue(), GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE);
			sgOnts.addGap(15).addComponent((JButton) meJBOnts.getValue(), GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE).addContainerGap(20, Short.MAX_VALUE);
			aJButtons[i++] = meJBOnts.getValue();
		};
		//attach parallel group to horizontal layout
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(35).addGroup(pgOnts)
						.addContainerGap(35, Short.MAX_VALUE)));
		//attach sequential group to vertical layout
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(sgOnts));
		//end of alignment groups
		
		layout.linkSize(SwingConstants.VERTICAL, aJButtons);
		
		pack();
	}

	private void jOntActionPerformed(java.awt.event.ActionEvent evt) {
		System.out.println ("Ontology Picked!!" + 
							((JButton) evt.getSource()).getName());
	}

	/*
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OptionsFrame().setVisible(true);
            }
        });
    }*/
}
