package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Save path solution
 */
public class Solution implements Serializable {
    private ArrayList<AState> solution;

    public Solution() {
        this.solution = new ArrayList<AState>();
    }
    public void Add(AState s){
    solution.add(0,s);
    }

    public ArrayList<AState> getSolutionPath() {
        return solution;
    }

    public void setSolution(ArrayList<AState> solution) {
        this.solution = solution;
    }

}
