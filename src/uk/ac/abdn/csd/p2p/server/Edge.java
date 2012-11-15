/*
 * Edge.java - Represents a link between two Servents in the Server
 * GUI
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

/**
 * The class is a data structure that hold all information regarding the edges / links between nodes on the overlay graph.
 * @see ServerGUI
 * @see Server
 * @author Ahmad Abdullah
 */
public class Edge
{
    private final int mID;
    private String mValue;
    private String mColor;
    
    /**
     * Constructor for {@code Edge}
     * @param x ID for {@code Edge}
     */
    public Edge(int x)
    {
        mID = x;
    }

    /**
     * Method to get ID
     * @return Return ID for {@code Edge}
     */
    public int getID()
    {
        return mID;
    }

    /**
     * Method to set value to {@code Edge}
     * @param mValue The value of {@code Edge}
     */
    public void setValue(String mValue)
    {
        this.mValue = mValue;
    }

    /**
     * Method to get value of {@code Edge}
     * @return {@code String} containing the value
     */
    public String getValue()
    {
        return mValue;
    }

    /**
     * {@code String} representation of {@code Edge}
     * @return Return value of {@code Edge}
     */
    @Override
    public String toString()
    {
        return (this.getValue());
    }
    
    /**
     * Method to set colour for {@code Edge}
     * @param color Name of colour
     */
    public void setColor(String color)
    {
        mColor = color;
    }

    /**
     * Method to get colour of {@code Edge}
     * @return Returns name of colour
     */
    public String getColor()
    {
        return (mColor);
    }
}//end class
