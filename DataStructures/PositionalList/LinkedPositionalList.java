import java.util.NoSuchElementException;

public class LinkedPositionalList<T> implements PositionalList<T> {

    // classe interna che identifica un nodo appartenente alla positional list
    private static class Node<T> implements Position<T> {

        private T element; // riferimento all'elemento puntato dal nodo
        private Node<T> next; // riferimento al nodo successivo
        private Node<T> prev; // riferimento al nodo precedente

        public Node(T element, Node<T> prev, Node<T> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public Node() {
            element = null;
            prev = null;
            next = null;
        }

        // accesso in lettura all'elemento puntanto da questo nodo
        public T getElement() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return element;
        }

        // accesso in lettura al riferimento puntante il nodo successivo
        public Node<T> getNext() {
            return next;
        }

        // accesso in lettura al riferimento puntante il nodo precedente
        public Node<T> getPrev() {
            return prev;
        }

        // accesso in scrittura al riferimento puntante il nodo successivo
        public void setNext(Node<T> next) {
            this.next = next;
        }

        // accesso in scrittura al riferimento puntante il nodo precedente
        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        // accesso in scrittura al dato trasportato
        public void setElement(T element) {
            this.element = element;
        }

        public boolean hasNext() {
            return next != null;
        }

    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedPositionalList() {
        size = 0;
        head = new Node<>();
        tail = new Node<>(null, head, null);
        head.setNext(tail);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<T> first() {
        return head.getNext();
    }

    @Override
    public Position<T> last() {
        return tail.getPrev();
    }

    @Override
    public Position<T> before(Position<T> p) throws NoSuchElementException {
        Node<T> node = check(p);
        return position(node.getPrev());
    }

    @Override
    public Position<T> after(Position<T> p) throws NoSuchElementException {
        Node<T> node = check(p);
        return position(node.getNext());
    }

    @Override
    public Position<T> addFirst(T e) {
        return addBetween(e, head, head.getNext());
    }

    @Override
    public Position<T> addLast(T e) {
        return addBetween(e, tail.getPrev(), tail);
    }

    @Override
    public Position<T> addAfter(Position<T> p, T e) throws NoSuchElementException {
        Node<T> node = check(p);
        return addBetween(e, node, node.next);
    }

    @Override
    public Position<T> addBefore(Position<T> p, T e) throws NoSuchElementException {
        Node<T> node = check(p);
        return addBetween(e, node.prev, node);
    }

    @Override
    public T set(Position<T> p, T e) {
        Node<T> node = check(p);
        T ans = node.getElement();
        node.setElement(e);
        return ans;
    }

    @Override
    public T remove(Position<T> p) {
        Node<T> node = check(p);
        Node<T> _prev = node.prev;
        Node<T> _next = node.next;

        _prev.setNext(_next);
        _next.setPrev(_prev);
        size--;

        T ans = node.getElement();
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return ans;
    }

    private Node<T> check(Position<T> position) throws IllegalArgumentException {
        if (!(position instanceof Node)) {
            throw new IllegalArgumentException();
        }
        Node<T> node = (Node<T>) position;
        if (!node.hasNext()) {
            throw new IllegalArgumentException();
        }
        return node;
    }

    private Position<T> position(Node<T> node) {
        if (node == head || node == tail) {
            return null;
        }
        return node;
    }

    private Position<T> addBetween(T element, Node<T> prev, Node<T> next) {
        Node<T> _node = new Node<T>(element, prev, next);
        prev.setNext(_node);
        next.setPrev(_node);
        size++;
        return _node;
    }
}