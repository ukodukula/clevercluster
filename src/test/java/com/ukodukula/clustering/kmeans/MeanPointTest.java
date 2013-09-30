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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.ukodukula.clustering.kmeans.MeanPoint;
import com.ukodukula.clustering.kmeans.Point;

/**
 * This class is a unit test that ensures that desired behavior for a MeanPoint.
 * 
 * @created Nov 4, 2012
 * @author ukodukula
 * 
 */
public class MeanPointTest {

    @Test
    public void testAddNeighbor() {
	MeanPoint kp = new MeanPoint(1, 1);
	assertEquals(kp.getNeighbors().size(), 0, 0);
	Point newPoint = new Point(1, 1);
	kp.addNeighbor(newPoint);
	assert (kp.getNeighbors().contains(newPoint));
    }

    @Test
    public void testRemoveNeighbor() {
	MeanPoint kp = new MeanPoint(1, 1);
	Point newPoint = new Point(1, 1);
	kp.addNeighbor(newPoint);
	assertEquals(kp.getNeighbors().size(), 1, 0);
	kp.removeNeighbor(newPoint);
	assertFalse(kp.getNeighbors().contains(newPoint));
    }

    @Test
    public void testUpdate() {
	MeanPoint kp = new MeanPoint(1, 1);
	kp.addNeighbor(new Point(10, 10));
	kp.addNeighbor(new Point(20, 20));
	kp.addNeighbor(new Point(30, 30));
	kp.update();

	assertEquals(20, kp.getX(), 0);
	assertEquals(20, kp.getY(), 0);
    }
}