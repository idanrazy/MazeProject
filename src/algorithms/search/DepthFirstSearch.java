package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * DepthFirstSearch Algorithm.
 */
public class DepthFirstSearch extends ASearchingAlgorithm {

    /**
     *
     * {@inheritDoc}
     */
    public DepthFirstSearch() {
        super("DepthFirstSearch");
    }

    public Solution solve(ISearchable s){
        Stack<AState> stack = new Stack<AState>();
        Solution solution=null;
        //get start point
        stack.push(s.getInitiaLState());
        evaluateNodes++;
        s.getInitiaLState().set_father(null);
        HashMap<String,Boolean> visited = new HashMap<String,Boolean>();
        boolean found = false;
        visited.put(s.getInitiaLState().getState(),true);
        AState temp=null;
        while(!stack.empty()&&!found)
        {
            temp = stack.pop();
            if(temp.getState().compareTo(s.getGoalState().getState())==0) {
                found = true;
                break;
            }
            else {
                ArrayList<AState> neighbors = s.getAllPossibleStates(temp);
                //no possible move
                if(neighbors==null)
                    continue;
                for (int i = 0; i < neighbors.size(); i++) {
                    if(!visited.containsKey(neighbors.get(i).getState())) {
                        neighbors.get(i).set_father(temp);
                        stack.push(neighbors.get(i));
                        evaluateNodes++;
                        visited.put(temp.getState(),true);
                    }
                }
            }
        }
        //temp = s.getGoalState();
        solution = new Solution();
        calculatePath(temp,solution);
        return solution;
    }


}
