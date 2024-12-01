package cat.uab.tqs._2048.view;

public class GameView {
    

    // Method to update and display the board on the console.
    public void update(int[][] board) {

        System.out.println("\n\n\nGame Board:");
        int rows = board.length;
        int cols = board[0].length;

  
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 0) {
                    System.out.print(".\t");
                } else {
                    System.out.print(board[i][j] + "\t");
                }
            }
            System.out.println();
        }
        System.out.println("Use W (up), A (left), S (down), D (right) to play.");
    }
    
    // Method to display the score on the console.
    public void showScore(int score) {
        System.out.println("Score: " + score);
    }
}
