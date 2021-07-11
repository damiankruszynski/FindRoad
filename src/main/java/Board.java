import java.awt.*;
import java.util.*;

public class Board {

    private final LogicBoard logicBoard;
    private final int sizeX;
    private final int sizeY;
    private int[][] tableBoard;
    private String[][] tableRoad;
    private Stack<Point> moveStack;

    public Board(int sizeX, int sizeY){
       this.logicBoard = new LogicBoard(sizeX, sizeY);
       this.sizeX = sizeX;
       this.sizeY = sizeY;
       this.tableBoard = new int[sizeX][sizeY];
       this.tableRoad = new String[sizeX][sizeY];
       this.moveStack = new Stack<>();
       initializeBorad();
    }

    public boolean possibleRouteDown(int x0, int y0, int x1, int y1){
        Point currentPoint = new Point(x0,y0);
        Point endPoint = new Point(x1,y1);
        try {
            while (!currentPoint.equals(endPoint)) {
                Optional<Point> possibleMove = getAroundPointWithTheLowestValue(currentPoint, endPoint);
                if (!possibleMove.isEmpty()) {
                    saveMove(currentPoint);
                    currentPoint = possibleMove.get();
                    blockPointForMove(new Point(currentPoint.x,currentPoint.y));
                } else {
                    blockPointForMove(new Point(currentPoint.x,currentPoint.y));
                    currentPoint = removeLastMove();
                }
            }
            if(currentPoint.equals(endPoint)) {
                saveMove(currentPoint);
                System.out.println("Route exist !");
            }
        }
        catch (EmptyStackException emptyStackException){
            System.out.println("Sorry ! No possible route !");
            return false;
        }
        printPossibleRoad();
        return true;

    }

    private void blockPointForMove(Point point){
        logicBoard.getTableBoard()[point.x][point.y] = false;
    }

    private Point saveMove(Point atPoint){
        moveStack.push(atPoint);
        System.out.println("Go to field: "+atPoint.toString());
        return atPoint;
    }

    private Point removeLastMove(){
        System.out.println("Back to field: "+moveStack.peek().toString());
        return moveStack.pop();
    }

    private Optional<Point> getAroundPointWithTheLowestValue(Point point, Point endPoint){
        Integer currentValue =  tableBoard[point.x][point.y];
        Integer endValue =  tableBoard[endPoint.x][endPoint.y];
        Map<Point, Integer> lowerValuePointList= new HashMap<>();
        Point lowestPoint =  null;

        if(point.y-1 >= 0){
            Integer leftValue = tableBoard[point.x][point.y-1];
            if(currentValue <= leftValue && logicBoard.getTableBoard()[point.x][point.y-1] ){
                lowerValuePointList.put(new Point(point.x,point.y-1), leftValue );
            }
        }
        if(point.y+1 < sizeY){
            Integer rightValue = tableBoard[point.x][point.y+1];
            if(currentValue <= rightValue && logicBoard.getTableBoard()[point.x][point.y+1] ){
                lowerValuePointList.put(new Point(point.x,point.y+1), rightValue);

            }
        }
        if(point.x - 1 >= 0){
            Integer upValue = tableBoard[point.x-1][point.y];
            if(currentValue <= upValue && logicBoard.getTableBoard()[point.x-1][point.y] ){
                lowerValuePointList.put(new Point(point.x-1, point.y), upValue);

            }
        }
        if(point.x +1 < sizeX){
            Integer downValue = tableBoard[point.x+1][point.y];
            if(currentValue <= downValue && logicBoard.getTableBoard()[point.x+1][point.y] ){
                lowerValuePointList.put(new Point(point.x+1,point.y), downValue);

            }
        }
        if(lowerValuePointList.containsKey(endPoint)){
            lowestPoint = endPoint;
        }
        else if( lowerValuePointList.size() > 0){
            lowestPoint = getPointWithLowerValue(lowerValuePointList);
            if (tableBoard[lowestPoint.x][lowestPoint.y] > endValue){
                lowestPoint = null;
            }
        }

       return Optional.ofNullable(lowestPoint);
    }

    private Point getPointWithLowerValue(Map<Point, Integer> map){
        Map.Entry<Point, Integer> maxEntry = Collections.min(map.entrySet(), new Comparator<>() {
            public int compare(Map.Entry<Point, Integer> e1, Map.Entry<Point, Integer> e2) {
                return e1.getValue()
                        .compareTo(e2.getValue());
            }
        });
       return maxEntry.getKey();
    }

    private void initializeBorad(){
        Random random = new Random();
        for(int x=0;x<sizeX;x++){
            for (int y=0;y<sizeY;y++){
                int r = random.nextInt(2);
                tableBoard[x][y] =r;
                tableRoad[x][y] = Integer.toString(r);
            }
        }

    }

    public void printBoard(){
        String newLine = System.getProperty("line.separator");
        System.out.println("Number Board:");
        for(int x=0;x<sizeX;x++){
            for (int y=0;y<sizeY;y++){
                if(tableBoard[x][y] < 0 | tableBoard[x][y] > 9  ){
                    System.out.print("["+tableBoard[x][y]+" ]");
                }
                else{
                    System.out.print("[ " + tableBoard[x][y] + " ]");
                }
            }
            System.out.print(newLine);
        }
        System.out.print(newLine);

    }

    private void printPossibleRoad(){
        String newLine = System.getProperty("line.separator");
        System.out.println("Possible Road:");
        moveStack.stream().peek( s -> System.out.print(s.toString())).forEach(s -> System.out.println("{"+tableBoard[s.x][s.y]+"}"));
        moveStack.stream().forEach( s -> tableRoad[s.x][s.y] = "X");
        for(int x=0;x<sizeX;x++){
            for (int y=0;y<sizeY;y++){
                if(tableRoad[x][y].length() == 2  ){
                    System.out.print("["+tableBoard[x][y]+" ]");
                }
                else{
                    System.out.print("[ " + tableRoad[x][y] + " ]");
                }
            }
            System.out.print(newLine);
        }
        System.out.print(newLine);
    }

    public void printLogicBoard(){
        String newLine = System.getProperty("line.separator");
        System.out.println("Logic Board:");
        for(int x=0;x<sizeX;x++){
            for (int y=0;y<sizeY;y++){
                System.out.print("["+logicBoard.getTableBoard()[x][y]+"]");
            }
            System.out.print(newLine);
        }
        System.out.print(newLine);
    }

}
