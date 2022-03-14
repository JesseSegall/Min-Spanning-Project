//Created by Jesse Segall
//Algorithms Final Programming assignment

public class Main {

    //
//k is edge, numberOfVertexes is Nodes
    static int findMinimumKey(int[] key, boolean[] visited, int numberOfVertexes) {
        int index = 0;
        int minimumValue = Integer.MAX_VALUE;

        for (int i = 0; i < numberOfVertexes; i++) {
            if (key[i] < minimumValue && !visited[i]) {
                minimumValue = key[i];
                index = i;
            }
        }
        return index;
    }

    static int[] primMinimumSpanningTree(int[][] g, int numberOfVertexes) {
        boolean[] primIncludedVertexes = new boolean[numberOfVertexes];
        int[] primVertexStorage = new int[numberOfVertexes]; //storing minimum weight of getting to vertex A to B
        int[] parents = new int[numberOfVertexes]; //parent of each node stored in an array
        parents[0] = -1; //setting root to first vertex in the graph
        primVertexStorage[0] = 0;

        for (int i = 1; i < numberOfVertexes; i++) {
            primVertexStorage[i] = Integer.MAX_VALUE; //initializing with infinity
            primIncludedVertexes[i] = false;
        }
        for (int i = 0; i < numberOfVertexes - 1; i++) { //going over each vertex, - 1 because we don't care about root,
            int minKey = findMinimumKey(primVertexStorage, primIncludedVertexes, numberOfVertexes);// could be min heap instead of calling  finminkey
            primIncludedVertexes[minKey] = true; //to check if we visited node already

            for (int j = 0; j < numberOfVertexes; j++) { //iterating over all nodes including root
                if (!primIncludedVertexes[j] && g[minKey][j] < primVertexStorage[j] && g[minKey][j] != 0) { //checking conditions looking for minimum
                    parents[j] = minKey; //updating parents array each parent is the shortest path
                    primVertexStorage[j] = g[minKey][j]; //updating new min edge
                }
            }
        }
        return parents;

    }

    public static void main(String[] args) {

        int g[][] = new int[][]{ //input adjacency list; must be square matrix
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};

        int parent[] = primMinimumSpanningTree(g, 5);//If changing input number of vertices must be changed to match


        for (int i = 1; i < 5; i++) {
            System.out.println(parent[i] + " to " + i + " weight " + g[i][parent[i]]);
        }

    }
}
