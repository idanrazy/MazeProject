package algorithms.mazeGenerators;

/**
 * abstract maze Generator
 */
public abstract class AMazeGenerator implements IMazeGenerator {

    Maze myMaze;
    @Override
    public long measureAlgorithmTimeMillis(int rows, int cols) {
        long countime = System.currentTimeMillis();
        myMaze = generate(rows,cols);
        countime =System.currentTimeMillis()-countime;
        return  countime;
    }
}
