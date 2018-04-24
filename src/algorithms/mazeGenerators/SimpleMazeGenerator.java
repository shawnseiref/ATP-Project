package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {


//    private int checkCorner(int point, int border){
//        if (point == 0)
//            point++;
//        if (point == border -1)
//            point--;
//        return point;
//    }

    private int[] makePosition(int rows, int columns) {
        int position[] = new int[2];
        Random coinFlip = new Random();
        int point = coinFlip.nextInt(3);
        if (point == 0) {
            position[0] = 0;
            point = coinFlip.nextInt(columns);
//            point = checkCorner(point, columns);
            position[1] = point;
        } else if (point == 1) {
            position[0] = rows - 1;
            point = coinFlip.nextInt(columns);
//            point = checkCorner(point, columns);
            position[1] = point;
        } else {
            point = coinFlip.nextInt(rows);
//            point = checkCorner(point, rows);
            position[0] = point;
            point = coinFlip.nextInt(2);
            if (point == 0)
                position[1] = 0;
            else
                position[1] = columns - 1;
        }
        return position;
    }

    private int[][] makePassage(int[][] grid, int x, int y, int i, int j) {
        while (x != i || y != j) {
            if (x > i) {
                i++;
                grid[i][j] = 0;
            } else if (x != i) {
                x++;
                grid[x][y] = 0;
            } else if (y > j) {
                j++;
                grid[i][j] = 0;
            } else {
                y++;
                grid[x][y] = 0;
            }
        }
        return grid;
    }

    private int[][] randomizeWalls(int[][] grid) {
        Random coinFlip = new Random();
        int res;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res = coinFlip.nextInt(2);
                    if (res == 0)
                        grid[i][j] = 0;
                }
            }
        }
        return grid;
    }

    public Maze generate(int rows, int columns) {
        Maze m = new Maze(rows, columns);
        int startPosition[] = makePosition(rows, columns);
        m.setStartPos(startPosition[0], startPosition[1]);
        boolean flag = false;
        int endingPosition[] = new int[2];
        while (!flag) {
            endingPosition = makePosition(rows, columns);
            if (m.getStartPos().getRowIndex() != endingPosition[0] && m.getStartPos().getColumnIndex() != endingPosition[1]) {
                flag = true;
            }
        }
        m.setGoalPos(endingPosition[0], endingPosition[1]);
        int[][] grid = m.getGrid();
        grid = makePassage(grid, m.getGoalPos().getRowIndex(), m.getGoalPos().getColumnIndex(), m.getStartPos().getRowIndex(), m.getStartPos().getColumnIndex());
        grid = randomizeWalls(grid);
        m.setGrid(grid);
        return m;
    }
}