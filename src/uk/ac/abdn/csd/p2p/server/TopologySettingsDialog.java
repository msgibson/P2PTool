/*
 * TopologySettingsDialog.java - Create a topology for Servents to join
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

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Point;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import org.apache.commons.collections15.TransformerUtils;
import org.apache.commons.collections15.Transformer;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * <code>P2PServentSettingsDialog</code> is a dialog to allow the user to
 * specify their own settings for <code>Servent</code>. This is called by
 * <code>P2PServentGUI</code> when a user clicks on Edit->Settings...
 * 
 * @author Michael Gibson
 */
public class TopologySettingsDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;

    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField peersText;
    private javax.swing.JTextField topologyText;
    private javax.swing.JButton settingsOKButton, settingsCancelButton;
    private javax.swing.JButton loadPeersButton, loadTopologyButton;
    private javax.swing.JRadioButton peerNameRadioButton, peerHostRadioButton;
    private javax.swing.ButtonGroup buttonGroup;
    
    private JFileChooser fc;
    private File peersFile, topologyFile;
    
    private JFrame parent;

    private String peers = "";    
    private String topology = ""; 
    
    private Graph<Vertex, Edge> graph;
    private Transformer transformer;
    private Map<Vertex, Point2D> map;
    private Map<String, Vertex> vertices;
    private Multimap<String, String> edges;

    public TopologySettingsDialog(JFrame frame, boolean modal, String peers, String topology) {
        super(frame, modal);
        java.awt.GridBagConstraints gridBagConstraints;

        this.peers = peers;
        this.topology = topology;    
        
        parent = frame;
        
        graph = new UndirectedSparseGraph<Vertex, Edge>();
        map = new HashMap<Vertex, Point2D>();
        vertices = new HashMap<String, Vertex>();
        edges = HashMultimap.create();

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();   
        jLabel3 = new javax.swing.JLabel();
        peersText = new javax.swing.JTextField();        
        topologyText = new javax.swing.JTextField(); 
        peerNameRadioButton = new javax.swing.JRadioButton();
        peerHostRadioButton = new javax.swing.JRadioButton();
        settingsOKButton = new javax.swing.JButton();
        settingsCancelButton = new javax.swing.JButton();
        loadPeersButton = new javax.swing.JButton();
        loadTopologyButton = new javax.swing.JButton();
        buttonGroup = new javax.swing.ButtonGroup();
        buttonGroup.add(peerNameRadioButton);
        buttonGroup.add(peerHostRadioButton);
        
        fc = new JFileChooser();

        setTitle("Topology Settings");
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        /*
        jLabel1.setText("Peers:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel1, gridBagConstraints);
        */
        
        jLabel2.setText("Topology file:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setText("Peer type:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jLabel3, gridBagConstraints);        

        /*
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        peersText.setText(peers);
        jPanel1.add(peersText, gridBagConstraints);
        */

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        peersText.setText(peers);
        jPanel1.add(topologyText, gridBagConstraints);
        
        /*
        loadPeersButton.setText("Load");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        //gridBagConstraints.ipadx = 18;
        //gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        loadPeersButton.addActionListener(this);
        jPanel1.add(loadPeersButton, gridBagConstraints);
        */
        
        loadTopologyButton.setText("Load");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        //gridBagConstraints.ipadx = 18;
        //gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        //gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        loadTopologyButton.addActionListener(this);
        jPanel1.add(loadTopologyButton, gridBagConstraints);
        
        JPanel radioButtonsPanel = new JPanel();
        
        peerNameRadioButton.setText("Name");
        peerNameRadioButton.setActionCommand("name");        
        radioButtonsPanel.add(peerNameRadioButton);        
              
        peerHostRadioButton.setText("Host");
        peerHostRadioButton.setActionCommand("host");
        radioButtonsPanel.add(peerHostRadioButton);       
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;        
        jPanel1.add(radioButtonsPanel, gridBagConstraints);

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
            peers = peersText.getText();
            topology = topologyText.getText();
            if(topology.equals("")){
            	JOptionPane.showMessageDialog(null, "Please load a peer and topology file", "Topology Settings", JOptionPane.ERROR_MESSAGE);
            	return;
            }
            if(createGraph()){
            	System.out.println(graph);
            	System.out.println(map);
            	System.out.println("-----");
            	System.out.println(vertices);
            	System.out.println(edges);
            	setVisible(false);
            }
        }
        else if(settingsCancelButton == e.getSource()) {
            setVisible(false);
        }
        /*
        else if(loadPeersButton == e.getSource()){
        	int returnVal = fc.showOpenDialog(parent);
        	if(returnVal == fc.APPROVE_OPTION){
        		peersFile = fc.getSelectedFile();
        		peersText.setText(peersFile.getAbsolutePath());
        	}
        }
        */
        else if(loadTopologyButton == e.getSource()){
        	int returnVal = fc.showOpenDialog(parent);
        	if(returnVal == fc.APPROVE_OPTION){
        		topologyFile = fc.getSelectedFile();
        		topologyText.setText(topologyFile.getAbsolutePath());
        	}
        }
    }
    
    private boolean createGraph(){
    	String line;
    	int e = 0;	// Unique id for each edge
    	
    	/*
    	if(peersFile == null){
    		peersFile = new File(peersText.getText());
    		if(peersFile == null){
    			JOptionPane.showMessageDialog(null, "Please load a peer file", "Topology Settings", JOptionPane.ERROR_MESSAGE);
            	return false;
    		}
    	}
    	*/
    	if(topologyFile == null){
    		topologyFile = new File(topologyText.getText());
    		if(topologyFile == null){
    			JOptionPane.showMessageDialog(null, "Please load a topology file", "Topology Settings", JOptionPane.ERROR_MESSAGE);
            	return false;
    		}
    	}
    	
    	if(!peerNameRadioButton.isSelected() && !peerHostRadioButton.isSelected()){
    		JOptionPane.showMessageDialog(null, "Please select the type of peer", "Topology Settings", JOptionPane.ERROR_MESSAGE);
        	return false;
    	}
    	
    	FileInputStream fisPeers, fisTopology;
    	DataInputStream disPeers, disTopology;
    	BufferedReader brPeers, brTopology;
    	try{
    		//fisPeers = new FileInputStream(peersFile);
    		//disPeers = new DataInputStream(fisPeers);
    		//brPeers = new BufferedReader(new InputStreamReader(disPeers));
    		fisTopology = new FileInputStream(topologyFile);    		
    		disTopology = new DataInputStream(fisTopology);    		
    		brTopology = new BufferedReader(new InputStreamReader(disTopology));
    	}catch(FileNotFoundException fnfe){
    		JOptionPane.showMessageDialog(null, "Files were not found", "Topology Settings", JOptionPane.ERROR_MESSAGE);
    		return false;
    	}    	
    	
    	// Read the peers file to create vertices for the graph
    	try{
    		/*
	    	while((line = brPeers.readLine()) != null){
	    		String[] info = line.split(",");
	    		Vertex vertex = new Vertex(info[0], new Point(Integer.parseInt(info[1]), Integer.parseInt(info[2])));
	    		vertex.setColor("grey");
	    		vertices.put(info[0], vertex);
	    		map.put(vertex, vertex.getPosition());
	    	}
	    	*/
    		/*
	    	while((line = brTopology.readLine()) != null){
	    		String[] info = line.split(",");
	    		Vertex start = vertices.get(info[0]);
	    		int size = info.length;
	    		for(int i=1; i<size; i++){
	    			Vertex next = vertices.get(info[i]);
	    			edges.put(start.getNodeName(), next.getNodeName());
	    			Edge edge = new Edge(e);
	    			edge.setValue(start.getNodeName() + "-" + next.getNodeName());
	    			graph.addEdge(edge, start, next);
	    			e++;
	    		}
	    	}
	    	*/
    		while((line = brTopology.readLine()) != null){
	    		String[] info = line.split(":");	    		
	    		
	    		String[] position = info[1].split(",");
	    		int x = Integer.parseInt(position[0]);
	    		int y = Integer.parseInt(position[1]);
	    		Vertex vertex = new Vertex(info[0], new Point(x, y));
	    		vertex.setColor("grey");
	    		vertices.put(info[0], vertex);
	    		map.put(vertex, vertex.getPosition());	    		
	    	}
    		
    		try{
        		//fisPeers = new FileInputStream(peersFile);
        		//disPeers = new DataInputStream(fisPeers);
        		//brPeers = new BufferedReader(new InputStreamReader(disPeers));
        		fisTopology = new FileInputStream(topologyFile);    		
        		disTopology = new DataInputStream(fisTopology);    		
        		brTopology = new BufferedReader(new InputStreamReader(disTopology));
        	}catch(FileNotFoundException fnfe){
        		JOptionPane.showMessageDialog(null, "Files were not found", "Topology Settings", JOptionPane.ERROR_MESSAGE);
        		return false;
        	}    
    		
    		while((line = brTopology.readLine()) != null){
    			String[] info = line.split(":");	 
    			String neighbours[] = info[2].split(",");
	    		Vertex start = vertices.get(info[0]);
	    		int size = neighbours.length;
	    		for(int i=0; i<size; i++){
	    			Vertex next = vertices.get(neighbours[i]);
	    			System.out.println(start);
	    			System.out.println(next);
	    			edges.put(start.getNodeName(), next.getNodeName());
	    			Edge edge = new Edge(e);
	    			edge.setValue(start.getNodeName() + "-" + next.getNodeName());
	    			graph.addEdge(edge, start, next);
	    			e++;
	    		}
    		}
	    	transformer = TransformerUtils.mapTransformer(map);
    	}catch(IOException ioe){
    		JOptionPane.showMessageDialog(null, "Files could not be read", "Topology Settings", JOptionPane.ERROR_MESSAGE);
    		return false;
    	}
    	return true;
    }

    public Graph<Vertex, Edge> getGraph(){
        return graph;
    }

    public Transformer getTransformer(){
        return transformer;
    }
    
    public Multimap<String, String> getEdges(){
    	return edges;
    }
    
    public boolean isName(){
    	return peerNameRadioButton.isSelected();
    }
}
