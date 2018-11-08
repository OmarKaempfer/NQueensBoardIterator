package BoardIterator;

public class MainClass {
    public static void main(String[] args) {

        int boardSize = 5;

        BoardIterator boardIterator = new BoardIterator(boardSize);
        BoardUtils boardUtils = new BoardUtils(boardSize);

        boardUtils.printSolutions(boardIterator);
    }
}
