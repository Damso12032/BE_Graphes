package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.ComparisonFailure;

public class TestDijkstra {
	//private static DijkstraAlgorithm dj;
	//private static BellmanFordAlgorithm bf;
	private static ArrayList<DijkstraAlgorithm> djs=new ArrayList<DijkstraAlgorithm>();
	private static ArrayList<BellmanFordAlgorithm> bfs=new ArrayList<BellmanFordAlgorithm>();
	private static ArrayList<DijkstraAlgorithm> djsC=new ArrayList<DijkstraAlgorithm>();
	private static ArrayList<BellmanFordAlgorithm> bfsC=new ArrayList<BellmanFordAlgorithm>();
	private static ArrayList<DijkstraAlgorithm> djf=new ArrayList<DijkstraAlgorithm>();
	private static ArrayList<BellmanFordAlgorithm> bff=new ArrayList<BellmanFordAlgorithm>();
	private static ArrayList<DijkstraAlgorithm> djfC=new ArrayList<DijkstraAlgorithm>();
	private static ArrayList<BellmanFordAlgorithm> bffC=new ArrayList<BellmanFordAlgorithm>();
	private static ArrayList<DijkstraAlgorithm> djP=new ArrayList<DijkstraAlgorithm>();
	private static ArrayList<BellmanFordAlgorithm> bfP=new ArrayList<BellmanFordAlgorithm>();
	private static DijkstraAlgorithm djaNF1 ;
	private static DijkstraAlgorithm djaNF2;
	private static BellmanFordAlgorithm bfANF1 ;
	private static BellmanFordAlgorithm bfANF2;
	
	
	 @BeforeClass
	 public static void initAll() throws IOException{
	// Visit these directory to see the list of available files on Commetud.
		 final String mapName = "/home/sroche/Bureau/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
		 //final String pathName = "/home/sroche/Bureau/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31insa_rangueil_insa.path";
	
	    // Create a graph reader.
	    final GraphReader reader = new BinaryGraphReader(
	       new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	    // TODO: Read the graph.
	    final Graph graph = reader.read();
	
	
	    // TODO: Create a PathReader.
	    //final PathReader pathReader = new BinaryPathReader(new DataInputStream(new BufferedInputStream(new FileInputStream(pathName))));
	
	    // TODO: Read the path.
	    //final Path path = pathReader.readPath(graph);
	    List<Node> nodes = graph.getNodes();
	    //Creation des shortest paths
	    ArcInspector arc0=ArcInspectorFactory.getAllFilters().get(0);
	    ArcInspector arc1=ArcInspectorFactory.getAllFilters().get(1);
	    ArcInspector arc2=ArcInspectorFactory.getAllFilters().get(2);
	    ArcInspector arc3=ArcInspectorFactory.getAllFilters().get(3);
	    ArcInspector arc4=ArcInspectorFactory.getAllFilters().get(4);
	    
	    for(int i=0;i<10;i++) {
	    	int x=(int)(Math.random()*10);
	    	//System.out.println(x);
	    	//System.out.println(graph.size());
	    	Node nodeOrigin=nodes.get((x+i)%graph.size());
	    	Node nodeDestination=nodes.get((x*10+i)%graph.size());
	    	ShortestPathData data0=new ShortestPathData(graph,nodeOrigin,nodeDestination,arc0);
	    	ShortestPathData data1=new ShortestPathData(graph,nodeOrigin,nodeDestination,arc1);
	    	ShortestPathData data2=new ShortestPathData(graph,nodeOrigin,nodeDestination,arc2);
	    	ShortestPathData data3=new ShortestPathData(graph,nodeOrigin,nodeDestination,arc3);
	    	ShortestPathData data4=new ShortestPathData(graph,nodeOrigin,nodeDestination,arc4);
	    	DijkstraAlgorithm dja0 = newDijkstraAlgorithm(data0);
	    	DijkstraAlgorithm dja1 = newDijkstraAlgorithm(data1);
	    	DijkstraAlgorithm dja2 = newDijkstraAlgorithm(data2);
	    	DijkstraAlgorithm dja3 = newDijkstraAlgorithm(data3);
	    	DijkstraAlgorithm dja4 = newDijkstraAlgorithm(data4);
	    	BellmanFordAlgorithm bfa0 = new BellmanFordAlgorithm(data0);
	    	BellmanFordAlgorithm bfa1 = new BellmanFordAlgorithm(data1);
	    	BellmanFordAlgorithm bfa2 = new BellmanFordAlgorithm(data2);
	    	BellmanFordAlgorithm bfa3 = new BellmanFordAlgorithm(data3);
	    	BellmanFordAlgorithm bfa4 = new BellmanFordAlgorithm(data4);
	    	while ((nodeOrigin.compareTo(nodeDestination)==0)||!dja0.run().isFeasible()||!bfa0.run().isFeasible()) {
	    		x=(int)(Math.random()*10);
	    		nodeOrigin=nodes.get((x+i)%graph.size());
		    	nodeDestination=nodes.get((x*10+i)%graph.size());
		    	data0=new ShortestPathData(graph,nodeOrigin,nodeDestination,arc0);
		    	data1=new ShortestPathData(graph,nodeOrigin,nodeDestination,arc1);
		    	data2=new ShortestPathData(graph,nodeOrigin,nodeDestination,arc2);
		    	data3=new ShortestPathData(graph,nodeOrigin,nodeDestination,arc3);
		    	data4=new ShortestPathData(graph,nodeOrigin,nodeDestination,arc4);
		    	dja0 = newDijkstraAlgorithm(data0);
		    	dja1 = newDijkstraAlgorithm(data1);
		    	dja2 = newDijkstraAlgorithm(data2);
		    	dja3 = newDijkstraAlgorithm(data3);
		    	dja4 = newDijkstraAlgorithm(data4);
		    	bfa0 = new BellmanFordAlgorithm(data0);
		    	bfa1 = new BellmanFordAlgorithm(data1);
		    	bfa2 = new BellmanFordAlgorithm(data2);
		    	bfa3 = new BellmanFordAlgorithm(data3);
		    	bfa4 = new BellmanFordAlgorithm(data4);
	        	
	        } 
        	djs.add(dja0);
        	bfs.add(bfa0);
        	djsC.add(dja1);
        	bfsC.add(bfa1);
        	djf.add(dja2);
        	bff.add(bfa2);
        	djfC.add(dja3);
        	bffC.add(bfa3);
        	djP.add(dja4);
        	bfP.add(bfa4);
	        
	    }
	    //créer un chemin vide
	    Node nodeOriginNF1=null;
    	Node nodeDestinationNF1=null;
    	ShortestPathData dataNF1=new ShortestPathData(graph,nodeOriginNF1,nodeDestinationNF1,arc0);
    	//crer un chemin contenant qu'un noeud
	    Node nodeOriginNF2=nodes.get(graph.size()-1);
    	ShortestPathData dataNF2=new ShortestPathData(graph,nodeOriginNF2,nodeOriginNF2,arc0);
    	djaNF1 = newDijkstraAlgorithm(dataNF1);
    	djaNF2 = newDijkstraAlgorithm(dataNF2);
    	bfANF1 = new BellmanFordAlgorithm(dataNF1);
    	bfANF2 = new BellmanFordAlgorithm(dataNF2);
   
    	
    	//System.out.println("ANF1 valide :"+bfANF1.run().getPath().isValid());
	 }
	 //ShortestPathSolution djsol = dj.run();
	 //ShortestPathSolution bfsol = bf.run();
	 @Test
	public void testdistfaisable() {
		 	for(int i=0;i<10;i++) {
		 		assertEquals(djs.get(i).run().getPath().getLength(), bfs.get(i).run().getPath().getLength(),1e-6);
		 	}
			/*assertEquals(djs.get(0).run().getPath().getLength(), bfs.get(0).run().getPath().getLength(),1e-6);//||
			assertEquals(djs.get(1).run().getPath().getLength(), bfs.get(1).run().getPath().getLength(),1e-6);
			assertEquals(djs.get(2).run().getPath().getLength(), bfs.get(2).run().getPath().getLength(),1e-6);
			assertEquals(djs.get(3).run().getPath().getLength(), bfs.get(3).run().getPath().getLength(),1e-6);
			assertEquals(djs.get(4).run().getPath().getLength(), bfs.get(4).run().getPath().getLength(),1e-6);//|
			assertEquals(djs.get(5).run().getPath().getLength(), bfs.get(5).run().getPath().getLength(),1e-6);
			assertEquals(djs.get(6).run().getPath().getLength(), bfs.get(6).run().getPath().getLength(),1e-6);
			assertEquals(djs.get(7).run().getPath().getLength(), bfs.get(7).run().getPath().getLength(),1e-6);
			assertEquals(djs.get(8).run().getPath().getLength(), bfs.get(8).run().getPath().getLength(),1e-6);
			assertEquals(djs.get(9).run().getPath().getLength(), bfs.get(9).run().getPath().getLength(),1e-6);
			*/
		
	}
	 
	 @Test
	 public void testdistCar() {
		 for(int i=0;i<10;i++) {
			 if(djsC.get(i).run().isFeasible()) {
		 		assertEquals(djsC.get(i).run().getPath().getLength(), bfsC.get(i).run().getPath().getLength(),1e-6);
			 }
			 else {
				 assertFalse(djsC.get(i).run().isFeasible());
			 }
			 }
	 }
	 @Test
	 public void testfastfaisable() {
		 	for(int i=0;i<10;i++) {
		 		assertEquals(djf.get(i).run().getPath().getMinimumTravelTime(), bff.get(i).run().getPath().getMinimumTravelTime(),1e-6);
		 	}
	 }
	 
	 @Test
	 public void testfastCar() {
		 	for(int i=0;i<10;i++) {
		 		if(djfC.get(i).run().isFeasible()) {
		 			assertEquals(djfC.get(i).run().getPath().getMinimumTravelTime(), bffC.get(i).run().getPath().getMinimumTravelTime(),1e-6);
		 		}
		 		else {
		 			assertFalse(djfC.get(i).run().isFeasible());
		 		}
		 	}
	 }
	 
	 @Test
	 public void testPieton() {
		 	for(int i=0;i<10;i++) {
		 		if(djP.get(i).run().isFeasible()) {
		 			assertEquals(djP.get(i).run().getPath().getMinimumTravelTime(), bfP.get(i).run().getPath().getMinimumTravelTime(),1e-6);
		 		}
		 		else {
		 			assertFalse(djP.get(i).run().isFeasible());
		 		}
	 		}
	 }
	 
	 @Test
	 public void testavec1noeud() {
		 assertTrue(djaNF2.run().isFeasible());
	 }
	 
	 @Test
	 public void testvide() {
		 assertTrue(djaNF1.run().isFeasible());
	 }
	 
	 public static DijkstraAlgorithm newDijkstraAlgorithm(ShortestPathData data) {
		 return new DijkstraAlgorithm(data);
	 }
	 
	
}
