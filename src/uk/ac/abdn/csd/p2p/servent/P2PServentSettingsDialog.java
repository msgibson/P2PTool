/*
 * P2PServentSettingsDialog.java - Configure settings for the servent
 * GUI
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

package uk.ac.abdn.csd.p2p.servent;

import javax.swing.JDialog;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;

/**
 * <code>P2PServentSettingsDialog</code> is a dialog to allow the user to
 * specify their own settings for <code>Servent</code>. This is called by
 * <code>P2PServentGUI</code> when a user clicks on Edit->Settings...
 * 
 * @author Michael Gibson
 */
public class P2PServentSettingsDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;

    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox sendToServerCheckBox;
    private javax.swing.JFormattedTextField maxNeighboursText;
    private javax.swing.JTextField serverAddressText;
    private javax.swing.JFormattedTextField serverPortNumberText;
    private javax.swing.JFormattedTextField listeningPortNumberText;
    private javax.swing.JFormattedTextField TTLNumberText;
    private javax.swing.JButton settingsCancelButton;
    private javax.swing.JButton settingsOKButton;
    private javax.swing.JTextField usernameText;

    String address = "";
    int serverPort = 0;
    String username = "";
    int listeningPort = 0;
    int max = 0;
    int TTL = 0;
    boolean server = true;

    public P2PServentSettingsDialog(JFrame frame, boolean modal, String address, int serverPort, String username, int listeningPort, int max, int TTL, boolean server) {
        super(frame, modal);
        java.awt.GridBagConstraints gridBagConstraints;

        this.address = address;
        this.serverPort = serverPort;
        this.username = username;
        this.listeningPort = listeningPort;
        this.max = max;
        this.TTL = TTL;
        this.server = server;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        serverAddressText = new javax.swing.JTextField();
        serverPortNumberText = new javax.swing.JFormattedTextField();
        usernameText = new javax.swing.JTextField();
        maxNeighboursText = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        sendToServerCheckBox = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        listeningPortNumberText = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        TTLNumberText = new javax.swing.JFormattedTextField();
        settingsOKButton = new javax.swing.JButton();
        settingsCancelButton = new javax.swing.JButton();

        setTitle("P2P Servent Settings");
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Server address:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setText("Server port number:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Username:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel3, gridBagConstraints);

        jLabel4.setText("Listening port number:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setText("Maximum number of neighbours:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel5, gridBagConstraints);
        
        jLabel6.setText("Send messages to server:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel6, gridBagConstraints);
        
        jLabel7.setText("Life of messages (TTL):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel7, gridBagConstraints);

        TTLNumberText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        if(TTL > 0){
            TTLNumberText.setText("" + TTL);
        }
        jPanel1.add(TTLNumberText, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        serverAddressText.setText(address);
        jPanel1.add(serverAddressText, gridBagConstraints);

        serverPortNumberText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        if(serverPort > 0){
            serverPortNumberText.setText(""+serverPort);
        }
        jPanel1.add(serverPortNumberText, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        usernameText.setText(username);
        jPanel1.add(usernameText, gridBagConstraints);

        maxNeighboursText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        if(max > 0){
            maxNeighboursText.setText("" + max);
        }
        jPanel1.add(maxNeighboursText, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        sendToServerCheckBox.setSelected(server);
        jPanel1.add(sendToServerCheckBox, gridBagConstraints);

        listeningPortNumberText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        if(listeningPort > 0){
            listeningPortNumberText.setText("" + listeningPort);
        }
        jPanel1.add(listeningPortNumberText, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 238;
        gridBagConstraints.ipady = 58;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(jPanel1, gridBagConstraints);

        settingsOKButton.setText("OK");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        settingsOKButton.addActionListener(this);
        getContentPane().add(settingsOKButton, gridBagConstraints);

        settingsCancelButton.setText("Cancel");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        settingsCancelButton.addActionListener(this);
        getContentPane().add(settingsCancelButton, gridBagConstraints);

        getRootPane().setDefaultButton(settingsOKButton);

        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(settingsOKButton == e.getSource()) {
            address = serverAddressText.getText();
            if(serverPortNumberText.getText().equals("")){
            	serverPort = 0;
            }else{
            	serverPort = Integer.parseInt(serverPortNumberText.getText());
            }
            username = usernameText.getText();
            if(listeningPortNumberText.getText().equals("")){
            	listeningPort = 0;
            }else{
            	listeningPort = Integer.parseInt(listeningPortNumberText.getText());
            }
            if(maxNeighboursText.getText().equals("")){
            	max = 0;
            }else{
            	max = Integer.parseInt(maxNeighboursText.getText());
            }
            if(TTLNumberText.getText().equals("")){
            	TTL = 0;
            }else{
            	TTL = Integer.parseInt(TTLNumberText.getText());
            }
            server = sendToServerCheckBox.isSelected();
            setVisible(false);
        }
        else if(settingsCancelButton == e.getSource()) {
            setVisible(false);
        }
    }

    public String getServerAddress(){
        return address;
    }

    public int getServerPortNumber(){
        return serverPort;
    }

    public String getUsername(){
        return username;
    }

     public int getListeningPortNumber(){
        return listeningPort;
    }

    public int getMaxNeighbours(){
        return max;
    }
    
    public int getTTL(){
    	return TTL;
    }

    public boolean getSendToServer(){
        return server;
    }

}
