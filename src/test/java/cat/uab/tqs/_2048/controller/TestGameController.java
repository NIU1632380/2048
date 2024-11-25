/* package cat.uab.tqs._2048.controller;

import cat.uab.tqs._2048.model.GameBoard;
import cat.uab.tqs._2048.view.GameView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    private GameBoard gameBoard;
    private GameView mockGameView;
    private GameController controller;

    @BeforeEach
    void setUp() {
        gameBoard = new GameBoard(null);  // Usamos el constructor normal, sin controlar aleatoriedad
        mockGameView = mock(GameView.class);
        controller = new GameController(gameBoard, mockGameView);
    }

    @Test
    void testProcessInputSwipeUp() {
        // Inicializamos el tablero con un estado conocido
        int[][] initialBoard = {
            {2, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        gameBoard.setGameBoard(initialBoard);  // Establecemos el estado inicial del tablero

        // Realizamos un movimiento hacia arriba
        controller.processInput("w");

        // Verificamos que el tablero haya cambiado correctamente
        int[][] expectedBoard = {
            {2, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        assertArrayEquals(expectedBoard, gameBoard.getBoard());  // Comparamos el estado esperado
    }

    @Test
    void testProcessInputSwipeLeft() {
        // Inicializamos el tablero con un estado conocido
        int[][] initialBoard = {
            {0, 2, 2, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        gameBoard.setGameBoard(initialBoard);  // Establecemos el estado inicial del tablero

        // Realizamos un movimiento hacia la izquierda
        controller.processInput("a");

        // Verificamos que el tablero haya cambiado correctamente
        int[][] expectedBoard = {
            {4, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        assertArrayEquals(expectedBoard, gameBoard.getBoard());  // Comparamos el estado esperado
    }

    @Test
    void testProcessInputSwipeDown() {
        // Inicializamos el tablero con un estado conocido
        int[][] initialBoard = {
            {2, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        gameBoard.setGameBoard(initialBoard);  // Establecemos el estado inicial del tablero

        // Realizamos un movimiento hacia abajo
        controller.processInput("s");

        // Verificamos que el tablero haya cambiado correctamente
        int[][] expectedBoard = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {2, 0, 0, 0}
        };
        assertArrayEquals(expectedBoard, gameBoard.getBoard());  // Comparamos el estado esperado
    }

    @Test
    void testProcessInputSwipeRight() {
        // Inicializamos el tablero con un estado conocido
        int[][] initialBoard = {
            {0, 2, 2, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        gameBoard.setGameBoard(initialBoard);  // Establecemos el estado inicial del tablero

        // Realizamos un movimiento hacia la derecha
        controller.processInput("d");

        // Verificamos que el tablero haya cambiado correctamente
        int[][] expectedBoard = {
            {0, 0, 2, 2},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        assertArrayEquals(expectedBoard, gameBoard.getBoard());  // Comparamos el estado esperado
    }

    @Test
    void testProcessInvalidInput() {
        // Probar un input no válido
        controller.processInput("z");

        // Verificar que no se llama a ninguno de los métodos del modelo
        verify(mockGameView).showErrorMessage("Invalid input");
    }

    @Test
    void testProcessMultipleInputs() {
        // Probar una secuencia de entradas
        controller.processInput("w");
        controller.processInput("a");
        controller.processInput("s");
        controller.processInput("d");

        // Verificar que la vista se actualiza después de cada movimiento
        verify(mockGameView, times(4)).update(gameBoard.getBoard());
    }
}
 */