import java.util.Iterator;


public class DepthFirstSearch {
// Data Fields
    /** A reference to the graph being searched. */
    private Graph graph;

    private int[] parent;
    /** Flag to indicate whether this vertex has been visited. */
    private boolean[] visited;
    /** The array that contains each vertex in discovery order. */
    private int[] discoveryOrder;
    /** The array that contains each vertex in finish order. */
    private int[] finishOrder;
    /** The index that indicates the discovery order. */
    private int discoverIndex = 0;
    /** The index that indicates the finish order. */
    private int finishIndex = 0;

    private int connected = 0;
// Constructors

    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
        int n = graph.getNumV();
        parent = new int[n];
        visited = new boolean[n];
        discoveryOrder = new int[n];
        finishOrder = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                Iterator<Edge> it = graph.edgeIterator(i);
                if(it.hasNext()) {
                    ++connected;
                }
                depthFirstSearch(i);
            }

        }
    }

    public DepthFirstSearch(Graph graph, int[] order) {
// Same as constructor above except for the if statement.
    }

    public void depthFirstSearch(int current) {
        /* Mark the current vertex visited. */
        visited[current] = true;
        discoveryOrder[discoverIndex++] = current;
        /* Examine each vertex adjacent to the current vertex */
        Iterator<Edge> itr = graph.edgeIterator(current);
        while (itr.hasNext()) {
            int neighbor = itr.next().getDest();

            if (!visited[neighbor]) {
                parent[neighbor] = current;
                depthFirstSearch(neighbor);
            }
        }


        finishOrder[finishIndex++] = current;
    }

    /**
     *Get finish order of dfs
     * @return finish order
     */
    public int[] getFinishOrder() {
        return finishOrder;
    }

    public int getConnected(){
        return connected;
    }
}