/**
 * Copyright (C) 2012 Uday Kodukula <ukodukula@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>. 
 *
 */
package com.ukodukula.clustering.kmeans;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A MeanPoint class is an extension of the traditional Point class. It is used
 * to model K number of centroids that are fundamental to the concept behind the
 * K-Means clustering algorithm. A Mean Point also manages a list of references
 * to neighboring points that are associated with it at any given moment.
 * 
 * A MeanPoint also has the ability to update it's own internal coordinates with
 * respect to average x and y coordinates of points that are associated with it.
 * This allows the MeanPoint to represent the centroid of k clusters.
 * 
 * @created Nov 4, 2012
 * @author ukodukula
 * 
 */
public class MeanPoint extends Point {

    private List<Point> neighbors;
    private Color color;
    
    public MeanPoint(double x, double y) {
    	super(x, y);

    	Random rand = new Random();
    	
    	float r = rand.nextFloat();
    	float g = rand.nextFloat();
    	float b = rand.nextFloat();

    	this.color = new Color(r,g,b);
    	this.neighbors = new ArrayList<Point>();
    }

    public void addNeighbor(Point neighbor) {
    	this.neighbors.add(neighbor);
    }

    public void removeNeighbor(Point neighbor) {
    	this.neighbors.remove(neighbor);
    }

    public List<Point> getNeighbors() {
    	return this.neighbors;
    }

    public void update() {

	if (this.neighbors.size() == 0) {
	    return;
	}

	double x = 0;
	double y = 0;

	for (Point neighbor : this.neighbors) {
	    x += neighbor.getX();
	    y += neighbor.getY();
	}

	this.setX(x / neighbors.size());
	this.setY(y / neighbors.size());
    }
        
    public Color getColor(){
    	return this.color;
    }
}
