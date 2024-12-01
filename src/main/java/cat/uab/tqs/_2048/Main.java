package cat.uab.tqs._2048;

import cat.uab.tqs._2048.model.GameBoard;
import cat.uab.tqs._2048.controller.GameController;
import cat.uab.tqs._2048.view.GameView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameBoard model = new GameBoard();
        GameView view = new GameView();
        GameController controller = new GameController(model, view);

        model.spawnTile();
        model.spawnTile();
        view.update(model.getBoard());
        view.showScore(model.calculateScore());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Your move was: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("q")) {
                System.out.println("Game exited. Thanks for playing!");
                break;
            }

            if (!input.equals("w") && !input.equals("a") && !input.equals("s") && !input.equals("d")) {
                System.out.println("Invalid move. Please use W, A, S, D, or type 'q' to quit the game.");
                continue;
            }

            controller.processInput(input);

            if (model.isGameOver(model.getBoard())) {
                System.out.println("\n\nGAME OVER!" + "\n");
                System.out.println("That's it! Your score was:"+model.calculateScore());
                break;
            }
        }

        scanner.close();
    }
}
