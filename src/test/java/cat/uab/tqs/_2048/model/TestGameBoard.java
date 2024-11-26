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
        // Inicializamos el tablero con un mock o el objeto que esté utilizando para controlarlo.
        int[][] predefinedTiles = new int[][] {
            {0, 0, 2}, // Fila 0, Columna 0, Valor 2
            {1, 1, 4}  // Fila 1, Columna 1, Valor 4
        };
    
        // Usamos MockSpawnTile si es necesario o directamente GameBoard
        MockSpawnTile mockBoard = new MockSpawnTile(predefinedTiles);
        
        // Llamamos a spawnTile para colocar dos tiles en el tablero
        mockBoard.spawnTile();
        mockBoard.spawnTile(); 
    
        int counter = 0;
        int[][] board = mockBoard.getBoard();
        // Contamos los tiles que no son cero (los tiles generados)
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0) {
                    counter++;
                }
            }
        }
    
        // Verificamos que haya exactamente dos tiles
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

        //System.out.println("Swipe Left expected Value: 4, 0, 0, 0");
        //System.out.println("Swipe Left actual Value: " + Arrays.toString(gameBoard.getBoard()[0]));
        
        assertEquals(4, gameBoard.getBoard()[0][0],
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
        assertEquals(4, gameBoard.getBoard()[0][3],
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
        //assertEquals(0, gameBoard.getBoard()[1][0], "Tiles should move correctly after swipe up.");

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
        //assertEquals(0, gameBoard.getBoard()[0][0], "Tiles should move correctly after swipe down.");
        assertEquals(4, gameBoard.getBoard()[3][0], "Swipe down should combine tiles correctly.");

        // BEFORE:  AFTER:
        // 2 0 0 0  0 0 0 0
        // 0 0 0 0  0 0 0 0
        // 0 0 0 0  0 0 0 0
        // 2 0 0 0  4 0 0 0
    }

    @Test
    public void testSpawnTileControlled() {
        // Asegúrate de que el array no es nulo
        int[][] predefinedTiles = new int[][] {
            {0, 0, 2}, // Fila, columna, valor
            {1, 1, 4}
        };
    
        // Crea una instancia de MockSpawnTile pasando el array
        MockSpawnTile mockBoard = new MockSpawnTile(predefinedTiles);
    
        // Llama al método que debe actualizar el tablero
        mockBoard.spawnTile();
        mockBoard.spawnTile();
    
        // Verifica que las posiciones y los valores de los tiles sean correctos
        assertEquals(2, mockBoard.getBoard()[0][0], "El valor en la posición (0, 0) debería ser 2");
        assertEquals(4, mockBoard.getBoard()[1][1], "El valor en la posición (1, 1) debería ser 4");
        
        // Imprime el tablero para verificar los cambios
        mockBoard.printBoard();
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