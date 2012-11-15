/*
 * Peer.java - Represents a peer in P2PTool and handles neighbours and
 * message passing
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

package uk.ac.abdn.csd.p2p;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Set;

import uk.ac.abdn.csd.p2p.message.Bye;
import uk.ac.abdn.csd.p2p.message.Message;

/**
 * This class holds all of the players that are currently on a server.
 * It can be used to send a message to all of the other players, except the
 * sending player, (broadcast) and to individual players as well.
 * 
 * @author Michael Gibson
 */
public abstract class Peer{
	private Hashtable<String, PeerInfo> neighbours;	
	private Hashtable<String, String> nextBestPeer;	
	
	protected PeerInfo info;
	
	protected int TTL, MAXPEERS;	
	
	protected boolean shutdown = false;
	
	/**
	 * Class constructor.
	 */
	public Peer(String name, int port, int TTL, int MAXPEERS) throws UnknownHostException{
		info = new PeerInfo(name, InetAddress.getLocalHost().getHostName(), port);
		this.TTL = TTL;
		this.MAXPEERS = MAXPEERS;
		neighbours = new Hashtable<String, PeerInfo>();		
		nextBestPeer = new Hashtable<String, String>();
	}
	
	public PeerInfo getPeerInfo(){
		return info;
	}
	
	public synchronized void setTTL(int TTL){
		this.TTL = TTL;
	}
	
	public int getTTL(){
		return TTL;
	}
	
	public int getMAXPEERS(){
		return MAXPEERS;
	}
	
	public synchronized void addNextBestNeighbour(String key, String value){
		nextBestPeer.put(key, value);
	}
	
	public synchronized String getNextBestNeighbour(String key){		
		return nextBestPeer.get(key);
	}	
	
	public synchronized void addNeighbour(String key, PeerInfo value){
		neighbours.put(key, value);
	}
	
	public synchronized void removeNeighbour(String name){		
		neighbours.remove(name);
	}	
	
	public synchronized Collection<PeerInfo> getNeighbourValues(){
		return neighbours.values();
	}
	
	public synchronized Set<String> getNeighbourKeys(){
		return neighbours.keySet();
	}
	
	public synchronized PeerInfo getNeighbour(String key){
		return neighbours.get(key);
	}
	
	public int size(){
		return neighbours.size();
	}
	
	/*
	 * Send a message to all neighbours except the one given.
	 * Useful for "flooded request" messaging where a message should
	 * not go back to the sending neighbour.
	 */
	public synchronized void sendAllExcept(PeerInfo pi, Message message){		
		int TTL = message.getTTL();
		if(TTL == 0){
			return;
		}
		message.setTTL(--TTL);
		for(PeerInfo peerInfo:neighbours.values()){
			if(!peerInfo.equals(pi)){
				try{
					PeerConnection peerConn = new PeerConnection(peerInfo);
					peerConn.send(message);
					peerConn.close();
				}catch(Exception e){
					e.printStackTrace();
					return;
				}
			}
		}		
	}	
	
	public synchronized void sendAll(Message message){		
		int TTL = message.getTTL();
		if(TTL == 0){
			return;
		}
		message.setTTL(--TTL);
		for(PeerInfo peerInfo:neighbours.values()){		
			try{
				PeerConnection peerConn = new PeerConnection(peerInfo);
				peerConn.send(message);
				peerConn.close();
			}catch(Exception e){
				e.printStackTrace();
				return;
			}
		}		
	}	
	
	public synchronized void send(PeerInfo peerInfo, Message message){		
		int TTL = message.getTTL();
		if(TTL == 0){			
			return;
		}
		message.setTTL(--TTL);
		try{
			PeerConnection peerConn = new PeerConnection(peerInfo);
			peerConn.send(message);
			peerConn.close();			
		}catch(Exception e){
			e.printStackTrace();
			return;
		}		
	}
	
	/*
	 * Tell all neighbours this peer is shutting down for a graceful shutdown.
	 */
	public void onExit(){
		for(PeerInfo peerInfo:neighbours.values()){
			Bye bye = new Bye(info, peerInfo, -1);
			send(peerInfo, bye);
		}
		shutdown();
	}
	
	/*
	 * Stop the socket listening thread loop.
	 */
	public void shutdown(){
		shutdown = true;
	}
}
