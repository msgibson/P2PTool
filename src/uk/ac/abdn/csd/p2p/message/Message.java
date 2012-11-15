/*
 * Message.java - Abstract message class
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

import java.io.Serializable;
import java.util.Date;
import java.util.Stack;

import uk.ac.abdn.csd.p2p.PeerInfo;

/**
 * A <code>Message</code> is a <b>serializable</b> container which stores
 * various details, such as the sender, receiver, type and a unique ID.
 * It must be extended to hold other details and other message types.
 * 
 * Subclasses of <code>Message</code> could have used <code>instanceOf()</code>
 * to check what type of message has arrived, but I found using an Enumeration
 * with the different types of messages available easier and more efficient
 * than the method calling way.
 * 
 * @author Michael Gibson
 */
public abstract class Message implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * The message types which are commonly found in a peer-to-peer network
	 * (especially flooded request networks).
	 */
	public enum Type{		
		PING,
		PONG,
		QUERY,
		QUERYHIT,
		BYE
	}

	/**
	 * A unique ID to distinguish one <code>Message</code> from another.
	 */
	private String ID;

	/**
	 * Stores the names of the creator and receiver of a <code>Message</code>.
	 */
	private PeerInfo sender, receiver;

	/**
	 * The type of message according to the enum.
	 */
	private Type type;

	/**
	 * Stores, in order, who last dealt with this message. Useful for tracing
	 * back messages.
	 */
	private Stack<PeerInfo> senders;

	/**
	 * Counter to show how many lives this message has, i.e. how many 'hops'
	 * can be made before being ignored.
	 */
	private int TTL;
	
	/**
	 * Current tick of the game cycle. Used to calculate how old this message is,
	 * therefore affecting the age of the information.
	 */
	private long creationTick;

	/**
	 * The constructor of a <code>Message</code>. This can only be called by sub-classing
	 * the <code>Message</code> object.
	 * 
	 * @param creator The name of the node which will send this.
	 * @param type The type of <code>Message</code> according to the enum.
	 */
	public Message(PeerInfo sender, PeerInfo receiver, Type type, int TTL){				
		this.sender = sender;
		this.receiver = receiver;
		this.type = type;
		ID = sender.getId() + "_" + type.toString() + "_" + receiver.getId() + "_" + new Date().getTime();		
		senders = new Stack<PeerInfo>();
		this.TTL = TTL;
		this.creationTick = new Date().getTime();
		senders.push(sender);
	}
	
	/**
	 * Return the game tick when this message was created
	 * 
	 * @return Game tick of message creation.
	 */
	public long getCreationTick(){
		return creationTick;
	}

	/**
	 * Return the number of lives this message has.
	 * 
	 * @return Number of lives this message has.
	 */
	public int getTTL(){
		return TTL;
	}

	/**
	 * Set the number of lives for this message.
	 * 
	 * @param TTL Number of lives this message should have.
	 */
	public void setTTL(int TTL){
		this.TTL = TTL;
	}

	/**
	 * Remove a life from this message. Should be used when the message has
	 * been sent to another node and restricting floods is important.
	 */
	public void decrementTTL(){
		TTL--;
	}

	/**
	 * Get the ID of this message.
	 * @return String of the ID of this message.
	 */
	public String getID(){
		return ID;
	}

	/**
	 * Get the creator of this message.
	 * @return String of the creator of this message.
	 */
	public PeerInfo getSender(){
		return sender;
	}

	/**
	 * Get the type of this message.
	 * @return Value (in the form of an <code>enum</code>)
	 * of the type of this message.
	 */
	public Type getType(){
		return type;
	}

	/**
	 * Set the receiver of this message.
	 * @param receiver A String of the receiving node's name.
	 */
	public void setReceiver(PeerInfo receiver){
		this.receiver = receiver;
	}

	/**
	 * Get the receiver of this message.
	 * @return String of the receiver of this message.
	 */
	public PeerInfo getReceiver(){
		return receiver;
	}

	/**
	 * Check to see if other senders have to be dealt with for this message.
	 * Ideal for checking if this message has reached the original sending
	 * node.
	 * @return <code>True</code> if <code>senders</code> stack is empty.
	 * Otherwise, <code>false</code>.
	 */
	public boolean isSendersEmpty(){
		return senders.isEmpty();
	}

	/**
	 * Add the name of a node who has dealt with this message.
	 * @param node Name of node who has seen (or passed on) this message.
	 */
	public void pushSender(PeerInfo peer){
		senders.push(peer);
	}

	/**
	 * Look at the last sender of this message. Does NOT remove the name
	 * from the <code>senders</code> stack.
	 * @return Name of node who last dealt with this message.
	 */
	public PeerInfo peekSenders(){
		return senders.peek();
	}

	/**
	 * Get the last sender of this message. This REMOVES the name from the
	 * <code>senders</code> stack.
	 * @return Name of node who last dealt with this message.
	 */
	public PeerInfo popSenders(){
		return senders.pop();
	}	

	/**
	 * Get the stack of node names who have passed on this message.
	 * @return Stack of node names who have passed on this message.
	 */
	public Stack<PeerInfo> getSenders(){
		return senders;
	}

	/**
	 * Get a copy of the stack of sender nodes. Useful for creating a new
	 * stack for tracing back.
	 * @return Copy of <code>senders</code> stack.
	 */
	@SuppressWarnings("unchecked")
	public Stack<String> copySenders(){
		return (Stack<String>) senders.clone();
	}

	/**
	 * Set the stack of sending nodes in this message
	 * @param senders Stack of node names who have dealt with this message
	 */
	public void setSenders(Stack<PeerInfo> senders){
		this.senders = senders;
	}

	/**
	 * A representation of this message by showing the sender, type
	 * and receiver of this message.
	 * @return String of the sender, type and receiver of this message.
	 */
	public String toString(){		
		return type.toString() + "<" + sender + ", " + receiver + ">";
	}
}
