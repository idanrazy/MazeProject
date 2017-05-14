package algorithms.search;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * BreadthFirstSearch Algorithm.
 */
public class BreadthFirstSearch extends ASearchingAlgorithm  {


    public BreadthFirstSearch(){
        super("BreadthFirstSearch");
    }
    /**
     *
     * {@inheritDoc}
     */
    public Solution solve(ISearchable s) {

        HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
        Queue<AState> queue = new LinkedList<>();

        //ArrayList<AState> queue=new ArrayList<>();
        cost(s.getGoalState());
        //search.getInitialState().set_cost(0);--open list
        queue.add(s.getInitiaLState());
        evaluateNodes++;
        //adding the state to visited set
        visited.put(s.getInitiaLState().getState(), true);
        //father of the initinal state null
        s.getInitiaLState().set_father(null);

        //explored.add(search.getInitialState());
        boolean end = false;

        while (!queue.isEmpty() && !end) {
            //remove the head of the queue
            AState current = queue.poll();
            String goal = s.getGoalState().getState();
            if (current.getState().equals(goal)) {
                //AState go=s.getGoalState();
                s.getGoalState().set_father(current.get_father());
                AState fa = s.getGoalState().get_father();
                end = true;
            } else {
                //there are posible moves for this state
                if (!s.getAllPossibleStates(current).isEmpty()) {
                    for (AState state : s.getAllPossibleStates(current)) {
                        if (!visited.containsKey(state.getState())) {
                            cost(state);
                            //s.set_cost(0);
                            visited.put(state.getState(), true);
                            queue.add(state);
                            evaluateNodes++;
                            state.set_father(current);
                        } else {
                            end = false;
                        }
                    }
                }
            }
        }
        Solution solution = new Solution();
        calculatePath(s.getGoalState(), solution);
        return solution;
    }


    /**
     * @param s represent the cost of the current state
     *          set the cost to zero because there is no meaning for the cost at BFS
     */
    public void cost(AState s){
        s.setCost(0);
    }


}
