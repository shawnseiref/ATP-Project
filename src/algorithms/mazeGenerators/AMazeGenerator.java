package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    public AMazeGenerator() {
    }

    @Override
    public abstract Maze generate(int rows, int columns);

    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        return 0;
    }
}
