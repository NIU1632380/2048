package cat.uab.tqs._2048.model;

public class MockSpawnTile extends GameBoard {
    private int[][] predefinedTiles;
    private int index = 0;

    // Constructor that initializes the mock spawn tiles with predefined values.
    public MockSpawnTile(int[][] predefinedTiles) {
        super();
        if (predefinedTiles == null) {
            System.out.println("¡Error! predefinedTiles es null!");  
        } else {
            System.out.println("Se ha recibido un array de tiles con longitud: " + predefinedTiles.length);
        }
        this.predefinedTiles = predefinedTiles;
    }

    // Spawns a tile on the board using the predefined positions and values.
    @Override
    public void spawnTile() {
        if (index < predefinedTiles.length) {  // Check if there are more tiles to spawn.
            int row = predefinedTiles[index][0];
            int col = predefinedTiles[index][1];
            int value = predefinedTiles[index][2];
    
            getBoard()[row][col] = value;  
    
            index++;  
        }
    }

    // Prints the current game board to the console.
    public void printBoard() {
        for (int[] row : getBoard()) {
            for (int tile : row) {
                System.out.print(tile + "\t");  // Prints the tile value with tab for spacing.
            }
            System.out.println();  
        }
    }

    // Merges predefined tiles onto the game board if the cell is empty.
    public void mergePredefinedTiles(int[][] board, int[][] predefinedTiles) {
        for (int i = 0; i < predefinedTiles.length; i++) {
            int row = predefinedTiles[i][0];
            int col = predefinedTiles[i][1];
            int value = predefinedTiles[i][2];
        
            if (board[row][col] == 0) {  // Check if the cell is empty.
                board[row][col] = value; 
            }
        }
    }

    // Sets the game board to a new state.
    public void setBoard(int[][] newBoard) {
        for (int i = 0; i < getBoard().length; i++) {
            for (int j = 0; j < getBoard()[i].length; j++) {
                getBoard()[i][j] = newBoard[i][j];  
            }
        }
    }
}
