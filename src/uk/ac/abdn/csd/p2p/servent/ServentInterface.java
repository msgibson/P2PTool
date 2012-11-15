/*
 * ServentInterface.java - Interface to enable model-view paradigm
 * for Servent
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

/**
 * This interface is required for views which have file and neighbour lists
 * to be updated automatically. It is the programmer's job to implement this
 * interface into their view class and override the <code>update()</code>
 * method to get the latest files and neighbours from <code>Servent</code>
 * and update any relevant list views. <code>Servent</code> calls
 * <code>update()</code> when it receives a file (passes the parameter
 * <code>Type.files</code>) and when a neighbour has dis/connected
 * (passes the paramter <code>Type.neighbours</code>).
 *
 * @author msgibson
 */
public interface ServentInterface {
	
	/**
	 * Types of updates that a view can update from the <code>Servent</code>.
	 */
    enum Type{
        neighbours,
        files
    }

    /**
     * Method called by <code>Servent</code> when its files or
     * neighbours have been updated.
     * 
     * @param type Enumeration value of which list has been updated.
     */
    void update(Type type);
}
