package cat.uab.tqs._2048.controller;

import cat.uab.tqs._2048.model.GameBoard;
import cat.uab.tqs._2048.view.GameView;

public class GameController {
    private GameBoard model;
    private GameView view;

    public GameController(GameBoard model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public void processInput(String input) {
        switch (input) {
            case "w": model.handleSwipeUp(); break;
            case "a": model.handleSwipeLeft(); break;
            case "s": model.handleSwipeDown(); break;
            case "d": model.handleSwipeRight(); break;
            default: System.out.println("Invalid input");
        }
        //System.out.println("UPDATEEE!!!");
    System.out.println("Update board:");
    view.update(model.getBoard());
    System.out.println("Show score:");
    view.showScore(model.calculateScore());
    }
}
