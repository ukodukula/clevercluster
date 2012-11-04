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

import org.junit.Test;

import com.ukodukula.util.Point;

/**
 * This class is a unit test that ensures that desired behavior for a Point.
 * 
 * @created Nov 4, 2012
 * @author ukodukula
 * 
 */
public class PointTest {

    @Test
    public void testDistance() {
	Point p1 = new Point(1, 1);
	Point p2 = new Point(2, 2);
	Point p3 = new Point(121, 14);
	Point p4 = new Point(209, 240);

	double distance1 = p1.distance(p2);
	double distance2 = p3.distance(p4);

	assertEquals(distance1, Math.sqrt(2), 0);
	assertEquals(distance2, Math.sqrt(7744 + 51076), 0);
    }
}