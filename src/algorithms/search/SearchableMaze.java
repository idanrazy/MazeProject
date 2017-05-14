package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import java.util.*;
import java.util.ArrayList;

/**
 * Created by idanr on 23/04/2017.
 */
public class SearchableMaze implements ISearchable {
    Maze maze ;
    AState StartPosition;
    AState EndPosition;
    /**
     *  save the visited state
     */
    HashMap<String,AState> visited;

    /**
     * Constructor
     * @param maze the maze of the problem
     */
    public SearchableMaze(Maze maze) {
        this.maze = maze;
        Position t =maze.getStartPosition();
        StartPosition = new MazeState(t.getRowIndex(),t.getColumnIndex());
        t =maze.getGoalPosition();
        EndPosition = new MazeState(t.getRowIndex(),t.getColumnIndex());
        visited=new HashMap<>();

    }

    /**
     * @return the initial state
     */
    @Override
    public AState getInitiaLState() {
        return  StartPosition;
    }

    /**
     * @return the goal state
     */
    @Override
    public AState getGoalState() {

        return  EndPosition;
    }

    /**
     * @param s current state
     * @return array list of the legal neighbors state
     */
    @Override
    public ArrayList<AState> getAllPossibleStates(AState s) {
        String[] coordinate = s.getState().split(",");
        // save possiblesState

        //cut "{" , "}"
       String x = coordinate[0].substring(1);
       String y = coordinate[1].substring(0,coordinate[1].length()-1);

        ArrayList<AState> L = new ArrayList<AState>();
        int row = Integer.parseInt(x);
        int col = Integer.parseInt(y);
        //down
        if(legalstate(row-1,col,s))
            stateTreat(row-1,col,1,L);        //up
        if(legalstate(row+1,col,s))
            stateTreat(row+1,col,1,L);
        //left
        if(legalstate(row,col-1,s))
            stateTreat(row,col-1,1,L);
        //right
        if(legalstate(row,col+1,s))
            stateTreat(row,col+1,1,L);

        //daigonals
       /* if(legalstate(row+1,col+1,s))
            if(legalstate(row,col+1,null) || legalstate(row+1,col,null))
                stateTreat(row+1,col+1,1.5,L);
        if(legalstate(row+1,col-1,s))
            if(legalstate(row,col-1,null) || legalstate(row+1,col,null))
                stateTreat(row+1,col-1,1.5,L);
        if(legalstate(row-1,col-1,s))
            if(legalstate(row-1,col,null) || legalstate(row,col-1,null))
                stateTreat(row-1,col-1,1.5,L);
        if(legalstate(row-1,col+1,s))
            if(legalstate(row-1,col,null) || legalstate(row,col+1,null))
                stateTreat(row-1,col+1,1.5,L);*/
        return L;

    }

    /**
     * @param row- the row that the state locate
     * @param col- the column that the state locate
     * @param s- the object state
     * @return if the state is a legal state (not a wall and inside the maze)
     */
    private boolean legalstate(int row , int col,AState s)
    {

        if(row<0||col<0||row>maze.getRows()-1||col>maze.getCols()-1||maze.getMaze()[row][col]==1)
            return false;
/*
        if(s!=null && s.get_father()!=null) {
            if (s.get_father().getState().equals(String.format("%s,%s", row, col)))
                return false;
        }

*/
return true;
    }

    /**
     * @param row- the row that the state locate
     * @param col- the column that the state locate
     * @param cost- the cost to get to the state
     * @param L- list of the neighbors of the state
     *         update the states that visited
     */
    private void stateTreat(int row,int col,double cost,ArrayList<AState> L){
        String mazeState=String.format("{%s,%s}",row,col);
        if(visited.containsKey(mazeState)){
            L.add(visited.get(mazeState));
        }
        else {
            AState state=new MazeState(row,col,cost);
            L.add(state);
            visited.put(state.toString(),state);
        }
    }

}
