package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.*;

import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.junit.Test;

public class TestAStar extends TestDijkstra{

	 public static DijkstraAlgorithm newDijkstraAlgorithm(ShortestPathData data) {
		 return new AStarAlgorithm(data);
	 }

}
