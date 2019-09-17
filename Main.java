package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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


public class Main {

    /*
     * Time complexity variables:
     * E = edges = Roads, 0 < E <= 150
     * V = vertexes = Huts, 1 < V <= 26
     * N = # of Roads for a given Hut, 0 < N <= 15
    */


    /*
     * Method PrimsAlg uses Prim's Algorithm to find the minimum spanning tree
     * of an ArrayList of Huts (vertexes) where each specific Hut has a LinkedList
     * of Roads (edges) that are attached to it. Method creates a PriorityQueue
     * of Roads that contains any Road that connects a known Hut to other Huts.
     * Method retrieves head of PriorityQueue: if this Road points to a previously
     * known Hut, the cost of the Road is ignored because there already exists a
     * cheaper Road that connects to the known Hut.
     *
     * Complexity: O(EN(LogE))
     */
    public static int PrimsAlg(ArrayList<Hut> huts, int roads) {        //Method time complexity: O(EN(LogE))
        int mincost = 0;
        int knownHuts = 1;
        PriorityQueue<Road> unKnown = new PriorityQueue<>(roads);       //O(E)
        //Lets us know if an individual Hut is known or not
        boolean[] known = new boolean[huts.size()];                     //O(E + V) == O(E)
        known[0] = true;
        Hut hut = huts.get(0);
        unKnown.addAll(hut.closeHuts);                                  //O(N(LogE))

        //Loops O(E) times
        while (!unKnown.isEmpty() || knownHuts < huts.size()) {         //O(N(LogE)+E(loop complexity))
            Road road = unKnown.poll();                                 //O(Log(E))
            int nextHut = road.getEnd();
            //Checks if path to Hut is already known
            if (known[nextHut]) {
                continue;
            }
            knownHuts++;
            known[nextHut] = true;

            //When Road from Hut A to Hut B is added to the minimum spanning
            //tree, the Road from Hut B to Hut A needs to be removed from
            //the PriorityQueue to avoid any unnecessary poll() calls
            Road duplicate = new Road(road.getCost(), hut.getID());
            huts.get(nextHut).closeHuts.remove(duplicate);              //O(N)

            unKnown.addAll(huts.get(nextHut).closeHuts);                //O(N*Log(E)) <- largest time complexity in loop
            mincost += road.getCost();
        }                                                               //O(N(LogE) + E(N(LogE))) ==
                                                                        //      O((N(LogE))(1 + E)) ==
                                                                        //      O(E*N(LogE)) <- method's time complexity

        return mincost;
    }


    public static void main(String[] args) throws FileNotFoundException {

        int roads = 0;
        //Holds all Hut objects
        ArrayList<Hut> huts = new ArrayList<>();
        Scanner scan = new Scanner(new File("src/com/company/inp1.txt"));
        int numHuts = scan.nextInt();

        //Loop for different villages
        while (numHuts != 0) {                                          //O(G)
            for (int i = 0; i < numHuts; i++) {                         //Declares all Huts, O(G(V))
                huts.add(new Hut(i));
            }

            //Initializes huts and roads with necessary information
            for (int i = 1; i < numHuts; i++) {                         //O(G(2V)) == O(G(V))
                Hut source = huts.get(Alph(scan.next().charAt(0)));
                int newRoads = scan.nextInt();

                //Loads each Hut's LinkedList with Roads that connect the Hut to others
                for (int j = 0; j < newRoads; j++) {                    //O(G(V*E))
                    Hut dest = huts.get(Alph(scan.next().charAt(0)));
                    int cost = scan.nextInt();
                    source.connect(dest, cost);
                    roads += 2;
                }
            }

            int minCost = PrimsAlg(huts, roads);                        //O(G(VE+EN(Log(E)))) == O(G(E(V+N(Log(E))))) ==
                                                                        //O(GE(V+NLogE))
                                                                        //Thus main time complexity is (O(GE(V+NLogE)))
            System.out.println(minCost);

            roads = 0;
            huts.clear();
            numHuts = scan.nextInt();
        }
    }

    //Method that converts a Hut's identification letter (A, B, ...) to integer (0, 1, ...)
    private static int Alph(char c) {
        return c - 65;
    }
}
