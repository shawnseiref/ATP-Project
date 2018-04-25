package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * our maze generator, uses prim's algorithm
 */
public class MyMazeGenerator extends AMazeGenerator {

    public MyMazeGenerator() {
        neighbors = new ArrayList<Position>();
    }

    @Override
    public Maze generate(int rows, int columns) {
        if (rows < 0 || columns < 0)
            return null;

        int[][] grid = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = 1;
            }
        }


        Position start = findRandomPositionOnFrame(rows, columns);
        grid[start.getRowIndex()][start.getColumnIndex()] = 0;
        neighbors.add(start);


        //add cells with value 0 to maze
        int count = 0;
        boolean flag = false;
        while (!neighbors.isEmpty()) {

            int r = (int) (Math.random() * neighbors.size()); // find random neighbor from list
            Position pos = new Position((Position) neighbors.get(r)); // save random position
            neighbors.remove(r); // remove random position from list

            //break wall if allowed and check that only one or less neighbors with value 0
            count = 0;
            if (pos.getRowIndex() - 1 >= 0 && grid[pos.getRowIndex() - 1][pos.getColumnIndex()] == 0)
                count++;
            if (pos.getColumnIndex() - 1 >= 0 && grid[pos.getRowIndex()][pos.getColumnIndex() - 1] == 0)
                count++;
            if (pos.getRowIndex() + 1 < grid.length && grid[pos.getRowIndex() + 1][pos.getColumnIndex()] == 0)
                count++;
            if (pos.getColumnIndex() + 1 < grid[0].length && grid[pos.getRowIndex()][pos.getColumnIndex() + 1] == 0)
                count++;
            if (count <= 1) {
                grid[pos.getRowIndex()][pos.getColumnIndex()] = 0;
                flag = true;
            }

            //add valid neighbors to list, only if pos was a valid neighbor and value was 1 before
            if (flag) {
                if (pos.getRowIndex() - 1 >= 0) {
                    if (grid[pos.getRowIndex() - 1][pos.getColumnIndex()] == 1) {
                        Position temp = new Position(pos.getRowIndex() - 1, pos.getColumnIndex());
                        neighbors.add(temp);
                    }
                }

                if (pos.getColumnIndex() - 1 >= 0) {
                    if (grid[pos.getRowIndex()][pos.getColumnIndex() - 1] == 1) {
                        Position temp = new Position(pos.getRowIndex(), pos.getColumnIndex() - 1);
                        neighbors.add(temp);
                    }
                }

                if (pos.getRowIndex() + 1 < grid.length) {
                    if (grid[pos.getRowIndex() + 1][pos.getColumnIndex()] == 1) {
                        Position temp = new Position(pos.getRowIndex() + 1, pos.getColumnIndex());
                        neighbors.add(temp);
                    }
                }

                if (pos.getColumnIndex() + 1 < grid[0].length) {
                    if (grid[pos.getRowIndex()][pos.getColumnIndex() + 1] == 1) {
                        Position temp = new Position(pos.getRowIndex(), pos.getColumnIndex() + 1);
                        neighbors.add(temp);
                    }
                }

                flag = false;
            }
        }

        Position goal = findRandomPositionOnFrame(rows, columns);
        while (goal.getColumnIndex()==start.getColumnIndex() || goal.getRowIndex()==start.getRowIndex() || grid[goal.getRowIndex()][goal.getColumnIndex()]!=0)
            goal = findRandomPositionOnFrame(rows, columns);

        //initialize myMaze with final grid
        Maze myMaze = new Maze(grid, start, goal);

        return myMaze;
    }


    private Position findRandomPositionOnFrame(int rows, int columns) {
        if (rows < 0 || columns < 0) return null;
        Position pos;
        int frameSide = (int) (Math.random() * 4); // choose a side of the frame
        if (frameSide % 2 == 0) {                  // if its an even number
            int onframe = (int) (Math.random() * columns);
            if (frameSide == 0)               // if its top frame side
                pos = new Position(0, onframe);
            else pos = new Position(rows - 1, onframe);
        } else {
            int onframe = (int) (Math.random() * rows);
            if (frameSide == 1)
                pos = new Position(onframe, 0);
            else pos = new Position(onframe, columns - 1);
        }
        return pos;
    }


}
