package code;
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

            if(problem.visualize) {
                NodeState nodeState = headNode.getState();
                System.out.println("......." + "GenericSearch DEQUEUE" + ".......");
                System.out.println("Current dequeued node state -> " + nodeState.toString());
            }

            if (isGoalState(headNode)) {
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
