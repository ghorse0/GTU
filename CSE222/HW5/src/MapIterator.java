public interface MapIterator<T>{

    T next();
    T prev();

    boolean hasNext();
}