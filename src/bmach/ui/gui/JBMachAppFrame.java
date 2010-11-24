/*
 *  Copyright 2010 Georgios Migdos <cyberpython@gmail.com>.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

/*
 * JBMachAppFrame.java
 *
 * Created on Nov 23, 2010, 11:56:35 AM
 */

package bmach.ui.gui;

import bmach.ui.gui.actions.BMachAction;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.KeyStroke;

/**
 *
 * @author Georgios Migdos <cyberpython@gmail.com>
 */
public class JBMachAppFrame extends javax.swing.JFrame{

    private class ExitAction extends BMachAction {

        public ExitAction() {
            super("Exit", "Exit", null, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK), "/bmach/ui/gui/resources/door_open.png");
        }

        public void actionPerformed(ActionEvent e) {
            exit();
        }
    }

    private BMachPanel bMachPanel1;

    /** Creates new form JBMachAppFrame */
    public JBMachAppFrame(String pathname){
        initComponents();
        this.setIconImage(new ImageIcon(JBMachAppFrame.class.getResource("resources/icon.png")).getImage());
        bMachPanel1 = new BMachPanel();
        jPanel1.add(bMachPanel1);

        jMenu1.add(bMachPanel1.getNewAction());
        jMenu1.add(bMachPanel1.getOpenAction());
        jMenu1.add(bMachPanel1.getSaveAction());
        jMenu1.add(bMachPanel1.getSaveAsAction());
        jMenu1.addSeparator();
        jMenu1.add(new ExitAction());

        JMenu jMenu2  = bMachPanel1.getEditMenu();
        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2,1);

        jMenu3.add(bMachPanel1.getExecuteAction());
        jMenu3.add(bMachPanel1.getExecuteStepByStepAction());
        jMenu3.add(bMachPanel1.getStopAction());

        jMenu4.add(bMachPanel1.getShowHelpAction());
        jMenu4.addSeparator();
        jMenu4.add(bMachPanel1.getShowAboutDialogAction());
        this.pack();
        //this.setPreferredSize(new Dimension(850, 650));
        //this.setSize(this.getPreferredSize());
        this.setTitle("BMach - "+bMachPanel1.getCurrentDocTitle());

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowListener() {

            public void windowOpened(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
                exit();
            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowActivated(WindowEvent e) {
            }

            public void windowDeactivated(WindowEvent e) {
            }
        });

        bMachPanel1.addFileDropSupport();
        if(pathname!=null){
            File f = new File(pathname);
            if(f.exists()){
                bMachPanel1.openDocument(f);
            }
        }
    }

    public void exit(){
        //System.err.println("shouldClose");
        if(bMachPanel1.queryExit()){
            setVisible(false);
            dispose();
            System.exit(0);
        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu3.setText("Machine");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Help");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JBMachAppFrame(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
