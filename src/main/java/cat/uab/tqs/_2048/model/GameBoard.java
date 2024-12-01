package cat.uab.tqs._2048.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {
    private int[][] board;
    private final int MAX_LENGTH = 4;
    private String movement;
    private boolean isGameOver = false;

    public int getMaxLength() {
        return MAX_LENGTH;
    }

    public GameBoard() {
        this.board = new int[MAX_LENGTH][MAX_LENGTH];
    }

    public int[][] getBoard() {
        return board;
    }

    public void setGameBoard(int[][] newBoard) {
        if (newBoard.length == MAX_LENGTH && newBoard[0].length == MAX_LENGTH) {
            board = newBoard;
        } else {
            throw new IllegalArgumentException("Invalid board size.");
        }
    }

    // Handles the swipe left movement at pressing "a" key.
    public void handleSwipeLeft() { 
        movement = "left";
        boolean hasBoardChanged = false;

        for (int row = 0; row < MAX_LENGTH; row++) {
            boolean rowChanged = processMovements(board[row], movement);
            if (rowChanged) {
                hasBoardChanged = true;
            }
        }

        if (hasBoardChanged) {
            spawnTile();
            /* int[][] predefinedTiles = new int[][] {
                    { 0, 3, 2 } // row, column, value
            };

            MockSpawnTile mockBoard = new MockSpawnTile(predefinedTiles);


            mockBoard.mergePredefinedTiles(getBoard(), predefinedTiles); */
        }

        isGameOver = isGameOver(board);
    }

    // Handles the swipe right movement at pressing "d" key.
    public void handleSwipeRight() {
        movement = "right";
        boolean hasBoardChanged = false;

        for (int row = 0; row < MAX_LENGTH; row++) {
            boolean rowChanged = processMovements(board[row], movement);
            if (rowChanged) {
                hasBoardChanged = true;
            }
        }

        if (hasBoardChanged) {
            spawnTile();
        }

        isGameOver = isGameOver(board);
    }

    // Handles the swipe up movement at pressing "w" key.
    public void handleSwipeUp() {
        movement = "up";
        boolean hasBoardChanged = false;

        for (int col = 0; col < MAX_LENGTH; col++) {
            int[] column = new int[MAX_LENGTH];
            for (int row = 0; row < MAX_LENGTH; row++) {
                column[row] = board[row][col];
            }

            boolean columnChanged = processMovements(column, movement);

            if (columnChanged) {
                for (int row = 0; row < MAX_LENGTH; row++) {
                    board[row][col] = column[row];
                }
                hasBoardChanged = true;
            }
        }

        if (hasBoardChanged) {
            spawnTile();
        }

        isGameOver = isGameOver(board);
    }

    // Handles the swipe down movement at pressing "s" key.
    public void handleSwipeDown() {
        movement = "down";
        boolean hasBoardChanged = false;

        for (int col = 0; col < MAX_LENGTH; col++) {
            int[] column = new int[MAX_LENGTH];
            for (int row = 0; row < MAX_LENGTH; row++) {
                column[row] = board[row][col];
            }
            boolean columnChanged = processMovements(column, movement);

            if (columnChanged) {
                for (int row = 0; row < MAX_LENGTH; row++) {
                    board[row][col] = column[row];
                }
                hasBoardChanged = true;
            }
        }

        if (hasBoardChanged) {
            spawnTile();
        }

        isGameOver = isGameOver(board);
    }

    //Spawns a new tile on the board in an empty cell.
    public void spawnTile() {
        List<int[]> emptyTiles = new ArrayList<>();
        for (int i = 0; i < MAX_LENGTH; i++) {
            for (int j = 0; j < MAX_LENGTH; j++) {
                if (board[i][j] == 0) {
                    emptyTiles.add(new int[] { i, j });
                }
            }
        }

        if (emptyTiles.isEmpty()) {
            return;
        }

        Random random = new Random();
        int[] position = emptyTiles.get(random.nextInt(emptyTiles.size()));

        board[position[0]][position[1]] = random.nextDouble() < 0.9 ? 2 : 4;
    }

    // Reverses the order of the elements in an array in order to process the movements.
    public void reverseArray(int[] row) {
        int start = 0;
        int end = row.length - 1;

        while (start < end) {
            int temp = row[start];
            row[start] = row[end];
            row[end] = temp;
            start++;
            end--;
        }
    }

    // Processes the movements of the tiles in the board according to the swipe direction.
    public boolean processMovements(int[] row, String movement) {
        int[] originalRow = row.clone();

        if (movement.equals("right") || movement.equals("down")) {
            reverseArray(row);
        }

        int[] compressedRow = compress(row);
        int[] mergedRow = merge(compressedRow);
        int[] finalRow = compress(mergedRow);

        if (movement.equals("right") || movement.equals("down")) {
            reverseArray(finalRow);
        }

        System.arraycopy(finalRow, 0, row, 0, row.length);

        return !java.util.Arrays.equals(originalRow, row); // Returning False if the comparison is TRUE (no possible movements)
    }

    // Compresses the row by removing the empty cells.
    public int[] compress(int[] row) {
        int[] newRow = new int[MAX_LENGTH];
        int index = 0;

        for (int i = 0; i < MAX_LENGTH; i++) {
            if (row[i] != 0) {
                newRow[index] = row[i];
                index++;
            }
        }
        return newRow;
    }

    // Merges the tiles in the row if they have the same value.
    public int[] merge(int[] row) {
        List<Integer> mergedRow = new ArrayList<>();
        boolean[] hasMerged = new boolean[MAX_LENGTH];

        for (int i = 0; i < MAX_LENGTH; i++) {
            if (i < MAX_LENGTH - 1 && row[i] == row[i + 1] && !hasMerged[i] && !hasMerged[i + 1]) {
                mergedRow.add(row[i] * 2);
                hasMerged[i] = true;
                row[i + 1] = 0;
            } else if (!hasMerged[i]) {
                mergedRow.add(row[i]);
            }
        }

        while (mergedRow.size() < MAX_LENGTH) {
            mergedRow.add(0);
        }

        int[] mergedArray = new int[MAX_LENGTH];
        for (int i = 0; i < mergedRow.size(); i++) {
            mergedArray[i] = mergedRow.get(i);
        }

        return mergedArray;
    }

    // Calculates the score of the game by summing all the values in the board.
    public int calculateScore() {
        int score = 0;
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                score += board[i][j];
            }
        }
        return score;
    }

    // Checks if the game is over by verifying if there are no empty cells and no possible moves.
    public boolean isGameOver(int[][] board) {
        // Traverse each cell of the board
        for (int i = 0; i < MAX_LENGTH; i++) {
            for (int j = 0; j < MAX_LENGTH; j++) {
                // If there is an empty cell, the game is not over
                if (board[i][j] == 0) {
                    return false;
                }

                // Check possible moves horizontally and vertically
                if (j < MAX_LENGTH - 1 && board[i][j] == board[i][j + 1]) { // Right
                    return false;
                }
                if (i < MAX_LENGTH - 1 && board[i][j] == board[i + 1][j]) { // Down
                    return false;
                }
            }
        }
        // If there are no empty cells and no possible moves, the game is over
        return true;
    }
}