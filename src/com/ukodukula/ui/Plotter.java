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
package com.ukodukula.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ukodukula.util.KMeans;
import com.ukodukula.util.MeanPoint;
import com.ukodukula.util.Point;

/**
 * This class is a used to render a GUI that renders points and demonstrates the
 * K-Means clustering algorithm in action.
 * 
 * @created Nov 4, 2012
 * @author ukodukula
 * 
 */
@SuppressWarnings("serial")
public class Plotter extends JPanel {

    public void paintComponent(Graphics g) {
	super.paintComponent(g);

	Graphics2D g2d = (Graphics2D) g;

	ArrayList<Point> points = new ArrayList<Point>();

	for (int i = 0; i < 1000; i++) {
	    double randX = (Math.random() * 1000) % 600;
	    double randY = (Math.random() * 1000) % 600;
	    points.add(new Point(randX, randY));
	}

	KMeans km = new KMeans(5, points);

	List<MeanPoint> data = km.run();

	for (MeanPoint meanPoint : data) {

	    double maxDist = 0;

	    for (Point point : meanPoint.getNeighbors()) {
		if (point.distance(meanPoint) > maxDist) {
		    maxDist = point.distance(meanPoint);
		}
		g2d.setColor(Color.gray);
		int x = (int) point.getX();
		int y = (int) point.getY();
		g2d.drawArc(x - 1, y - 1, 2, 2, 0, 360);
		g2d.drawLine(x, y, (int) meanPoint.getX(),
			(int) meanPoint.getY());
	    }

	    g2d.setColor(Color.red);
	    int meanx = (int) meanPoint.getX();
	    int meany = (int) meanPoint.getY();
	    int maxRad = (int) maxDist / 2;

	    g2d.drawArc(meanx - 1, meany - 1, 2, 2, 0, 360);
	    g2d.drawArc(meanx - maxRad, meany - maxRad, (int) maxDist,
		    (int) maxDist, 0, 360);
	}
    }

    public static void main(String[] args) {
	Plotter plot = new Plotter();
	JFrame frame = new JFrame("Uday's K-Means Fun!");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(plot);
	frame.setSize(600, 600);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
    }
}