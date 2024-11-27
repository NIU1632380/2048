package cat.uab.tqs._2048.view;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GameViewTest {

    @Test
    void testUpdateDisplaysBoardCorrectly() {
        // Configurar el tablero de prueba
        int[][] board = {
            {2, 0, 4, 0},
            {0, 2, 0, 4},
            {2, 0, 0, 0},
            {0, 0, 0, 0}
        };
        int score = 14;

        // Capturar la salida de consola
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Instanciar GameView y llamar al método update
        GameView view = new GameView();
        view.update(board);
        view.showScore(score);

        // Restaurar la salida estándar
        System.setOut(originalOut);

        // Definir la salida esperada
        String expectedOutput = """

            Game Board:
            2\t.\t4\t.\t
            .\t2\t.\t4\t
            2\t.\t.\t.\t
            .\t.\t.\t.\t
            Use W (up), A (left), S (down), D (right) to play.
            Score: 14
            """;

        // Verificar que la salida generada coincide con la esperada
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    void testUpdateDisplaysEmptyBoard() {
        // Configurar el tablero vacío
        int[][] board = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        int score = 0;

        // Capturar la salida de consola
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Instanciar GameView y llamar al método update
        GameView view = new GameView();
        view.update(board);
        view.showScore(score);

        // Restaurar la salida estándar
        System.setOut(originalOut);

        // Definir la salida esperada
        String expectedOutput = """

            Game Board:
            .\t.\t.\t.\t
            .\t.\t.\t.\t
            .\t.\t.\t.\t
            .\t.\t.\t.\t
            Use W (up), A (left), S (down), D (right) to play.
            Score: 0
            """;

        // Verificar que la salida generada coincide con la esperada
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    void testUpdateDisplaysFullBoard() {
        // Configurar el tablero lleno
        int[][] board = {
            {2, 4, 8, 16},
            {32, 64, 128, 256},
            {512, 1024, 2048, 4096},
            {8192, 16384, 32768, 65536}
        };
        int score = 131070;

        // Capturar la salida de consola
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Instanciar GameView y llamar al método update
        GameView view = new GameView();
        view.update(board);
        view.showScore(score);

        // Restaurar la salida estándar
        System.setOut(originalOut);

        // Definir la salida esperada
        String expectedOutput = """

            Game Board:
            2\t4\t8\t16\t
            32\t64\t128\t256\t
            512\t1024\t2048\t4096\t
            8192\t16384\t32768\t65536\t
            Use W (up), A (left), S (down), D (right) to play.
            Score: 131070
            """;

        // Verificar que la salida generada coincide con la esperada
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }
}
