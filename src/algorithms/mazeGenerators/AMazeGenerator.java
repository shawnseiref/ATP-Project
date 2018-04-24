package algorithms.mazeGenerators;

import java.util.ArrayList;

public abstract class AMazeGenerator implements IMazeGenerator {

    protected ArrayList<Position> neighbors;

    @Override
    public abstract Maze generate(int rows, int columns);

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        long startTime = System.currentTimeMillis();
        generate(rows, columns);
        long finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }
}
