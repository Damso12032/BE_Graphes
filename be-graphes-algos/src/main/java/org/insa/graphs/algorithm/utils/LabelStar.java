package org.insa.graphs.algorithm.utils;

import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;
import org.insa.graphs.algorithm.*;


public class LabelStar extends Label implements Comparable<Label> {
	private float distmin;

	public LabelStar(Node noeud, double cout, ShortestPathData data) {
		super(noeud,cout);

		if (data.getMode() == AbstractInputData.Mode.LENGTH) {
			this.distmin = (float)Point.distance(noeud.getPoint(),data.getDestination().getPoint());
		}
		else {
			ShortestPathData bla = new ShortestPathData(data.getGraph(),noeud,data.getDestination(),data.getArcins());
			int vitesse = bla.getMaximumSpeed();
			if(vitesse==-1) {
				vitesse = bla.getGraph().getGraphInformation().getMaximumSpeed() ;
			}
			this.distmin = (float)Point.distance(noeud.getPoint(),data.getDestination().getPoint())/(vitesse*1000.0f/3600.0f);
		}
	}
	
	@Override
	public double getTotalCost() {
		return getCost()+distmin;
	}

}
