package algorithms.mazeGenerators;

/**
 * Maze class
 */
public class Maze {
    private int [][] grid;
    private Position startPos;
    private Position goalPos;


    /**
     * Maze constructor, initialize all slots as wall (1)
     *                   and start and goal positions as (-1,-1)
     * @param rows - number of rows the maze will have
     * @param columns - number of columns the maze will have
     */
    public Maze (int rows, int columns){
        if (rows < 2) rows = 10;
        if (columns < 2) columns = 10;
        grid = new int[rows][columns];
        // CHECK IF ROWS OR COLUMNS >= 0 !!!!!
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid [i][j]=1;
            }
        }
        startPos = new Position(-1,-1);
        goalPos = new Position(-1,-1);
    }

    /**
     * Constuctor for prepared grid and positions
     * @param preparedGrid - the grid to make maze of
     * @param start - start position
     * @param goal - goal position
     */
    public Maze(int[][] preparedGrid, Position start, Position goal) {
        grid = preparedGrid;
        startPos = start;
        goalPos = goal;
    }

    /**
     * get the maze' grid
     * @return  int [][] grid
     */
    public int[][] getGrid(){
        return grid;
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
    public Position getGoalPosition() {
        return goalPos;
    }

    /**
     * set the Start position
     * @param row - the new row value
     * @param column - the new column value
     */
    public void setStartPos(int row, int column){
        if(getGoalPosition().getColumnIndex() == -1 && getGoalPosition().getRowIndex() == -1) {
            startPos.setRow(row);
            startPos.setColumn(column);
            grid[row][column] = 0;
        }
    }


    /**
     * set the Goal position
     * @param row - the new row value
     * @param column - the new column value
     */
    public void setGoalPos(int row, int column){
        if(getGoalPosition().getColumnIndex() == -1 && getGoalPosition().getRowIndex() == -1) {
            goalPos.setRow(row);
            goalPos.setColumn(column);
            grid[row][column] = 0;
        }
    }

    /**
     * set the grid
     * @param grid - the new grid to use
     */
    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    /**
     * print the maze
     * S - start Position
     * E - exit Position
     */
    public void print(){
        int s_row = getStartPosition().getRowIndex();
        int s_column = getStartPosition().getColumnIndex();
        int e_row = getGoalPosition().getRowIndex();
        int e_column = getGoalPosition().getColumnIndex();
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j< grid[0].length; j++){
                if (i == s_row && j == s_column)
                    System.out.print("S");
                else if (i == e_row && j == e_column)
                    System.out.print("E");
                else
                    System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
}
