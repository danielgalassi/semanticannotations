package ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/***
 * 
 * @author daniel
 *
 */
public class OptionsFrame extends JFrame {

    private JButton jButton1;
    private JButton jButton2;

	private static final long serialVersionUID = 1L;

	/** Creates new form OptionsFrame */
    public OptionsFrame() {
        initComponents();
    }

    private void initComponents() {

        jButton1 = new JButton();
        jButton2 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        jButton1.setIcon(new ImageIcon("/media/disk/workspace/semanticannotations/rdf.jpg")); // NOI18N
        jButton1.setText("jButton1.text"); // NOI18N
        jButton1.setIconTextGap(10);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new ImageIcon("/media/disk/workspace/semanticannotations/rdf.jpg")); // NOI18N
        jButton2.setText("jButton2.text"); // NOI18N
        jButton2.setIconTextGap(10);
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                    .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap(117, Short.MAX_VALUE))
        );

        layout.linkSize(SwingConstants.VERTICAL, new Component[] {jButton1, jButton2});

        pack();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OptionsFrame().setVisible(true);
            }
        });
    }
}
