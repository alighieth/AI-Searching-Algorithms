interface Q_INGFunc {
    void onCallback();
}

public class GenericSearch {

    public Object GeneralSearchFuntion(SearchProblem problem, Q_INGFunc callbackFunction) {
        if (problem.queue.isEmpty()) {
            return null;
        }
        for (int i = 0; i < problem.queue.size(); i++) {
            Node headNode = problem.queue.removeFirst();
            problem.currentNode = headNode;
            // NodeState nodeState = headNode.getState();
            System.out.println("Operator " + headNode.getOperator());
            if (isGoalState(headNode)) {
                System.out.println("Head Node Food " + headNode.getState().food + "Head Node energy "
                        + headNode.getState().energy + "Head Node prosperity " + headNode.getState().prosperity);
                return headNode;
            } else {
                callbackFunction.onCallback();
            }
        }
        return null;
    }

    public boolean isGoalState(Node node) {
        NodeState nodeState = node.getState();
        return nodeState.prosperity >= 100;
    }
}
