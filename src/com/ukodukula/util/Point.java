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

/**
 * A Point class represents a point in a 2 dimensional space. It contains basic
 * accessor and mutator methods to manage the x and y coordinates of a point. It
 * also provides an ability to determine the Euclidean distance between this
 * point and another point in a 2 dimensional space.
 * 
 * @created Nov 4, 2012
 * @author ukodukula
 * 
 */
public class Point {

    private double x, y;

    public Point(double x, double y) {
	this.x = x;
	this.y = y;
    }

    public double getX() {
	return this.x;
    }

    public double getY() {
	return this.y;
    }

    public void setX(double x) {
	this.x = x;
    }

    public void setY(double y) {
	this.y = y;
    }

    public double distance(Point dest) {
	return Math.sqrt(Math.pow((dest.getX() - getX()), 2)
		+ Math.pow((dest.getY() - getY()), 2));
    }
}