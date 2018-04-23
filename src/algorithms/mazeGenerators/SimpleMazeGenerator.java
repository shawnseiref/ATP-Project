package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {


    private Maze randomStartingPoint(Maze m, int rows, int columns) {
        int startPosition[] = new int[2];
        Random coinFlip = new Random();
        int startPoint = coinFlip.nextInt(3);
        if (startPoint == 0) {
            startPosition[0] = 0;
            startPoint = coinFlip.nextInt(columns);
            startPosition[1] = startPoint;
        } else if (startPoint == 1) {
            startPosition[0] = rows - 1;
            startPoint = coinFlip.nextInt(columns);
            startPosition[1] = startPoint;
        } else {
            startPoint = coinFlip.nextInt(rows);
            startPosition[0] = startPoint;
            startPoint = coinFlip.nextInt(2);
            if (startPoint == 0)
                startPosition[1] = 0;
            else
                startPosition[1] = columns - 1;
        }
        m.setStartPosition(startPosition[0], startPosition[1]);
        return m;
    }


    private int getBlock(int[][] grid, int x, int y) { // return -1 if out of range
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return -1;
        } else {
            return grid[x][y];
        }
    }

    private int[][] makePassage(int[][] grid, int x, int y, int i, int j) {
        if (getBlock(grid, x + i, y + j) == 1 && getBlock(grid, x + i + j, y + j + i) == 1
                && getBlock(grid, x + i - j, y + j - i) == 1) {
            if (getBlock(grid, x + i + i, y + j + j) == 1
                    && getBlock(grid, x + i + i + j, y + j + j + i) == 1
                    && getBlock(grid, x + i + i - j, y + j + j - i) == 1
                    ) {
                double dice = 0.5;
                if (Math.random() > dice) {
                    grid[x + i][y + j] = 0;
                }
            }
        }
        return grid;
    }

    @Override
    public Maze generate(int rows, int columns) {
        Maze m = new Maze(rows, columns);
        m = randomStartingPoint(m, rows, columns);
        int[][] grid = m.getGrid();
        int pCount = 0;
        for (int y = 0; y < grid[0].length; y++) {
            for (int x = 0; x < grid.length; x++) {
                if (grid[x][y] == 0) {
                    //check neighbours
                    grid = makePassage(grid, x, y, -1, 0);
                    grid = makePassage(grid, x, y, 1, 0);
                    grid = makePassage(grid, x, y, -1, -1);
                    grid = makePassage(grid, x, y, -1, 1);
                }
            }
        }
        for (int x = 0; x < grid.length; x++) {
            if (getBlock(grid, x, grid[0].length - 2) == 0) {
                grid[x][grid[0].length - 1] = 0;
                break;
            }
        }

        m.setGrid(grid);
        return m;
    }
}

