import java.util.HashSet;
import java.util.Iterator;


public class DijkstrasAlgorithm {

    /**
     * Dijkstras Shortest-Path algorithm.
     *
     * @param graph The weighted graph to be searched
     * @param start The start vertex
     * @param pred  Output array to contain the predecessors
     *              in the shortest path
     * @param dist  Output array to contain the distance
     *              in the shortest path
     */
    public static void dijkstrasAlgorithm(Graph graph, int start, int[] pred, double[] dist, type t, int op) {
        int numV = graph.getNumV();
        HashSet<Integer> vMinusS = new HashSet<>(numV);

        for (int i = 0; i < numV; i++) {
            if (i != start) {
                vMinusS.add(i);
            }
        }

        for (int v : vMinusS) {
            pred[v] = start;
            if (graph.getEdge(start, v, t) != null) {
                dist[v] = graph.getEdge(start, v).getWeight(t);
            } else {
                dist[v] = Integer.MAX_VALUE;
            }
        }
        // Main loop
        while (vMinusS.size() != 0) {
            // Find the value u in V–S with the smallest dist[u].
            double minDist = Double.POSITIVE_INFINITY;
            int u = -1;
            for (int v : vMinusS) {
                if (dist[v] < minDist) {
                    minDist = dist[v];
                    u = v;
                }
            }
            // Remove u from vMinusS.
            vMinusS.remove(u);

            Iterator<Edge> edgeIter = graph.edgeIterator(u);
            while (edgeIter.hasNext()) {
                Edge edge = edgeIter.next();
                int v = edge.getDest();
                if (vMinusS.contains(Integer.valueOf(v))) {
                    double weight = edge.getWeight(t);
                    double res = dist[u] + weight;
                    if(op == 1)
                        res = dist[u] * weight;
                    else if(op == 2)
                        res = dist[u] + weight - dist[u] * weight;
                    if (res < dist[v]) {
                        dist[v] = res;
                        pred[v] = u;
                    }
                }
            }
        }
    }
}