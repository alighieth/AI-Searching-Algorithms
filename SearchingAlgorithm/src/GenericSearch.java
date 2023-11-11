import java.util.LinkedList;

interface Q_INGFunc {
    void onCallback();
}

public class GenericSearch {

    public Object GeneralSearchFuntion(SearchProblem problem, Q_INGFunc callbackFunction) {
        if (problem.queue.isEmpty()) {
            return false;
        }
        for (int i = 0; i < problem.queue.size(); i++) {
            Node headNode = problem.queue.removeFirst();

            if (isGoalState(headNode)) {
                return headNode;
            } else {
                callbackFunction.onCallback();
            }
        }
        return false;
    }

    public boolean isGoalState(Node node) {
        NodeState nodeState = node.getState();
        return nodeState.prosperity >= 100;
    }
}
