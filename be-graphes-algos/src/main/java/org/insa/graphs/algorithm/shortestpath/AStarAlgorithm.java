package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Label;
import org.insa.graphs.model.Node;
import org.insa.graphs.algorithm.utils.*;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    @Override
    protected Label newLabel(Node node, double cout, ShortestPathData data) {
		return new LabelStar(node, cout,data);
	}
}
