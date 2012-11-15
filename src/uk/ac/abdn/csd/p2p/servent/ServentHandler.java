/*
 * ServentHandler.java - Extension of PeerHandler to allow Servents
 * to handle messages
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;
import java.net.SocketException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

import uk.ac.abdn.csd.p2p.Peer;
import uk.ac.abdn.csd.p2p.PeerConnection;
import uk.ac.abdn.csd.p2p.PeerHandler;
import uk.ac.abdn.csd.p2p.PeerInfo;

import uk.ac.abdn.csd.p2p.message.Bye;
import uk.ac.abdn.csd.p2p.message.Message;
import uk.ac.abdn.csd.p2p.message.Ping;
import uk.ac.abdn.csd.p2p.message.Pong;
import uk.ac.abdn.csd.p2p.message.Query;
import uk.ac.abdn.csd.p2p.message.QueryHit;

import uk.ac.abdn.csd.p2p.servent.ServentInterface.Type;


/**
 * This class processes the client's messages so that they can be broadcast
 * to other players and to gather details about them to be referenced later.
 * 
 * Since this is controlled by the Server, a new ServerHandler is created for
 * each (player) client so that the Server can simultaneously handle each client's
 * requests. This has the ability to stop itself once a player has quit.
 * 
 * @author Michael Scott Gibson
 */
public class ServentHandler extends PeerHandler{
	Servent servent;	
	
	public ServentHandler(PeerConnection peerConn, Servent servent) throws IOException{
		super(peerConn, servent);
		this.servent = servent;
	}		
	
	public void updateServentViews(Type type){		
		servent.updateServentViews(type);
	}	
	
	public void processMessage(Message message){
		if(!isOldMessage(message)){
			super.processMessage(message);
		}
	}
	
	private synchronized boolean isOldMessage(Message message){
		if(!servent.containsMessage(message)){
			servent.addMessage(message);
			return false;
		}
		return true;
	}
	
	public void processPing(Ping ping){	
		if(servent.size() < servent.getMAXPEERS()){
			PeerInfo sender = ping.getSender();				
			Pong pong = new Pong(servent.getPeerInfo(), sender, servent.getTTL(), null);
			servent.addNeighbour(sender.getId(), sender);
			servent.send(sender, pong);		
			updateServentViews(Type.neighbours);				
			System.out.println("Added new servent - " + sender.getId());			
		}			
	}
	
	public void processPong(Pong pong){		
		PeerInfo sender = pong.getSender();		

		if(isFromTarget(pong, servent.getServerInfo())){		   
			System.out.println("Connected to server");			
			servent.setServerInfo(sender);
			servent.setPeers(pong.getPeers());
			servent.checkNeighbours();			
		}else{
			if(servent.size() < servent.getMAXPEERS()){
				System.out.println("Connected to " + sender.getId());
				servent.addNeighbour(sender.getId(), sender);
				updateServentViews(Type.neighbours);
			}else{
				Bye bye = new Bye(servent.getPeerInfo(), sender, servent.getTTL());
				servent.send(sender, bye);
			}
		}		
	}
	
	public void processQuery(Query query){		
		PeerInfo sender = query.getSender();		
		PeerInfo lastSender = query.peekSenders();
		
		System.out.println("Received query from " + sender.getId());
			
		HashSet<String> queries = query.getQueries();
		HashMap<String, Object> results = new HashMap<String, Object>();
		if(queries != null || !queries.isEmpty()){
			HashSet<String> files = servent.getFiles();			
			for(String q:queries){				
				if(files.contains(q)){
					results.put(q, q);
				}
			}
		}		
		
		if(results.isEmpty()){	
			query.pushSender(servent.getPeerInfo());
			servent.sendAllExcept(lastSender, query);
		}else{
			QueryHit qh = new QueryHit(servent.getPeerInfo(), sender, servent.getTTL(), results);			
			qh.setSenders(query.getSenders());
			servent.send(lastSender, qh);
		}		
	}
	
	public void processQueryHit(QueryHit qh){
		System.out.println("Received query hit from " + qh.getSender().getId());
		
		if(isForSelf(qh)){		
			HashMap<String, Object> results = qh.getResults();
			for(String file: results.keySet()){
				servent.addFile(file);				
			}
		}else{
			PeerInfo nextSender = qh.popSenders();
			servent.send(nextSender, qh);			
		}
	}
	
	public void processBye(Bye b){
		if(isForSelf(b)){
			PeerInfo sender = b.getSender();
			servent.removeNeighbour(sender.getId());
			updateServentViews(Type.neighbours);
			System.out.println(sender.getId() + " has disconnected");
		}
	}
}