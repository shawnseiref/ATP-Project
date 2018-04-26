package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected HashSet<AState> evaluatedNodes;
    protected int evaluatedNodesCount;
    protected ArrayList<AState> neighbors;
    protected Solution solution;

    public int getNumberOfNodesEvaluated() {
        return evaluatedNodesCount;
    }

    public  String getName(){
        return this.getClass().getSimpleName();
    }


}
