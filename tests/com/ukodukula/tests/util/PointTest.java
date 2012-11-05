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