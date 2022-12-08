
import java.util.Random;


public class Driver {



    public static void main(String[] args) {

        HashTableChainTS<Integer, String> tableTS = new HashTableChainTS<>(10);
        CoalescedHash<Integer, String> tableCH = new CoalescedHash<>(10);
        testIterator();
        System.out.println("Testing hashTables with 10 sized");
        testLL();
        testTS();
        testCH();

    }
    public static void testLL(){
        HashTableChain<Integer, String> table = new HashTableChain<>(10);
        table.put(3,"test1");
        table.put(12,"test2");
        table.put(13,"test3");
        table.put(25,"test4");
        table.put(23,"test5");
        table.put(51,"test6");
        table.put(42,"test7");
        table.print();
        System.out.println("Remove 3");
        table.remove(3);
        table.print();
        System.out.println("51: " + table.get(51));
        System.out.println("Size is : " + table.size());
    }

    public static void testTS() {
        HashTableChainTS<Integer, String> table = new HashTableChainTS<>(10);
        table.put(3,"test1");
        table.put(12,"test2");
        table.put(13,"test3");
        table.put(25,"test4");
        table.put(23,"test5");
        table.put(51,"test6");
        table.put(42,"test7");
        table.print();
        System.out.println("Remove 3");
        table.remove(3);
        table.print();
        System.out.println("51 : " + table.get(51));
        System.out.println("Size is : " + table.size());
    }

    public static void testCH(){
        CoalescedHash<Integer, String> table = new CoalescedHash<>(10);
        table.put(3,"test1");
        table.put(12,"test2");
        table.put(13,"test3");
        table.put(25,"test4");
        table.put(23,"test5");
        table.put(51,"test6");
        table.put(42,"test7");
        table.print();
        System.out.println("Remove 3");
        table.remove(3);
        table.print();
        System.out.println("51: "+ table.get(51));
        System.out.println("Size is : " + table.size());
    }


    public static void testIterator(){
        MyHashMap<Integer, String> map = new MyHashMap<>();
        System.out.println("Testing iterator, adding 2,3,23,54,66 keys to hashMap");
        map.put(2,"test0");
        map.put(3,"test1");
        map.put(23,"test2");
        map.put(54,"test3");
        map.put(54,"test4");
        System.out.println("Testing next method and hasNext method");
        MapIterator<Integer> iter = map.iterator();
        MapIterator<Integer> iter2 = map.iterator(23);
        for(int i = 0 ; i < 6 ; ++i){
            System.out.printf("next : %d, hasNext : %b\n", iter.next(), iter.hasNext());
        }
        System.out.println("Testing with key given created iterator MapIterator(K key)");
        for(int i = 0 ; i < 6 ; ++i){
            System.out.printf("next : %d, hasNext : %b\n", iter2.next(), iter2.hasNext());
        }
        System.out.println("Testing prev method");

        for(int i = 0 ; i < 6 ; ++i){
            System.out.printf("prev : %d\n", iter.prev());
        }
        System.out.println("Testing with key given created iterator MapIterator(K key)");
        for(int i = 0 ; i < 6 ; ++i){
            System.out.printf("prev : %d\n", iter2.prev());
        }

    }

}

    