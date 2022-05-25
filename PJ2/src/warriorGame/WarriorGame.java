package warriorGame;

import java.util.*;

public class WarriorGame {

    private List<Edge>[] graph; // Graph representation, array of lists of predecessor
    private final int numberOfChallenges; // challenges that exist

    public WarriorGame(int C) {
        this.createDT(C);
        this.numberOfChallenges = C;
    }

    /**
     * adds an edge to the graph
     * @param c1
     * @param c2
     * @param v
     */
    public void addEdge(int c1, int c2, int v) {
        graph[c2].add(new Edge(c1, v));
    }

    /**
     * method that returns the final output to the problem
     * @param s - node that initializes the game
     * @param w - final node
     * @param initialEnergy - energy that the player starts with
     * @return
     */
    public long processGame(int s, int w, int initialEnergy) {
        long resultEnergy = bellmanFord(s, w, graph.length, initialEnergy);

        if(resultEnergy == initialEnergy + 1)
            return initialEnergy + 1;

        long finalEnergy = initialEnergy - resultEnergy;

        return Math.max(finalEnergy, 0);
    }

    /**
     * bellmanFord algorithm
     *
     * @param initialNode - node that initializes the game
     * @param wizard - final node
     * @param size - size of the graph -> numNodes
     * @param initialEnergy - energy that the player starts with
     * @return
     */
    private long bellmanFord(int initialNode, int wizard, int size, int initialEnergy){
        long[] length = new long[size];
        int[] via = new int[size];

        for(int i = 0; i < size; i++)
            length[i] = Long.MAX_VALUE;

        length[wizard] = 0;
        via[wizard] = wizard;

        boolean changes = false;

        for(int i = 1; i < size; i++){
            changes = updateLengths(length, via);

            if(!changes)
                break;
        }

        // Negative - weight cycles detection
        if(changes && updateLengths(length, via))
            return initialEnergy + 1;
        else
            return length[initialNode];
    }

    /**
     * update lengths, cont of the bellmanFord alg.
     * @param len - array with points per node
     * @param via - array with the node connecting to that node
     * @return
     */
    private boolean updateLengths(long[] len, int[] via) {
        boolean changes = false;

        for (int i = 0; i < graph.length; i++) {
            for(Edge edge: graph[i]) {
                int firstNode = i;
                int secondNode = edge.getC();

                if (len[firstNode] < Long.MAX_VALUE) {
                    long newLen = len[firstNode] + edge.getV();
                    if (newLen < len[secondNode]) {
                        len[secondNode] = newLen;
                        via[secondNode] = firstNode;
                        changes = true;
                    }
                }
            }
        }
        return changes;
    }

    //----------------------Private methods--------------------------//

    /**
     * creates the graph
     * @param length
     */
    @SuppressWarnings("unchecked")
    private void createDT(int length) {
        this.graph = new List[length];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
    }

}
