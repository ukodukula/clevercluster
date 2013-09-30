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

import java.util.ArrayList;
import java.util.List;

/**
 * Core class that uses MeanPoints (centroids) and Points to implement the
 * K-Means algorithm. It allows clients to step through each iteration and
 * retrieve the latest state of the data and centroids.
 * 
 * @created Nov 4, 2012
 * @author ukodukula
 * 
 */
public class KMeans {

    private List<Point> points;
    private List<MeanPoint> meanPoints;
    private boolean converged = false;

    public KMeans(int k, List<Point> points) {

	getFreshMeans(k);
	this.points = points;
    }
    
    public void getFreshMeans(int k){
	// Initialize K Random Points.
	this.meanPoints = new ArrayList<MeanPoint>();

	for (int i = 0; i < k; i++) {
	    double randX = Math.random() * 600;
	    double randY = Math.random() * 600;
	    this.meanPoints.add(new MeanPoint(randX, randY));
	}
	
	// Since this is a fresh set of Means, they obviously haven't converged so reset
	// from prior runs.
	this.converged = false;
    }

    public boolean hasConverged() {
	return this.converged;
    }

    public List<MeanPoint> step() {

	tellPointsToFindMeans();

	double preAggX = 0;
	double preAggY = 0;
	double postAggX = 0;
	double postAggY = 0;

	for (MeanPoint meanPoint : this.meanPoints) {
	    preAggX += meanPoint.getX();
	    preAggY += meanPoint.getY();
	}

	updateMeans();

	for (MeanPoint meanPoint : this.meanPoints) {
	    postAggX += meanPoint.getX();
	    postAggY += meanPoint.getY();
	}
	this.converged = (preAggX == postAggX) && (preAggY == postAggY);
	return this.meanPoints;
    }

    public void tellPointsToFindMeans() {

	for (Point point : this.points) {

	    double closestDist = -1;
	    MeanPoint closestMean = null;

	    for (MeanPoint meanPoint : this.meanPoints) {
		double curDistance = point.distance(meanPoint);

		if ((curDistance < closestDist) || closestDist == -1) {
		    closestDist = curDistance;
		    closestMean = meanPoint;
		}
	    }

	    // To preserve simplicity, just purge the association of this point
	    // and all mean points. We can then simply re association this point
	    // with the current closest mean point.
	    for (MeanPoint meanPoint : this.meanPoints) {
		meanPoint.removeNeighbor(point);
	    }

	    closestMean.addNeighbor(point);
	}
    }

    public void updateMeans() {
	for (MeanPoint meanPoint : this.meanPoints) {
	    meanPoint.update();
	}
    }
    
    public void setPoints(List<Point> points){
	this.points = points;
    }
}