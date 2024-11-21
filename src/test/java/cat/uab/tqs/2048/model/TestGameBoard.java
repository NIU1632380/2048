import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import cat.uab.tqs._2048.model.GameBoard;

class GameBoardTest {
    private GameBoard gameBoard;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard();
    }

    @Test
    void testInitialBoardHasTwoTiles() {
        int counter = 0;
        int[][] board = gameBoard.getBoard();
        for (int i = 0; i < board.MAX_LENGTH; i++) {
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
        gameBoard.getBoard()[0] = new int[]{2, 2, 0, 0};
        gameBoard.handleSwipeLeft();
        assertArrayEquals(new int[]{4, 0, 0, 0}, gameBoard.getBoard()[0], "Swipe left should combine tiles correctly.");
        
        // BEFORE:		AFTER:
        // 2 2 0 0		4 0 0 0
        // 0 0 0 0		0 0 0 0
        // 0 0 0 0		0 0 0 0
        // 0 0 0 0		0 0 0 0
    }

    @Test
    void testSwipeRight() {
        gameBoard.getBoard()[0] = new int[]{2, 0, 0, 2};
        gameBoard.handleSwipeRight();
        assertArrayEquals(new int[]{0, 0, 0, 4}, gameBoard.getBoard()[0], "Swipe right should combine tiles correctly.");

        // BEFORE:      AFTER:
        // 2 0 0 2		0 0 0 4
        // 0 0 0 0		0 0 0 0
        // 0 0 0 0		0 0 0 0
        // 0 0 0 0		0 0 0 0
    }

    @Test
    void testSwipeUp() {
        gameBoard.getBoard()[0][0] = 2;
        gameBoard.getBoard()[1][0] = 2;
        gameBoard.handleSwipeUp();
        assertEquals(4, gameBoard.getBoard()[0][0], "Swipe up should combine tiles correctly.");
        assertEquals(0, gameBoard.getBoard()[1][0], "Tiles should move correctly after swipe up.");

        //  BEFORE:              AFTER:
        //  2 0 0 0              4 0 0 0
        //  2 0 0 0              0 0 0 0
        //  0 0 0 0              0 0 0 0
        //  0 0 0 0              0 0 0 0
    }

    @Test
    void testSwipeDown() {
        gameBoard.getBoard()[0][0] = 2;
        gameBoard.getBoard()[3][0] = 2;
        gameBoard.handleSwipeDown();
        assertEquals(0, gameBoard.getBoard()[0][0], "Tiles should move correctly after swipe down.");
        assertEquals(4, gameBoard.getBoard()[3][0], "Swipe down should combine tiles correctly.");
        
        //  BEFORE:              AFTER:
        //  2 0 0 0              0 0 0 0
        //  0 0 0 0              0 0 0 0 
        //  0 0 0 0              0 0 0 0
        //  2 0 0 0              4 0 0 0 
    }

    @Test
    void testSpawnTileAddsNewTile() {
        int[][] boardBefore = {
                {2, 4, 8, 16},
                {32, 64, 128, 256},
                {512, 1024, 2048, 0},
                {0, 0, 0, 0}
        };
        gameBoard = new GameBoard();
        gameBoard.setGameBoard(boardBefore);
        gameBoard.spawnTile();
        int zeroCount = 0;
        int[][] boardAfter = gameBoard.getBoard();
    
        for (int i = 0; i < boardAfter.length; i++) {
            for (int j = 0; j < boardAfter[i].length; j++) {
                if (boardAfter[i][j] == 0) {
                    zeroCount++;
                }
            }
        }
    
        assertEquals(4, zeroCount, "After spawning, there should be 3 tiles on the board.");
    }
    

    @Test
    void testIsGameOver() {
        gameBoard.getBoard()[0] = new int[]{2, 4, 2, 4};
        gameBoard.getBoard()[1] = new int[]{4, 2, 4, 2};
        gameBoard.getBoard()[2] = new int[]{2, 4, 2, 4};
        gameBoard.getBoard()[3] = new int[]{4, 2, 4, 2};

        assertTrue(gameBoard.isGameOver(gameBoard.getBoard()), "The game should be over when there are no valid moves.");
    }

    @Test
    void testGameNotOverWhenEmptyTileExists() {
        gameBoard.getBoard()[3][3] = 0; // Ensure there's at least one empty tile
        assertFalse(gameBoard.isGameOver(gameBoard.getBoard()), "The game should not be over when there are empty tiles.");
    }
}
