/*
 * Vertex.java - Representation of a Servent in the Server GUI
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

import java.awt.geom.Point2D;

/**
 * This class is a data structure that holds all information regarding a node that is created on an overlay graph.
 * @see ServerGUI
 * @see Server
 * @author Ahmad Abdullah
 */
public class Vertex {
    private String nodeName;
    private String mColor;
    private int pingCount=0;
    private int pongCount=0;
    private int queryCount=0;
    private int queryHitCount=0;
    private long creationTime=0;
    private Point2D position;
    
    /**
     * Constructor for Vertex class
     * @param nodeName Name of a node
     */
    public Vertex(String nodeName)
    {
        this.nodeName=nodeName;
        setCreationTime();
    }
    
    public Vertex(String nodeName, Point2D position){
    	this.nodeName = nodeName;
    	this.position = position;
    	setCreationTime();
    }
    
    /**
     * Method to get name of a node
     * @return Return name of a node
     */
    public String getNodeName()
    {
        return nodeName;
    }
    
    public void setNodeName(String name){
    	this.nodeName = name;
    }
    
    public Point2D getPosition(){
    	return position;
    }
    
    /**
     * Method to get reference to the object {@code Vertex}
     * @param name Name of a node
     * @return Returns reference to the object
     */
    public Vertex getReference(String name)
    {
        return this;
    }
    
    /**
     * String representation for {@code Vertex}
     * @return {@code String} containing name of the node
     */
    public String toString()
    {
    	return nodeName;
    }
    
    /**
     * Method to set a colour to for a {@code Vertex}
     * @param color Name of colour
     */
    public void setColor(String color)
    {
        mColor = color;
    }

    /**
     * Method to get current colour of a {@code Vertex}
     * @return Returns name of colour associated with {@code Vertex}
     */
    public String getColor()
    {
        return (mColor);
    }
    
    /**
     * Method to keep count of number of Ping sent by {@code Vertex}
     */
    public void incrementPing()
    {
        pingCount = pingCount + 1;
    }
    
    /**
     * Method to keep count of number of Pong received by {@code Vertex}
     */
    public void incrementPong()
    {
        pongCount = pongCount + 1;
    }
    
    /**
     * Method to keep count of number of Query sent and received by {@code Vertex}
     */
    public void incrementQuery()
    {
        queryCount = queryCount +1;
    }
    
    /**
     * Method to keep count of number of QueryHit sent by {@code Vertex}
     */
    public void incrementQueryHit()
    {
        queryHitCount = queryHitCount + 1;
    }
    
    /**
     * Method to get Ping Count
     * @return current Ping count
     */
    public int getPingCount()
    {
        return pingCount;
    }
    
    /**
     * Method to get Pong Count
     * @return current Pong count
     */
    public int getPongCount()
    {
        return pongCount;
    }
    
    /**
     * Method to get Query Count
     * @return current Query count
     */
    public int getQueryCount()
    {
        return queryCount;
    }
    
    /**
     * Method to get QueryHit Count
     * @return current QueryHit count
     */
    public int getQueryHitCount()
    {
        return queryHitCount;
    }
    
    /**
     * Method to prepare messaging activity statistics
     * @return {@code String} containing the count of each messaging activity with respect to this {@code Vertex}
     */
    public String getMessageStats()
    {
        String ping = "Number of PING = "+getPingCount()+"\n";
        String pong = "Number of PONG = "+getPongCount()+"\n";
        String query = "Number of QUERY = "+getQueryCount()+"\n";
        String queryHit = "Number of QUERYHIT = "+getQueryHitCount();
        
        return ping + pong + query + queryHit;
    }
    
    /**
     * Method to set creation time of {@code Vertex}
     */
    public final void setCreationTime()
    {
        creationTime=System.currentTimeMillis();
    }
    
    /**
     * Method to get creation time of {@code Vertex}
     * @return Returns creation time in {@code Long} format
     */
    public long getCreationTime()
    {
        return creationTime;
    }
    
    /**
     * Method to compute creation time in HH:MM:SS format. This
     * method has been adapted from http://www.coderanch.com/t/378404/java/java/Convert-milliseconds-time 
     * @return {@code String} containing creation time of {@code Vertex}
     */
    public String getTimeCreate()
    {
        long time = getCreationTime() / 1000;  
        String seconds = Integer.toString((int)(time % 60));  
        String minutes = Integer.toString((int)((time/60) % 60));
        String hours = Integer.toString((int)((time / 3600) % 24) + 1);
        for (int i = 0; i < 2; i++) 
        {  
            if (seconds.length() < 2) {  
                seconds = "0" + seconds;  
            }  
            if (minutes.length() < 2) {  
                minutes = "0" + minutes;  
            }  
            if (hours.length() < 2) {  
                hours = "0" + hours;  
            }  
        }
        
        return hours+":"+minutes+":"+seconds;
    }
    
}//end class
