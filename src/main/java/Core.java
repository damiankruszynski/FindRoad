import java.util.ArrayList;

public class Core {

    public static void main(String[] args) {

        Board board = new Board(20 ,20);
        board.printBoard();
        board.possibleRouteDown(0,5 ,5,5);

    }

}
