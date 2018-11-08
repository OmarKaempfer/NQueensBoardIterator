package BoardIterator;

public class BoardIterator {
    int[] indexes;
    int[] board;
    int boardSize;
    int[] nextBoard;

    public BoardIterator(int boardSize) {
        this.boardSize = boardSize;
        init();
    }

    private void init() {
        board = new int[boardSize*boardSize];
        indexes = new int[boardSize];
        for(int i = 0; i < boardSize; i++) {
            board[i] = 1;
            indexes[i] = i;
        }
        nextBoard = board.clone();
    }

    public boolean hasNext() {
        return indexes[0] != board.length - indexes.length;
    }

    public int[] next() {
        if(nextBoard != null) {
            int[] tmpBoard = nextBoard;
            nextBoard = null;
            return tmpBoard;
        }

        int j = 0;
        for(int i = indexes.length - 1; i >= 0; i--) {
            if(indexes[i] == board.length - 1 - j) {
                j++;
                continue;
            }
            board[indexes[i]] = 0;
            indexes[i]++;
            board[indexes[i]] = 1;

            for(int k = i + 1; k < indexes.length; k++) {
                board[indexes[k]] = 0;
                indexes[k] = indexes[k - 1] + 1;
                board[indexes[k]] = 1;
            }
            break;
        }

        return board;
    }
}
