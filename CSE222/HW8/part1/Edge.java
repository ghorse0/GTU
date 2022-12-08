public class Edge {

    /**
     * Destination node
     */
    private int dest;
    /**
     * Source node
     */
    private int source;

    /**
     * Weight of edge
     */
    private double weight;
    private float weightF;
    private int weightI;

    /**
     * Constructor for creating weighted edge
     *
     * @param source source node
     * @param dest destination node
     * @param w weight of edge
     */
    public Edge(int source, int dest, double w) {
        this.source = source;
        this.dest = dest;
        this.weight = w;
        this.weightI = 1;
        this.weightF = 1.0f;
    }

    public Edge(int source, int dest, int w) {
        this.source = source;
        this.dest = dest;
        this.weightI = w;
        this.weight = 1.0;
        this.weightF = 1.0f;
    }

    public Edge(int source, int dest, float w) {
        this.source = source;
        this.dest = dest;
        this.weightF = w;
        this.weight = 1.0;
        this.weightI = 1;
    }

    public Edge(int source, int dest, double w, int w2, float w3){
        this.source = source;
        this.dest = dest;
        this.weight = w;
        this.weightI = w2;
        this.weightF = w3;
    }

    /**
     * Constructor for creating edge with weight of 1
     *
     * @param source source node
     * @param dest destination node
     */
    public Edge(int source, int dest) {
        this(source, dest, 1.0, 1, 1.0f);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge))
            return false;

        Edge otherEdge = (Edge) obj;

        return (this.dest == otherEdge.dest && this.source == otherEdge.source);
    }

    /**
     * Getter for destination node
     *
     * @return destination node
     */
    public int getDest() {
        return this.dest;
    }

    /**
     * Getter for source node
     *
     * @return source node
     */
    public int getSource() {
        return this.source;
    }

    /**
     * Getter for weight of edge
     *
     * @return weight of edge
     */
    public double getWeight() {
        return this.weight;
    }
    public int getWeightI(){ return this.weightI;}
    public float getWeightF(){ return this.weightF;}

    public double getWeight(type t){
        switch(t){
            case FLOAT:
                return (double)this.weightF;
            case INTEGER:
                return Double.valueOf(this.weightI);
        }
        return this.weight;
    }

    @Override
    public int hashCode() {
        int hash = 5;

        hash = 31 * hash + dest;
        hash = 31 * hash + source;

        return hash;
    }

    @Override
    public String toString() {
        return String.format("Edge[dest=%d, source=%d, weight=%f]", dest, source, weight);
    }
}