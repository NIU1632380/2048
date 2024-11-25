package cat.uab.tqs._2048.view;


public class GameView {
    public void update(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print((cell == 0 ? "." : cell) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void showGameOver() {
        System.out.println("Game Over!");
    }
}
