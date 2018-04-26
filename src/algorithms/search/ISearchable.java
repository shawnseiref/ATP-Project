package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {

    AState getStartingState();

    AState getEndingState();

    ArrayList<AState> getAllPossibleStates(AState state);
}
