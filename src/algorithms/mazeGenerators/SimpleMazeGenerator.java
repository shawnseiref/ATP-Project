package algorithms.mazeGenerators;

/**
 * Created by זיו on 17/04/2017.
 */
public class SimpleMazeGenerator extends AMazeGenerator {
    /**
     * build the 2d maze in a simple way
     * the start position in the left side and the goal position in the right side
     * @param rows
     * @param columns
     * @return 2d generated maze
     */
    @Override
    public Maze generate(int rows, int columns) {

        if (rows < 0 || columns < 0) {
            return null;
        }
        int[][] data = new int[rows][columns]; //create 2D int array

        for (int i = 0; i < rows; i++) { //init all cells random number 0/1
            for (int j = 1; j < columns; j++)
                data[i][j] = (int) (Math.random() * 2);
        }

        int enter = (int) (Math.random() * rows); //random enter
        data[enter][0] = 0;

        int exit = (int) (Math.random() * rows);  //random exit
        data[exit][columns - 1] = 0;

        double flipCoin;
        boolean finished = false;
        int i = enter;
        int j = 0;
        while (!finished){ //build path and junctions
            flipCoin = Math.random();
            if (flipCoin > 0.5 && i!=exit) {
                if (i < exit && i+1<rows) {
                    data[i+1][j] = 0;
                    i++;
                }
                else if (i > exit && i-1>=0) {
                    data[i - 1][j] = 0;
                    i--;
                }
            }
            else if (j+1 < columns){
                data[i][j + 1] = 0;
                j++;
            }

            if (i == exit && j == columns - 1)
                finished = true;
        }
        Maze maze = new Maze(rows,columns);
        maze.setGrid(data);
        Position start = new Position(enter, 0);
        maze.setStartPos(start.getRowIndex(),start.getColumnIndex());
        Position goal = new Position(exit, columns-1);
        maze.setGoalPos(goal.getRowIndex(),goal.getColumnIndex());

        return maze;
    }

}
