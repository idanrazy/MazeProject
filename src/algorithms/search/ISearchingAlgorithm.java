package algorithms.search;

/**
 * Interface for Searching Algorithm.
 */
public interface ISearchingAlgorithm {
    /**
     * solve the Searching Problem
     * @param s Searchable Object
     * @return Solution Path
     */
    Solution solve(ISearchable s);

    /**
     * Count the Number of Nodes that was visited
     * @return Number of Nodes that was visited
     */
    int getNumberOfNodesEvaluated();

    /**
     *
     * @return the Name of the Algorithm
     */
     String getName();
}
