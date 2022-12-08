import java.util.Random;

public class Main {

    public static void main(String[] args){

        long k = 0;
        MyHashMap<Integer, String> mHashMap = new MyHashMap<>();
        HashTableChain<Integer, String> tableLL1 = new HashTableChain<>(10);
        HashTableChainTS<Integer, String> tableTS1 = new HashTableChainTS<>(10);
        CoalescedHash<Integer, String> tableCH1 = new CoalescedHash<>(10);

        HashTableChain<Integer, String> tableLL2 = new HashTableChain<>(100);
        HashTableChainTS<Integer, String> tableTS2 = new HashTableChainTS<>(100);
        CoalescedHash<Integer, String> tableCH2 = new CoalescedHash<>(100);

        HashTableChain<Integer, String> tableLL3 = new HashTableChain<>(1000);
        HashTableChainTS<Integer, String> tableTS3 = new HashTableChainTS<>(1000);
        CoalescedHash<Integer, String> tableCH3 = new CoalescedHash<>(1000);

        System.out.println("Comparing 10 sized tables");
        System.out.println("Adding 8 items");

        long t = System.nanoTime();
        for(int i = 0 ; i < 8 ; ++i){
            tableLL1.put(i*2,"test");
        }
        long t1 = System.nanoTime() - t;
        t = System.nanoTime();
        for(int i = 0 ; i < 8 ; ++i)
            tableTS1.put(i*2,"test");
        long t2 = System.nanoTime() - t;

        t = System.nanoTime();
        for(int i = 0 ; i < 8 ; ++i)
            tableCH1.put(i*2,"test");
        long t3 = System.nanoTime() - t;
        System.out.printf("Chaining with LL : %d ns\nChaining with TreeSet : %d ns\nCoalesced hashing : %d ns\n\n", t1, t2, t3);
        System.out.printf("Removing 1 item\n");

        t = System.nanoTime();
        tableLL1.remove(4);
        t1 = System.nanoTime() - t;

        t = System.nanoTime();
        tableTS1.remove(4);
        t2 = System.nanoTime() - t;


        t = System.nanoTime();
        tableCH1.remove(4);
        t3 = System.nanoTime() - t;
        System.out.printf("Chaining with LL : %d ns\nChaining with TreeSet : %d ns\nCoalesced hashing : %d ns\n\n", t1, t2, t3);

        System.out.printf("Accessing existing 1 item\n");
        t = System.nanoTime();
        tableLL1.get(8);
        t1 = System.nanoTime() - t;

        t = System.nanoTime();
        tableTS1.get(8);
        t2 = System.nanoTime() - t;

        t = System.nanoTime();
        tableCH1.get(8);
        t3 = System.nanoTime() - t;
        System.out.printf("Chaining with LL : %d ns\nChaining with TreeSet : %d ns\nCoalesced hashing : %d ns\n\n", t1, t2, t3);


        System.out.printf("Accessing non-existing 1 item\n");
        t = System.nanoTime();
        tableLL1.get(999);
        t1 = System.nanoTime() - t;

        t = System.nanoTime();
        tableTS1.get(999);
        t2 = System.nanoTime() - t;

        t = System.nanoTime();
        tableCH1.get(999);
        t3 = System.nanoTime() - t;
        System.out.printf("Chaining with LL : %d ns\nChaining with TreeSet : %d ns\nCoalesced hashing : %d ns\n\n", t1, t2, t3);


        System.out.println("Comparing 100 sized tables");
        System.out.println("Adding 80 items");

        t = System.nanoTime();
        for(int i = 0 ; i < 80 ; ++i){
            tableLL2.put(i * 2,"test");
        }
        t1 = System.nanoTime() - t;
        t = System.nanoTime();
        for(int i = 0 ; i < 80 ; ++i) {
            tableTS2.put(i * 2, "test");
        }
        t2 = System.nanoTime() - t;

        t = System.nanoTime();
        for(int i = 0 ; i < 80 ; ++i) {
            tableCH2.put(i * 2, "test");

        }
        t3 = System.nanoTime() - t;
        System.out.printf("Chaining with LL : %d ns\nChaining with TreeSet : %d ns\nCoalesced hashing : %d ns\n\n", t1, t2, t3);
        System.out.printf("Removing 30 item\n");

        t = System.nanoTime();
        for(int i = 0 ; i < 30 ; ++i)
            tableLL2.remove(i*2);
        t1 = System.nanoTime() - t;

        t = System.nanoTime();
        for(int i = 0 ; i < 30 ; ++i)
            tableTS2.remove(i*2);
        t2 = System.nanoTime() - t;


        t = System.nanoTime();
        for(int i = 0 ; i < 30 ; ++i)
            tableCH2.remove(i*2);
        t3 = System.nanoTime() - t;
        System.out.printf("Chaining with LL : %d ns\nChaining with TreeSet : %d ns\nCoalesced hashing : %d ns\n\n", t1, t2, t3);

        System.out.printf("Accessing existing 30 item\n");
        t = System.nanoTime();
        for(int i = 30 ; i < 60 ; ++i)
            tableLL2.get(i * 2);
        t1 = System.nanoTime() - t;

        t = System.nanoTime();
        for(int i = 30 ; i < 60 ; ++i)
            tableTS2.get(i * 2);
        t2 = System.nanoTime() - t;

        t = System.nanoTime();
        for(int i = 30 ; i < 60 ; ++i)
            tableCH2.get(i * 2);
        t3 = System.nanoTime() - t;
        System.out.printf("Chaining with LL : %d ns\nChaining with TreeSet : %d ns\nCoalesced hashing : %d ns\n\n", t1, t2, t3);


        System.out.printf("Accessing non-existing 50 item\n");
        t = System.nanoTime();
        for(int i = 300 ; i < 350 ; ++i)
            tableLL2.get(i * 2);
        t1 = System.nanoTime() - t;

        t = System.nanoTime();
        for(int i = 300 ; i < 350 ; ++i)
            tableTS2.get(i * 2);
        t2 = System.nanoTime() - t;

        t = System.nanoTime();
        for(int i = 300 ; i < 350 ; ++i)
            tableCH2.get(i * 2);
        t3 = System.nanoTime() - t;
        System.out.printf("Chaining with LL : %d ns\nChaining with TreeSet : %d ns\nCoalesced hashing : %d ns\n\n", t1, t2, t3);



        System.out.println("Comparing 1000 sized tables");
        System.out.println("Adding 800 items");

        t = System.nanoTime();
        for(int i = 0 ; i < 800 ; ++i){
            tableLL3.put(i * 2,"test");
        }
        t1 = System.nanoTime() - t;
        t = System.nanoTime();
        for(int i = 0 ; i < 800 ; ++i) {
            tableTS3.put(i * 2, "test");
        }
        t2 = System.nanoTime() - t;

        t = System.nanoTime();
        for(int i = 0 ; i < 800 ; ++i) {
            tableCH3.put(i * 2, "test");

        }
        t3 = System.nanoTime() - t;
        System.out.printf("Chaining with LL : %d ns\nChaining with TreeSet : %d ns\nCoalesced hashing : %d ns\n\n", t1, t2, t3);
        System.out.printf("Removing 400 item\n");

        t = System.nanoTime();
        for(int i = 0 ; i < 400 ; ++i)
            tableLL3.remove(i*2);
        t1 = System.nanoTime() - t;

        t = System.nanoTime();
        for(int i = 0 ; i < 400 ; ++i)
            tableTS3.remove(i*2);
        t2 = System.nanoTime() - t;


        t = System.nanoTime();
        for(int i = 0 ; i < 400 ; ++i)
            tableCH3.remove(i*2);
        t3 = System.nanoTime() - t;
        System.out.printf("Chaining with LL : %d ns\nChaining with TreeSet : %d ns\nCoalesced hashing : %d ns\n\n", t1, t2, t3);

        System.out.printf("Accessing existing 500 item\n");
        t = System.nanoTime();
        for(int i = 400 ; i < 900 ; ++i)
            tableLL3.get(i * 2);
        t1 = System.nanoTime() - t;

        t = System.nanoTime();
        for(int i = 400 ; i < 900 ; ++i)
            tableTS3.get(i * 2);
        t2 = System.nanoTime() - t;

        t = System.nanoTime();
        for(int i = 400 ; i < 900 ; ++i)
            tableCH3.get(i * 2);
        t3 = System.nanoTime() - t;
        System.out.printf("Chaining with LL : %d ns\nChaining with TreeSet : %d ns\nCoalesced hashing : %d ns\n\n", t1, t2, t3);


        System.out.printf("Accessing non-existing 500 item\n");
        t = System.nanoTime();
        for(int i = 1100 ; i < 1600 ; ++i)
            tableLL3.get(i * 2);
        t1 = System.nanoTime() - t;

        t = System.nanoTime();
        for(int i = 1100 ; i < 1600 ; ++i)
            tableTS3.get(i * 2);
        t2 = System.nanoTime() - t;

        t = System.nanoTime();
        for(int i = 1100 ; i < 1600 ; ++i)
            tableCH3.get(i * 2);
        t3 = System.nanoTime() - t;
        System.out.printf("Chaining with LL : %d ns\nChaining with TreeSet : %d ns\nCoalesced hashing : %d ns\n\n", t1, t2, t3);
    }

}
