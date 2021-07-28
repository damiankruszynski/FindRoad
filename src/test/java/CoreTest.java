import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoreTest {

    @Test
    public void shouldFindRoad(){
        //GIVEN
        int[][] tableBoard = {
                {0,1,0,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0,0,0},
                {0,3,0,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0,0},
                {0,2,3,4,4,4,0,0,0,0},
                {0,1,0,0,0,4,0,0,0,0},
                {0,2,4,4,0,4,0,0,0,0},
                {0,0,0,4,0,4,0,0,0,0},
                {0,2,4,4,0,4,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0} };
          Board board = new Board(tableBoard);
        //WHEN
        board.printBoard();
        boolean isRoadExist = board.possibleRouteDown(0,0,9,9);
        //THEN
        assertTrue(isRoadExist);
    }

    @Test
    public void shouldNotFindRoad(){
        //GIVEN
        int[][] tableBoard = {
                {0,1,0,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0,0,0},
                {0,3,0,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0,0},
                {0,2,3,4,4,4,0,0,0,0},
                {0,1,0,0,0,4,0,0,0,0},
                {0,2,4,4,0,4,0,0,0,0},
                {0,0,0,4,0,4,0,0,0,0},
                {0,2,4,4,0,4,0,0,0,0},
                {0,0,0,0,0,4,0,0,0,0} };
        Board board = new Board(tableBoard);
        //WHEN
        board.printBoard();
        boolean isRoadExist = board.possibleRouteDown(0,0,9,9);
        //THEN
        assertFalse(isRoadExist);
    }

    @Test
    public void shouldJustWorks(){
        //GIVEN
        Board board = new Board(10,10);
        //WHEN
        board.printBoard();
        Boolean isRoadExist = null;
        isRoadExist = board.possibleRouteDown(0,0,9,9);
        //THEN
        assertNotNull(isRoadExist);
    }

}