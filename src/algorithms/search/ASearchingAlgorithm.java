package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {

    protected HashSet<AState> evaluatedNodes;
    protected int evaluatedNodesCount;
    protected ArrayList<AState> neighbors;
    protected Solution solution;

    /**
     * get the number of nodes that the searching algorithm has been in their state
     * @return evaluatedNodesCount - the counter of the evaluated nodes
     */
    public int getNumberOfNodesEvaluated() {
        return evaluatedNodesCount;
    }

    /**
     *
     * @return
     */
    public  String getName(){
        return this.getClass().getSimpleName();
    }


    //////////////////////////////////////////////////////////////

    int BFS(int mat[][COL], Point src, Point dest)
    {
        // check source and destination cell
        // of the matrix have value 1
        if (!mat[src.x][src.y] || !mat[dest.x][dest.y])
            return INT_MAX;

        bool visited[ROW][COL];
        memset(visited, false, sizeof visited);

        // Mark the source cell as visited
        visited[src.x][src.y] = true;

        // Create a queue for BFS
        queue<queueNode> q;

        // distance of source cell is 0
        queueNode s = {src, 0};
        q.push(s);  // Enqueue source cell

        // Do a BFS starting from source cell
        while (!q.empty())
        {
            queueNode curr = q.front();
            Point pt = curr.pt;

            // If we have reached the destination cell,
            // we are done
            if (pt.x == dest.x && pt.y == dest.y)
                return curr.dist;

            // Otherwise dequeue the front cell in the queue
            // and enqueue its adjacent cells
            q.pop();

            for (int i = 0; i < 4; i++)
            {
                int row = pt.x + rowNum[i];
                int col = pt.y + colNum[i];

                // if adjacent cell is valid, has path and
                // not visited yet, enqueue it.
                if (isValid(row, col) && mat[row][col] &&
                        !visited[row][col])
                {
                    // mark cell as visited and enqueue it
                    visited[row][col] = true;
                    queueNode Adjcell = { {row, col},
                            curr.dist + 1 };
                    q.push(Adjcell);
                }
            }
        }

        //return -1 if destination cannot be reached
        return INT_MAX;
    }

    //////////////////////////////////////////////////////////////


}


