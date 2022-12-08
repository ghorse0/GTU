import java.util.Random;

public class Main {

    public static void main(String[] args) {

        testGeneric(new BinarySearchTree<Integer>(), "BinarySearchTree");
        testGeneric(new RedBlackTree<Integer>(), "RedBlackTree");

        testGeneric(new SkipList<Integer>(), "Skip list");
        testGeneric(new BTree<Integer>(6), "B tree with order of 6");
    }

    /**
     * Tester method for data structures, time is by nanoseconds
     * @param adt
     * @param name
     */
    private static void testGeneric(BenchI<Integer> adt, String name) {
        System.out.println("------");
        int curSize = 10_000;
        long startTime[] = new long[10];
        long stopTime[] = new long[10];
        long avgTime[] = new long[10];
        while (curSize <= 80_000) {
            for (int i = 0; i < 10; i++) {
                System.out.println(i+1 + " Inserting " + name + " with size : " + curSize);
                adt.clearRoot();
                Integer[] arr = generateRandoms(curSize);
                for (Integer e : arr) {
                    adt.add(e);
                }

                Integer[] randoms = generateRandoms(100);
                System.out.println("Adding 100 random items..");
                startTime[i] = System.nanoTime();
                for (Integer e : randoms) {
                    adt.add(e);
                }
                stopTime[i] = System.nanoTime();
                avgTime[i] = stopTime[i] - startTime[i];
                System.out.println(avgTime[i]);

            }
            long avg = calculateAvg(avgTime);
            System.out.println("Average time for " + name + " with size of " + curSize + " = " + avg + " ns");

            curSize *= 2;
        }
        System.out.println("------");
    }

    /**
     * Generate array with size and fill it with random integers
     * @param size
     * @return
     */
    public static Integer[] generateRandoms(int size) {
        Integer[] arr = new Integer[size];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            int num = rand.nextInt(size);
            arr[i] = num;
        }
        return arr;
    }

    public static long calculateAvg(long[] arr){
        long avg = 0;
        for(int i = 0 ; i < 10 ; ++i){
            avg += arr[i];
        }
        avg = avg / 10;
        return avg;
    }
}
