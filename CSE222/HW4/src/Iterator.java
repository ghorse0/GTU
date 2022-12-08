
/**
* Generic java iterator class
* 
*/
public interface Iterator<E> {
    int pos = 0;


    public E next();
    public E set(E val);
    public boolean hasNext();
    
}
