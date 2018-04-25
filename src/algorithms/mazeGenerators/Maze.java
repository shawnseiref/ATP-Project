package algorithms.mazeGenerators;

/**
 * Maze class
 */
public class Maze {
    private int[][] maze;
    private Position startPos;
    private Position goalPos;

    public Maze() {
        maze = null;
        startPos = null;
        goalPos = null;
    }

    /**
     * Maze constructor, initialize all slots as wall (1)
     * and start and goal positions as (-1,-1)
     *
     * @param rows    - number of rows the maze will have
     * @param columns - number of columns the maze will have
     */
    public Maze(int rows, int columns) {
        maze = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze[i][j] = 1;
            }
        }
        startPos = new Position(-1, -1);
        goalPos = new Position(-1, -1);
    }


    public Maze(int rows, int columns, Position startPos, Position goalPos) {
        this(rows, columns);
        this.startPos = startPos;
        this.goalPos = goalPos;
    }

    public Maze(int[][] grid, Position start, Position goal) {
        maze = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                maze[i][j] = grid[i][j];
            }
        }
        startPos = start;
        goalPos = goal;
    }

    /**
     * get the start position
     *
     * @return startPosition
     */
    public Position getStartPosition() {
        return startPos;
    }

    /**
     * get the goal position
     *
     * @return goalPosition
     */
    public Position getGoalPosition() {
        return goalPos;
    }

    public void setStartPos(Position start) {
        if (startPos.getColumnIndex() == -1 && startPos.getRowIndex() == -1)
            startPos = start;
    }

    public void setGoalPos(Position goal) {
        if (goalPos.getColumnIndex() == -1 && goalPos.getRowIndex() == -1)
            goalPos = goal;
    }

    /**
     * print the maze
     * S - start Position
     * E - exit Position
     */
    public void print() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 1)
                    System.out.print("[#]");
                else if (i==startPos.getRowIndex() && j==startPos.getColumnIndex())
                    System.out.print("|S|");
                else if (i==goalPos.getRowIndex() && j==goalPos.getColumnIndex())
                    System.out.print("|G|");
                else System.out.print("[ ]");
            }
            System.out.println();
        }
    }
}
