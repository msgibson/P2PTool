/*
 * ServerGUI.java - GUI for Server
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

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.ObservableGraph;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.control.AbstractPopupGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import edu.uci.ics.jung.visualization.transform.MutableTransformer;

import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.border.*;

import org.apache.commons.collections15.Transformer;

import uk.ac.abdn.csd.p2p.PeerInfo;
import uk.ac.abdn.csd.p2p.Version;
import uk.ac.abdn.csd.p2p.message.Message;
import uk.ac.abdn.csd.p2p.servent.P2PServentSettingsDialog;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import edu.uci.ics.jung.algorithms.layout.AbstractLayout;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout2;
import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.SpringLayout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;

import java.awt.geom.Point2D;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Server GUI class prepares the graphical components required to present the overlay network. 
 * Also, this class serves as user interface for a user to interact with application.
 * This class is prepares the graphical components required to present the overlay network. 
 * Also, this class serves as user interface for a user to interact with P2P Visualisation Server.
 * 
 * @see Server
 *
 * @author Ahmad Abdullah
 */
public class ServerGUI extends JFrame implements Runnable
{
	private static final long serialVersionUID = 1L;

	private int listeningPort;

	private Graph<Vertex, Edge> overlay = null;
	private VisualizationViewer<Vertex, Edge> vv = null;
	private AbstractLayout<Vertex, Edge> layout1 = null;

	Edge edge = null;
	int e = 0; //initial id for edge
	HashMap<Vertex,String> memory=null;
	
	HashMap<String, Vertex> vertices;
	Multimap<String, String> links;

	private String nodeColour;
	private String pingColour;
	private String pongColour;
	private String queryColour;
	private String queryHitColour;
	private String relayorColour;
	private String searchColour;
	Vertex findNode;
	Vertex PreviousNode;
	private ColourSettingDialog settingsColor;
	private LayoutSettingDialog settingsLayout;
	private VertexLabelPositionDialog labelPosition;
	private SpeedSettingDialog settingsSpeed;
	// private SearchForNodeDialog searchNode;
	
	private boolean customGraph = false;
	private boolean isName = true;
	private Multimap<String, String> edges;

	private long speedPing;
	private long speedPong;
	private long speedQuery;
	private long speedQueryHit;

	/**
	 * The actual <code>Server</code> model.
	 */
	Server server = null;
	// Variables declaration
	private JPanel contentPane;
	//-----
	private JPanel displayPanel;
	//-----
	private JTextArea nodeTextArea;
	private JScrollPane jScrollPane1;
	private JPanel nodePanel;
	//-----
	private JTextArea linkTextArea;
	private JScrollPane jScrollPane3;
	private JPanel linkPanel;
	//-----
	private JTextArea consoleTextArea;
	private JScrollPane jScrollPane2;
	private JButton clearButton;
	private JPanel consolePanel;
	// Variables declaration - do not modify
	private javax.swing.JMenuItem aboutMenuItem;
	private javax.swing.JMenuItem clearMenuItem;
	private javax.swing.JMenu editMenu;
	private javax.swing.JMenuItem exitMenuItem;
	private javax.swing.JMenu helpMenu;
	private javax.swing.JPopupMenu.Separator jSeparator1;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenu serverMenu;
	private javax.swing.JMenuItem settingMenuItem;
	private javax.swing.JMenuItem colorSettingMenuItem;
	private javax.swing.JMenuItem layoutSettingMenuItem;
	private javax.swing.JMenuItem speedSettingMenuItem;
	private javax.swing.JMenuItem topologySettingMenuItem;
	private javax.swing.JMenuItem startServerMenuItem;
	private javax.swing.JMenu toolMenuItem;
	private javax.swing.JMenuItem zoominMenuItem;
	private javax.swing.JMenuItem zoomoutMenuItem;
	private javax.swing.JMenuItem labelPositionMenuItem;
	private javax.swing.JMenuItem findNodeMenuItem;
	// End of variables declaration

	/**
	 * This method has been overridden as {@code ServerGUI} implements {@code Runnable}.
	 * 
	 */
	@Override
	public void run()
	{
		initializeComponent();      
		SwingUtilities.updateComponentTreeUI(this);
		this.setLocationRelativeTo(null);
		settingsColor = new ColourSettingDialog(this,true);
		settingsLayout = new LayoutSettingDialog(this,true);
		labelPosition = new VertexLabelPositionDialog(this, true);
		settingsSpeed = new SpeedSettingDialog(this,true);
		memory = new HashMap<Vertex,String>();
		nodeColour = "white";
		pingColour = "yellow";
		pongColour = "pink";
		queryColour= "Magenta";
		queryHitColour = "green";
		relayorColour = "orange";
		searchColour = "cyan";
		speedPing = 500;
		speedPong = 500;
		speedQuery = 500;
		speedQueryHit = 500;
	}


	/**
	 * This the constructor for {@code ServerGUI}
	 */
	public ServerGUI()
	{
		super();	
		vertices = new HashMap<String, Vertex>();	
		edges = HashMultimap.create();
	}

	/**
	 * This method prepares all the graphical components that are contained with P2P Visualisation Server.
	 */
	// <editor-fold defaultstate="collapsed" desc="...GUI">
	private void initializeComponent()
	{
		listeningPort=0;

		JFrame frame = new JFrame();
		BorderLayout layout = new BorderLayout();

		//create a graph
		overlay = new UndirectedSparseGraph<Vertex, Edge>();
		ObservableGraph<Vertex, Edge> og = new ObservableGraph<Vertex, Edge>(overlay);
		/*	og.addGraphEventListener(new GraphEventListener<Vertex, Edge>() {
			@Override
			public void handleGraphEvent(
					GraphEvent<Vertex, Edge> evt) {
				System.err.println("got " + evt);

			}
		});*/
		this.overlay = og;
		layout1 = new FRLayout2<Vertex, Edge>(overlay);
		vv = new VisualizationViewer<Vertex, Edge>(layout1,new Dimension(700, 200));
		vv.setGraphMouse(new DefaultModalGraphMouse<Vertex, Edge>());
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Vertex>());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<Edge>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		vv.getRenderContext().setVertexFillPaintTransformer(new VertexPainter());

		DefaultModalGraphMouse gm1 = new DefaultModalGraphMouse();
		gm1.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		gm1.setMode(ModalGraphMouse.Mode.PICKING);
		gm1.add(new MyPopupGraphMousePlugin());
		vv.setGraphMouse(gm1);
		vv.addKeyListener(gm1.getModeKeyListener());

		contentPane = new JPanel(layout);

		displayPanel = new javax.swing.JPanel();

		consoleTextArea = new JTextArea();
		jScrollPane2 = new JScrollPane();
		clearButton = new JButton();
		consolePanel = new JPanel();
		clearButton = new javax.swing.JButton();

		nodeTextArea = new JTextArea();
		jScrollPane1 = new JScrollPane();
		nodePanel = new JPanel();

		linkTextArea = new JTextArea();
		jScrollPane3 = new JScrollPane();
		linkPanel = new JPanel();

		menuBar = new javax.swing.JMenuBar();
		serverMenu = new javax.swing.JMenu();
		startServerMenuItem = new javax.swing.JMenuItem();
		jSeparator1 = new javax.swing.JPopupMenu.Separator();
		exitMenuItem = new javax.swing.JMenuItem();
		editMenu = new javax.swing.JMenu();
		settingMenuItem = new javax.swing.JMenuItem();
		colorSettingMenuItem = new javax.swing.JMenuItem();
		toolMenuItem = new javax.swing.JMenu();
		clearMenuItem = new javax.swing.JMenuItem();
		helpMenu = new javax.swing.JMenu();
		aboutMenuItem = new javax.swing.JMenuItem();
		zoominMenuItem = new javax.swing.JMenuItem();
		zoomoutMenuItem = new javax.swing.JMenuItem();
		layoutSettingMenuItem = new javax.swing.JMenuItem();
		labelPositionMenuItem = new javax.swing.JMenuItem();
		speedSettingMenuItem = new javax.swing.JMenuItem();
		topologySettingMenuItem = new javax.swing.JMenuItem();
		findNodeMenuItem = new javax.swing.JMenuItem();

		serverMenu.setText("Server");
		serverMenu.setToolTipText("Server");

		startServerMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
		startServerMenuItem.setText("Start Server");
		startServerMenuItem.setToolTipText("Start Server");
		startServerMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startServerMenuItemActionPerformed(evt);
			}
		});
		serverMenu.add(startServerMenuItem);
		serverMenu.add(jSeparator1);

		exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_MASK));
		exitMenuItem.setText("Exit");
		exitMenuItem.setToolTipText("Exit");
		exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitMenuItemActionPerformed(evt);
			}
		});
		serverMenu.add(exitMenuItem);

		menuBar.add(serverMenu);

		editMenu.setText("Edit");
		editMenu.setToolTipText("Edit");

		settingMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
		settingMenuItem.setText("Server Settings");
		settingMenuItem.setToolTipText("Server Settings");
		settingMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				settingMenuItemActionPerformed(evt);
			}
		});
		editMenu.add(settingMenuItem);

		colorSettingMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK));
		colorSettingMenuItem.setText("Colour Settings");
		colorSettingMenuItem.setToolTipText("Colour Settings");
		colorSettingMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				colorSettingMenuItemActionPerformed(evt);
			}
		});
		editMenu.add(colorSettingMenuItem);

		layoutSettingMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
		layoutSettingMenuItem.setText("Layout Settings");
		layoutSettingMenuItem.setToolTipText("Layout Settings");
		layoutSettingMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				layoutSettingMenuItemActionPerformed(evt);
			}
		});
		editMenu.add(layoutSettingMenuItem);

		speedSettingMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
		speedSettingMenuItem.setText("Speed Settings");
		speedSettingMenuItem.setToolTipText("Speed Settings");
		speedSettingMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				speedSettingMenuItemActionPerformed(evt);
			}
		});
		editMenu.add(speedSettingMenuItem);
		
		topologySettingMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
		topologySettingMenuItem.setText("Topology Settings");
		topologySettingMenuItem.setToolTipText("Topology Settings");
		topologySettingMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				topologySettingMenuItemActionPerformed(evt);
			}
		});
		editMenu.add(topologySettingMenuItem);

		menuBar.add(editMenu);

		toolMenuItem.setText("Tools");
		toolMenuItem.setToolTipText("Tools");


		labelPositionMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK ));
		labelPositionMenuItem.setText("Vertex Label Position");
		labelPositionMenuItem.setToolTipText("Vertex Label Position");
		toolMenuItem.add(labelPositionMenuItem);
		labelPositionMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				labelPositionMenuItemActionPerformed(evt);

			}
		});

		final ScalingControl scaler = new CrossoverScalingControl();

		zoominMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
		zoominMenuItem.setText("Zoom-In");
		zoominMenuItem.setToolTipText("Zoom-In");
		toolMenuItem.add(zoominMenuItem);
		zoominMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				scaler.scale(vv, 1.1f, vv.getCenter());
			}
		});
		zoomoutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
		zoomoutMenuItem.setText("Zoom-Out");
		zoomoutMenuItem.setToolTipText("Zoom-Out");
		toolMenuItem.add(zoomoutMenuItem);
		zoomoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				scaler.scale(vv, 0.9f, vv.getCenter());                        
			}
		});

		findNodeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
		findNodeMenuItem.setText("Find Node on Overlay");
		findNodeMenuItem.setToolTipText("Find Node");
		toolMenuItem.add(findNodeMenuItem);
		findNodeMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {          
				findNodeMenuItemMenuItemActionPerformed(evt);
			}
		});

		menuBar.add(toolMenuItem);

		helpMenu.setText("Help");
		helpMenu.setToolTipText("Help");

		aboutMenuItem.setText("About");
		aboutMenuItem.setToolTipText("About");
		aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				aboutMenuItemActionPerformed(evt);
			}
		});
		helpMenu.add(aboutMenuItem);

		menuBar.add(helpMenu);

		displayPanel.setLayout(new BorderLayout());
		displayPanel.setBorder(new TitledBorder("P2P Overlay"));
		displayPanel.add(vv);
		validate();
		//
		// nodeTextArea
		//
		nodeTextArea.setEditable(false);
		nodeTextArea.setLineWrap(true);

		//
		// jScrollPane1
		//
		jScrollPane1.setViewportView(nodeTextArea);
		//jScrollPane1.setsi
		//
		// nodePanel
		//
		nodePanel.setLayout(new BoxLayout(nodePanel, BoxLayout.X_AXIS));
		nodePanel.add(jScrollPane1, 0);

		nodePanel.setBorder(new TitledBorder("Nodes Active"));
		//
		// linkTextArea
		//
		linkTextArea.setEditable(false);
		linkTextArea.setLineWrap(true);
		//
		// jScrollPane3
		//
		jScrollPane3.setViewportView(linkTextArea);
		//
		// linkPanel
		//
		linkPanel.setLayout(new BoxLayout(linkPanel, BoxLayout.X_AXIS));
		linkPanel.add(jScrollPane3, 0);
		linkPanel.setBorder(new TitledBorder("Node Links"));
		//
		// consoleTextArea
		//
		consoleTextArea.setEditable(false);
		consoleTextArea.setLineWrap(true);
		//
		// jScrollPane2
		//
		jScrollPane2.setViewportView(consoleTextArea);
		//
		// clearButton
		//
		clearButton.setText("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearButton_actionPerformed(e);
			}

		});
		//
		// consolePanel
		//
		consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.X_AXIS));
		consolePanel.add(jScrollPane2, 0);
		consolePanel.add(clearButton, 1);
		consolePanel.setBorder(new TitledBorder("Console"));
		//
		// ServerGUI
		//
		Dimension panelD = new Dimension(700,330);  // display Panel
		Dimension panelD1 = new Dimension(700,134); // control Panel		
		Dimension panelD2 = new Dimension(350,140); // nodepanel and linkpanel
		Dimension panelD3 = new Dimension(700,140); // nodepanel and linkpanel togther
		consolePanel.setPreferredSize(panelD1);
		displayPanel.setPreferredSize(panelD);
		nodePanel.setPreferredSize(panelD2);
		linkPanel.setPreferredSize(panelD2);
		Box box = new Box(BoxLayout.X_AXIS); // put nodepanel and linkpanel in one box
		box.add(nodePanel);
		box.add(linkPanel);
		box.setSize(panelD3);

		//contentPane.add(BorderLayout.NORTH,displayPanel);
		//contentPane.add(BorderLayout.CENTER,box);
		contentPane.add(BorderLayout.NORTH,box);
		contentPane.add(BorderLayout.CENTER,displayPanel);		
		contentPane.add(BorderLayout.SOUTH,consolePanel);

		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(contentPane);
		frame.setTitle("P2P Visualisation Server");
		frame.setSize(new Dimension(800, 600));		
		frame.setResizable(true);
		// This code has been adapted from http://lookass.ch/?id=4&area=art&art=47
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = frame.getSize();
		screenSize.height = screenSize.height/2;
		screenSize.width = screenSize.width/2;
		size.height = size.height/2;
		size.width = size.width/2;
		int y = screenSize.height - size.height;
		int x = screenSize.width - size.width;
		frame.setLocation(x, y);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
	}// </editor-fold>

	/**
	 * This method is invoked when CLEAR button is pressed on P2P Visualisation Server interface. 
	 * @param e Indicates the associated event.
	 */
	private void clearButton_actionPerformed(ActionEvent e)
	{
		consoleTextArea.setText("");

	}

	/**
	 * This method is invoked when ABOUT menu item is pressed on P2P Visualisation Server interface. 
	 * @param evt Indicates the associated event.
	 */
	private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {		
		JOptionPane.showMessageDialog(this, Version.NAME + " Server " + Version.BUILD + "\n" + 
				"Created by " + Version.AUTHOR_1 + " and " + Version.AUTHOR_2 + "\n" +
				Version.URL, "P2P Tool", JOptionPane.INFORMATION_MESSAGE);
	}                                  

	/**
	 * This method is invoked when SETTING menu item is pressed on P2P Visualisation Server interface.
	 * @param evt Indicates the associated event.
	 */
	private void settingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                
		ServerSettingsDialog settings = new ServerSettingsDialog(this,true);
		listeningPort = settings.getListeningPort();
	}

	/**
	 * This method is invoked when COLOUR SETTING menu item is pressed on P2P Visualisation Server interface.
	 * @param evt Indicates the associated event.
	 */
	private void colorSettingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		settingsColor.setVisible(true);
		nodeColour = settingsColor.getDefaultColor();
		pingColour = settingsColor.getPingColor();
		pongColour = settingsColor.getPongColor();
		queryColour = settingsColor.getQueryColor();
		queryHitColour = settingsColor.getQueryHitColor();
		relayorColour = settingsColor.getRelayorColor();
		searchColour = settingsColor.getSearchColor();

		revalidation();
	}

	/**
	 * This methos id invoked when LABLEL POISTION SETTING menu item is pressed on P2P Visualisation Server interface.
	 * @param evt Indicates the associated event.
	 */
	private void labelPositionMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                
		labelPosition.setVisible(true);
		String vertexLabelPosition;
		vertexLabelPosition = labelPosition.getPositionType();
		if(vertexLabelPosition.equals("East of the node"))
		{
			vv.getRenderer().getVertexLabelRenderer().setPosition(Position.E);
			revalidation();
		}else if(vertexLabelPosition.equals("Center of the node"))
		{
			vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
			revalidation();
		}
		else if(vertexLabelPosition.equals("North of the node"))
		{
			vv.getRenderer().getVertexLabelRenderer().setPosition(Position.N);
			revalidation();
		}
		else if(vertexLabelPosition.equals("West of the node"))
		{
			vv.getRenderer().getVertexLabelRenderer().setPosition(Position.W);
			revalidation();
		}
		else if(vertexLabelPosition.equals("South of the node"))
		{
			vv.getRenderer().getVertexLabelRenderer().setPosition(Position.S);
			revalidation();
		}
		else if(vertexLabelPosition.equals("North-East of the node"))
		{
			vv.getRenderer().getVertexLabelRenderer().setPosition(Position.NE);
			revalidation();
		}
		else if(vertexLabelPosition.equals("North-West of the node"))
		{
			vv.getRenderer().getVertexLabelRenderer().setPosition(Position.NW);
			revalidation();
		}
		else if(vertexLabelPosition.equals("South-East of the node"))
		{
			vv.getRenderer().getVertexLabelRenderer().setPosition(Position.SE);
			revalidation();
		}
		else if(vertexLabelPosition.equals("South-West of the node"))
		{
			vv.getRenderer().getVertexLabelRenderer().setPosition(Position.SW);
			revalidation();
		}         
	}

	/**
	 * This method is invoked when LAYOUT SETTING menu item is pressed on P2P Visualisation Server interface.
	 * @param evt Indicates the associated event.
	 */
	private void layoutSettingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                
		settingsLayout.setVisible(true);
		String layoutType ;
		layoutType = settingsLayout.getLayoutType();
		if(layoutType == null){
			return;
		}
		if(layoutType.equals("FRLayout2"))
		{
			layout1 = new FRLayout2<Vertex, Edge>(overlay);
			vv.getModel().setGraphLayout(layout1,new Dimension(700, 200));
			revalidation();
		}else if(layoutType.equals("KKLayout"))
		{
			layout1 = new KKLayout<Vertex, Edge>(overlay);
			vv.getModel().setGraphLayout(layout1,new Dimension(700, 200));
		}
		else if(layoutType.equals("FRLayout"))
		{
			layout1 = new FRLayout<Vertex, Edge>(overlay);
			vv.getModel().setGraphLayout(layout1,new Dimension(700, 200));
		}
		else if(layoutType.equals("CircleLayout"))
		{
			layout1 = new CircleLayout<Vertex, Edge>(overlay);
			vv.getModel().setGraphLayout(layout1,new Dimension(700, 200));
		}
		else if(layoutType.equals("SpringLayout"))
		{
			layout1 = new SpringLayout<Vertex, Edge>(overlay);
			vv.getModel().setGraphLayout(layout1,new Dimension(700, 200));
		}
		else if(layoutType.equals("ISOMLayout"))
		{
			layout1 = new ISOMLayout<Vertex, Edge>(overlay);
			vv.getModel().setGraphLayout(layout1,new Dimension(700, 200));
		}
	}

	/**
	 * This method is invoked with SPEED SETTING menu item is pressed on P2P Visualisation Server interface.
	 * @param evt Indicates the associated event.
	 */
	private void speedSettingMenuItemActionPerformed(java.awt.event.ActionEvent evt) {  
		settingsSpeed.setVisible(true);
		speedPing = settingsSpeed.getPingSpeed();
		speedPong = settingsSpeed.getPongSpeed();
		speedQuery = settingsSpeed.getQuerySpeed();
		speedQueryHit = settingsSpeed.getQueryHitSpeed();
		revalidation();
	}
	
	private void topologySettingMenuItemActionPerformed(java.awt.event.ActionEvent evt){
		TopologySettingsDialog settings = new TopologySettingsDialog(this, true, "", "");		
		Graph<Vertex, Edge> graph = settings.getGraph();
		Transformer transformer = settings.getTransformer();
		if(graph == null || transformer == null){
			return;
		}
		customGraph = true;
		overlay = graph;
		links = settings.getEdges();
		isName = settings.isName();
		layout1 = new StaticLayout<Vertex, Edge>(overlay, transformer);
		vv.getModel().setGraphLayout(layout1, new Dimension(700, 200));		
		displayPanel.revalidate();
		displayPanel.repaint(); 
	}

	/**
	 * This method is invoked when SEARCH NODE menu item is pressed on P2P Visualisation Server interface.
	 * @param evt Indicates the associated event.
	 */
	private void findNodeMenuItemMenuItemActionPerformed(java.awt.event.ActionEvent evt) {
		Collection<Vertex> allVertices = overlay.getVertices();
		ArrayList<String> allNodes = new ArrayList<String>();
		Iterator<Vertex> it = allVertices.iterator();
		while(it.hasNext())
		{
			allNodes.add(it.next().getNodeName());
		}
		Object[] names = allNodes.toArray();
		String nodeName = null;

		try
		{
			nodeName = ((String)JOptionPane.showInputDialog(this,"Select Node from List","Find Node",JOptionPane.PLAIN_MESSAGE,null,names,null)).trim();
		}
		catch(Exception e){}

		if (PreviousNode != null)
		{
			PreviousNode.setColor(nodeColour);
			revalidation();
		}

		if((nodeName!=null) && (nodeName.length()>0) && (vertices.containsKey(nodeName)))
		{
			findNode = vertices.get(nodeName);
			PreviousNode = findNode; 
			findNode.setColor(searchColour);
			revalidation(); 
		}
	}


	/**
	 * This method is ivoked when START SERVER menu item is pressed on P2P Visualisation Server interface.
	 * @param evt Indicates the associated event.
	 */
	private void startServerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                                    
		if(listeningPort==0)
		{
			JOptionPane.showMessageDialog(this, "Incorrect settings. Please check Edit->Settings", "Error Encountered", JOptionPane.ERROR_MESSAGE);
		}else
		{
			try{
				if(customGraph){
					server = new Server(this, listeningPort, links, isName);
					new Thread(server).start();
				}else{
					server = new Server(this, listeningPort);
					new Thread(server).start();
				}
			}catch(Exception e){
				System.err.println("Could not connect to server. Please check Settings");
				e.printStackTrace();
			}
		}
	}
	
	private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
	    onExit();
	}//GEN-LAST:event_exitMenuItemActionPerformed

	private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
	    onExit();
	}//GEN-LAST:event_formWindowClosing

	/**
	 * Override the default close operation to make sure the user really wants
	 * to exit.
	 */
	private void onExit() {
		int n = JOptionPane.showConfirmDialog(this, "Are you sure you wish to quit?", "P2P Overlay Visualisation Server", JOptionPane.YES_NO_OPTION);
		if(n == JOptionPane.YES_OPTION){
			if(server != null){
				server.onExit();
			}
			System.exit(0);
		}
	}	
	
	public void updateConsole(String message){
		consoleTextArea.append(message+"\n");
	}
	
	public void addVertex(PeerInfo peer){
		Vertex vertex = new Vertex(peer.getId());
		vertex.setColor(nodeColour);
		vertices.put(peer.getId(), vertex);
		overlay.addVertex(vertex);		
	}
	
	public void updateVertex(PeerInfo peer){		
		String name = peer.getId();;
		if(!isName){
			name = peer.getHost();
		}
		for(Vertex v: overlay.getVertices()){
			if(v.getNodeName().equals(name)){
				v.setColor("white");	
				v.setNodeName(peer.getId());
				vertices.put(peer.getId(), v);
				break;
			}
		}		
	}
	
	public void removeVertex(PeerInfo peer){
		Vertex vertex = vertices.get(peer.getId());
		overlay.removeVertex(vertex);
		edges.removeAll(peer.getId());		
	}	
	
	public boolean addEdge(PeerInfo sender, PeerInfo receiver){
		Edge edge = new Edge(e);
		Vertex vertexSender = vertices.get(sender.getId());
		Vertex vertexReceiver = vertices.get(receiver.getId());
		if(vertexSender != null && vertexReceiver != null){
			edges.put(sender.getId(), receiver.getId());
			edge.setValue(sender.getId() + "-" + receiver.getId());
			overlay.addEdge(edge, vertexSender, vertexReceiver);			
			e++;			
			return true;
		}
		return false;
	}
	
	public boolean updateEdge(PeerInfo sender, PeerInfo receiver){
		Vertex v1 = vertices.get(sender.getId());
		Vertex v2 = vertices.get(receiver.getId());
		if(v1 == null || v2 == null){
			return false;
		}
		Edge edge = overlay.findEdge(v1, v2);
		if(edge == null){
			edge = overlay.findEdge(v2, v1);
			if(edge == null){
				return false;
			}
		}		
		overlay.removeEdge(edge);
		edge.setValue(sender.getId() + "-" + receiver.getId());
		overlay.addEdge(edge, v1, v2);
		edges.put(sender.getId(), receiver.getId());		
		return true;
	}	
	
	public boolean removeEdge(PeerInfo sender, PeerInfo receiver){		
		Edge edge = overlay.findEdge(vertices.get(sender.getId()), vertices.get(receiver.getId()));
		if(edge != null){
			edges.remove(sender.getId(), receiver.getId());
			overlay.removeEdge(edge);			
			return true;
		}
		return false;
	}
	
	

	/**
	 * This method is used for creating animation with regard to {@code Ping} and {@code Pong} messaging activity between nodes represented by
	 * {@code Vertex}.
	 * @param pong {@code Vertex} representing node where message destination is
	 * @param ping {@code Vertex} representing node where message started from
	 */
	public  void showMessagesAnimation(Vertex pong , Vertex ping)
	{
		if(!memory.containsKey(ping) || !memory.containsKey(pong))
		{

			//PING
			ping.setColor(pingColour);
			ping.incrementPing();//stats only
			pong.setColor(pongColour);
			edge.setValue(ping + " Ping " + pong);
			revalidation();
			waitDelay(speedPing);

			setDefaultColour(ping,pong);

			//PONG
			ping.setColor(pongColour);
			pong.setColor(pingColour);
			edge.setValue(pong +" Pong " + ping);
			revalidation();
			waitDelay(speedPong);

			setDefaultColour(ping,pong);

			memory.put(ping, "new");
			memory.put(pong, "new");
		}else if(memory.containsKey(pong))
		{
			//PING
			ping.setColor(pingColour);
			ping.incrementPing(); //stats only
			pong.setColor(pongColour);
			pong.incrementPong(); //stats only
			edge.setValue(ping + " Ping " + pong);
			revalidation();
			waitDelay(speedPing);

			setDefaultColour(ping,pong);

			//PONG
			ping.setColor(pongColour);
			pong.setColor(pingColour);
			edge.setValue(pong +" Pong " + ping);
			revalidation();
			waitDelay(speedPong);

			setDefaultColour(ping,pong);
		}
	}

	/**
	 * This method is created for setting animation speed.
	 * @param speed Indicates delay in animation in milliseconds. Larger the number for speed, slower is the animation. It is recommended to 
	 * set this value at 500. 
	 */
	public void waitDelay(long speed)
	{
		try
		{
			Thread.sleep(speed);
		} catch (InterruptedException ex) {
			Logger.getLogger(ServerGUI.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * This method is used to revalidate and repaint graph upon receiving updates.
	 */
	public void revalidation()
	{
		linkTextArea.setText("");
		for(String edge: edges.keySet()){
			linkTextArea.append(edge + ":" +edges.get(edge) + "\n");
		}
		
		nodeTextArea.setText("");
		for(String peer: server.getNeighbourKeys()){
			nodeTextArea.append(peer + "\n");
		}
		displayPanel.revalidate();
		displayPanel.repaint();
	}

	/**
	 * This method is used for setting default colour for nodes represented by {@code Vertex} after message animation activity has completed.
	 * @param ping {@code Vertex} 1 where messaging started.
	 * @param pong {@code Vertex} 2 where messaging ends.
	 */
	public void setDefaultColour(Vertex ping, Vertex pong)
	{
		ping.setColor(nodeColour);
		edge.setValue("Edge-"+ping.getNodeName()+"-"+pong.getNodeName());
		pong.setColor(nodeColour);
		revalidation();
	}

	/**
	 * This method is used for resetting colour of a single node represented by {@code Vertex}.
	 * @param ping {@code Vertex} that requires its colour to be reset.
	 */
	public void setDefaultColour(Vertex ping)
	{
		ping.setColor(nodeColour);
		revalidation();
	}

	/**
	 * This method is used to create animation related to Query messaging activity between nodes.
	 * @param message Instance of {@code Message} object
	 */
	public void animateQuery(Message message)
	{
		System.out.println("Creator "+message.getSender());
		System.out.println("Relayor "+message.getSenders().toString());
		System.out.println("Relayor "+message.peekSenders());
		System.out.println("Receiver the Query "+message.getReceiver());
		
		Vertex vSender = vertices.get(message.getSender().getId());
		vSender.setColor(queryColour);
		revalidation();
		waitDelay(speedQuery);
		setDefaultColour(vSender);
		revalidation();
		vSender.incrementQuery();//stats only

		if(!(message.peekSenders()).equals(message.getSender()))
		{
			Vertex vRelayor = vertices.get(message.peekSenders());
			vRelayor.setColor(relayorColour);
			revalidation();
			waitDelay(speedQuery);
			setDefaultColour(vRelayor);
			revalidation();
		}
	}

	/**
	 * This method is used to create animation related to QueryHit messaging activity between nodes.
	 * @param message Instance of {@code Message} object
	 */
	public void animateQueryHit(Message message)
	{
		Vertex vHitter=null;
		Vertex vSender=null;

		String hitter = message.getSender().getId();
		String sender = message.getReceiver().getId();

		if(!hitter.equals(null))
		{
			vHitter = vertices.get(hitter);

			vHitter.incrementQueryHit();//stats only

			vSender = vertices.get(sender);

			vSender.setColor(queryColour);
			vHitter.setColor(queryHitColour);

			if(overlay.isNeighbor(vSender, vHitter))
			{
				Edge edge1 = new Edge(0);
				edge1 = (Edge) overlay.findEdge(vSender, vHitter);
				edge1.setValue(vHitter+" Query Hit "+vSender);
				revalidation();
				waitDelay(speedQueryHit);
				vSender.setColor(nodeColour);
				edge1.setValue(vSender.getNodeName()+"-"+vHitter.getNodeName());
				vHitter.setColor(nodeColour);
				revalidation();
			}
			else
			{
				edge = new Edge(0);
				edge.setValue(vHitter+" Query Hit "+vSender);
				overlay.addEdge(edge, vSender, vHitter);
				revalidation();
				waitDelay(speedQueryHit);
				overlay.removeEdge(edge);
				setDefaultColour(vSender,vHitter);
			}
		}
	}

	/**
	 * This method is used to prepare statistics report with regard to selected node.
	 * @param node Indicates {@code Vertex} selected by a user.
	 */
	public void showNodeStatistics(Vertex node)
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append("Node ").append(node.getNodeName()).append(" Creation Time = ").append(node.getTimeCreate()).append("\n");
		buffer.append("Number of Neighbours for Node ").append(node.getNodeName()).append(" = ").append(overlay.getNeighborCount(node)).append("\n");
		buffer.append("Neighbours for Node ").append(node.getNodeName()).append(" = ").append(getNeighbours(node).toString()).append("\n\n");
		buffer.append("Message Statistics for Node ").append(node.getNodeName()).append("\n").append(node.getMessageStats()).append("\n");
		JOptionPane.showMessageDialog(this,buffer.toString(),"Statistics for Node "+node.getNodeName(),JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * This method is used to query overlay for getting all the neighbours for a user selected node.
	 * @param node Indicates {@code Vertex} selected by a user.
	 * @return List of names of nodes that are neighbours to selected node.
	 */
	public ArrayList<String> getNeighbours(Vertex node)
	{
		ArrayList<String> neighbourList = new ArrayList<String>();
		Collection allVertices = overlay.getNeighbors(node);
		Iterator it = allVertices.iterator();
		while(it.hasNext())
		{
			neighbourList.add(((Vertex)it.next()).getNodeName());
		}
		return neighbourList;
	}

	/**
	 * This method is used to query overlay for getting all the edges for a user selected node.
	 * @param node Indicates {@code Vertex} selected by a user.
	 * @return List of names of edges that are links between the selected node and its neighbours.
	 */
	public ArrayList<String> getEdges(Vertex node)
	{
		ArrayList<String> edgeList = new ArrayList<String>();
		Collection allEdges = overlay.getInEdges(node);
		Iterator it = allEdges.iterator();
		while(it.hasNext())
		{
			edgeList.add(((Edge)it.next()).getValue());
		}
		return edgeList;
	}

	/**
	 * This method is used to prepare statistics report with regard to the overlay graph.
	 */
	public void showOverlayStatistics()
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append("Number of Nodes on Overlay Network - ").append(overlay.getVertexCount()).append("\n");
		buffer.append("Nodes on Overlay Network - ").append(overlay.getVertices().toString()).append("\n");
		buffer.append("Number of Edges on Overlay Network - ").append(overlay.getEdgeCount()).append("\n\n");
		buffer.append("Message Statistics on Overlay").append("\n").append(getTotalOverlayMessages()).append("\n");
		JOptionPane.showMessageDialog(this, buffer.toString(), "Statistics for Overlay",JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * This method is used to query overlay graph for getting list of all nodes that are participating on an overlay network.
	 * @return List of names of nodes.
	 */
	public ArrayList<String> getVertices()
	{
		ArrayList<String> nodeList = new ArrayList<String>();
		Collection allNodes = overlay.getVertices();
		Iterator it = allNodes.iterator();
		while(it.hasNext())
		{
			nodeList.add(((Vertex)it.next()).getNodeName());
		}
		return nodeList;
	}

	/**
	 * This method is used to query overlay graph for getting list of all edges that exist with overlay network.
	 * @return List of names of edges.
	 */
	public ArrayList<String> getEdges()
	{
		ArrayList<String> edgeList = new ArrayList<String>();
		Collection allEdges = overlay.getEdges();
		Iterator it = allEdges.iterator();
		while(it.hasNext())
		{
			edgeList.add(((Edge)it.next()).getValue());
		}
		return edgeList;
	}

	/**
	 * This method is used to compute total number of messages of each type for an overlay network.
	 * @return {@code String} containing report of number of messages of each type of messaging activity.
	 */
	public String getTotalOverlayMessages()
	{
		int totalPing=0;
		int totalPong=0;
		int totalQuery=0;
		int totalQueryHit=0;
		Collection allNodes = overlay.getVertices();
		Iterator it = allNodes.iterator();
		while(it.hasNext())
		{
			Vertex v = ((Vertex)it.next());
			totalPing = totalPing + v.getPingCount();
			totalPong = totalPong + v.getPongCount();
			totalQuery = totalQuery + v.getQueryCount();
			totalQueryHit = totalQueryHit + v.getQueryHitCount();
		}

		String ping = "Total PING Messages on Overlay "+totalPing+"\n";
		String pong = "Total PONG Messages on Overlay "+totalPong+"\n";
		String query = "Total QUERY Messages on Overlay "+totalQuery+"\n";
		String queryHit = "Total QUERYHIT Messages on Overlay "+totalQueryHit+"\n";

		return ping + pong + query + queryHit;
	}

	/**
	 * Main Method
	 * @param args No arguments are required
	 */
	public static void main(String[] args)
	{	
		try{
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
			new Thread(new ServerGUI()).start();
		}catch (Exception e){
			e.printStackTrace();
		}					
	}

	/**
	 * A GraphMousePlugin that offers popup
	 * menu support<br/>
	 * Adapted from: http://www.koders.com/java/fid4A732D7D821B6C5A023A54F5218815A8F7501072.aspx?s=Point2d#L12
	 */
	protected class MyPopupGraphMousePlugin extends AbstractPopupGraphMousePlugin
	implements MouseListener {

		public MyPopupGraphMousePlugin() {
			this(MouseEvent.BUTTON3_MASK);
		}
		public MyPopupGraphMousePlugin(int modifiers) {
			super(modifiers);
		}

		/**
		 * If this event is over a node, pop up a menu to
		 * allow the user to center view to the node
		 *
		 * @param e
		 */
		protected void handlePopup(MouseEvent e) {
			final VisualizationViewer<Vertex,Vertex> vv =
					(VisualizationViewer<Vertex,Vertex>)e.getSource();
			final Point2D p = e.getPoint(); 

			GraphElementAccessor<Vertex,Vertex> pickSupport = vv.getPickSupport();
			if(pickSupport != null) {
				final Vertex station = pickSupport.getVertex(vv.getGraphLayout(), p.getX(), p.getY());
				final PickedState<Vertex> pickedVertexState = vv.getPickedVertexState();
				if(station != null) {
					JPopupMenu popup = new JPopupMenu();					

					popup.add(new AbstractAction("Center to Node") {
						public void actionPerformed(ActionEvent e) {

							MutableTransformer view = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW);
							MutableTransformer layout = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);

							Point2D ctr = vv.getCenter(); 
							Point2D pnt = view.inverseTransform(ctr);

							double scale = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).getScale();

							double deltaX = (ctr.getX() - p.getX())*1/scale;
							double deltaY = (ctr.getY() - p.getY())*1/scale;
							Point2D delta = new Point2D.Double(deltaX, deltaY);

							layout.translate(deltaX, deltaY);
						}
					});

					/*
					String delete = "Delete Node";

					popup.add(new AbstractAction("<html><center>" + delete) {
						public void actionPerformed(ActionEvent e) {
							pickedVertexState.pick(station, false);
							//server.disconnect(station.getNodeName());
						}
					});
					*/				

					popup.add(new AbstractAction("Show Node Statistics") {
						public void actionPerformed(ActionEvent e) {
							pickedVertexState.pick(station, false);
							showNodeStatistics(station);
						}
					});

					popup.show(vv, e.getX(), e.getY());
				} else {
					JPopupMenu popup = new JPopupMenu();					

					popup.add(new AbstractAction("Show Overlay Statistics") {
						public void actionPerformed(ActionEvent e) {
							showOverlayStatistics();
						}
					});

					popup.show(vv, e.getX(), e.getY());

				}
			}
		}
	}
}//end class
