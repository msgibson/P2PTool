/*
 * Server.java - A bootstrapping peer for Servents to join a network
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

import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import uk.ac.abdn.csd.p2p.Peer;
import uk.ac.abdn.csd.p2p.PeerConnection;
import uk.ac.abdn.csd.p2p.PeerInfo;
import uk.ac.abdn.csd.p2p.message.Message;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class Server extends Peer implements Runnable{	
    private ServerGUI gui;
    
    private boolean customGraph = false;
    private boolean isName = true;
    private Multimap<String, String> edges;
	
	/**
	 * Class constructor.
	 * 
	 * @param port The port number for the server to listen on
	 * @param benchmarking True to enable benchmarking, false to disable it
	 */
	public Server(ServerGUI serverGUI, int port) throws UnknownHostException{
		super("SERVER", port, -1, -1);		
		gui = serverGUI;       
	}
	
	public Server(ServerGUI serverGUI, int port, Multimap<String, String> edges, boolean isName) throws UnknownHostException{
		super("SERVER", port, -1, -1);		
		gui = serverGUI; 
		this.edges = edges;
		this.isName = isName;
		this.customGraph = true;
	}
	
	public void run()
    {
		ServerSocket serverSocket = null;		
		try{
			serverSocket = new ServerSocket(getPeerInfo().getPort());
		}catch(Exception e){
			shutdown = true;
			e.printStackTrace();
		}
		
        gui.updateConsole(getPeerInfo().getId() + ": Creating server on port " + getPeerInfo().getPort() + "...");
        gui.updateConsole(getPeerInfo().getId() + ": Listening...");
        
        /**
         * Make the server listen forever.
         */
        while(!shutdown){        	       	
            try{            	
            	Socket socket = serverSocket.accept();            	
				PeerConnection peerConn = new PeerConnection(socket);				
				ServerHandler sh = new ServerHandler(peerConn, this);
				sh.start();				
            }catch(Exception e){               
                e.printStackTrace();
            }
        }
    }
	
	public void revalidation(){
		gui.revalidation();
	}
	
	public Multimap<String, String> getEdges(){
		return edges;
	}
	
	public boolean isCustomGraph(){
		return customGraph;
	}
	
	public boolean isName(){
		return isName;
	}
	
	public void addVertex(PeerInfo peer){
		gui.addVertex(peer);
	}
	
	public void removeVertex(PeerInfo peer){
		gui.removeVertex(peer);
	}
	
	public void updateVertex(PeerInfo peer){
		gui.updateVertex(peer);
	}
	
	public boolean addEdge(PeerInfo sender, PeerInfo receiver){
		return gui.addEdge(sender, receiver);
	}
	
	public boolean updateEdge(PeerInfo sender, PeerInfo receiver){
		return gui.updateEdge(sender, receiver);
	}
	
	public boolean removeEdge(PeerInfo sender, PeerInfo receiver){
		return gui.removeEdge(sender, receiver);
	}
	
	public void animateQuery(Message message){
		gui.animateQuery(message);
	}
	
	public void animateQueryHit(Message message){
		gui.animateQueryHit(message);
	}
	
	public void shutdown(){
		shutdown = true;
	}
	
	public void updateConsole(String message){
		gui.updateConsole(message);
	}
}
