/*
 * Ping.java - Represents a Ping message for connecting to another peer
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

package uk.ac.abdn.csd.p2p.message;

import uk.ac.abdn.csd.p2p.PeerInfo;

public class Ping extends Message {
	private static final long serialVersionUID = 1L;	
	
	public Ping(PeerInfo sender, PeerInfo receiver, int TTL){
		super(sender, receiver, Message.Type.PING, TTL);		
	}
}
