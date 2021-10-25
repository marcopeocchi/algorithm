import java.util.NoSuchElementException;

interface PositionalList<T> {
    int size();

    boolean isEmpty();

    Position<T> first();

    Position<T> last();

    Position<T> before(Position<T> p) throws NoSuchElementException;

    Position<T> after(Position<T> p) throws NoSuchElementException;

    Position<T> addFirst(T e);

    Position<T> addLast(T e);

    Position<T> addBefore(Position<T> p, T e) throws NoSuchElementException;

    Position<T> addAfter(Position<T> p, T e) throws NoSuchElementException;

    T set(Position<T> p, T e);

    T remove(Position<T> p);
}