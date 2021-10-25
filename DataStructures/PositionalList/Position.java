import java.util.NoSuchElementException;

interface Position<T> {
    T getElement() throws NoSuchElementException;
}