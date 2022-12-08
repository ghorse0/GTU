public class Main {
    public static void main(String[] args) {
        ListGraph lg = new ListGraph(15, false);
        lg.insert(new Edge(0, 1,1.0,12,2.132f));
        lg.insert(new Edge(1,3,1.0,6,3.4520f));
        lg.insert(new Edge(2,5,1.0,4,8.1230f));
        lg.insert(new Edge(3,4,1.0,3,2.023f));
        lg.insert(new Edge(4,5,1.0,1,3.343f));

        int[] pred = new int[15];
        double[] dist = new double[15];
        System.out.println("Testing double typed weight");
        testAlgorithm2(lg,type.DOUBLE);
        System.out.println("Testing int typed weight");
        testAlgorithm2(lg,type.INTEGER);
        System.out.println("Testing float typed weight");
        testAlgorithm2(lg, type.FLOAT);
    }

    public static void testAlgorithm(Graph lg, type t, int op){
        int[] pred = new int[15];
        double[] dist = new double[15];
        DijkstrasAlgorithm.dijkstrasAlgorithm(lg,0,pred,dist,t, op);
        for(int i = 0 ; i < 5 ; ++i){
            System.out.println("pred -> " + pred[i] + " dist -> " + dist[i]);
        }
    }

    public static void testAlgorithm2(Graph lg, type t){
        System.out.println("Calling method with op +");
        testAlgorithm(lg, t, 0);
        System.out.println("Calling method with op multiplication");
        testAlgorithm(lg, t, 1);
        System.out.println("Calling method with  op = *");
        testAlgorithm(lg, t, 2);
    }

}
