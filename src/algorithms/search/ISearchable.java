package algorithms.search;

import java.util.ArrayList;

/**
 * Isearchable <<Interface>>
 */
public interface ISearchable {

    /**
     * get the initial state
     * @return the state that the search begins with
     */
    AState getStartingState();

    /**
     * get the goal state
     * @return the state that the search finishes with
     */
    AState getEndingState();

    ArrayList<AState> getAllPossibleStates(AState state);
}
