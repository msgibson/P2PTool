/*
 * Servent.java - Standard peer which is able to talk with other
 * peers and send/receive file(names)
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

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import uk.ac.abdn.csd.p2p.Peer;
import uk.ac.abdn.csd.p2p.PeerConnection;
import uk.ac.abdn.csd.p2p.PeerHandler;
import uk.ac.abdn.csd.p2p.PeerInfo;
import uk.ac.abdn.csd.p2p.message.Bye;
import uk.ac.abdn.csd.p2p.message.Message;
import uk.ac.abdn.csd.p2p.message.Ping;
import uk.ac.abdn.csd.p2p.message.Query;
import uk.ac.abdn.csd.p2p.servent.ServentInterface.Type;

public class Servent extends Peer implements Runnable{
	
	private PeerInfo serverInfo;
	
	private boolean viaServer;	
	
	private HashSet<PeerInfo> peers;
	private HashSet<String> messages, files;	
	
	private List<ServentInterface> serventViews;	
	
	Servent(String name, int port, int TTL, int MAXPEERS, String serverHost, int serverPort, boolean viaServer) throws UnknownHostException{
		super(name, port, TTL, MAXPEERS);	
		
		serverInfo = new PeerInfo("SERVER", serverHost, serverPort);
		
		this.viaServer = viaServer;	
		
		peers = new HashSet<PeerInfo>();
		messages = new HashSet<String>();
		files = new HashSet<String>();		
		
		serventViews = new CopyOnWriteArrayList();		
	}
	
	public void addMessage(Message message){
		messages.add(message.getID());		
	}
	
	public boolean containsMessage(Message message){
		return messages.contains(message.getID());
	}
	
	public void setPeers(HashSet<PeerInfo> peers){
		this.peers = peers;
	}
	
	public HashSet<PeerInfo> getPeers(){
		return peers;
	}
	
	public PeerInfo getServerInfo(){
		return serverInfo;
	}
	
	public void setServerInfo(PeerInfo serverInfo){
		this.serverInfo = serverInfo;
	}
	
	public void findFile(String file){
		Query query = new Query(info, info, TTL);
		query.addQuery(file);
		sendAll(query);	
		super.send(serverInfo, query);
		System.out.println("Sent query about " + file + " to neighbours");		
	}

	public void addFile(String file){		
		files.add(file);
		System.out.println("Added " + file + " to file list");
		updateServentViews(Type.files);
	}

	public void removeFile(String file){		
		files.remove(file);
		updateServentViews(Type.files);
	}

	public void setFiles(HashSet<String> files){		
		this.files = files;
		updateServentViews(Type.files);
	}

	public HashSet<String> getFiles(){
		return files;
	}	
	
	public void addServentView(ServentInterface serventView){
		serventViews.add(serventView);
	}
	
	public void updateServentViews(Type type){
		for(ServentInterface view:serventViews){
			view.update(type);
		}
	}
	
	private boolean register(){		
		System.out.println("Connecting to SERVER at " + serverInfo.getHost() + ":" + serverInfo.getPort() + "...");
		try{
			Ping ping = new Ping(getPeerInfo(), serverInfo, getTTL());			
			PeerConnection serverConn = new PeerConnection(serverInfo);
			serverConn.send(ping);
			serverConn.close();
			return true;
		}catch(Exception e){
			System.out.print("\n");
			System.err.println("Could not create server socket");
			e.printStackTrace();
		}	
		return false;
	}	
	
	public void checkNeighbours(){	
		System.out.println("Checking neighbours");
		if(peers == null || peers.isEmpty()){			
			return;
		}		
		synchronized(peers){
			for(Iterator<PeerInfo> i = peers.iterator(); i.hasNext();){	
				try{	
					PeerInfo newPeer = i.next();
					Ping ping = new Ping(getPeerInfo(), newPeer, getTTL());
					ping.pushSender(getPeerInfo());
					PeerConnection peerConn = new PeerConnection(newPeer);
					peerConn.send(ping);
					peerConn.close();					
					i.remove();
				}catch(Exception e){
					System.err.println("Could not connect");
					e.printStackTrace();
				}			
			}
		}
	}
	
	public void send(PeerInfo peerInfo, Message message){
		super.send(peerInfo, message);
		if(viaServer && !peerInfo.equals(serverInfo)){
			message.setTTL(-1);	// Guarantee the message gets to the server
			super.send(serverInfo, message);
		}
	}
	
	public void onExit(){		
		Bye bye = new Bye(getPeerInfo(), serverInfo, -1);
		send(serverInfo, bye);
		super.onExit();
	}
		
	public void run(){		
		register();					
		try{						
			mainLoop();				
		}catch(Exception e){				
			e.printStackTrace();
		}			
	}	
	
	private void mainLoop() throws IOException{		
		ServerSocket serverSocket = new ServerSocket(getPeerInfo().getPort());
		while(!shutdown){			
			Socket socket = serverSocket.accept();
			PeerConnection peerConn = new PeerConnection(socket);
			ServentHandler sh = new ServentHandler(peerConn, this);
			sh.start();
		}
		serverSocket.close();		
	}
}
