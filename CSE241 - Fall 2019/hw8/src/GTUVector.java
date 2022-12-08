import java.lang.reflect.Array;
import java.security.InvalidParameterException;

public class GTUVector<T> extends GTUContainer<T>{

    public GTUVector(Class<T[]> type){
        type_ = type;
        capacity = 1;
        used = 0;
        data = type.cast(Array.newInstance(type.getComponentType(),capacity));
    }

    public class GTUVectorIterator implements GTUIterator<T>{
        int pos = 0;

        public T next(){
            return data[pos++];
        }

        public boolean hasNext(){
            if(pos < used)
                return true;
            
            return false;
        }
    }
    @Override
    public boolean empty(){
        return (used == 0);
    }
    @Override
    public void clear(){
        used = 0;
        data = null;
    }
    @Override
    public int max_size(){
        return capacity;
    }

    @Override
    public int size(){
        return used;
    }

    @Override
    public void erase(Object obj){
        boolean check = false;
        int index = -1;
        for(int i = 0 ; i < used ; ++i){
            if(data[i] == obj)
                check = true;
                index = i;
        }

        if(check){
            for(int i = index ; i < used - 1; ++i){
                data[i] = data[i + 1];
            }
            --used;

        }
    }

    @Override
    public void insert(T obj) throws InvalidParameterException{
        if(capacity <= used || capacity == 0){
            T[] temp = type_.cast(Array.newInstance(type_.getComponentType(), capacity * 2));
            for(int i = 0 ; i < used ; ++i){
                temp[i] = data[i];         
            }
            
            data = type_.cast(Array.newInstance(type_.getComponentType(), capacity * 2));
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

    @Override
    public boolean contains(Object o){

        for(int i = 0 ; i < used ; ++i){
            if(data[i] == o)
                return true;
        }
        return false;
    }

    public GTUIterator<T> iterator(){
        return new GTUVectorIterator();
    }





}