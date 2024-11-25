package cat.uab.tqs._2048.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        int counter = 0;
        int[][] board = gameBoard.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    counter++;
                }
            }
        }

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
        assertArrayEquals(new int[] { 4, 0, 0, 0 }, gameBoard.getBoard()[0],
                "Swipe left should combine tiles correctly.");

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
        assertArrayEquals(new int[] { 0, 0, 0, 4 }, gameBoard.getBoard()[0],
                "Swipe right should combine tiles correctly.");

        // BEFORE:      AFTER:
        // 2 0 0 2      0 0 0 4
        // 0 0 0 0      0 0 0 0
        // 0 0 0 0      0 0 0 0
        // 0 0 0 0      0 0 0 0
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
        assertEquals(0, gameBoard.getBoard()[1][0], "Tiles should move correctly after swipe up.");

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
        assertEquals(0, gameBoard.getBoard()[0][0], "Tiles should move correctly after swipe down.");
        assertEquals(4, gameBoard.getBoard()[3][0], "Swipe down should combine tiles correctly.");

        // BEFORE:  AFTER:
        // 2 0 0 0  0 0 0 0
        // 0 0 0 0  0 0 0 0
        // 0 0 0 0  0 0 0 0
        // 2 0 0 0  4 0 0 0
    }

   @Test
void testSpawnTileControlled() {
    // Configurar l'estat inicial del tauler
    int[][] initialBoard = {
        { 2, 4, 8, 16 },
        { 32, 64, 128, 256 },
        { 512, 1024, 2048, 0 },
        { 0, 0, 0, 0 }
    };

    // Configurar les fitxes predefinides per MockSpawnTile
    int[][] predefinedTiles = {
        { 2, 3, 2 }  // Nova fitxa apareix a (2, 3) amb valor 2
    };

    // Crear MockSpawnTile
    MockSpawnTile mockBoard = new MockSpawnTile(predefinedTiles);
    mockBoard.setBoard(initialBoard);

    System.out.println("Before spawn:");
    mockBoard.printBoard();

    mockBoard.spawnTile(); // Generar la fitxa controlada
    System.out.println("After spawn:");
    mockBoard.printBoard();

    // Verificar que la nova fitxa s'ha generat correctament
    assertEquals(2, mockBoard.getBoard()[2][3], "The new tile should be added at position (2, 3) with value 2.");
}


    @Test
    void testReverseArray() {
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
        int[] row = { 2, 0, 2, 4 };
        int[] compressed = gameBoard.compress(row);
        assertArrayEquals(new int[] { 2, 2, 4, 0 }, compressed, "Array should be compressed correctly.");
    }

    @Test
    void testMerge() {
        int[] row = { 2, 2, 4, 4 };
        int[] merged = gameBoard.merge(row);
        assertArrayEquals(new int[] { 4, 0, 8, 0 }, merged, "Tiles should merge correctly.");
    }

    @Test
    void testIsGameOver() {
        int[][] board = {
                { 2, 4, 2, 4 },
                { 4, 2, 4, 2 },
                { 2, 4, 2, 4 },
                { 4, 2, 4, 2 }
        };
        gameBoard.setGameBoard(board);

        assertTrue(gameBoard.isGameOver(gameBoard.getBoard()),
                "The game should be over when there are no valid moves.");
    }

    @Test
    void testGameNotOverWhenEmptyTileExists() {
        gameBoard.getBoard()[3][3] = 0; // Ensure there's at least one empty tile
        assertFalse(gameBoard.isGameOver(gameBoard.getBoard()),
                "The game should not be over when there are empty tiles.");
    }
}