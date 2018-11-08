package BoardIterator;

public class MainClass {
    public static void main(String[] args) {
        BoardIterator boardIterator = new BoardIterator(5);
        BoardUtils boardUtils = new BoardUtils();

        while(boardIterator.hasNext()) {
            boardUtils.printBoard(boardIterator.next());
            System.out.println();
        }

    }
}
