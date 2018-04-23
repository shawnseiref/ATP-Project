package algorithms.mazeGenerators;

/**
 * Maze class
 */
public class Maze {
    private int [][] maze;
    private Position startPos;
    private Position goalPos;

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
