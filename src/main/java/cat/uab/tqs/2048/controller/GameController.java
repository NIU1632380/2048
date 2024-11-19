public class GameController {
    private GameBoard model;
    private GameView view;

    public GameController(GameBoard model, GameView view) {
        this.model = model;
        this.view = view;
    }

    public void processInput(String input) {
        switch (input) {
            case "w": model.moveUp(); break;
            case "a": model.moveLeft(); break;
            case "s": model.moveDown(); break;
            case "d": model.moveRight(); break;
            default: System.out.println("Invalid input");
        }
        view.update(model.getBoard());
    }
}
