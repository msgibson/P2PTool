/*
 * VertexPainter.java - Draws the Vertices with their configured
 * colour in the Server GUI
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

import org.apache.commons.collections15.Transformer;

import java.awt.*;

//Here we want to dictate how our nodes are painted. The Metadata which we are reading in from our GraphML file is a color
//value. So we write a vertex painter which we will pass to our VisualizationViewer. This way when Jung goes to draw our
//nodes, it will check this function and paint the nodes accordingly.

/**
 * This class is a helper class that associates itself with the nodes on graph and provides list of colours 
 * can be associated to transform the colour of nodes.
 * @see Vertex
 * @author Ahmad Abudullah
 */

public class VertexPainter implements Transformer<Vertex, Paint>
{
    /**
     * Method to tranform the colour asscoiated with a {@code Vertex}
     * @param v Instance of a {@code Vertex}
     * @return Instance of {@code Paint} object
     */
    public Paint transform(Vertex v) //So for each node that we draw...
    {
        //We check the member variable, mColor, of the node.
        if (v.getColor().equalsIgnoreCase("yellow")) //If the node's mColor value is "yellow" we...
            return (Color.yellow); // Return our color, Color.yellow.
        else if (v.getColor().equalsIgnoreCase("red"))
            return (Color.red);
        else if (v.getColor().equalsIgnoreCase("blue"))
            return (Color.blue);
        else if (v.getColor().equalsIgnoreCase("green"))
            return (Color.green);
        else if (v.getColor().equalsIgnoreCase("orange"))
            return (Color.orange);
        else if (v.getColor().equalsIgnoreCase("pink"))
            return (Color.pink);
        else if (v.getColor().equalsIgnoreCase("black"))
            return (Color.black);
        else if (v.getColor().equalsIgnoreCase("cyan"))
            return (Color.cyan);
        else if (v.getColor().equalsIgnoreCase("dark_grey"))
            return (Color.DARK_GRAY);
        else if (v.getColor().equalsIgnoreCase("grey"))
            return (Color.gray);
        else if (v.getColor().equalsIgnoreCase("light_grey"))
            return (Color.lightGray);
        else if (v.getColor().equalsIgnoreCase("white"))
            return (Color.white);
        else
            return (Color.MAGENTA);
    }
}

