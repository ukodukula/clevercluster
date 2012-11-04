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
package com.ukodukula.tests.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.ukodukula.util.MeanPoint;
import com.ukodukula.util.Point;

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