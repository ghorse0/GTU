import java.util.*;

public class SkipListSet<E extends Comparable<E>> extends AbstractSet<E> implements NavigableSet<E> {
    /**
     * Head of the skip-list
     */
    public SkipListSet.SLNode<E> head;

    private Object[] itArr;
    /**
     * Size of the skip list
     */
    private int size;
    /**
     * The maximum level of the skip-list
     */
    private int maxLevel;
    /**
     * Smallest power of 2 that is greater than the current skip-list size
     */
    private int maxCap;
    /**
     * Natural log of 2
     */
    static final double LOG2 = Math.log(2.0);
    /**
     * Minimum possible integer value for the head
     */
    static final int MIN = Integer.MIN_VALUE;
    /**
     * Random number generator
     */
    protected Random rand = new Random();


    @SuppressWarnings({ "unchecked", "rawtypes" })
    public SkipListSet(){
        size = 0;
        maxLevel = 0;
        maxCap = computeMaxCap(maxLevel);
        head = new SLNode(maxLevel, MIN);
    }

    /**
     * Add method for skiplist
     * @param item
     * @return true if item is added, false if item already exists.
     */
    @Override
    public boolean add(E item){
        if(size > 0)
            if (find(item) != null) return false;
        size++;
        SLNode<E>[] pred = search(item);
        if(size > maxCap){
            maxLevel++;
            maxCap = computeMaxCap(maxLevel);
            head.links = Arrays.copyOf(head.links, maxLevel);
            pred = Arrays.copyOf(pred, maxLevel);
            pred[maxLevel - 1] = head;
        }
        SLNode<E> newNode = new SLNode<E>(logRandom(), item);
        for(int i = 0; i < newNode.links.length; i++){
            newNode.links[i] = pred[i].links[i];
            pred[i].links[i] = newNode;
        }
        return true;
    }

    /**
     * removes given Object type item from skiplist
     * @param item
     * @return false if item is not in the list or list is empty, true if item is removed.
     */
    @Override
    public boolean remove(Object item){
        SLNode<E>[] pred = search((E) item);
        if(pred[0].links[0] != null &&
                pred[0].links[0].data.compareTo((E) item) != 0){
            return false; //item is not in the list
        } else {
            size--;
            SLNode<E> deleteNode = pred[0];
            for(int i = 0; i < deleteNode.links.length; i++){
                if(pred[i].links[i] != null)
                    pred[i].links[i] = pred[i].links[i].links[i];
            }
            return true;
        }
    }

    /**
     * Search for an item in the list
     * @param target The item being sought
     * @return An SLNode array which references the predecessors of the target at each level.
     */
    @SuppressWarnings("unchecked")
    private SLNode<E>[] search(E target){
        SLNode<E>[] pred = (SLNode<E>[]) new SLNode[maxLevel];
        SLNode<E> current = head;
        for(int i = current.links.length - 1; i >= 0; i--){
            while(current.links[i] != null
                    && current.links[i].data.compareTo(target) < 0){
                current = current.links[i];
            }
            pred[i] = current;
        }
        return pred;
    }

    /**
     * Find an object in the skip-list
     * @param target The item being sought
     * @return A reference to the object in the skip-list that matches
     * 		   the target. If not found, null is returned
     */
    public E find(E target){
        SLNode<E>[] pred = search(target);
        if(pred[0].links[0] != null &&
                pred[0].links[0].data.compareTo(target) == 0){
            return pred[0].links[0].data;
        } else {
            return null;
        }
    }

    /**
     * Method to generate a logarithmic distributed integer between 1 and maxLevel.
     *  I.E. 1/2 of the values are 1, 1/4 are 2, etc.
     * @return a random logarithmic distributed int between 1 and maxLevel
     */
    protected int logRandom(){
        int r = rand.nextInt(maxCap);
        int k = (int) (Math.log(r + 1) / LOG2);
        if(k > maxLevel - 1)
            k = maxLevel - 1;
        return maxLevel - k;
    }

    /**
     * Recompute the max cap
     * @param level
     * @return
     */
    protected int computeMaxCap(int level){
        return (int) Math.pow(2, level) - 1;
    }

    @SuppressWarnings("rawtypes")
    public String toString(){
        if(size == 0)
            return "Empty";
        StringBuilder sc = new StringBuilder();
        SLNode itr = head;
        sc.append("Head: " + maxLevel);
        int lineMaker = 0;
        while(itr.links[0] != null){
            itr = itr.links[0];
            sc.append(" --> " + itr.toString());
            lineMaker++;
            if(lineMaker == 10){
                sc.append("\n");
                lineMaker = 0;
            }
        }
        return sc.toString();
    }

    /**
     * Static class to contain data and links
     * @author Jacob / Koffman & Wolfgang
     *
     * @param <E> The type of data stored. Must be a Comparable
     */
    static class SLNode<E>{
        SLNode<E>[] links;
        E data;

        /**
         * Create a node of level m
         * @param m The level of the node
         * @param data The data to be stored
         */
        @SuppressWarnings("unchecked")
        public SLNode(int m, E data){
            links = (SLNode<E>[]) new SLNode[m];
            this.data = data;
        }

        public String toString(){
            return (data.toString() + " |" + links.length + "|");
        }
    }

    /**
     * toArray method, builds an object type array from skip-list
     * @return object array
     */
    @Override
    public Object[] toArray(){
        Object[] arr = new Object[size];
        int index = 0;
        SLNode itr = head;
        while(itr.links[0] != null){
            itr = itr.links[0];
            arr[index++] = itr.data;
        }
        return arr;
    }

    /**
     * Gives array in descending order
     * @return object type array
     */
    private Object[] descendingArray(){
        Object[] arr = toArray();
        Object [] descendingArr = new Object[arr.length];
        int j = 0;

        for(int i = arr.length - 1 ; i >= 0 ; --i){
            descendingArr[j++] = arr[i];
        }
        return descendingArr;
    }

    /**
     * implements Iterator
     * @param <T>
     */
    public class MySetIterator<T> implements Iterator<E>{
        int pos;
        E next;

        public MySetIterator(){
            pos = 0;
        }

        @Override
        public E next(){
            return (E) itArr[pos++];
        }

        @Override
        public boolean hasNext() {
            if(pos < size)
                return true;
            return false;
        }
    }

    /**
     * returns iterator in ascending order
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        itArr = new Object[size];
        itArr = toArray();
        return new MySetIterator();
    }


    /**
     * Returns descending view only iterator
     * @return
     */
    @Override
    public Iterator<E> descendingIterator() {
        itArr = new Object[size];
        itArr = descendingArray();
        return new MySetIterator();
    }


    @Override
    public NavigableSet<E> descendingSet() {
        return null;
    }


    @Override
    public E lower(E e) {
        return null;
    }

    @Override
    public E floor(E e) {
        return null;
    }

    @Override
    public E ceiling(E e) {
        return null;
    }

    @Override
    public E higher(E e) {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }


    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super E> comparator() {
        return null;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return null;
    }

    @Override
    public E first() {
        return null;
    }

    @Override
    public E last() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
