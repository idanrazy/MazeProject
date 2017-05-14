package algorithms.search;

import java.util.*;
import java.util.PriorityQueue;
import java.util.Comparator;


/**
 * BestFirstSearch Algorithm.
 */
public class BestFirstSearch extends ASearchingAlgorithm {

    /**
     * Constructor that insert the name of searching algorithm
     */
    public BestFirstSearch() {

        super("BestFirstSearch");
    }

    /**
     * {@inheritDoc}
     */
    public Solution solve(ISearchable s) {
        PriorityComp copm = new PriorityComp();

        PriorityQueue<AState> open = new PriorityQueue<>(copm);

        HashSet<AState> closed = new HashSet<>();
        open.add(s.getInitiaLState());
        evaluateNodes++;
        boolean end = false;
        while (!open.isEmpty() && !end) {
            AState current = open.poll();
            closed.add(current);
            String goal = s.getGoalState().getState();
            if (current.getState().equals(goal)) {
                //AState go=s.getGoalState();
                s.getGoalState().set_father(current.get_father());
                AState fa = s.getGoalState().get_father();
                end = true;
            } else {
                //the nighebors of the current state
                ArrayList<AState> nighbor = s.getAllPossibleStates(current);
                if (!nighbor.isEmpty()) {

                    for (AState state : nighbor) {
                        if (!closed.contains(state) && !open.contains(state)) {
                            state.set_father(current);
                            state.setDistance(state.getCost() + state.get_father().getDistance());
                            open.add(state);
                            evaluateNodes++;
                        }
                        double newCost = state.getCost() + current.getDistance();

                        //if the cost from the new path lower from the current path
                        if (newCost < state.getDistance()) {
                            if (!open.contains(state)) {
                                open.add(state);
                                evaluateNodes++;
                            }
                            state.setDistance(newCost);
                            state.set_father(current);
                        }
                    }
                }
            }
        }

        Solution solution = new Solution();
        calculatePath(s.getGoalState(), solution);
        return solution;
    }
}


/**
 * Class that present compare by the distance of the states
 */
class PriorityComp implements Comparator<Object>{
    public int compare(Object o1,Object o2)
    {
        AState _o1=(AState)o1;
        AState _o2=(AState)o2;
        //if>0 o1 begger than o2
        return Double.compare(_o1.getDistance(),_o2.getDistance());
    }
}





