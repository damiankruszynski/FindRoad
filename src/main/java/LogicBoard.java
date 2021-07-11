import java.util.Arrays;

public class LogicBoard {
    private final int sizeX;
    private final int sizeY;
    private boolean[][] tableBoard;

    public LogicBoard(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.tableBoard = new boolean[sizeX][sizeY];
        initializeBorad();
    }

    public boolean[][] getTableBoard() {
        return tableBoard;
    }

    private void initializeBorad(){
        for(int x=0;x<sizeX;x++){
            for (int y=0;y<sizeY;y++){
                tableBoard[x][y] = true;
            }
        }
    }
}
