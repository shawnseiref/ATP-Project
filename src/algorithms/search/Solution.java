package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {

    private ArrayList solutionPath;

    /**
     * constructor for solution
     */
    public Solution(){
        solutionPath = new ArrayList();
    }

    /**
     *
     * @return list of the solution path
     */
    public ArrayList getSolutionPath() {

        return solutionPath;
    }

    /**
     * goes back from the goal state through the path to start and reverse it
     * the solution path is from start to goal
     * @param goal
     */
    public void backTrace(AState goal){

        this.solutionPath.add((goal));
        while(goal.getPrev()!=null){
            this.solutionPath.add(goal.getPrev());
            goal = goal.getPrev();
        }
        Collections.reverse(solutionPath);

    }

    public String toString(){
        return solutionPath.size() + " Steps";
    }
}