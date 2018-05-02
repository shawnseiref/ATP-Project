package algorithms.search;

import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JUnitTestingBestFirstSearch {

    @Test
    void solveSimpleMaze() {
        IMazeGenerator mg = new SimpleMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch bestFS = new BestFirstSearch();
        long startTime = System.currentTimeMillis();
        Solution solution = bestFS.solve(searchableMaze);
        long finishTime = System.currentTimeMillis();
        assertTrue(finishTime - startTime < 30000);
        }

    @Test
    void solveMyMaze() {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(1000, 1000);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch bestFS = new BestFirstSearch();
        long startTime = System.currentTimeMillis();
        Solution solution = bestFS.solve(searchableMaze);
        long finishTime = System.currentTimeMillis();
        assertTrue(finishTime - startTime < 30000);
    }

    @Test
    void solveSimpleMazeWrongParameters() {
        IMazeGenerator mg = new SimpleMazeGenerator();
        Maze maze = mg.generate(1, 7);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch bestFS = new BestFirstSearch();
        long startTime = System.currentTimeMillis();
        Solution solution = bestFS.solve(searchableMaze);
        long finishTime = System.currentTimeMillis();
        assertTrue(finishTime - startTime < 30000);
    }

    @Test
    void solveMyMazeWrongParameters() {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(-1, 0);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch bestFS = new BestFirstSearch();
        long startTime = System.currentTimeMillis();
        Solution solution = bestFS.solve(searchableMaze);
        long finishTime = System.currentTimeMillis();
        assertTrue(finishTime - startTime < 30000);
    }

    @Test
    void simpleMazePositionsStartEndWrongParameters() {
        IMazeGenerator mg = new SimpleMazeGenerator();
        Maze maze = mg.generate(-1, 7);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch bestFS = new BestFirstSearch();
        Solution solution = bestFS.solve(searchableMaze);
        assertFalse(maze.getStartPosition().getRowIndex() == -1 || maze.getStartPosition().getColumnIndex()== -1);
    }

    @Test
    void myMazePositionsStartEndWrongParameters() {
        IMazeGenerator mg = new MyMazeGenerator();
        Maze maze = mg.generate(-1, 7);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        BestFirstSearch bestFS = new BestFirstSearch();
        Solution solution = bestFS.solve(searchableMaze);
        assertFalse(maze.getStartPosition().getRowIndex() == -1 || maze.getStartPosition().getColumnIndex()== -1);
    }
}