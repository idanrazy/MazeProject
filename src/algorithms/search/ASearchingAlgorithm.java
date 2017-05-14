package algorithms.search;

/**
 * abstract Searching Algorithm
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    int evaluateNodes;
    String name;

    /**
     *Constructor
     * @param name of the Algorithm
     */
    public ASearchingAlgorithm(String name) {
        this.evaluateNodes = 0;
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public int getNumberOfNodesEvaluated() {
        return evaluateNodes;
    }

    /**
     * Calculate the Solution Path from the start to the Goal state
     * @param temp Goal State
     * @param solution Solution Object to add the Path
     */
    public void calculatePath(AState temp,Solution solution){
        while(temp.get_father()!=null&&temp!=null)
        {
            solution.Add(temp);
            temp=temp.get_father();
        }
        solution.Add(temp);


    }

}
