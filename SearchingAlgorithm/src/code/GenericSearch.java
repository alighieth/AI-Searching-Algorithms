package code;

interface Q_INGFunc {
    void onCallback(SearchProblem problem, Node parentNode);
}
public class GenericSearch {

    public Node GeneralSearchFuntion(SearchProblem problem, Q_INGFunc callbackFunction) {
        if (problem.queue.isEmpty()) {
            return null;
        }

        while (!problem.queue.isEmpty()) {
            Node headNode = problem.queue.removeFirst();
            problem.currentNode = headNode;
            if (isGoalState(headNode)) {
                return headNode;
            } else {
                callbackFunction.onCallback(problem, headNode);
            }
        }

        return null;
    }

    public static boolean isGoalState(Node node) {
        NodeState nodeState = node.getState();
        return nodeState.prosperity >= 100;
    }
}
