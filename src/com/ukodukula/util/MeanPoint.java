/**
 * Copyright (C) 2012 Uday Kodukula <ukodukula@gmail.com>
 *
 * This program is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU General Public License as published by the Free 
 * Software Foundation; either version 2 of the License, or (at your option) 
 * any later version.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along 
 * with this program; if not, write to the Free Software Foundation, Inc., 
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA 
 *
 */
package com.ukodukula.util;

import java.util.ArrayList;
import java.util.List;

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

    public MeanPoint(double x, double y) {
	super(x, y);
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
}
