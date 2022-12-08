import java.util.LinkedList;

public class CoalescedHash <K, V>{

    /** Contains key-value pairs for a hash table. */
    private static class Entry<K, V> {

        /**
         * The key
         */
        private K key;
        /**
         * The value
         */
        private V value;

        /**
         * next link of the entry
         */
        private int next;
        /**
         * Creates a new key-value pair.
         *
         * @param key   The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = -1;
        }

        /**
         * Retrieves the key.
         *
         * @return The key
         */
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value.
         *
         * @return The value
         */
        public V getValue() {
            return value;
        }

        /**
         * Sets the value.
         *
         * @param val The new value
         * @return The old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }

        public K setKey(K val){
            K oldKey = key;
            key = val;
            return oldKey;
        }

        public int setNext(int val){
            int oldNext = next;
            next = val;
            return oldNext;
        }

        public int getNext(){ return next; }

        /**
         * Returns a string representation of the Entry.
         *
         * @return The string in format "key=value"
         */
        public String toString() {
            return key + "=" + value;
        }
    }

    /** The table */
    private Entry<K,V>[] table;
    /** The number of keys */
    private int numKeys;
    private int numDeletes;
    /** The capacity */
    private static final int CAPACITY = 101;
    /** The maximum load factor */
    private static final double LOAD_THRESHOLD = 0.75;
    int[] probe;
    private final Entry<K, V> DELETED =
            new Entry<>(null, null);

    public CoalescedHash(){
        probe = new int[CAPACITY];
        table = new Entry[CAPACITY];
    }

    public CoalescedHash(int cap){
        probe = new int[cap];
        table = new Entry[cap];
    }


    /** Finds either the target key or the first empty slot in the
     search chain using linear probing.
     @param key The key of the target object
     @return The position of the target or the first empty slot if
     the target is not in the table.
     */
    private int find(Object key) {// Calculate the starting index.
        int startIndex = key.hashCode() % table.length;
        int lastIndex = -1;
        int index = startIndex;
        if (index < 0)
            index += table.length; // Make it positive.
        // Increment index until an empty slot is reached
        // or the key is found.
        if(table[index] != null && probe[index] != 0) {
            lastIndex = (index + (probe[index]-1) * (probe[index]-1)) % table.length;
        }
        int tempIndex = startIndex;
        while ((table[index] != null)
                && (!key.equals(table[index].getKey()))) {
            index = (tempIndex + probe[startIndex] * probe[startIndex]) % table.length;
            tempIndex++;
        // Check for wraparound.
            if (index >= table.length)
                index = 0; // Wrap around.
        }
        if(lastIndex != -1 && table[lastIndex] != null)
            table[lastIndex].setNext(index);
        probe[startIndex]++;
        return index;
    }

    /** Method get for class HashtableOpen.
     @param key The key being sought
     @return the value associated with this key if found;
     otherwise, null
     */
    public V get(Object key) {
        int index = find(key);
        if (table[index] != null)
            return table[index].getValue();
        else
            return null; // key not found.
    }

    /** Method put for class HashtableOpen.
     @post This key‐value pair is inserted in the
     table and numKeys is incremented. If the key is already
     in the table, its value is changed to the argument
     value and numKeys is not changed. If the LOAD_THRESHOLD
     is exceeded, the table is expanded.
     @param key The key of item being inserted
     @param value The value for this key
     @return Old value associated with this key if found;
     otherwise, null
     */
    public V put(K key, V value) {
        // Find the first table element that is empty
        // or the table element that contains the key.
        int index = find(key);
        // If an empty element was found, insert new entry.
        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
            numKeys++;
            // Check whether rehash is needed.
            double loadFactor =
                    (double) (numKeys + numDeletes) / table.length;
            if (loadFactor > LOAD_THRESHOLD)
                rehash();
            return null;
        }
        // assert: table element that contains the key was found.
        // Replace value for this key.
        V oldVal = table[index].getValue();
        table[index].setValue(value);
        return oldVal;
    }


    /** Expands table size when loadFactor exceeds LOAD_THRESHOLD
     @post The size of the table is doubled and is an odd integer.
     Each nondeleted entry from the original table is
     reinserted into the expanded table.
     The value of numKeys is reset to the number of items
     actually inserted; numDeletes is reset to 0.
     */
    private void rehash() {
        // Save a reference to oldTable.
        Entry<K, V>[] oldTable = table;
        int[] oldProbe = probe;
        // Double capacity of this table.
        table = new Entry[2 * oldTable.length + 1];
        probe = new int[2 * oldProbe.length + 1];
        // Reinsert all items in oldTable into expanded table.
        numKeys = 0;
        numDeletes = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if ((oldTable[i] != null) && (oldTable[i] != DELETED)) {
                // Insert entry in expanded table
                put(oldTable[i].getKey(), oldTable[i].getValue());
                probe[i] = oldProbe[i];
                numKeys++;
            }
        }
    }

    /**
     * Remove function
     * @param key
     * @return old value or null
     */
    public V remove(Object key) {
        int index = key.hashCode() % table.length;
        int startIndex = index;
        int temp = index;
        if(!table[index].getKey().equals(key)){
            for(int i = 0 ; i < probe[startIndex] ; ++i){
                index = table[index].getNext();
                if(index == -1) return null;
                if(table[index].getKey().equals(key)) break;
            }
        }
        if (table[index] == null){
            return null; // key is not in table
        }
        Entry<K,V> entry = table[index];
        if (entry.getKey().equals(key)){
            V value = entry.getValue();
            removeNext(index);
            probe[index]--;
            numKeys--;
            numDeletes++;
            return value;
        }
        return null;
    }

    /**
     * Helper for remove function, if given key has next, changes next of the given key to next
     * Arranges the next links after removing given index from hashTable.
     * @param index
     */
    private void removeNext(int index){
        int startIndex = index;
        int oldIndex = index;
        for(int i = 0 ; i <= probe[startIndex] ; ++i){
            int nextIndex = table[index].getNext();
            if(nextIndex == -1){
                table[index] = DELETED;
                table[oldIndex].setNext(-1);
                return;
            }
            table[index].setKey(table[nextIndex].getKey());
            table[index].setValue(table[nextIndex].getValue());
            oldIndex = index;
            index = nextIndex;
            table[oldIndex].setNext(nextIndex);
            if(i == probe[startIndex]) {
                table[index] = DELETED;
                table[oldIndex].setNext(-1);
            }
        }


    }

    public int size(){
        return numKeys;
    }


    public void print(){
        int i = 0;
        System.out.println("Index-key-value-next");
        for(Entry<K,V> entry : table){
            if(entry != null){
                System.out.print(i + ": ");
                    System.out.print(entry.getKey() + "-");
                    System.out.print(entry.getValue() + "-");
                    if(entry.getNext() == -1) System.out.print("NULL");
                    else
                        System.out.print(entry.getNext());
                System.out.print("\n");
            }
            /*else{
                System.out.print("null-null\n");
            }*/
            ++i;
        }
    }



}
