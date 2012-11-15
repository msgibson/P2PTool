/*
 * ServentGUI.java - GUI to control a Servent
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

import java.awt.Color;

import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import uk.ac.abdn.csd.p2p.PeerInfo;
import uk.ac.abdn.csd.p2p.Version;

/**
 * <code>P2PServentGUI</code> is a GUI designed to represent and control a
 * <code>Servent</code> in an easy manner. It allows users to specify the
 * address and port of a bootstrapping node
 * (in this project, the <code>Server</code>), a username to represent 
 * the servent, the servent's listening address, how many neighbours are
 * allowed and whether to send a copy of sent messages to the
 * <code>Server</code>. It also displays a list of connected neighbours
 * and available files for sharing (not actual files, but filenames) as well
 * as a console output area for more verbose messages.
 * 
 * This GUI has been designed with the <b>model/view<b> paradigm in mind.
 * The model is <code>Servent</code> and this GUI being the view.
 * To refresh the file and neighbour GUI lists, the interface
 * <code>ServentInterface</code> must be implemented so that
 * <code>Servent</code> can call the view's <code>update()</code> method to
 * refresh the files and neighbours lists. * 
 *
 * @author msgibson
 */
public class ServentGUI extends javax.swing.JFrame implements ServentInterface{

	private static final long serialVersionUID = 1L;
	private String serverAddress = "";
    private int serverPort;
    private String name = "";
    private int listeningPort;
    
    /**
     * Default number of neighbours per servent.
     */
    private int max;
    
    /**
     * Default number of lives (hops) per message.
     */
    private int TTL;
    
    
    /**
     * Send a copy of each sent message to the server?
     */
    private boolean viaServer = true;

    /**
     * The actual <code>Servent</code> model.
     */
    Servent servent = null;

    /**
     * Models to hold String lists
     */
    DefaultListModel neighboursModel, filesModel;    
    
    /**
     * Thread of Servent to run and stop
     */
    private Thread serventThread;

    /**
     * Constructor for the GUI.
     */
    public ServentGUI() {
        neighboursModel = new DefaultListModel();
        filesModel = new DefaultListModel();

        initComponents();

        this.setLocationRelativeTo(null);
        
        /**
         * Create a new <code>MessageConsole</code> to pipe the Java console
         * to a jTextArea.
         */
        MessageConsole mc = new MessageConsole(consoleText);
        mc.redirectOut();
        mc.redirectErr(Color.RED, null);
    }
    
    public ServentGUI(String serverAddress, int serverPort, String name, int listeningPort, int max, int TTL, boolean viaServer) {
        neighboursModel = new DefaultListModel();
        filesModel = new DefaultListModel();

        initComponents();

        this.setLocationRelativeTo(null);
        
        /**
         * Create a new <code>MessageConsole</code> to pipe the Java console
         * to a jTextArea.
         */
        MessageConsole mc = new MessageConsole(consoleText);
        mc.redirectOut();
        mc.redirectErr(Color.RED, null);
        
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.name = name;
        this.listeningPort = listeningPort;
        this.max = max;
        this.TTL = TTL;
        this.viaServer = viaServer;
    }

    /**
     * Implemented <code>update()</code> method from the interface
     * <code>ServentInterface</code>
     */
    public void update(ServentInterface.Type type){
        switch(type){
            case neighbours:
                Collection<PeerInfo> neighbours = servent.getNeighbourValues();                
                if(!neighbours.isEmpty()){
                	neighboursModel.clear();
                	for(PeerInfo neighbour: neighbours){                		
                        neighboursModel.addElement(neighbour.getId());                       
                    }
                }else{
                	neighboursModel.clear();
                }
                break;
            case files:
                HashSet<String> files = servent.getFiles();
                if(!files.isEmpty()){
                	filesModel.clear();
                	for(String file: files){
                        filesModel.addElement(file);
                    }                    
                }else{
                	filesModel.clear();
                }
                break;
        }
    }

    /** 
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
	 //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        findFilesPanel = new javax.swing.JPanel();
        findFileText = new javax.swing.JTextField();
        findFileButton = new javax.swing.JButton();
        consolePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        consoleText = new javax.swing.JTextArea();
        consoleClearButton = new javax.swing.JButton();
        filesPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        filesList = new javax.swing.JList();
        removeFileButton = new javax.swing.JButton();
        addFileButton = new javax.swing.JButton();
        addFileText = new javax.swing.JTextField();
        neighboursPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        neighboursList = new javax.swing.JList();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        connectMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        settingsMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("P2P Servent");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridBagLayout());

        findFilesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Find file"));
        findFilesPanel.setLayout(new javax.swing.BoxLayout(findFilesPanel, javax.swing.BoxLayout.LINE_AXIS));
        findFilesPanel.add(findFileText);

        findFileButton.setText("Find...");
        findFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findFileButtonActionPerformed(evt);
            }
        });
        findFilesPanel.add(findFileButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(findFilesPanel, gridBagConstraints);

        consolePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Console"));
        consolePanel.setLayout(new javax.swing.BoxLayout(consolePanel, javax.swing.BoxLayout.LINE_AXIS));

        consoleText.setColumns(30);
        consoleText.setEditable(false);
        consoleText.setFont(new java.awt.Font("Monospaced", 0, 12));
        consoleText.setRows(5);
        jScrollPane1.setViewportView(consoleText);

        consolePanel.add(jScrollPane1);

        consoleClearButton.setText("Clear");
        consoleClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consoleClearButtonActionPerformed(evt);
            }
        });
        consolePanel.add(consoleClearButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(consolePanel, gridBagConstraints);

        filesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Files"));
        filesPanel.setLayout(new java.awt.GridBagLayout());

        filesList.setModel(filesModel);
        filesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        filesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                filesListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(filesList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        filesPanel.add(jScrollPane3, gridBagConstraints);

        removeFileButton.setText("Remove file");
        removeFileButton.setEnabled(false);
        removeFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFileButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHEAST;
        gridBagConstraints.weightx = 1.0;
        filesPanel.add(removeFileButton, gridBagConstraints);

        addFileButton.setText("Add file");
        addFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFileButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        filesPanel.add(addFileButton, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        filesPanel.add(addFileText, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(filesPanel, gridBagConstraints);

        neighboursPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Neighbours"));
        neighboursPanel.setLayout(new java.awt.BorderLayout());

        neighboursList.setModel(neighboursModel);
        neighboursList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(neighboursList);

        neighboursPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(neighboursPanel, gridBagConstraints);

        fileMenu.setText("Servent");

        connectMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        connectMenuItem.setText("Connect...");
        connectMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(connectMenuItem);
        fileMenu.add(jSeparator3);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
        exitMenuItem.setText("Exit...");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        settingsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        settingsMenuItem.setText("Settings...");
        settingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(settingsMenuItem);

        menuBar.add(editMenu);

        helpMenu.setText("Help");

        aboutMenuItem.setText("About...");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void removeFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFileButtonActionPerformed
        int index = filesList.getSelectedIndex();
        String file = (String)filesModel.get(index);
        filesModel.remove(index);
        if(servent != null){
            servent.removeFile(file);
        }
    }//GEN-LAST:event_removeFileButtonActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        onExit();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        onExit();
    }//GEN-LAST:event_formWindowClosing

    private void settingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsMenuItemActionPerformed
        P2PServentSettingsDialog settings = new P2PServentSettingsDialog(this, true, serverAddress, serverPort, name, listeningPort, max, TTL, viaServer);
        serverAddress = settings.getServerAddress();
        serverPort = settings.getServerPortNumber();
        name = settings.getUsername();
        listeningPort = settings.getListeningPortNumber();
        max = settings.getMaxNeighbours();
        TTL = settings.getTTL();
        viaServer = settings.getSendToServer();
    }//GEN-LAST:event_settingsMenuItemActionPerformed

    /**
     * Start the <code>Servent</code> when the user clicks "Connect...".
     * 
     * @param evt Event passed on menu click.
     */
    @SuppressWarnings("unchecked")
	public void connectMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectMenuItemActionPerformed
        if(serverAddress.equals("") || name.equals("")){
            System.err.println("Incorrect settings. Please check Edit->Settings...");
        }else{
            try{
                setTitle("P2P Servent - " + name);
                servent = new Servent(name, listeningPort, TTL, max,serverAddress, serverPort, viaServer);
                servent.addServentView(this);
                Enumeration e = filesModel.elements();
                servent.setFiles(new HashSet<String>(Collections.list(e)));
                serventThread = new Thread(servent);
                serventThread.start();
                connectMenuItem.setEnabled(false); 
            }catch(Exception e){
                System.err.println("Could not connect to server. Please check settings...");
                e.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_connectMenuItemActionPerformed

    /**
     * Clear the console area when the "Clear" button is pressed.
     * 
     * @param evt Event passed on button press.
     */
    private void consoleClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consoleClearButtonActionPerformed
        consoleText.setText(null);
    }//GEN-LAST:event_consoleClearButtonActionPerformed

    /**
     * Add a file to the servent's available list.
     * 
     * @param evt Event passed on button press.
     */
    private void addFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFileButtonActionPerformed
        String file = "";
        file = addFileText.getText();
        if(!file.equals("")){
            filesModel.addElement(file);
            if(servent != null){
                servent.addFile(file);
            }
            addFileText.setText(null);
        }
    }//GEN-LAST:event_addFileButtonActionPerformed

    /**
     * Only activate the "Remove file" button when a file in the list has
     * been selected.
     * 
     * @param evt Event passed on mouse click.
     */
    private void filesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_filesListValueChanged
        int index = filesList.getSelectedIndex();
        if(index == -1){
            removeFileButton.setEnabled(false);
        }else{
            removeFileButton.setEnabled(true);
        }
    }//GEN-LAST:event_filesListValueChanged

    /**
     * Make the servent find the specified file.
     * 
     * @param evt Event passed on button press.
     */
    private void findFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findFileButtonActionPerformed
        String file = findFileText.getText();
        if(!file.equals("") && (servent != null)){
            servent.findFile(file);
        }
    }//GEN-LAST:event_findFileButtonActionPerformed

    /**
     * About box.
     * 
     * @param evt Event passed on menu click.
     */
    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        JOptionPane.showMessageDialog(this, Version.NAME + " Servent " + Version.BUILD + "\n" +
                "Created by " + Version.AUTHOR_1 + "\n" +
                Version.URL, "P2P Tool", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_aboutMenuItemActionPerformed

    /**
     * Override the default close operation to make sure the user really wants
     * to exit.
     */
    private void onExit() {
        int n = JOptionPane.showConfirmDialog(this, "Are you sure you wish to quit?", "P2P Servent", JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION){
        	if(servent != null){
        		servent.onExit();
        	}
            System.exit(0);
        }
    }

    /**
     * Main method to start the GUI.
     * 
     * @param args N/A
     */
    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        }

        if(args.length == 0){
        	java.awt.EventQueue.invokeLater(new Runnable() {
        		public void run() {
        			new ServentGUI().setVisible(true);
        		}
        	});
        }else{
        	final String serverAddress = args[0];
        	final int serverPort = Integer.parseInt(args[1]);
        	final String name = args[2];
        	final int listeningPort = Integer.parseInt(args[3]);
        	final int maxConns = Integer.parseInt(args[4]);
        	final int TTL = Integer.parseInt(args[5]);
        	final boolean viaServer = Boolean.parseBoolean(args[6]);
        	java.awt.EventQueue.invokeLater(new Runnable() {
        		public void run() {
        			ServentGUI gui = new ServentGUI(serverAddress, serverPort, name,
        					listeningPort, maxConns, TTL, viaServer);
        			gui.setVisible(true);
        			gui.connectMenuItemActionPerformed(null);
        		}
        	});
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton addFileButton;
    private javax.swing.JTextField addFileText;
    private javax.swing.JMenuItem connectMenuItem;
    private javax.swing.JButton consoleClearButton;
    private javax.swing.JPanel consolePanel;
    private javax.swing.JTextArea consoleText;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JList filesList;
    private javax.swing.JPanel filesPanel;
    private javax.swing.JButton findFileButton;
    private javax.swing.JTextField findFileText;
    private javax.swing.JPanel findFilesPanel;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JList neighboursList;
    private javax.swing.JPanel neighboursPanel;
    private javax.swing.JButton removeFileButton;
    private javax.swing.JMenuItem settingsMenuItem;
    // End of variables declaration//GEN-END:variables

}
