import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class BSTHeap <E extends Comparable<E>>{
    
    protected ArrayList<E> dataArray;
    private ArrayList<Integer> occurence;

    public BSTHeap(){
        dataArray = new ArrayList<>();
        occurence = new ArrayList<Integer>();
        for(int i = 0 ; i < 7 ; ++i) occurence.add(0);
    }

    private int parent(int p){
        return (p - 1) / 2;
    }

    private int leftChild(int p){
        return (2 * p) + 1;
    }

    /**
     * Decrements occurence of the indexed element
     * @param index
     */
    public void occurenceDec(int index){
        occurence.set(index, occurence.get(index) - 1);
    }

    /**
     * Increments occurence of the indexed element
     * @param index
     */
    public void occurenceInc(int index){
        occurence.set(index, occurence.get(index) + 1);
    }

    public int size(){
        return dataArray.size();
    }

    /**
     * Inserts item to the heap and orders heap by swapping new element - Taken from textbook -
     * @param data
     */
    public void insert(E data){
        if(size() == 0){
            dataArray.add(data);
            occurenceInc(0);
            return;
        }
        boolean check = dataArray.contains(data);
        if(check == false){
            dataArray.add(data);
            int child = dataArray.size() - 1;
            int parent = this.parent(child);
            if(this.size() == 0) return;
        
            while(parent >= 0 && (dataArray.get(child).compareTo(dataArray.get(parent)) > 0)){
                Collections.swap(dataArray, parent, child);
                Collections.swap(occurence, parent, child);
                child = parent;
                parent = this.parent(child);
            }
        }
        
        int index = getIndex(data);
        occurence.set(index, occurence.get(index) + 1);
    }

    public boolean contains(E data){
        return dataArray.contains(data);
    }

    /**
     * Removes top of the heap, orderes heap and occurence of elements - Taken from textbook -
     * 
     */
    public E remove(){
        if(dataArray.size() == 0) throw new NullPointerException("Heap is empty.");
        Collections.swap(dataArray, 0, dataArray.size()-1);
        Collections.swap(occurence, 0, dataArray.size()-1);
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
                Collections.swap(occurence, parent, maxChild);
                parent = maxChild;
            }
            else
                break;
        }
        return temp;
    }

    /**
     * Removes an item from heap.
     * throws NoSuchElementException if item is not in the heap
     * @param item
     * @return null if item is not in heap, if it's in heap returns removed item
     */
    public E remove(E item){
        int index = getIndex(item);
        if(index != -1){
            if(occurence.get(index) == 1){
                dataArray.remove(index);
                buildHeap();
            }
            else if(occurence.get(index) > 1){
                occurence.set(index, occurence.get(index)-1);
            }
            return item;
        }
        throw new NoSuchElementException();
        
    }

    /**
     * Recursive building heap function and orders occurence of elements correctly
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
            Collections.swap(occurence, i, largest);
            heapify(largest);
        }
    }

    /**
     * Builds heap from an array
     */
    public void buildHeap(){
        int start = (size() / 2) - 1;

        for(int i = start; i >= 0 ; i--){
            heapify(i);
        }
    }

    public void print(){
        int depth = 0;
        int parent = 0;
        if(dataArray.size() == 0) return;
        for(int i = 0 ; i < this.size() ; ++i){
            int child = leftChild(parent);
            if(i == child) {
                depth++;
            }
            for(int j = 0 ; j < depth ; ++j){
                System.out.printf("-");
            }    

            System.out.println(dataArray.get(i));
            if(i == child) parent = child;
        }
    }

    public E get(int i){
        return dataArray.get(i);
    }

    public void mergeHeap(Heap<E> heap){
        for(int i = 0 ; i < heap.size() ; ++i){
            this.insert(heap.get(i));
        }
    }

    public int getIndex(E data){
        for(int i = 0 ; i < size() ; ++i){
            if(data.compareTo(dataArray.get(i)) == 0) return i;
        }
        return -1;
    }


    public int getOccurence(E data){
        int index = getIndex(data);
        if(index != -1) return occurence.get(index);
        
        return 0;
    }

    public int getOccurenceByIndex(int index){
        return occurence.get(index);
    }

    public int getMaxOccurence(){
        if(size() == 0) throw new NullPointerException("Heap is empty.");

        int max = getOccurenceByIndex(0);

        for(int i  = 0 ; i < size() ; ++i){
            if(getOccurenceByIndex(i) > max) max = getOccurenceByIndex(i);
        }
        return max;
    }

    public E getMaxOccurenceData(){
        if(size() == 0) throw new NullPointerException("Heap is empty.");

        int max = getOccurenceByIndex(0);
        int index = 0;

        for(int i  = 0 ; i < size() ; ++i){
            if(getOccurenceByIndex(i) > max){
                max = getOccurenceByIndex(i);
                index = i;
            };
        }
        return get(index);
    }

}