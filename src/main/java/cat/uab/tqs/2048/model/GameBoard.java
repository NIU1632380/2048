public class GameBoard {
    private int[][] board;
    private final int size = 4;

    public GameBoard() {
        board = new int[size][size];
        spawnTile();
        spawnTile();
    }

    public void moveLeft() {
        // Implementa la lògica per moure a l'esquerra
    }

    public void moveRight() {
        // Implementa la lògica per moure a la dreta
    }

    public void moveUp() {
        // Implementa la lògica per moure amunt
    }

    public void moveDown() {
        // Implementa la lògica per moure avall
    }

    public void spawnTile() {
        // Genera un 2 o 4 aleatoriament en una posició buida
    }

    public int[][] getBoard() {
        return board;
    }
}
