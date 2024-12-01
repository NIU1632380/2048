package cat.uab.tqs._2048.view;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GameViewTest {

    @Test
    void testUpdateDisplaysBoardCorrectly() {
        //Set up the test board
        int[][] board = {
            {2, 0, 4, 0},
            {0, 2, 0, 4},
            {2, 0, 0, 0},
            {0, 0, 0, 0}
        };
        int score = 14;

        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Instantiate GameView and call the update method
        GameView view = new GameView();
        view.update(board);
        view.showScore(score);

        // Restore standard output
        System.setOut(originalOut);

        // Define the expected output
        String expectedOutput = """

            Game Board:
            2\t.\t4\t.\t
            .\t2\t.\t4\t
            2\t.\t.\t.\t
            .\t.\t.\t.\t
            Use W (up), A (left), S (down), D (right) to play.
            Score: 14
            """;

        // Verify that the generated output matches the expected output
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    void testUpdateDisplaysEmptyBoard() {
        // Set up the empty board
        int[][] board = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        int score = 0;

        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Instantiate GameView and call the update method
        GameView view = new GameView();
        view.update(board);
        view.showScore(score);

        // Restore standard output
        System.setOut(originalOut);

        // Define the expected output
        String expectedOutput = """

            Game Board:
            .\t.\t.\t.\t
            .\t.\t.\t.\t
            .\t.\t.\t.\t
            .\t.\t.\t.\t
            Use W (up), A (left), S (down), D (right) to play.
            Score: 0
            """;

        // Verify that the generated output matches the expected output
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }

    @Test
    void testUpdateDisplaysFullBoard() {
        // Set up the full board
        int[][] board = {
            {2, 4, 8, 16},
            {32, 64, 128, 256},
            {512, 1024, 2048, 4096},
            {8192, 16384, 32768, 65536}
        };
        int score = 131070;

        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Instantiate GameView and call the update method
        GameView view = new GameView();
        view.update(board);
        view.showScore(score);

        // Restore standard output
        System.setOut(originalOut);

        // Define the expected output
        String expectedOutput = """

            Game Board:
            2\t4\t8\t16\t
            32\t64\t128\t256\t
            512\t1024\t2048\t4096\t
            8192\t16384\t32768\t65536\t
            Use W (up), A (left), S (down), D (right) to play.
            Score: 131070
            """;

        // Verify that the generated output matches the expected output
        assertEquals(expectedOutput.trim(), outputStream.toString().trim());
    }
}
