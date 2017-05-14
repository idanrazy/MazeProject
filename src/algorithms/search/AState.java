package algorithms.search;

/**
 * State represent a situation in Searching Problem
 */
public  abstract class AState {
    /**
     * state String represent situation
     * cost value to get to the state
     * father point to previous father
     */
    private String state;
    private double cost;
    private AState _father;
    private double distance;


    /**
     * Constructor
     * @param state String represent situation
     */
    public AState(String state) {
        this.state = state;
    }

    /**
     * Constructor
     * @param state represent the situation
     * @param cost represent the cost to get to the situation
     */
    public AState(String state, double cost) {
        this.state = state;
        this.cost = cost;
        distance=0;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state represent the state of state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the cost for the state
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost represent the cost of state
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * @return from who the state came from
     */
    public AState get_father() {
        return _father;
    }

    /**
     * @param _father represent from who the state came from
     */
    public void set_father(AState _father) {
        this._father = _father;
    }

    /**
     * @return the string of the state
     */
    public String toString(){
        return state;
    }

    /**
     * @return the distance to get to the state
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance represnt the distance to get to state
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

}
