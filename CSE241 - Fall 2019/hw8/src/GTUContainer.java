

public abstract class GTUContainer<T>{

    protected T[] data;
    protected int capacity;
    protected int used;
    protected Class<T[]> type_;
   
     /**
     * checks if container is empty
     * @return boolean
     */  
    public abstract boolean empty();
    /**
     * returns size of container
     * @return int
     */ 
    public abstract int size();

    /**
     * returns capacity of container
     * @return int
     */ 
    public abstract int max_size();
    /**
     * inserts value to container,in set its not possible to insert same values.
     * throws exception if same value.
     */ 
    public abstract void insert(T val);
    /**
     * erasing a value that is in container if it's not in container does nothing.
     *
     */ 
    public abstract void erase(T val);
        /**
     * clears container
     *
     */ 
    public abstract void clear();  
            /**
     * returns iterator pointing to begin
     *
     */
    public abstract GTUIterator<T> iterator();

            /**
     * checks if object is in container
     *@param Object o
     */
    public abstract boolean contains(Object o);


}