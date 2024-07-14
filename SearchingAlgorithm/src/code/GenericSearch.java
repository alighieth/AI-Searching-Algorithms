package code;

interface Q_INGFunc {
    void onCallback(SearchProblem problem, Node parentNode);
}

public class GenericSearch {

    public Node GeneralSearchFuntion(SearchProblem problem, Q_INGFunc callbackFunction) {
        while (!problem.queue.isEmpty()) {
            Node headNode = problem.queue.remove();

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
        return node.getState().getOrganisms().size() == 1;
    }
}
