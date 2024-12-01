package cat.uab.tqs._2048.controller;

import cat.uab.tqs._2048.model.GameBoard;
import cat.uab.tqs._2048.model.MockSpawnTile;
import cat.uab.tqs._2048.view.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.ArgumentCaptor;

@ExtendWith(MockitoExtension.class)
class GameControllerTest {

    private GameBoard gameBoard;
    private GameView mockGameView;
    private GameController controller;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard(); // Usamos el constructor normal, sin controlar aleatoriedad
        mockGameView = mock(GameView.class);
        controller = new GameController(gameBoard, mockGameView);
    }

    @Test
    void testProcessInputSwipeUp() {
        // Inicializamos el tablero con un estado conocido
        int[][] initialBoard = {
                { 0, 0, 0, 0 },
                { 0, 4, 0, 2 },
                { 2, 0, 0, 0 },
                { 0, 8, 0, 2 }
        };
        gameBoard.setGameBoard(initialBoard); // Establecemos el estado inicial del tablero

        // Realizamos un movimiento hacia arriba
        controller.processInput("w");

        // Verificamos que el tablero haya cambiado correctamente
        int[][] expectedBoard = {
                { 2, 4, 0, 4 },
                { 0, 8, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };

        // System.out.println("Expected board:", expectedBoard);
        // assertArrayEquals(expectedBoard, gameBoard.getBoard()); // Comparamos el
        // estado esperado
        assertEquals(2, gameBoard.getBoard()[0][0], "first assert failed");
        assertEquals(4, gameBoard.getBoard()[0][3], "second assert failed");
        assertEquals(4, gameBoard.getBoard()[0][1], "third assert failed");
        assertEquals(8, gameBoard.getBoard()[1][1], "fourth assert failed");
    }

    @Test
    void testProcessInputSwipeLeft() {
        // Inicializamos el tablero con un estado conocido
        int[][] initialBoard = {
                { 0, 2, 2, 0 },
                { 0, 0, 0, 0 },
                { 0, 8, 8, 0 },
                { 0, 16, 16, 0 }
        };
        gameBoard.setGameBoard(initialBoard); // Establecemos el estado inicial del tablero

        // Realizamos un movimiento hacia la izquierda
        controller.processInput("a");

        // Verificamos que el tablero haya cambiado correctamente
        int[][] expectedBoard = {
                { 4, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 16, 0, 0, 0 },
                { 32, 0, 0, 0 }
        };
        // assertArrayEquals(expectedBoard, gameBoard.getBoard()); // Comparamos el
        // estado esperado
        assertEquals(4, gameBoard.getBoard()[0][0], "first assert failed");
        assertEquals(16, gameBoard.getBoard()[2][0], "second assert failed");
        assertEquals(32, gameBoard.getBoard()[3][0], "third assert failed");
    }

    @Test
    void testProcessInputSwipeDown() {
        // Inicializamos el tablero con un estado conocido
        int[][] initialBoard = {
                { 2, 2, 0, 0 },
                { 2, 2, 4, 512 },
                { 0, 2, 4, 512 },
                { 0, 0, 0, 0 }
        };
        gameBoard.setGameBoard(initialBoard); // Establecemos el estado inicial del tablero

        // Realizamos un movimiento hacia abajo
        controller.processInput("s");

        // Verificamos que el tablero haya cambiado correctamente
        int[][] expectedBoard = {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 2, 0, 0 },
                { 4, 4, 8, 1024 }
        };
        assertEquals(4, gameBoard.getBoard()[3][0], "first assert failed");
        assertEquals(4, gameBoard.getBoard()[3][1], "second assert failed");
        assertEquals(8, gameBoard.getBoard()[3][2], "third assert failed");
        assertEquals(2, gameBoard.getBoard()[2][1], "fourth assert failed");
        assertEquals(1024, gameBoard.getBoard()[3][3], "fifth assert failed");
    }

    @Test
    void testProcessInputSwipeRight() {
        // Inicializamos el tablero con un estado conocido
        int[][] initialBoard = {
                { 0, 2, 2, 0 },
                { 8, 0, 0, 4 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        gameBoard.setGameBoard(initialBoard); // Establecemos el estado inicial del tablero

        // Realizamos un movimiento hacia la derecha
        controller.processInput("d");

        // Verificamos que el tablero haya cambiado correctamente
        int[][] expectedBoard = {
                { 0, 0, 0, 4 },
                { 0, 0, 8, 4 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };
        // assertArrayEquals(expectedBoard, gameBoard.getBoard()); // Comparamos el
        // estado esperado
        assertEquals(4, gameBoard.getBoard()[0][3], "first assert failed");
        assertEquals(8, gameBoard.getBoard()[1][2], "second assert failed");
    }

    /*
     * @Test
     * void testProcessInvalidInput() {
     * // Redirigir la salida estándar a un ByteArrayOutputStream
     * ByteArrayOutputStream outContent = new ByteArrayOutputStream();
     * System.setOut(new PrintStream(outContent));
     * 
     * // Probar un input no válido
     * controller.processInput("z");
     * 
     * // Verificar que se imprimió el mensaje de error esperado
     * assertTrue(outContent.toString().contains("Invalid input"));
     * 
     * // Restaurar la salida estándar
     * System.setOut(System.out);
     * }
     */

    @Test
    void testProcessMultipleInputs() {

        int[][] initialBoard = {
        { 2, 0, 0, 2 },
        { 4, 0, 4, 0 },
        { 0, 8, 0, 8 },
        { 16, 0, 0, 16 }

        };
        gameBoard.setGameBoard(initialBoard);
        // Probar una secuencia de entradas

       /**  System.out.println("sheeetii actual Value: " + Arrays.toString(gameBoard.getBoard()[0]));
        System.out.println("sheeeeti movement actual Value: " + Arrays.toString(gameBoard.getBoard()[1]));
        System.out.println("sheeeeti movement actual Value: " + Arrays.toString(gameBoard.getBoard()[2]));
        System.out.println("sheeeti movement actual Value: " + Arrays.toString(gameBoard.getBoard()[3]));
        */
        controller.processInput("a");
        
        /*
        System.out.println("first movement actual Value: " + Arrays.toString(gameBoard.getBoard()[0]));
        System.out.println("first movement actual Value: " + Arrays.toString(gameBoard.getBoard()[1]));
        System.out.println("first movement actual Value: " + Arrays.toString(gameBoard.getBoard()[2]));
        System.out.println("first movement actual Value: " + Arrays.toString(gameBoard.getBoard()[3]));
        */

        controller.processInput("w");
        
        /*
        System.out.println("second movement actual Value: " + Arrays.toString(gameBoard.getBoard()[0]));
        System.out.println("second movement actual Value: " + Arrays.toString(gameBoard.getBoard()[1]));
        System.out.println("second movement actual Value: " + Arrays.toString(gameBoard.getBoard()[2]));
        System.out.println("second movement actual Value: " + Arrays.toString(gameBoard.getBoard()[3]));
        */

        controller.processInput("s");
        
        /*
        System.out.println("third movement actual Value: " + Arrays.toString(gameBoard.getBoard()[0]));
        System.out.println("third movement actual Value: " + Arrays.toString(gameBoard.getBoard()[1]));
        System.out.println("third movement actual Value: " + Arrays.toString(gameBoard.getBoard()[2]));
        System.out.println("third movement actual Value: " + Arrays.toString(gameBoard.getBoard()[3]));
        */
        
        controller.processInput("d");
        
        /*
        System.out.println("fourth movement actual Value: " + Arrays.toString(gameBoard.getBoard()[0]));
        System.out.println("fourth movement actual Value: " + Arrays.toString(gameBoard.getBoard()[1]));
        System.out.println("fourth movement actual Value: " + Arrays.toString(gameBoard.getBoard()[2]));
        System.out.println("fourth movement actual Value: " + Arrays.toString(gameBoard.getBoard()[3]));
        */
        
        doNothing().when(mockGameView).update(gameBoard.getBoard());
        doNothing().when(mockGameView).showScore(anyInt());

        // Verificar que la vista se actualiza después de cada movimiento
        verify(mockGameView, times(4)).update(gameBoard.getBoard());
        verify(mockGameView, times(1)).showScore(gameBoard.calculateScore());

        
    }

    
    @Test
    void testMockGameView() {
        // Configuramos un estado inicial conocido del tablero
        int[][] initialBoard = {
            { 0, 2, 2, 0 },
            { 0, 0, 0, 0 },
            { 0, 8, 8, 0 },
            { 0, 16, 16, 0 }
        };
        gameBoard.setGameBoard(initialBoard);

        controller.processInput("a");

        // Esperamos que el tablero después del movimiento sea este
        int[][] expectedBoard = {
            { 4, 0, 0, 2 },
            { 0, 0, 0, 0 },
            { 16, 0, 0, 0 },
            { 32, 0, 0, 0 }
        };

        int expectedScore = 4 +2+ 16 +32; // Cálculo manual del puntaje basado en las combinaciones

        // Capturamos los argumentos que se pasaron al método update del mock
        ArgumentCaptor<int[][]> boardCaptor = ArgumentCaptor.forClass(int[][].class);
        ArgumentCaptor<Integer> scoreCaptor = ArgumentCaptor.forClass(Integer.class);

        // Verificamos que se llamó a update con los argumentos correctos
        verify(mockGameView).update(boardCaptor.capture());
        verify(mockGameView).showScore(scoreCaptor.capture());

        // Obtenemos los valores capturados
        int[][] capturedBoard = boardCaptor.getValue();
        int capturedScore = scoreCaptor.getValue();

        // Verificamos que el tablero pasado a la vista es el esperado
        assertArrayEquals(expectedBoard, capturedBoard, "El tablero pasado a la vista no coincide con el esperado");

        // Verificamos que la puntuación pasada a la vista es la esperada
        assertEquals(expectedScore, capturedScore, "La puntuación pasada a la vista no coincide con la esperada");
    }
     
      

}
