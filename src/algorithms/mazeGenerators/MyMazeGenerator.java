package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by idanr on 05/04/2017.
 * Generate Maze with Prim Algorithm
 */
public class MyMazeGenerator extends AMazeGenerator {
    @Override
    /**
     * {@inheritDoc}
     */
    public Maze generate(int rows, int cols) {
        //bade on prim algorithm
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
        List<Position> Path =new ArrayList<Position>();
        if (rows < cols) {
            start = (int) (Math.random() * (rows-1));
            r = 0;
            c = start;
        }
        else {
            start = (int) (Math.random() * (cols-1));
            r = start;
            c = 0;
        }
        Position s_p = new Position(r, c, "S");
        int[][] IntPMaze = new int[rows][cols];
        for (int i = 0; i < IntPMaze.length; i++) {
            for (int j = 0; j < IntPMaze[i].length; j++) {
                IntPMaze[i][j]=1;
            }
        }
        IntPMaze[s_p.getRowIndex()][s_p.getColumnIndex()]=0;
        Path.add(s_p);
        while(!Path.isEmpty())
        {
            addneighborscell(Path,IntPMaze);
        }
        int j=IntPMaze[0].length-1;
        List<Position> EndP = new ArrayList<Position>();
        //choose random End point
        for(int i=0;i<IntPMaze.length;i++)
        {
            if(IntPMaze[i][j]==0)
                EndP.add(new Position(i,j,"E"));
        }

        Random rand = new Random();
        int i = rand.nextInt(EndP.size());
        //int i =(int)Math.random()*(EndP.size()-1);
        Maze myMaze=null;
        if(EndP.size()>0)
             myMaze = new Maze(rows,cols,s_p,EndP.remove(i),IntPMaze);
        return  myMaze;
    }
    //add neighbors cell to the list
    private void addneighborscell(List<Position> L,int[][] grid){
        if(L.size()==0 || L==null)
            return;
        //choose random cell from the list
        Random rand = new Random();
        int i = rand.nextInt(L.size());
        Position p = L.remove(i);
        //count the 0 neighbors
        int count = 0;
        if(p.getData()!="S")
        {


            if (p.getRowIndex() - 1 >= 0 && grid[p.getRowIndex() - 1][p.getColumnIndex()] == 0)
                count++;
            if (p.getColumnIndex() - 1 >= 0 && grid[p.getRowIndex()][p.getColumnIndex() - 1] == 0)
                count++;
            if (p.getColumnIndex() + 1 < grid[0].length && grid[p.getRowIndex()][p.getColumnIndex() + 1] == 0)
                count++;
            if (p.getRowIndex() + 1 < grid.length && grid[p.getRowIndex() + 1][p.getColumnIndex()] == 0)
                count++;
        }
        else
            count++;
        if(count==1) {
            if(p.getData()=="1") {
                p.setData("0");
                grid[p.getRowIndex()][p.getColumnIndex()]=0;
            }
            //add the neighbors
            if (p.getRowIndex() - 1 >= 0 && grid[p.getRowIndex() - 1][p.getColumnIndex()] == 1)
                L.add(new Position(p.getRowIndex() - 1, p.getColumnIndex(), "1"));
            if (p.getColumnIndex() - 1 >= 0 && grid[p.getRowIndex()][p.getColumnIndex() - 1] == 1)
                L.add(new Position(p.getRowIndex(), p.getColumnIndex() - 1, "1"));
            if (p.getColumnIndex() + 1 < grid[0].length && grid[p.getRowIndex()][p.getColumnIndex() + 1] == 1)
                L.add(new Position(p.getRowIndex(), p.getColumnIndex() + 1, "1"));
            if (p.getRowIndex() + 1 < grid.length && grid[p.getRowIndex() + 1][p.getColumnIndex()] == 1)
                L.add(new Position(p.getRowIndex() + 1, p.getColumnIndex(), "1"));

        }

        }
}
