package cat.uab.tqs._2048.model;

public class MockSpawnTile extends GameBoard {
    private int[][] predefinedTiles;
    private int index = 0;

    public MockSpawnTile(int[][] predefinedTiles) {
        super();
        if (predefinedTiles == null) {
            System.out.println("¡Error! predefinedTiles es null!");
        } else {
            System.out.println("Se ha recibido un array de tiles con longitud: " + predefinedTiles.length);
        }
        this.predefinedTiles = predefinedTiles;
    }
    
    


    @Override
    public void spawnTile() {
        if (index < predefinedTiles.length) {
            int row = predefinedTiles[index][0];
            int col = predefinedTiles[index][1];
            int value = predefinedTiles[index][2];
    
            // Establece el valor de la celda
            getBoard()[row][col] = value;
    
            // Avanza al siguiente índice para la próxima llamada a spawnTile
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
