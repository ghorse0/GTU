/**
 * Helper interface for testing
 * @param <E>
 */

public interface BenchI<E> {
    boolean add(E data);
    boolean remove(E data);
    void clearRoot();
}
