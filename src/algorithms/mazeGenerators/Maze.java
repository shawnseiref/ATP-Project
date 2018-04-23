package algorithms.mazeGenerators;

/**
 * Maze class
 */
public class Maze {
    private int [][] maze;
    private Position startPos;
    private Position goalPos;


    /**
     * Maze constructor, initialize all slots as wall (1)
     *                   and start and goal positions as (-1,-1)
     * @param rows - number of rows the maze will have
     * @param columns - number of columns the maze will have
     */
    public Maze (int rows, int columns){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze [i][j]=1;
            }
        }
        startPos = new Position(-1,-1);
        goalPos = new Position(-1,-1);
    }

    /**
     * get the start position
     * @return startPosition
     */
    public Position getStartPosition(){
        return startPos;
    }

    /**
     * get the goal position
     * @return goalPosition
     */
    public Position getGoalPos() {
        return goalPos;
    }

    /**
     * print the maze
     * S - start Position
     * E - exit Position
     */
    public void print(){

    }
}
