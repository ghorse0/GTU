import java.util.ArrayList;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class Main {
    public static void main(String[] args) {
        SLSetTest();
        AVLSetTest();

    }

    public static void SLSetTest(){
        SkipListSet<Integer> i = new SkipListSet<Integer>();

        System.out.println("---Testing SkipListSet<Integer>---");
        System.out.println("Adding 1, 15, 24, 10, 643, 77");
        i.add(1);
        System.out.println(i.toString());
        i.add(15);
        System.out.println(i.toString());
        i.add(24);
        System.out.println(i.toString());
        i.add(10);
        System.out.println(i.toString());
        i.add(643);
        System.out.println(i.toString());
        i.add(77);
        System.out.println(i.toString());
        System.out.println("\nAdding 24 again");
        i.add(24);
        System.out.println(i.toString());
        System.out.println("\nRemoving 15");
        i.remove(15);
        System.out.println(i.toString());
        System.out.println(i.toString());

        System.out.println("Descending iterator tests : \n");
        Iterator<Integer> it = i.descendingIterator();
        for(int j = 0 ; j < i.size() ; ++j){
            System.out.println("it.next = " + it.next() + " it.hasNext = " + it.hasNext());
        }
    }

    public static void AVLSetTest(){
        AVLTreeSet<Integer> i = new AVLTreeSet<Integer>();

        System.out.println("---Testing AVLTreeSet<Integer>---");
        System.out.println("Adding 1, 15, 24, 10, 643, 77");
        i.add(1);
        System.out.println(i.toString());
        i.add(15);
        System.out.println(i.toString());
        i.add(24);
        System.out.println(i.toString());
        i.add(10);
        System.out.println(i.toString());
        i.add(643);
        System.out.println(i.toString());
        i.add(77);
        System.out.println(i.toString());
        System.out.println("\nAdding 24 again");
        i.add(24);
        System.out.println(i.toString());
        System.out.println("\nRemoving 15");
        i.remove(15);
        System.out.println(i.toString());
        System.out.println(i.toString());

        System.out.println("iterator tests : \n");
        Iterator<Integer> it = i.iterator();
        for(int j = 0 ; j < i.size() ; ++j){
            System.out.println("it.next = " + it.next() + " it.hasNext = " + it.hasNext());
        }

        System.out.println("calling headSet(24, false) (headSet(E toElement, boolean inclusive)\n");
        NavigableSet<Integer> hs = i.headSet(24, false);
        System.out.println("Navigating with iterator");
        it = hs.iterator();
        for(int j = 0 ; j < hs.size() ; ++j){
            System.out.println("it.next = " + it.next() + " it.hasNext = " + it.hasNext());
        }
        System.out.println("calling headSet(24) (headSet(E toElement)\n");
        SortedSet<Integer> hs2 = i.headSet(24);
        System.out.println("Navigating with iterator");
        it = hs2.iterator();
        for(int j = 0 ; j < hs2.size() ; ++j){
            System.out.println("it.next = " + it.next() + " it.hasNext = " + it.hasNext());
        }

        System.out.println("\ncalling tailSet(77, true) (tailSet(E toElement, boolean inclusive)");
        NavigableSet<Integer> ts = i.tailSet(77, true);
        System.out.println("Navigating with iterator");
        it = ts.iterator();
        for(int j = 0 ; j < ts.size() ; ++j){
            System.out.println("it.next = " + it.next() + " it.hasNext = " + it.hasNext());
        }
        System.out.println("\ncalling tailSet(77) (tailSet(E toElement) not inclusive");
        SortedSet<Integer> ts2 = i.tailSet(77);
        System.out.println("Navigating with iterator");
        it = ts2.iterator();
        for(int j = 0 ; j < ts2.size() ; ++j){
            System.out.println("it.next = " + it.next() + " it.hasNext = " + it.hasNext());
        }

    }
}
