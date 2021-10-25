public class PositionalListTester {
    public static void main(String[] args) {
        PositionalList<Integer> pl = new LinkedPositionalList<Integer>();
        pl.addFirst(12);
        pl.addFirst(24);
        pl.addLast(29);
        pl.addAfter(pl.first(), 3);
        pl.addBefore(pl.first(), 17);
    }

}
