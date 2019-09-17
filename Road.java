package com.company;

/*
Andrew Knox
CSC 221 Data Structures and Algorithms
5/1/2018
This program involves finding the minimum spanning tree on an undirected and weighted graph for multiple graphs.
For this project, vertexes and edges will be called Huts and Roads, and graphs will be called Villages. The program
gives 1 to 100 data sets that need to be parsed by the user, where each data set is a Village. All information about
the Village is included in the data set (connections, # of roads, # of huts). Each road has a weight and the user has
to find the minimum cost of all the roads needed such that all huts can be traveled to from any hut in the village.
Additionally, the user must write an algorithm such that, given the maximum amount of input, all Villages’ minimum
costs can be found in less than a minute.
 */

//Road connects two villages, has a cost, and an end. Road is an edge class.
public class Road implements Comparable<Road>{
    private int cost;
    private int end;

    //Constructor
    public Road(int cost, int end) {
        this.cost = cost;
        this.end = end;
    }

    //Implements comparable so the priority queue of roads can function
    @Override
    public int compareTo(Road rd) {
        return Integer.compare(this.cost, rd.getCost());
    }

    //Getters follow

    int getCost() { return cost; }

    int getEnd() { return end; }
}
