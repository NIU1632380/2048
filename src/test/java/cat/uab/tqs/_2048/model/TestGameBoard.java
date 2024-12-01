package cat.uab.tqs._2048.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class GameBoardTest {
    private GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard();
    }

    @Test
    void testSetGameBoardValidBoard() {
        int[][] newBoard = {
                { 2, 4, 8, 16 },
                { 32, 64, 128, 256 },
                { 512, 1024, 2048, 0 },
                { 0, 0, 0, 0 }
        };
        GameBoard gameBoard = new GameBoard();
        gameBoard.setGameBoard(newBoard);
        int[][] board = gameBoard.getBoard();
        assertArrayEquals(newBoard, board, "Board should be set correctly.");
    }

    @Test
    void testSetGameBoardInvalidBoard() {
        int[][] invalidBoard = {
                { 2, 4, 8 },
                { 32, 64, 128 },
                { 512, 1024, 2048 },
                { 0, 0, 0, 0 }
        };
        GameBoard gameBoard = new GameBoard();
        assertThrows(IllegalArgumentException.class, () -> gameBoard.setGameBoard(invalidBoard),
                "Should throw exception for invalid board size.");
    }

    @Test
    void testInitialBoardHasTwoTiles() {
        // Initialize the board with a mock or the object you are using to control it.
        int[][] predefinedTiles = new int[][] {
                { 0, 0, 2 }, // Row 0, Column 0, Value 2
                { 1, 1, 4 } // Row 1, Column 1, Value 4
        };

        // Use MockSpawnTile if necessary or directly GameBoard
        MockSpawnTile mockBoard = new MockSpawnTile(predefinedTiles);

        // Call spawnTile to place two tiles on the board
        mockBoard.spawnTile();
        mockBoard.spawnTile();

        int counter = 0;
        int[][] board = mockBoard.getBoard();
        // Count the tiles that are not zero (the generated tiles)
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    counter++;
                }
            }
        }

        // Verify that there are exactly two tiles
        assertEquals(2, counter, "The board should start with exactly 2 tiles.");
    }

    @Test
    void testSwipeLeft() {
        int[][] board = {
                { 2, 2, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        gameBoard.setGameBoard(board);
        gameBoard.handleSwipeLeft();

        // System.out.println("Swipe Left expected Value: 4, 0, 0, 0");
        // System.out.println("Swipe Left actual Value: " +
        // Arrays.toString(gameBoard.getBoard()[0]));

        assertEquals(4, gameBoard.getBoard()[0][0],
                "Swipe left should combine tiles correctly.");
        assertEquals(0, gameBoard.getBoard()[0][1], "Tiles should move correctly after swipe left.");

        // BEFORE: AFTER:
        // 2 2 0 0 4 0 0 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
    }

    @Test
    void testSwipeRight() {
        int[][] board = {
                { 2, 0, 0, 2 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        gameBoard.setGameBoard(board);
        gameBoard.handleSwipeRight();
        assertEquals(4, gameBoard.getBoard()[0][3],
                "Swipe right should combine tiles correctly.");

        // BEFORE: AFTER:
        // 2 0 0 2 0 0 0 4
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
    }

    @Test
    void testSwipeUp() {
        int[][] board = {
                { 2, 0, 0, 0 },
                { 2, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        gameBoard.setGameBoard(board);
        gameBoard.handleSwipeUp();

        assertEquals(4, gameBoard.getBoard()[0][0], "Swipe up should combine tiles correctly.");
        // assertEquals(0, gameBoard.getBoard()[1][0], "Tiles should move correctly
        // after swipe up.");

        // BEFORE: AFTER:
        // 2 0 0 0 4 0 0 0
        // 2 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
    }

    @Test
    void testSwipeDown() {
        int[][] board = {
                { 2, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 2, 0, 0, 0 }
        };
        gameBoard.setGameBoard(board);
        gameBoard.handleSwipeDown();

        System.out.println(gameBoard.getBoard());
        // assertEquals(0, gameBoard.getBoard()[0][0], "Tiles should move correctly
        // after swipe down.");
        assertEquals(4, gameBoard.getBoard()[3][0], "Swipe down should combine tiles correctly.");

        // BEFORE: AFTER:
        // 2 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
        // 0 0 0 0 0 0 0 0
        // 2 0 0 0 4 0 0 0
    }

    @Test
    public void testSpawnTileControlled() {
        // Ensure the array is not null
        int[][] predefinedTiles = new int[][] {
                { 0, 0, 2 }, // Row, column, value
                { 1, 1, 4 }
        };

        // Create an instance of MockSpawnTile passing the array
        MockSpawnTile mockBoard = new MockSpawnTile(predefinedTiles);

        // Call the method that should update the board
        mockBoard.spawnTile();
        mockBoard.spawnTile();

        // Verify that the positions and values of the tiles are correct
        assertEquals(2, mockBoard.getBoard()[0][0], "The value at position (0, 0) should be 2");
        assertEquals(4, mockBoard.getBoard()[1][1], "The value at position (1, 1) should be 4");

        // Print the board to verify the changes
        mockBoard.printBoard();
    }

    @Test
    public void testCalculateScore() {
        // Test 1: Empty board (no values)
        GameBoard gameBoardEmpty = new GameBoard();
        int[][] boardEmpty = new int[4][4]; // Empty board (all values are 0)
        gameBoardEmpty.setGameBoard(boardEmpty);
        assertEquals(0, gameBoardEmpty.calculateScore(), "The score should be 0 for an empty board");

        // Test 2: Board with large values
        GameBoard gameBoardLargeValues = new GameBoard();
        int[][] boardLargeValues = {
                { 1024, 2048, 0, 0 },
                { 4096, 8192, 0, 0 },
                { 16384, 32768, 0, 0 },
                { 0, 0, 0, 0 }
        };
        gameBoardLargeValues.setGameBoard(boardLargeValues);
        assertEquals(64512, gameBoardLargeValues.calculateScore(),
                "The score should be the sum of all large values on the board");
    }

    @Test
    void testReverseArray() {
        // Case 1: Empty array
        int[] array1 = {};
        gameBoard.reverseArray(array1);
        assertArrayEquals(new int[] {}, array1, "The empty array should not change.");

        // Case 2: Array with a single element
        int[] array2 = { 5 };
        gameBoard.reverseArray(array2);
        assertArrayEquals(new int[] { 5 }, array2, "The array with a single element should not change.");

        // Case 3: Array with two elements
        int[] array3 = { 1, 2 };
        gameBoard.reverseArray(array3);
        assertArrayEquals(new int[] { 2, 1 }, array3, "The array with two elements should be reversed.");

        // Case 4: Array with an odd number of elements
        int[] array4 = { 3, 5, 7 };
        gameBoard.reverseArray(array4);
        assertArrayEquals(new int[] { 7, 5, 3 }, array4, "The odd array should be reversed correctly.");

        // Case 5: Array with an even number of elements
        int[] row = { 1, 2, 3, 4 };
        gameBoard.reverseArray(row);
        assertArrayEquals(new int[] { 4, 3, 2, 1 }, row, "Array should be reversed.");
    }

    @Test
    void testProcessMovementsLeft() {
        int[] row = { 2, 2, 0, 0 };
        boolean changed = gameBoard.processMovements(row, "left");
        assertTrue(changed, "Row should have been modified.");
        assertArrayEquals(new int[] { 4, 0, 0, 0 }, row, "Tiles should have merged correctly.");
    }

    @Test
    void testProcessMovementsRight() {
        int[] row = { 2, 2, 0, 0 };
        boolean changed = gameBoard.processMovements(row, "right");
        assertTrue(changed, "Row should have been modified.");
        assertArrayEquals(new int[] { 0, 0, 0, 4 }, row, "Tiles should have merged correctly after reversing.");
    }

    @Test
    void testCompress() {
        // Case 1: Fully filled array (no zeros)
        int[] array1 = { 2, 4, 8, 16 };
        int[] result1 = gameBoard.compress(array1);
        assertArrayEquals(new int[] { 2, 4, 8, 16 }, result1, "Values without zeros should not change.");

        // Case 2: Zeros at the end
        int[] array2 = { 2, 4, 0, 0 };
        int[] result2 = gameBoard.compress(array2);
        assertArrayEquals(new int[] { 2, 4, 0, 0 }, result2, "Zeros at the end should remain.");

        // Case 3: Zeros in the middle
        int[] array3 = { 2, 0, 4, 0 };
        int[] result3 = gameBoard.compress(array3);
        assertArrayEquals(new int[] { 2, 4, 0, 0 }, result3, "Non-zero values should move to the front.");

        // Case 4: Completely empty array
        int[] array4 = { 0, 0, 0, 0 };
        int[] result4 = gameBoard.compress(array4);
        assertArrayEquals(new int[] { 0, 0, 0, 0 }, result4, "An empty array should not change.");

        // Case 5: Partially filled array
        int[] array5 = { 0, 2, 0, 4 };
        int[] result5 = gameBoard.compress(array5);
        assertArrayEquals(new int[] { 2, 4, 0, 0 }, result5, "Non-zero values should move to the front.");

        // Case 6: Filled array with repeated values
        int[] array6 = { 2, 2, 0, 2 };
        int[] result6 = gameBoard.compress(array6);
        assertArrayEquals(new int[] { 2, 2, 2, 0 }, result6, "Repeated values should preserve the order.");
    }

    @Test
    void testMerge() {
        int[] row = { 2, 2, 4, 4 };
        int[] merged = gameBoard.merge(row);
        assertArrayEquals(new int[] { 4, 0, 8, 0 }, merged, "Tiles should merge correctly.");
    }

    @Test
    void testIsGameOver() {
        // Case 1: The board is full and there are no possible moves
        int[][] boardFullNoMoves = {
                { 2, 4, 2, 4 },
                { 4, 2, 4, 2 },
                { 2, 4, 2, 4 },
                { 4, 2, 4, 2 }
        };
        gameBoard.setGameBoard(boardFullNoMoves);
        assertTrue(gameBoard.isGameOver(gameBoard.getBoard()), 
                   "The game should be over when there are no valid moves.");
        
        // Case 2: The board has at least one empty space (0)
        int[][] boardWithEmptySpace = {
                { 2, 4, 2, 4 },
                { 4, 0, 4, 2 },
                { 2, 4, 2, 4 },
                { 4, 2, 4, 2 }
        };
        gameBoard.setGameBoard(boardWithEmptySpace);
        assertFalse(gameBoard.isGameOver(gameBoard.getBoard()), 
                    "The game should not be over when there is at least one empty space.");

        // Case 3: Completely empty board
        int[][] emptyBoard = new int[4][4]; // All values are 0 by default
        gameBoard.setGameBoard(emptyBoard);
        assertFalse(gameBoard.isGameOver(gameBoard.getBoard()), 
                    "The game should not be over when the board is completely empty.");

        // Case 4: Full board but with a possible move (two adjacent equal values)
        int[][] boardWithPossibleMove = {
                { 2, 4, 2, 4 },
                { 4, 2, 4, 2 },
                { 2, 4, 4, 4 }, // Possible move in row 3
                { 4, 2, 4, 2 }
        };
        gameBoard.setGameBoard(boardWithPossibleMove);
        assertFalse(gameBoard.isGameOver(gameBoard.getBoard()), 
                    "The game should not be over when there is a valid move.");
    }

    @Test
    void testGameNotOverWhenEmptyTileExists() {
        gameBoard.getBoard()[3][3] = 0; // Ensure there's at least one empty tile
        assertFalse(gameBoard.isGameOver(gameBoard.getBoard()),
                "The game should not be over when there are empty tiles.");
    }

       @Test
    void testProcessMovements() {
        GameBoard gameBoard = new GameBoard();

        // Case 1: Empty row with "left" movement
        int[] row = {0, 0, 0, 0};
        String movement = "left";
        assertFalse(gameBoard.processMovements(row, movement), 
            "The row should not change as it is completely empty.");

        // Case 2: Empty row with "right" movement
        row = new int[]{0, 0, 0, 0};
        movement = "right";
        assertFalse(gameBoard.processMovements(row, movement), 
            "The row should not change as it is completely empty.");

        // Case 3: Row with unique values with "left" movement
        row = new int[]{2, 4, 8, 16};
        movement = "left";
        assertFalse(gameBoard.processMovements(row, movement), 
            "The row should not change because the values are unique and already ordered.");

        // Case 4: Row with unique values with "right" movement
        row = new int[]{2, 4, 8, 16};
        movement = "right";
        assertFalse(gameBoard.processMovements(row, movement), 
            "The row should not change because the values are unique and already ordered.");

        // Case 5: Row with repeated values with "left" movement
        row = new int[]{2, 2, 4, 4};
        movement = "left";
        assertTrue(gameBoard.processMovements(row, movement), 
            "The row should change because the values can be merged.");

        // Case 6: Row with repeated values with "right" movement
        row = new int[]{2, 2, 4, 4};
        movement = "right";
        assertTrue(gameBoard.processMovements(row, movement), 
            "The row should change because the values can be merged.");

        // Case 7: Row with interspersed zeros with "left" movement
        row = new int[]{2, 0, 2, 0};
        movement = "left";
        assertTrue(gameBoard.processMovements(row, movement), 
            "The row should change because the values can be compressed.");

        // Case 8: Row with interspersed zeros with "right" movement
        row = new int[]{2, 0, 2, 0};
        movement = "right";
        assertTrue(gameBoard.processMovements(row, movement), 
            "The row should change because the values can be compressed.");
    }

}