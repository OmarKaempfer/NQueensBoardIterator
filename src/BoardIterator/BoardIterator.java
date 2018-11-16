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
    /* First board is a board_size x board_size one
     * with board_size queens one next to each other
     * in the first row.
     * Queens are 1s, empty positions are 0s.
     * 'indexes' stores the position of each queen.
     */
    private void init() {
        board = new int[boardSize*boardSize];
        indexes = new int[boardSize];
        for(int i = 0; i < boardSize; i++) {
            board[i] = 1;
            indexes[i] = i;
        }
        nextBoard = board.clone();
    }

    // Checks whether the last movable queen is in its last
    // possible position or not
    public boolean hasNext() {
        return indexes[0] != board.length - indexes.length;
    }

    public int[] next() {

        // On the first 'next' call we just return
        // the initialized board
        if(nextBoard != null) {
            int[] tmpBoard = nextBoard;
            nextBoard = null;
            return tmpBoard;
        }

        int j = 0;
        for(int i = indexes.length - 1; i >= 0; i--) {

            /* Checks whether the current queen has reached its
             * last possible position or not. If it did, we now
             * check the next queen in line
             */
            if(indexes[i] == board.length - 1 - j) {
                j++;
                continue;
            }
            // Moves the queen to the next position, clearing
            // the previous one
            board[indexes[i]] = 0;
            indexes[i]++;
            board[indexes[i]] = 1;

            // Moves each queen which was in its last position
            // to their new initial position
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
