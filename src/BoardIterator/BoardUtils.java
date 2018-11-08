package BoardIterator;

public class BoardUtils {
    public void printBoard(int[] board) {
        int rowIndex = 0;

        for(int i = 0; i < board.length; i++) {
            System.out.print(board[i] + " ");
            rowIndex++;

            if(rowIndex == Math.sqrt(board.length)) {
                rowIndex = 0;
                System.out.println();
            }
        }
    }
}
