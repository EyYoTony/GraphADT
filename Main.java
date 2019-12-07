import java.util.*;

public class Main {

    private static int[] u;

    public static void main(String[] args) {
	    //test graph sizes 10, 100, 10000, 100000
        directedGraph(10, 45L);
        directedGraph(100, 4950L);
        directedGraph(10000, 49995000L);
        directedGraph(100000, 4999950000L);
    }

    private static void directedGraph(int size, long edges) {
        //create ADT
        //I used a 2d ArrayList because a 2d fixed array took up too much memeory
        //I uses this as a pseudo adjacency matrix
        List<List<Integer>> graph = new ArrayList<>();
        //initialize
        for(int i=0; i<size; i++){
            graph.add(new ArrayList<>());
        }
        //we will use this as an adjacency matrix
        //there will be nC2 edges generated where every i.e. n!/(r!*(n-r)!)
        //I hard coded this because my factorial function didn't work for larger sizes
        //create edges so that all vertex have an edge initially
        for(int i=0; i<size; i++){
            graph.get(i).add(getRandom(size-1));
        }
        edges -= 10;
        //create other edges randomly
        for(int i=0; i<edges; i++){
            int rand1 = getRandom(size-1);
            int rand2 = getRandom(size-1);
            if(!graph.get(rand1).contains(rand2) && rand1 != rand2) {
                graph.get(rand1).add(getRandom(rand2));
                //System.out.println(i+ ": " + rand1 + ", " + rand2);
            } else {
                i--;
            }
        }
        //Verify that all vertex have a edge
        if(verifyEdges(graph))
            System.out.print("Size: "+size+", All nodes have an edge");
        else
            System.out.print("Size: "+size+", Some node does not have an edge");
        //find strongly connected components using Kosaraju’s algorithm
        long startTime = System.nanoTime();
        findSCC(graph);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("");
        System.out.println("It took " + duration + " to find the SCC of the graph");
    }

    //this factorial was too inefficient to run at 100+ factorials
    /*static int factorial(int n)
    {
        if (n == 0)
            return 1;

        return n*factorial(n-1);
    }*/

    private static int getRandom(int max){
        return (int) (Math.random()*max);
    }

    private static boolean verifyEdges(List<List<Integer>> inGraph){
        for(int i=0; i<inGraph.size(); i++){
            if(inGraph.get(i).size()<1)
                return false;
        }
        return true;
    }

    private static void findSCC(List<List<Integer>> inGraph){
        //Kosaraju’s algorithm as described in this wiki - https://en.wikipedia.org/wiki/Kosaraju%27s_algorithm
        //initialize array to indicate if a node has been visited and pseudo stack L
        Main.u = new int[inGraph.size()];
        for(int i=0;i<Main.u.length;i++)
            Main.u[i] = 0;
        //run Visit on all nodes
        for(int i=0;i<inGraph.size();i++) {
            ArrayList<Integer> l = new ArrayList<>();
            if(Main.u[i] == 0){
                System.out.println("");
            }
            Visit(i, inGraph, l);
            for(int x: l){
                System.out.print(x + " ");
            }
        }
    }

    private static void Visit(int node, List<List<Integer>> inGraph, ArrayList<Integer> l){
        if(Main.u[node]==0){
            Main.u[node] = 1;
            for(int i=0; i<inGraph.get(node).size(); i++) {
                Visit(inGraph.get(node).get(i), inGraph, l);
            }
            l.add(0, node);
        }
    }


}
