/*
 * ServerSettingsDialog.java - Configure settings for the Server GUI.
 * 
 * Copyright (C) 2012 Michael Gibson
 * 
 * This file is part of P2PTool.
 *
 * P2PTool is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package uk.ac.abdn.csd.p2p.server;

/**
 * This class is a swing dialog that provides interface to user for entering the listening port 
 * where server would listen for new incoming connections from nodes.
 * @see ServerGUI
 * @see Server
 * @author Ahmad Abdullah
 */
public class ServerSettingsDialog extends javax.swing.JDialog {
    
    private int listeningPort;
    String errorText = "";

    /**
     * Creates new form ServerSettingsDialog
     * @param parent Indicates {@code ServerGUI}
     * @param modal Indicates if {@code ServerSettingsDialog} is of modal form or not
     */
    public ServerSettingsDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        listeningPort = 0;
        initComponents();
        setComponentsNames();
        setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    /**
     * Creates new form ServerSettingsDialog
     */
    public ServerSettingsDialog() {
        initComponents();
        setComponentsNames();
    }

    /** 
     * This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        settingsPanel = new javax.swing.JPanel();
        portLabel = new javax.swing.JLabel();
        portField = new javax.swing.JFormattedTextField();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Server Settings Dialog");
        setResizable(false);

        settingsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));
        settingsPanel.setLayout(new javax.swing.BoxLayout(settingsPanel, javax.swing.BoxLayout.LINE_AXIS));

        portLabel.setText("Listening port number:");
        settingsPanel.add(portLabel);

        portField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portFieldActionPerformed(evt);
            }
        });
        settingsPanel.add(portField);

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(settingsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, okButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.TRAILING, cancelButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(settingsPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(okButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cancelButton))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Method invoked when Cancel button is pressed on dialog interface
     * @param evt Indicates associated event
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Method invoked when OK button is pressed on dialog interface
     * @param evt Indicates associated event
     */
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if(portField.getText().equals(""))
        {
            listeningPort = 0;
            this.setVisible(true);
        }else if(isNumber(portField.getText())==true)
        {
            listeningPort = Integer.parseInt(portField.getText());
            this.setVisible(false);
        }else if(isNumber(portField.getText())==false)
        {
            errorText = "NOT A NUMBER - "+portField.getText();
            this.setVisible(true);
        }
    }//GEN-LAST:event_okButtonActionPerformed

    /**
     * Method invoked upon pressing Enter button while focus is the portFiedl
     * @param evt Indicates associated event
     */
    private void portFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portFieldActionPerformed
        if(portField.getText().equals(""))
        {
            listeningPort = 0;
            this.setVisible(true);
        }else if(isNumber(portField.getText())==true)
        {
            listeningPort = Integer.parseInt(portField.getText());
            this.setVisible(false);
        }else if(isNumber(portField.getText())==false)
        {
            errorText = "NOT A NUMBER - "+portField.getText();
            this.setVisible(true);
        }
    }//GEN-LAST:event_portFieldActionPerformed

    /**
     * Get Listening port
     * @return Returns listening port set by a user
     */
    public int getListeningPort()
    {
        return listeningPort;
    }
    
    /**
     * Method to set names of all graphical components
     */
    private void setComponentsNames()
    {
        portField.setName("portField");
        okButton.setName("okButton");
    }
    
    /**
     * Method to check of entered value in field is a number
     * @param string Value entered by user
     * @return Returns True if it is a number else it returns false.
     */
    public static boolean isNumber(String string) {
        boolean check = false;
        try
        {
        check = string.matches("^\\d+$");
        }catch (Exception e)
        {
            System.out.println("ERROR");
        }
        return check;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton okButton;
    private javax.swing.JFormattedTextField portField;
    private javax.swing.JLabel portLabel;
    private javax.swing.JPanel settingsPanel;
    // End of variables declaration//GEN-END:variables
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerSettingsDialog().setVisible(true);
            }
        });
    }
}
