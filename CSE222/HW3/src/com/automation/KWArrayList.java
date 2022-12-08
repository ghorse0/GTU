package com.automation;
import java.util.*;

/** This class implements some of the methods of the Java ArrayList class. It 
    does not implement the List interface.
*/

public class KWArrayList<E> {
    // Data Fields
    /** The default initial capacity */
    private static final int INITIAL_CAPACITY = 10;
    /** The underlying data array */
    private E[] theData;
    /** The current size */
    private int size = 0;
    /** The current capacity */
    private int capacity = 0;

    @SuppressWarnings("unchecked")
    public KWArrayList(){
        capacity = INITIAL_CAPACITY;
        this.theData = (E[]) new Object[capacity];
    }

    private void reallocate() {
        capacity = 2 * capacity;
        theData = Arrays.copyOf(theData, capacity);
    }
    
    public boolean add(E obj){
        if(size == capacity){
            reallocate();
        }
        theData[size] = obj;
        size++;
        return true;

    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return theData[index];
    }
    public E set(int index, E newValue) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E oldValue = theData[index];
        theData[index] = newValue;
        return oldValue;
    }
    

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        E returnValue = theData[index];
        for (int i = index + 1; i < size; i++) {
            theData[i - 1] = theData[i];
        }
        --size;
        return returnValue;
    }

    public int size(){
        return size;
    }

    public void print(){
        for(int i = 0 ; i < size ; ++i){
            System.out.println(get(i));
        }
    }

    public int getIndex(E obj){
        for(int i = 0 ; i < size ; ++i){
            if(obj.equals(theData[i])) return i;
        }
        return -1;
    }

}