package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Label;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        // TODO:
        Graph graph = data.getGraph();
        final int nbNodes = graph.size();
        
     // Initialize array of distances.
        double[] distances = new double[nbNodes];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        
        BinaryHeap<Label> heap = new BinaryHeap<Label>();
        ArrayList<Label> lablist = new ArrayList<Label>();
        
     // Notify observers about the first event (origin processed).
        notifyOriginProcessed(data.getOrigin());
        
        
        distances[data.getOrigin().getId()] = 0;
        for (Node node : graph.getNodes()) {
        	if(node==data.getOrigin()) {
        		Label labelnode = new Label(node, 0);
        		lablist.add(node.getId(),labelnode);
        		heap.insert(labelnode);
        	}
        	else {
        		Label labelnode = new Label(node,  Double.POSITIVE_INFINITY);
        		lablist.add(node.getId(),labelnode);
        	}
        }
        
        boolean nonmarque=true;
        Arc[] predecessorsArcs = new Arc[graph.size()];
        while(nonmarque) {
        	Label currentnode = heap.findMin();
        	currentnode.setMarque(true);
        	System.out.println(currentnode.getCost());
        	notifyNodeMarked(currentnode.getSommet_courant());
        	for (Arc succes : currentnode.getSommet_courant().getSuccessors()) {
        		if (lablist.get(succes.getDestination().getId()).isMarque()) {
        			continue;
        		}
        		else {
        			double oldcost = lablist.get(succes.getDestination().getId()).getCost();
        			double newcost = lablist.get(succes.getOrigin().getId()).getCost()+data.getCost(succes);
        			if(newcost<oldcost) {
        				lablist.get(succes.getDestination().getId()).setCout(newcost);
        				
        				lablist.get(succes.getDestination().getId()).setPere(currentnode.getSommet_courant());
        				predecessorsArcs[succes.getDestination().getId()]=succes;
        				if(oldcost==Double.POSITIVE_INFINITY) {
        					heap.insert(lablist.get(succes.getDestination().getId()));
        					notifyNodeReached(succes.getDestination());
        				}
        				else {
        					//on le met à jour dans le heap
        					heap.remove(lablist.get(succes.getDestination().getId()));
        					heap.insert(lablist.get(succes.getDestination().getId()));
        				}
        			}
        		}
        	}
        	heap.remove(currentnode);
        	if(heap.isEmpty()) {
        		nonmarque=false;
        	}
        }
        
        
        
        if(lablist.get(data.getDestination().getId()).getCost()==Double.POSITIVE_INFINITY) {
        	solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {
        	notifyDestinationReached(data.getDestination());
        	Node dest = data.getDestination();
        	ArrayList<Arc> arcs = new ArrayList<>();
        	Arc arc = predecessorsArcs[dest.getId()];
            while (arc != null) {
                arcs.add(arc);
                arc = predecessorsArcs[arc.getOrigin().getId()];
            }

            // Reverse the path...
            Collections.reverse(arcs);

            // Create the final solution.
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));  
				
        }
        
        
        return solution;
    }
    
    
    
    

}
