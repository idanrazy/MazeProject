package algorithms.mazeGenerators;

/**
 * Create Maze object
 */
public class Maze {
    private int Rows;
    private int Cols;
    private Position start_point;
    private Position end_point;
    private int Maze[][];

    /**
     * Maze constructor
     * @param rows Number of rows in the Maze
     * @param cols Number of columns in the Maze
     * @param start_point Position of the start Point
     * @param end_point Position of the End Point
     * @param Maze Integer matrix with 0 to represent Path or 1 to represent Wall
     */
    public Maze(int rows, int cols, Position start_point, Position end_point, int[][] Maze) {
        Rows = rows;
        Cols = cols;
        this.start_point = start_point;
        this.end_point = end_point;
        this.Maze = Maze;
    }

    public int getRows() {
        return Rows;
    }

    public void setRows(int rows) {
        Rows = rows;
    }

    public int getCols() {
        return Cols;
    }

    public void setCols(int cols) {
        Cols = cols;
    }

    /**
     *
     * @return  the start Position
     */
    public Position getStartPosition() {
        return start_point;
    }

    /**
     *
     * @param  start_point the start point
     */
    public void setStartPosition(Position start_point) {
        this.start_point = start_point;
    }

    /**
     *
     * @return  the goal point
     */
    public Position getGoalPosition() {
        return end_point;
    }

    public void setGoalPosition(Position end_point) {
        this.end_point = end_point;
    }

    /**
     *
     * @return Integer matrix with 0 to rePresent Path or 1  to represent Wall
     */
    public int[][] getMaze() {
        return Maze;
    }

    /**
     *
     * @param Maze Integer Maze
     */
    public void setMaze(int[][] Maze) {
        this.Maze = Maze;
    }

    /**
     * Print the Maze
     */
    public void print(){
        for(int i=0;i<Rows;i++)
        {
            for(int j=0;j<Cols;j++)
            {
                if(i==start_point.getRowIndex() && j==start_point.getColumnIndex())
                    System.out.print("S"+" ");
                else if(i==end_point.getRowIndex() && j==end_point.getColumnIndex())
                    System.out.print("E"+" ");
                else if(Maze[i][j]==1)
                    System.out.print("\u2588"+" ");
                else
                    System.out.print("\u2591"+" ");
            }
            System.out.println("");

        }
    }
}
