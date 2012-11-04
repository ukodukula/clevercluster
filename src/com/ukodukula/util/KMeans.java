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
 * Description of what this class does.
 * 
 * @created Nov 4, 2012
 * @author ukodukula
 * 
 */
public class KMeans {

    private List<Point> points;
    private List<MeanPoint> meanPoints;

    public KMeans(int k, List<Point> points) {

	// Initialize K Random Points.
	this.meanPoints = new ArrayList<MeanPoint>();
	this.points = points;

	for (int i = 0; i < k; i++) {
	    double randX = Math.random();
	    double randY = Math.random();
	    this.meanPoints.add(new MeanPoint(randX, randY));
	}
    }

    public List<MeanPoint> run() {

	boolean converged = false;
	// Keep running the algorithm until all points are comfortable with the
	// cluster that they belong to. Convergence in this context means that
	// the centroids of all clusters have not updated themselves, which
	// means that points have not moved between clusters.
	while (!converged) {

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

	    converged = (preAggX == postAggX) && (preAggY == postAggY);
	}

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
}