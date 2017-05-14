package algorithms.search;

/**
 * Create Maze State with row and col index
 */
public class MazeState extends AState {
    /**
     * Create Maze State with row and col index
     * @param row value of current row
     * @param col value of current col
     */
    public MazeState(int row , int col) {
        super(String.format("{%s,%s}",row,col));
    }

    /**
     * Create Maze State with row and col index
     * @param row value of current row
     * @param col value of current col
     * @param cost value of the cost to get to this current state
     */
    public MazeState(int row , int col,double cost) {
        super(String.format("{%s,%s}",row,col),cost);
    }
}
