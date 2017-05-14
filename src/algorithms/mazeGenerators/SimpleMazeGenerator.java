package algorithms.mazeGenerators;

/**
 * Generate random maze
 */
public class SimpleMazeGenerator extends AMazeGenerator {
    @Override
    //create Simple maze
    /**
     * {@inheritDoc}
     */
    public Maze generate(int rows, int cols) {
        //select random start point to the maze
        int start;
        int r;
        int c;
        if(rows==0||cols==0)
            System.out.println("illegal argument , changed to 10*10");
        if(rows==0)
            rows=10;
        if(cols==0)
            cols=10;
        if (rows < cols) {
            start = (int) (Math.random() * (rows-1));
            r = 0;
            c = start;
        } else {
            start = (int) (Math.random() * (cols-1));
            r = start;
            c = 0;
        }
        Position s_p = new Position(r, c, "S");
        int[][] IntPMaze = new int[rows][cols];
        for (int i = 0; i < IntPMaze.length; i++) {
            for (int j = 0; j < IntPMaze[i].length; j++) {
                IntPMaze[i][j]=-1;
            }
        }
        Position EndPoint = Mazeintgen(rows, cols, s_p,IntPMaze);
        Position StartPoint = new Position(r,c,"S");
        Maze MySimpMaze = new Maze(rows,cols,StartPoint,EndPoint,IntPMaze);
        return MySimpMaze;
    }

    private Position Mazeintgen(int rows, int cols, Position start_point,int[][] IntPMaze) {

        double Case;
        int r = start_point.getRowIndex();
        int c = start_point.getColumnIndex();
        IntPMaze[r][c] = 0;

        //fill the maze with road solution
        while (r < rows && c < cols) {

            Case = Math.random();
            //choose go left
            if (Case < 0.25) {
                if (c - 1 >= 0 && IntPMaze[r][c - 1] == -1) {
                    IntPMaze[r][c - 1] = 0;
                    c--;
                }
            }
            //choose go right
            else if (Case < 0.5) {
                if (c + 1 < cols && IntPMaze[r][c + 1] == -1)
                    IntPMaze[r][c + 1] = 0;
                c++;

            }
            //choose go up
            else if (Case < 0.75) {
                if (r + 1 < rows && IntPMaze[r + 1][c] == -1)
                    IntPMaze[r + 1][c] = 0;
                r++;

            }
            //choose go down
            else {
                if (r - 1 >= 0 && IntPMaze[r - 1][c] == -1) {
                    IntPMaze[r - 1][c] = 0;
                    r--;
                }
            }
        }
        if(r>rows-1)
            r--;
        if(c>cols-1)
            c--;
        if(r<0)
            r++;
        if(c<0)
            c++;
        Position EndPoint = new Position(r,c,"E");
        //fill the rest of the maze
        for (int i = 0; i < IntPMaze.length; i++) {
            for (int j = 0; j < IntPMaze[i].length; j++) {
                if (IntPMaze[i][j] == 0)
                    continue;
                else {
                    if (Math.random() < 0.75)
                        IntPMaze[i][j] = 1;
                    else
                        IntPMaze[i][j] = 0;

                }
            }
        }
        return EndPoint;
    }
}
