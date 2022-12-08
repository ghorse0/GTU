
import java.util.Random;


public class Driver {



    public static void main(String[] args) {
        BSTHeapTree<Integer> bst = new BSTHeapTree<Integer>();
        Heap<Integer> heap = new Heap<Integer>();
        int[] arr = new int[100];
        
        
        System.out.println("Inserting 3,23,55,44,1,67,56,96 to heap");
        heap.insert(3);
        heap.insert(23);
        heap.insert(55);
        heap.insert(44);
        heap.insert(1);
        heap.insert(67);
        heap.insert(56);
        heap.insert(96);
        heap.print();
        System.out.println("Searching 55 in heap");
        System.out.println("heap.search(55) = " + heap.search(55));
        System.out.println("Searching 96 in heap");
        System.out.println("heap.search(96) = " + heap.search(96));
        System.out.println("Searching 1 in heap");
        System.out.println("heap.search(1) = " + heap.search(1));
        System.out.println("Searching 323 in heap");
        System.out.println("heap.search(323) = " + heap.search(323));

        System.out.println("Creating new heap and inserting 654, 44, 55, 23, 9, 66, 75 to the heap.");
        Heap<Integer> heap2 = new Heap<Integer>();
        heap2.insert(654);
        heap2.insert(44);
        heap2.insert(55);
        heap2.insert(23);
        heap2.insert(9);
        heap2.insert(66);
        heap2.insert(75);
        heap2.print();
        System.out.println("Merging first heap and second heap.");
        heap.mergeHeap(heap2);
        System.out.println("Printing merged heap.");
        heap.print();


        System.out.println("Removing biggest element from heap...");
        heap.removeElement(1);
        heap.print();
        System.out.println("Removing 6th element from heap...");
        heap.removeElement(6);
        heap.print();
        System.out.println("Removing 3rd element from heap...");
        heap.removeElement(3);
        heap.print();

        System.out.println("Creating iterator and printing next()");
        Iterator<Integer> iter = heap.iterator();
        System.out.println("iter.next() = " + iter.next());
        System.out.println("iter.next() one more and calling iter.set(999)");
        System.out.println("iter.next() = " + iter.next());
        iter.set(999);
        heap.print();
        System.out.println("iter.next() = " + iter.next());
        System.out.println("iter.set(7)");
        iter.set(7);
        heap.print();

        System.out.println("----PART 2----");
        System.out.println("Inserting 50 random from range of 1 to 100 to BSTHeapTree");

        Random rand = new Random();

        for(int i = 0 ; i < 50 ; ++i){
            arr[i] = rand.nextInt(100) + 1;
            bst.add(arr[i]);
        }

        System.out.println("Sorting random array and printing it...");
        for(int i = 0 ; i < 49 ; ++i){
            for(int j = 0 ; j < 49 - i ; ++j){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        for(int i = 0 ; i < 50 ; ++i){
            System.out.printf("%d ", arr[i]);
        }
        System.out.print("\n\n");
        System.out.println("Printing tree");
        System.out.println(bst.toString());
        System.out.println("Printing root node's heap");
        bst.printHeap();
        System.out.println("Printing left child's heap if it's not null");
        HeapTree<Integer> left = bst.getLeftSubtree();
        if(left != null)
            left.printHeap();
        System.out.println("Printing right child's heap if it's not null");
        HeapTree<Integer> right = bst.getRightSubtree();
        if(right != null)
            right.printHeap();
        System.out.println("Mode of tree = " + bst.find_mode());

        System.out.println("Creating new double BSTHeapTree and inserting 33.1, 2.5, 2.5, 2.5, 2.5, 44.6,44.2,44.2, 56.7,56.7, 1.3, 7.1, 14.2, 14.2, 13.3, 23.3, 25.5");
        BSTHeapTree<Double> bst2 = new BSTHeapTree<Double>();
        bst2.add(33.1);
        bst2.add(2.5);
        bst2.add(2.5);
        bst2.add(2.5);
        bst2.add(44.6);
        bst2.add(44.2);
        bst2.add(44.2);
        bst2.add(56.7);
        bst2.add(56.7);
        bst2.add(1.3);
        bst2.add(7.1);
        bst2.add(14.2);
        bst2.add(14.2);
        bst2.add(13.3);
        bst2.add(23.3);
        bst2.add(25.5);
        System.out.println(bst2.toString());
        System.out.println("Printing parent node's heap");
        bst2.printHeap();
        System.out.println("Printing left node's heap");
        bst2.getLeftSubtree().printHeap();
        System.out.println("Finding 2.5 and Occurence of 2.5 = " + bst2.find(2.5));
        System.out.println("Mode frequency = " + bst2.find_mode_freq());
        System.out.println("Mode of tree = " + bst2.find_mode());
        System.out.println("Finding 2.6, occurence = " + bst2.find(2.6));
        

    }

}

    