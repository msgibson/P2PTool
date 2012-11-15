/*
 * AutoLauncher.java - GUI to create multiple peers for P2PTool
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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uk.ac.abdn.csd.p2p.servent.ServentGUI;

public class AutoLauncher extends JFrame{		
	private static final long serialVersionUID = 1L;
	
	private JFormattedTextField txtQuantity;
	private JFormattedTextField txtPortNumber;
	private JTextField txtServerAddress;
	private JFormattedTextField txtServerPort;
	private JFormattedTextField txtMaxConnsMin;
	private JFormattedTextField txtMaxConnsMax;
	private JFormattedTextField txtTTLMin;
	private JFormattedTextField txtTTLMax;
	private JFormattedTextField txtSpeedMin;
	private JFormattedTextField txtSpeedMax;
	
	private int quantity = 0;
	private int portNumber = 0;
	private String serverAddress = "";
	private char name = 'a';
	private int serverPort = 0;
	private int maxConnsMin = 0;
	private int maxConnsMax = 0;
	private int TTLMin = 0;
	private int TTLMax = 0;
	private int speedMin = 0;
	private int speedMax = 0;
	private int randomMaxConns = 0;
	private int randomTTL = 0;
	private int randomSpeed = 0;
    
	public AutoLauncher(){		
		JLabel lblQuantity = new JLabel("Number of Servents: ");
		txtQuantity = new JFormattedTextField();
		txtQuantity.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
		
		JLabel lblPortNumber = new JLabel("Starting port number: ");
		txtPortNumber = new JFormattedTextField();
		txtPortNumber.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
		
		JLabel lblServerAddress = new JLabel("Server address: ");
		txtServerAddress = new JTextField();
		
		JLabel lblServerPort = new JLabel("Server port number: ");
		txtServerPort = new JFormattedTextField();
		txtServerPort.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
		
		JLabel lblMaxConns = new JLabel("Maximum number of neighbours: ");
		txtMaxConnsMin = new JFormattedTextField();
		txtMaxConnsMin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
		txtMaxConnsMax = new JFormattedTextField();
		txtMaxConnsMax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
		
		JLabel lblTTL = new JLabel("Life of messages (TTL): ");
		txtTTLMin = new JFormattedTextField();
		txtTTLMin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
		txtTTLMax = new JFormattedTextField();
		txtTTLMax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
		
		JLabel lblSpeed = new JLabel("Game speed of servent: ");
		txtSpeedMin = new JFormattedTextField();
		txtSpeedMin.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
		txtSpeedMax = new JFormattedTextField();
		txtSpeedMax.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
		
		JButton btnLaunch = new JButton("Launch");
		btnLaunch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				btnLaunch_clicked(e);
			}

		});
		
		// Swing components layout
		JPanel panel = new JPanel(new GridBagLayout());
		
		// Quantity
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblQuantity, gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.gridwidth = 3;
		panel.add(txtQuantity, gbc);	
		
		// Port number
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblPortNumber, gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.gridwidth = 3;
		panel.add(txtPortNumber, gbc);
		
		// Server address
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblServerAddress, gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.gridwidth = 3;
		panel.add(txtServerAddress, gbc);
		
		// Server port
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblServerPort, gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.gridwidth = 3;
		panel.add(txtServerPort, gbc);
		
		// Neighbours
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblMaxConns, gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;		
		panel.add(txtMaxConnsMin, gbc);
		gbc.gridx = 2;
		gbc.weightx = 0.1;		
		panel.add(new JLabel(" to"), gbc);
		gbc.gridx = 3;
		gbc.weightx = 1;		
		panel.add(txtMaxConnsMax, gbc);		
		
		// Messages
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblTTL, gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;		
		panel.add(txtTTLMin, gbc);
		gbc.gridx = 2;
		gbc.weightx = 0.1;		
		panel.add(new JLabel(" to"), gbc);
		gbc.gridx = 3;
		gbc.weightx = 1;		
		panel.add(txtTTLMax, gbc);		
		
		// Game speed
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.EAST;
		panel.add(lblSpeed, gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;		
		panel.add(txtSpeedMin, gbc);
		gbc.gridx = 2;
		gbc.weightx = 0.1;		
		panel.add(new JLabel(" to"), gbc);
		gbc.gridx = 3;
		gbc.weightx = 1;		
		panel.add(txtSpeedMax, gbc);
		
		// Launch button
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 4;
		gbc.anchor = GridBagConstraints.SOUTH;
		panel.add(btnLaunch, gbc);
		
		setSize(400, 300);
		setTitle("P2PTool Servent Launcher");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().add(panel);
		setLocationRelativeTo(null);
	}
	
	private void btnLaunch_clicked(ActionEvent e){
		gatherParameters();
		startServents();
	}
	
	private void gatherParameters(){
		if(!txtQuantity.getText().equals("")){
			quantity = Integer.parseInt(txtQuantity.getText());
		}
		if(!txtPortNumber.getText().equals("")){
			portNumber = Integer.parseInt(txtPortNumber.getText());
		}
		serverAddress = txtServerAddress.getText();
		if(!txtServerPort.getText().equals("")){
			serverPort = Integer.parseInt(txtServerPort.getText());
		}
		if(!txtMaxConnsMin.getText().equals("")){
			maxConnsMin = Integer.parseInt(txtMaxConnsMin.getText());
		}
		if(!txtMaxConnsMax.getText().equals("")){
			maxConnsMax = Integer.parseInt(txtMaxConnsMax.getText());
		}
		if(!txtTTLMin.getText().equals("")){
			TTLMin = Integer.parseInt(txtTTLMin.getText());
		}
		if(!txtTTLMax.getText().equals("")){
			TTLMax = Integer.parseInt(txtTTLMax.getText());
		}
		if(!txtSpeedMin.getText().equals("")){
			speedMin = Integer.parseInt(txtSpeedMin.getText());
		}
		if(!txtSpeedMax.getText().equals("")){
			speedMax = Integer.parseInt(txtSpeedMax.getText());
		}
		
		String result = "" + quantity + " " + portNumber + " " + serverAddress + " " +  
		serverPort + " " + maxConnsMin + " " + maxConnsMax + " " + TTLMin + " " + 
		TTLMax + " " + speedMin + " " + speedMax;		
		
		JOptionPane.showMessageDialog(this, "Test " + result);		
	}
	
	private void startServents(){		
		for(int i = 0; i < quantity; i++){			
			Random random = new Random();			
			randomMaxConns = maxConnsMin + random.nextInt((maxConnsMax - maxConnsMin) + 1);
			randomTTL = TTLMin + random.nextInt((TTLMax - TTLMin) + 1);
			randomSpeed = speedMin + random.nextInt((speedMax - speedMin) + 1);
			JavaProcessBuilder builder = new JavaProcessBuilder();
			builder.setMainClass("uk.ac.abdn.csd.p2p.P2PServentGUI");	
			builder.setWorkingDirectory(".");			
						
			// Need to tell JavaProcessBuilder where the Java executable is.
			// Currently set to Windows Java 6 32-bit.
			builder.setJavaRuntime("C:\\Program Files (x86)\\Java\\jre6\\bin\\java.exe");
			
			builder.addArgument(serverAddress);
			builder.addArgument(""+serverPort);
			builder.addArgument(""+name++);
			builder.addArgument(""+portNumber++);
			builder.addArgument(""+randomMaxConns);
			builder.addArgument(""+randomTTL);
			builder.addArgument(""+randomSpeed);
			try{
				builder.startProcess();
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			new Runnable(){
				public void run(){
					ServentGUI gui = new ServentGUI(serverAddress, serverPort,
	                		""+name++, portNumber++, randomMaxConns,
	                		randomTTL, true);
					gui.setVisible(true);
			        gui.connectMenuItemActionPerformed(null);	
				}
			}.run();	 
			              
		}
	}
	
    public static void main(String argv[])
    {
        try{
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
        }
        AutoLauncher launcher = new AutoLauncher();
        launcher.setVisible(true);
    }
            
}
