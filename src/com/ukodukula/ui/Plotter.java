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
package com.ukodukula.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    private List<MeanPoint> data;
    private KMeans km;

    public void initKMeans(KMeans km) {
	this.km = km;
	this.stepKMeans();
    }

    public void stepKMeans() {
	this.data = km.step();
	if (!km.hasConverged()) {
	    this.repaint();
	}
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);

	Graphics2D g2d = (Graphics2D) g;

	for (MeanPoint meanPoint : this.data) {

	    double maxDist = 0;

	    for (Point point : meanPoint.getNeighbors()) {
		if (point.distance(meanPoint) > maxDist) {
		    maxDist = point.distance(meanPoint);
		}
		g2d.setColor(Color.gray);
		int x = (int) point.getX();
		int y = (int) point.getY();
		g2d.drawArc(x - 1, y - 1, 2, 2, 0, 360);
	    }

	    g2d.setColor(Color.red);
	    int meanx = (int) meanPoint.getX();
	    int meany = (int) meanPoint.getY();
	    int maxRad = (int) maxDist / 2;

	    g2d.drawArc(meanx - 1, meany - 1, 2, 2, 0, 360);
	    g2d.drawArc(meanx - maxRad, meany - maxRad, (int) maxDist,
		    (int) maxDist, 0, 360);
	}

	this.stepKMeans();
    }

    public static void main(String[] args) {
	Plotter plot = new Plotter();

	ArrayList<Point> points = new ArrayList<Point>();

	Random rand = new Random();

	for (int i = 0; i < 10000; i++) {
	    double randX = (rand.nextGaussian() * 100 + 300) % 600;
	    double randY = (rand.nextGaussian() * 100 + 300) % 600;
	    points.add(new Point(randX, randY));
	}

	KMeans km = new KMeans(8, points);

	plot.initKMeans(km);

	JFrame frame = new JFrame("Uday's K-Means Fun!");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(plot);
	frame.setSize(600, 600);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
    }
}