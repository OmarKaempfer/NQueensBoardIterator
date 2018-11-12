package BoardIterator;

import java.util.ArrayList;
import java.util.List;

public class BoardUtils {
    private int[] columns;
    private int[] rows;
    private int[] descendingDiagonals;
    private int[] ascendingDiagonals;

    private int boardSize;

    public BoardUtils(int boardSize) {
        this.boardSize = boardSize;
        columns = new int[boardSize];
        rows = new int[boardSize];
        descendingDiagonals = new int[2*boardSize - 1];
        ascendingDiagonals = new int[2*boardSize - 1];
    }

    public void printBoard(int[] board) {
        int rowIndex = 0;

        for(int i = 0; i < board.length; i++) {
            System.out.print(board[i] + " ");
            rowIndex++;

            if(rowIndex == boardSize) {
                rowIndex = 0;
                System.out.println();
            }
        }
    }

    public boolean isSolution(int[] board) {
        int rowIndex;
        int columnIndex;

        for(int i = 0; i < board.length; i++) {
            if(board[i] == 0) {
                continue;
            }

            rowIndex = i/boardSize;
            columnIndex = i%boardSize;
            if(rows[rowIndex] == 1 || columns[columnIndex] == 1
                    || descendingDiagonals[columnIndex - rowIndex + boardSize - 1] == 1
                    || ascendingDiagonals[rowIndex + columnIndex] == 1) {
                resetAuxArrays();
                return false;
            }
            columns[columnIndex]++;
            rows[rowIndex]++;
            descendingDiagonals[columnIndex - rowIndex + boardSize - 1]++;
            ascendingDiagonals[rowIndex + columnIndex]++;
        }

        return true;
    }

    public void printAllSolutions(BoardIterator boardIterator) {
        List<int[]> solutions = findAllSolutions(boardIterator);
        for(int[] solution : solutions) {
            printBoard(solution);
            System.out.println();
        }
    }

    public List<int[]> findAllSolutions(BoardIterator boardIterator) {
        int[] currentBoard;
        List<int[]> solutions = new ArrayList<>();
        while(boardIterator.hasNext()) {
            currentBoard = boardIterator.next();
            if(isSolution(currentBoard)) {
                solutions.add(currentBoard.clone());
            }
        }

        return solutions;
    }

    public void resetAuxArrays() {
        for(int i = 0; i < rows.length; i++) {
            rows[i] = 0;
            columns[i] = 0;
        }

        for(int i = 0; i < descendingDiagonals.length; i++) {
            descendingDiagonals[i] = 0;
            ascendingDiagonals[i] = 0;
        }
    }

    public int[] findFirstSolution(BoardIterator boardIterator) {
        int[] currentBoard;
        while(boardIterator.hasNext()) {
            currentBoard = boardIterator.next();
            if(isSolution(currentBoard)) {
                return currentBoard;
            }
        }
        return null;
    }
}
