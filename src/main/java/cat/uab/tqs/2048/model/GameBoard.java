package cat.uab.tqs._2048.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {
    private int[][] board;
    private final int MAX_LENGTH = 4;
    private String movement;
    private boolean isGameOver = false;

    public GameBoard() {
        board = new int[MAX_LENGTH][MAX_LENGTH];
        spawnTile();
        spawnTile();
    }

    public int[][] getBoard() {
        return board;
    }

    public void setGameBoard(int [][] newBoard){
        if (newBoard.length == MAX_LENGTH && newBoard[0].length == MAX_LENGTH) {
            board = newBoard;
        }
        else throw new IllegalArgumentException("Invalid board size.");
    }

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
        }

        isGameOver = isGameOver(board);
    }

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

    private void reverseArray(int[] row) {
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

    private boolean processMovements(int[] llista, String movement) {

        if (movement == "right" || movement == "down") {
            reverseArray(llista);
        }
        int[] originalRow = llista.clone();
        int[] compressedRow = compress(llista);

        int[] mergedRow = merge(compressedRow);

        int[] finalRow = compress(mergedRow);

        System.arraycopy(finalRow, 0, llista, 0, llista.length);

        return !java.util.Arrays.equals(originalRow, llista);
    }

    private int[] compress(int[] row) {
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

    public boolean isGameOver(int[][] board) {
        isGameOver = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    isGameOver = false;
                }
            }
        }

        return isGameOver;
    }
}
