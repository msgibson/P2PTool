/*
 * PeerConnection.java - Holds socket information for each peer connection
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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class PeerConnection {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;	
	
	public PeerConnection(PeerInfo peerInfo) throws IOException{
		socket = new Socket(peerInfo.getHost(), peerInfo.getPort());
		System.out.println(peerInfo.getHost() + peerInfo.getPort());
		in = new ObjectInputStream(socket.getInputStream());		
		out = new ObjectOutputStream(socket.getOutputStream());		
	}
	
	public PeerConnection(Socket socket) throws IOException{
		this.socket = socket;		
		out = new ObjectOutputStream(socket.getOutputStream());		
		in = new ObjectInputStream(socket.getInputStream());		
	}
	
	public PeerConnection(String host, int port) throws IOException{
		socket = new Socket(host, port);
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
	}
	
	public void send(Object message) throws IOException{
		out.writeObject(message);
		out.flush();
	}
	
	public Object read() throws IOException, ClassNotFoundException{
		return in.readObject();
	}
	
	public void close() throws IOException{
		if(socket != null){			
			socket.close();			
		}
	}
}
