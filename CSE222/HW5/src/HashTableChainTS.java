import java.util.TreeSet;

public class HashTableChainTS<K extends Comparable<K>, V> implements KWHashMap<K,V> {



        /** Contains key-value pairs for a hash table. */
        private static class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K,V>>{

            /** The key */
            private K key;
            /** The value */
            private V value;

            /**
             * Creates a new key-value pair.
             * @param key The key
             * @param value The value
             */
            public Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }

            /**
             * Retrieves the key.
             * @return The key
             */
            public K getKey() {
                return key;
            }

            /**
             * Retrieves the value.
             * @return The value
             */
            public V getValue() {
                return value;
            }

            public int compareTo(Entry<K,V> obj){
                return key.compareTo(obj.getKey());
            }

            /**
             * Sets the value.
             * @param val The new value
             * @return The old value
             */
            public V setValue(V val) {
                V oldVal = value;
                value = val;
                return oldVal;
            }

            /**
             * Returns a string representation of the Entry.
             * @return The string in format "key=value"
             */
            public String toString() {
                return key + "=" + value;
            }
        }



        /** The table */
        private TreeSet<Entry<K, V>>[] table;
        /** The number of keys */
        private int numKeys;
        /** The capacity */
        private static final int CAPACITY = 101;
        /** The maximum load factor */
        private static final double LOAD_THRESHOLD = 3.0;

        public HashTableChainTS(){
            table = new TreeSet[CAPACITY];
        }

        public HashTableChainTS(int cap){
            table = new TreeSet[cap];
        }


        /**
         * Method get for class HashtableChain.
         * @param key The key being sought
         * @return The value associated with this key if found;
         *         otherwise, null
         */
        public V get(Object key) {
            int index = key.hashCode() % table.length;
            if (index < 0) {
                index += table.length;
            }
            if (table[index] == null) {
                return null; // key is not in the table.
            }
            // Search the list at table[index] to find the key.
            for (Entry<K, V> nextItem : table[index]) {
                if (nextItem.key.equals(key)) {
                    return nextItem.value;
                }
            }

            // assert: key is not in the table.
            return null;
        }



        /**
         * Method put for class HashtableChain.
         *       This key-value pair is inserted in the
         *       table and numKeys is incremented. If the key is already
         *       in the table, its value is changed to the argument
         *       value and numKeys is not changed.
         * @param key The key of item being inserted
         * @param value The value for this key
         * @return The old value associated with this key if
         *         found; otherwise, null
         */
        public V put(K key, V value) {
            int index = key.hashCode() % table.length;
            if (index < 0) {
                index += table.length;
            }
            if (table[index] == null) {
                // Create a new treeSet at table[index].
                table[index] = new TreeSet<Entry<K, V>>();
            }

            // Search the list at table[index] to find the key.
            for (Entry<K, V> nextItem : table[index]) {
                // If the search is successful, replace the old value.
                if (nextItem.key.equals(key)) {
                    // Replace value for this key.
                    V oldVal = nextItem.value;
                    nextItem.setValue(value);
                    return oldVal;
                }
            }

            // assert: key is not in the table, add new item.
            table[index].add(new Entry<K, V>(key, value));
            numKeys++;
            if (numKeys > (LOAD_THRESHOLD * table.length)) {
                rehash();
            }
            return null;
        }

        /**
         * Method to rehash table.
         */
        private void rehash() {
            TreeSet<Entry<K, V>>[] oldTable = table;
            table = new TreeSet[oldTable.length * 2 + 1];
            numKeys = 0;
            for (TreeSet<Entry<K, V>> set : oldTable) {
                if (set != null) {
                    for (Entry<K, V> entry : set) {
                        put(entry.getKey(), entry.getValue());
                        numKeys++;
                    }
                }
            }
        }

        /** Returns true if empty */
        public boolean isEmpty() {
            return numKeys == 0;
        }

        public int size(){
            return numKeys;
        }

        /*
         * Removes the mapping for a key from this map if it is present.
         *
         */
        public V remove(Object key) {
            int index = key.hashCode() % table.length;
            if (index < 0){
                index += table.length;
            }
            if (table[index] == null){
                return null; // key is not in table
            }
            for (Entry<K, V> entry : table[index]){
                if (entry.getKey().equals(key)){
                    V value = entry.getValue();
                    table[index].remove(entry);
                    numKeys--;
                    if (table[index].isEmpty()){
                        table[index] = null;
                    }
                    return value;
                }
            }
            return null;
        }

        public void print(){
            int i = 0;
            boolean check = false;
            System.out.println("Index-KEY-VALUE");
            for(TreeSet<Entry<K,V>> list : table){
                System.out.printf("%d: ",i);
                if(list != null){
                    for(Entry<K, V> entry : list){
                        if(!check) {
                            System.out.print(entry.getKey() + "-");
                            check = true;
                        }
                        System.out.print(entry.getValue() + " ");
                        check = false;
                    }
                    System.out.print("\n");
                }
            else{
                System.out.print("null-null\n");
            }
            ++i;
            }
        }



}
