import java.util.ArrayList;
import java.util.Collections;

public class Heap <E extends Comparable<E>>{
    
    protected ArrayList<E> dataArray;

    public Heap(){
        dataArray = new ArrayList<>();
    }

    public class HeapIterator implements Iterator<E>{
        int pos = 0;
        public E next(){
            return dataArray.get(pos++);
        }

        public E set(E val){
            if(pos == 0) return null;
            E temp = get(pos-1);
            dataArray.set(pos-1, val);
            buildHeap();
            return temp;
        }

        public boolean hasNext(){
            if(pos < size())
                return true;
            return false;
        }


    }
    public Iterator<E> iterator(){
        return new HeapIterator();
    }

    private int parent(int p){
        return (p - 1) / 2;
    }

    private int leftChild(int p){
        return (2 * p) + 1;
    }

    public int size(){
        return dataArray.size();
    }

    /**
     * Inserts item to the heap and orders heap by swapping new element - Taken from textbook -
     * @param data
     */
    public void insert(E data){
        dataArray.add(data);
        int child = dataArray.size() - 1;
        int parent = this.parent(child);
        if(this.size() == 0) return;
    
        while(parent >= 0 && (dataArray.get(child).compareTo(dataArray.get(parent)) > 0)){
            Collections.swap(dataArray, parent, child);
            child = parent;
            parent = this.parent(child);
        }
    }


    /**
     * Removes top of the heap - Taken from textbook -
     * 
     */
    public E remove(){
        if(dataArray.size() == 0) throw new NullPointerException("Heap is empty.");;
        Collections.swap(dataArray, 0, dataArray.size() - 1);
        E temp = dataArray.remove(dataArray.size() - 1);
        int size = this.size();
        int parent = 0;
        while(true){
            int leftChild = this.leftChild(parent);
            int rightChild = leftChild + 1;
            int maxChild = leftChild;
            if(leftChild >= size)
                break;
            
            if(rightChild < size && (dataArray.get(rightChild).compareTo(dataArray.get(leftChild))) > 0){
                maxChild = rightChild;
            }

            if(dataArray.get(parent).compareTo(dataArray.get(maxChild)) < 0){
                Collections.swap(dataArray, parent, maxChild);
                parent = maxChild;
            }
            else
                break;
        }
        return temp;

    }

    
    /**
     * Recursive building heap function
     * @param i, position of the element
     */
    public void heapify(int i){
        int largest = i;
        int left = leftChild(i);
        int right = left + 1;
        int size = size();

        if(left < size && get(left).compareTo(get(largest)) > 0){
            largest = left;
        }

        if(right < size && get(right).compareTo(get(largest)) > 0){
            largest = right;
        }
        if(largest != i){
            Collections.swap(dataArray, i, largest);
            heapify(largest);
        }
    }

    public void buildHeap(){
        int start = (size() / 2) - 1;

        for(int i = start; i >= 0 ; i--){
            heapify(i);
        }
    }

    /**
     * Prints heap by their level
     */
    public void print(){
        int depth = 0;
        int parent = 0;
        for(int i = 0 ; i < this.size() ; ++i){
            int child = leftChild(parent);
            if(i == child) {
                depth++;
            }
            for(int j = 0 ; j < depth ; ++j){
                System.out.printf("-");
            }    

            System.out.print(dataArray.get(i));
            if(i != 0)
                System.out.print(" (p:" + dataArray.get(parent(i)) + ")");
            System.out.print("\n");
            if(i == child) parent = child;
        }
    }

    /**
     * Searches given data in the heap
     * @param data
     * @return
     */
    public boolean search(E data){
        return dataArray.contains(data);
    }

    public E get(int i){
        return dataArray.get(i);
    }

    /**
     * Merges given heap to this heap
     * @param heap
     */
    public void mergeHeap(Heap<E> heap){
        for(int i = 0 ; i < heap.size() ; ++i){
            this.insert(heap.get(i));
        }
    }

    /**
     * Gets the index of given data if it's in heap
     * @param data
     * @return index or -1 if it's not in the heap
     */
    public int getIndex(E data){
        for(int i = 0 ; i < size() ; ++i){
            if(data.compareTo(dataArray.get(i)) == 0) return i;
        }
        return -1;
    }

    /**
     * Removes element by their rank in heap, rank = i, removes ith element from heap
     * @param rank
     * @return removed element
     */
    public E removeElement(int rank){
        //int currentRank = 0;
        ArrayList<E> tempArray = dataArray;
        int n = size();
        if(rank > n) return null;
        for(int i = 0 ; i < n-1 ; ++i){
            for(int j = 0 ; j < n-i-1 ; ++j){
                E data_ = tempArray.get(j);
                if(data_.compareTo(tempArray.get(j+1)) < 0)
                {
                    Collections.swap(tempArray, j, j+1);
                }
            }
        }
        E temp = tempArray.get(rank - 1);
        int index = getIndex(temp);
        dataArray.remove(index);
        buildHeap();
        return temp;
        
    }
}