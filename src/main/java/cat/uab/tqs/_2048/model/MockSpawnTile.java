package cat.uab.tqs._2048.model;

public class MockSpawnTile extends GameBoard {
    private int[][] predefinedTiles;
    private int index = 0;

    public MockSpawnTile(int[][] predefinedTiles) {
        super(); // Inicialitza la classe pare
        if (predefinedTiles == null) {
            throw new IllegalArgumentException("Predefined tiles cannot be null");
        }
        System.out.println("MockSpawnTile constructor");
        this.predefinedTiles = predefinedTiles;
    }


    @Override
    public void spawnTile() {
        if (index < predefinedTiles.length) {
            int row = predefinedTiles[index][0];
            int col = predefinedTiles[index][1];
            int value = predefinedTiles[index][2];
            getBoard()[row][col] = value;
            index++;
        }
    }

    // Mètode per imprimir el tauler
    public void printBoard() {
        for (int[] row : getBoard()) {
            for (int tile : row) {
                System.out.print(tile + "\t");
            }
            System.out.println();
        }
    }

    // Mètode per establir un tauler inicial
    public void setBoard(int[][] newBoard) {
        for (int i = 0; i < getBoard().length; i++) {
            for (int j = 0; j < getBoard()[i].length; j++) {
                getBoard()[i][j] = newBoard[i][j];
            }
        }
    }
}
