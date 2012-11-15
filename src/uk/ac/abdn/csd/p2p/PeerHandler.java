/*
 * PeerHandler.java - Abstract thread to handle incoming messages from
 * other peers
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

import java.io.IOException;

import uk.ac.abdn.csd.p2p.message.*;

public abstract class PeerHandler extends Thread{
	private PeerConnection peerConn;	
	private Peer self;
	
	public PeerHandler(PeerConnection peerConn, Peer self) throws IOException{
		this.peerConn = peerConn;
		this.self = self;
	}	
	
	public void run(){
		try{
			Message message = (Message)peerConn.read();
			peerConn.close();
			processMessage(message);								
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	 * These types of messages are typically used in Gnutella 0.4.
	 */
	public void processMessage(Message message){
		if(message != null){			
            switch(message.getType()){								
				case PING:					
					processPing((Ping)message);					
					break;					
				case PONG:
					processPong((Pong)message);
					break;					
				case QUERY:					
					processQuery((Query)message);
					break;					
				case QUERYHIT:	
					processQueryHit((QueryHit)message);
					break;
				case BYE:
					processBye((Bye)message);
					break;
		    }
		}
	}
	
	/*
	 * Check if a message is for itself.
	 * Usually if it's not, it should be forwarded to its intended recipient.
	 */
	public boolean isForSelf(Message message){
		if(self.getPeerInfo().getId().equals(message.getReceiver().getId())){
			return true;
		}
		return false;
	}
	
	/*
	 * Check if a message is from the specified target.
	 * Useful to know if a message has been forwarded or not.
	 */
	public boolean isFromTarget(Message message, PeerInfo target){
		if(target.getId().equals(message.getSender().getId())){
			return true;
		}
		return false;
	}
	
	/*
	 * Empty so it can be overridden by child classes.
	 */
	public void processPing(Ping ping){
		
	}
	
	public void processPong(Pong pong){
		
	}
	
	public void processQuery(Query query){
		
	}
	
	public void processQueryHit(QueryHit qh){
		
	}
	
	public void processBye(Bye b){
		
	}
}