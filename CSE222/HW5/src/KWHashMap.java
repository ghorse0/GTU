public interface KWHashMap<K, V> {

    /**
     *  Returns the value associated with the specified key.
     *  Returns null if the key is not present.
     * @return key if present else null
     */
    V get(Object key);

    /**
     * Checks if hashMap is empty
     * @return true if empty else false
     */
    boolean isEmpty();

    /**
     *  Puts given key and value to hashMap
     * @return previous value or null if it was empty
     */
    V put(K key, V value);


    /**
     *  Removes the mapping for this key from this table if it is present (optional
     *  operation). Returns the previous value associated with the specified key, or null if
     *   there was no mapping
     * @return null or previous value
     *
     */
    V remove(Object key);

    /**
     *
     * @return current size of hashMap
     */
    int size();
}
