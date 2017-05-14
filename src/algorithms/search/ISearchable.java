package algorithms.search;

import java.util.ArrayList;

/**
 * adapt problem to Searchable problem .
 */
public interface ISearchable {
    /**
     *
     * @return Initial State
     */
    AState getInitiaLState();

    /**
     *
     * @return return goal State
     */
    AState getGoalState();

    /**
     * calculate Possible States to continue from current state
     * @param s current state
     * @return List of possible State to continue
     */
    ArrayList<AState> getAllPossibleStates(AState s);
}
