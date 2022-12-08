package com.automation;

public class DynamicArray<T> {
    private Object[] data;
    private int capacity;
    private int used;

    public DynamicArray(){
        capacity = 1;
        used = 0;
        data = new Object[capacity];
;    }

    public T getData(int i){
        @SuppressWarnings("unchecked")
        final T data_ = (T) data[i];
        return data_;
    }
   
    public boolean empty(){
        return (used == 0);
    }
   
    public void clear(){
        used = 0;
        data = null;
    }
    
    public int max_size(){
        return capacity;
    }

    
    public int size(){
        return used;
    }

    
    public boolean erase(Object obj){
        boolean check = false;
        int index = -1;
        for(int i = 0 ; i < used ; ++i){
            if(data[i] == obj){
                check = true;
                index = i;
            }
        }

        if(check){
            for(int i = index ; i < used - 1; ++i){
                data[i] = data[i + 1];
            }
            --used;

        }
        return check;
    }

    public boolean eraseByIndex(int index){
        if(used < index) return false;
        boolean check = false;
        int tempIndex = 0;
        Object[] temp = new Object[capacity];
        
        for(int i = 0 ; i < used ; ++i){
            if(i != index){
                temp[tempIndex] = data[i];
                tempIndex++;
            }
            else{
                check = true;
            }
        }
        data = temp;
        used--;

        return check;
    }
    
    public void insert(T obj){
        if(capacity <= used || capacity == 0){
            Object temp[] = new Object[capacity];
            for(int i = 0 ; i < used ; ++i){
                temp[i] = data[i];        
            }
            
            data = new Object[capacity * 2];
            capacity *= 2;
            for(int i = 0 ; i < used ; ++i)
                data[i] = temp[i];
            data[used] = obj;
            ++used;
        } 
        else{
            data[used] = obj;
            ++used;
        }
    }

    public boolean update(int index, T val){
        if(index > used) return false;
        data[index] = val;
        return true;
    }

    
    public boolean contains(Object o){

        for(int i = 0 ; i < used ; ++i){
            if(data[i] == o)
                return true;
        }
        return false;
    }

    public void print(){
        for(int i = 0 ; i < used ; ++i){
            System.out.println(getData(i));
        }
    }

    public int getIndexByData(T obj){
        for(int i = 0 ; i < used ; ++i){
            if(obj.equals(data[i])) return i;
        }
        return -1;

    }
}
