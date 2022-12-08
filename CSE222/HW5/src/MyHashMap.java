import java.util.HashMap;

public class MyHashMap<K, V> extends HashMap<K, V> {
    public K[] toArray(){
        return (K[]) keySet().toArray();
    }

    public K indexedGet(int pos){
        K[] arr = toArray();
        return arr[pos];
    }

    public int getIndex(K key){
        K[] arr = toArray();
        for(int i = 0 ; i < size() ; ++i){
            if(key == arr[i]) return i;
        }
        return -1;
    }

    public class MyMapIterator<T> implements MapIterator{
        private int pos;
        private K firstEntry;
        private boolean check = false, check2 = true;

        MyMapIterator(){
            pos = 0;
            firstEntry = indexedGet(pos);
        }

        MyMapIterator(K key){

            pos = getIndex(key);
            firstEntry = indexedGet(pos);
        }

        public K next(){
            if(pos == size()) pos = 0;
            return indexedGet(pos++);
        }

        public K prev(){
            if(pos == 0) pos = size();
            return indexedGet(--pos);
        }

        public boolean hasNext(){
            int tempIndex = getIndex(firstEntry);
            if(tempIndex == 0){
                if(check2 == false) return false;
                if(pos < size()) return true || check2;
                check2 = false;
                return false;
            }
            if (check == true && pos == tempIndex) {
                check2 = false;
            }
            if (pos - 1 == tempIndex) {
                check = true;
            }
            if (!check2) return false;

            return true;
        }
    }
    public MapIterator<K> iterator(){
        return new MyMapIterator();
    }

    public MapIterator<K> iterator(K key){
        return new MyMapIterator(key);
    }

}
