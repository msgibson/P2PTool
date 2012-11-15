/*
 * ServerHandler.java - Extenstion of PeerHandler for Server to handle
 * messages
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

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

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
public class ServerHandler extends PeerHandler{
	Server server;	
	
	public ServerHandler(PeerConnection peerConn, Server server) throws IOException {
		super(peerConn, server);
		this.server = server;
	}		
	
	public void processMessage(Message message){
		super.processMessage(message);
		System.out.println(message);
	}
	
	public void processPing(Ping ping){		
		PeerInfo sender = ping.getSender();		
		
		if(isForSelf(ping)){
			Pong pong;
			HashSet<PeerInfo> neighbours = new HashSet<PeerInfo>(server.getNeighbourValues());	
			HashSet<PeerInfo> newNeighbours = new HashSet<PeerInfo>();			
			if(server.isCustomGraph()){
				HashSet<String> forcedNeighbours;
				if(server.isName()){
					forcedNeighbours = new HashSet<String>(server.getEdges().get(sender.getId()));
					for(String forcedNeighbour:forcedNeighbours){						
						for(PeerInfo pi:neighbours){							
							if(pi.getId().equals(forcedNeighbour)){
								newNeighbours.add(pi);
							}
						}
					}
				}else{
					forcedNeighbours = (HashSet<String>)server.getEdges().get(sender.getHost());
					for(String forcedNeighbour:forcedNeighbours){						
						for(PeerInfo pi:neighbours){
							if(pi.getHost().equals(forcedNeighbour)){
								newNeighbours.add(pi);
							}
						}
					}
				}				
				server.updateVertex(sender);
				pong = new Pong(server.getPeerInfo(), sender, server.getTTL(), newNeighbours);
			}else{					
				server.addVertex(sender);
				pong = new Pong(server.getPeerInfo(), sender, server.getTTL(), neighbours);
			}			
			server.addNeighbour(sender.getId(), sender);
			server.send(sender, pong);				
			server.updateConsole("Added new servent - " + sender.getId());
			server.revalidation();				
		}		
	}
	
	public void processPong(Pong pong){
		if(server.isCustomGraph()){
			server.updateEdge(pong.getSender(), pong.getReceiver());
		}else{
			server.addEdge(pong.getSender(), pong.getReceiver());
			System.out.println("Adding edge");
		}
		server.revalidation();
	}
	
	public void processQuery(Query query){		
		server.animateQuery(query);
	}
	
	public void processQueryHit(QueryHit qh){		
		server.animateQueryHit(qh);
	}
	
	public void processBye(Bye b){		
		if(isForSelf(b)){
			server.removeNeighbour(b.getSender().getId());
			server.removeVertex(b.getSender());
			server.updateConsole("Servent disconnected - " + b.getSender().getId());			
			System.out.println(b.getSender().getId() + " has disconnected");
		}else{
			server.removeEdge(b.getReceiver(), b.getSender());
		}
		server.revalidation();
	}	
}