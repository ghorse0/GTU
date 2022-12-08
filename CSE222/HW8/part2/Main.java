import java.util.Iterator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ListGraph graph = new ListGraph(1000 , false);
        System.out.println("Testing for 1000 sized graph and various density");
        testGraphWithDensity(1000);
        testGraphWithDensity(2000);
        testGraphWithDensity(5000);
        testGraphWithDensity(10000);


    }

    private static void testGraphWithDensity(int size){
        System.out.println("Testing Graph methods 5 times with size of " + size);
        testGraph(size, 6);
        testGraph(size, 3);
        testGraph(size, 2);
        System.out.println("--------End of this size--------\n");
    }
    private static void testGraph(int size, int mod){
        long sec = 1000;
        long start = 0;
        long end = 0;
        long avg1 = 0;
        long avg2 = 0;
        int connected, connected2;
        Random rand = new Random();
        int range = size/mod + size / 10;


        for(int i = 0 ; i < 5 ; ++i) {
            ListGraph graph = new ListGraph(size, false);
            for (int j = 0; j < range; ++j) {
                int n1 = rand.nextInt(size) % range;
                int n2 = rand.nextInt(size) % range;
                while(n1 == n2) n2 = rand.nextInt(size) % range;
                graph.insert(new Edge(n1, n2));
            }
                start = System.nanoTime();
                connected = graph.bfs_connected(size);
                end = System.nanoTime();
                avg1 += (end - start) / 1000;

                System.out.printf("connected component by bfs : %d, ", connected);

                start = System.nanoTime();
                connected2 = graph.dfs();
                end = System.nanoTime();
                avg2 += (end - start) / 1000;

                System.out.printf("connected component by dfs : %d\n", connected2);
                graph = null;
        }

        avg1 /= 5;
        avg2 /= 5;
        System.out.printf("\n");
        System.out.printf("Avg BFS time for max num of %d edge is %d microseconds\n", range, avg1);
        System.out.printf("Avg DFS time for max num of %d edge is %d microseconds\n\n", range, avg2);

    }

    public static Integer[] generateRandoms(int size, int range) {
        Integer[] arr = new Integer[size];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            int num = rand.nextInt(size) % range;
            arr[i] = num;
        }
        return arr;
    }
}
