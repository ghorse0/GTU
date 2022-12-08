import java.util.Iterator;

public class Driver {
    public static void main(String[] args) {
        test();
    }


    public static void test(){
        ListGraph graph = new ListGraph(50, false);
        int size = 50;
        graph.insert(new Edge(0, 6));
        graph.insert(new Edge(9, 10));
        graph.insert(new Edge(6, 2));

        System.out.println("Connected component outputs for graph of 0->6->2 9->10");
        System.out.println("dfs is called : " + graph.dfs());
        System.out.println("bfs is called : " + graph.bfs_connected(50));

        graph = null;
        graph = new ListGraph(100, false);

        graph.insert(new Edge(0, 34));
        graph.insert(new Edge(54, 65));
        graph.insert(new Edge(65, 75));
        graph.insert(new Edge(75, 33));
        graph.insert(new Edge(88, 22));

        System.out.println("Connected compnonent outputs for graph of 0->34, 54->65->75->33, 88->22");
        System.out.println("dfs is called : " + graph.dfs());
        System.out.println("bfs is called : " + graph.bfs_connected(50));

    }
}



