package algorithms.mazeGenerators;
//check
/**
 * Created by idanr on 05/04/2017.
 */
public interface IMazeGenerator {
    /**
     *create A maze
     * @param rows number of rows to create maze
     * @param cols number of columns to create maze

     * @return Maze object
     */
    Maze generate(int rows , int cols);

    /**
     * measure time to create Maze
     * @param rows number of rows to create maze
     * @param cols number of columns to create maze
     * @return Maze object
     */
    long measureAlgorithmTimeMillis(int rows , int cols);
}
