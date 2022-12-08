import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    public static int[] breadthFirstSearch(Graph graph, int start, int visited[]) {
        int connectedNum = 0;
        Queue<Integer> theQueue = new LinkedList<Integer>();
// Declare array parent and initialize its elements to –1.
        int[] parent = new int[graph.getNumV()];
        int[] ret = new int[2];
        int last = -1;
        for (int i = 0; i < graph.getNumV(); i++) {
            parent[i] = -1;
        }
// Declare array identified and
// initialize its elements to false.
        boolean[] identified = new boolean[graph.getNumV()];
        /* Mark the start vertex as identified and insert it into the queue */
        identified[start] = true;
        theQueue.offer(start);

        while (!theQueue.isEmpty()) {

            /* Take a vertex, current, out of the queue. (Begin visiting current). */
            int current = theQueue.remove();

            if(visited[current] != 1){
                ++connectedNum;
                visited[current] = 1;
            }

            /* Examine each vertex, neighbor, adjacent to current. */
            Iterator<Edge> itr = graph.edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();
                int neighbor = edge.getDest();
// If neighbor has not been identified
                if (!identified[neighbor] && neighbor != graph.getNumV()) {
// Mark it identified.
                    if(visited[neighbor] != 1 && neighbor != graph.getNumV()){
                        //++connectedNum;
                        visited[neighbor] = 1;
                    }

                    identified[neighbor] = true;
// Place it into the queue.
                    theQueue.offer(neighbor);
                    /* Insert the edge (current, neighbor) into the tree. */
                    parent[neighbor] = current;
                }
            }
// Finished visiting current.
        }
        visited[graph.getNumV()] = connectedNum;
        return visited;
    }
}