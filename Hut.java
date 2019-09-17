package com.company;

import java.util.LinkedList;

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

//Hut is a vertex class. It contains an identifier and a linkedList of connected roads.
public class Hut {
    private int ID;
    LinkedList<Road> closeHuts;

    //Constructor
    public Hut(int name) {
        this.ID = name;
        closeHuts = new LinkedList<>();
    }

    //Method that adds one Road to the source Hut and one to the destination Hut.
    //This is needed because the graph is undirected, thus source Hut A is
    //connected to destination Hut B, and destination Hut B is likewise connected
    //to source Hut A.
    public void connect(Hut hut, int cost) {
        closeHuts.add(new Road(cost, hut.getID()));
        hut.closeHuts.add(new Road(cost, this.getID()));
    }

    //Getter
    public int getID() { return ID; }
}
