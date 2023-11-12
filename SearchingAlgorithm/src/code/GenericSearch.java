package code;
interface Q_INGFunc {
    void onCallback(SearchProblem problem, Node parentNode);
}

public class GenericSearch {

    public Node GeneralSearchFuntion(SearchProblem problem, Q_INGFunc callbackFunction) {
        if (problem.queue.isEmpty()) {
            return null;
        }
        while(!problem.queue.isEmpty()) {
            problem.iterations++;
            System.out.println("Num iterations " + problem.iterations);
            Node headNode = problem.queue.removeFirst();
            problem.currentNode = headNode;
            System.out.println("Head node " + headNode.getOperator() + " " + headNode.getState().toString());

            if(problem.visualize) {
                NodeState nodeState = headNode.getState();
                System.out.println("......." + "GenericSearch DEQUEUE" + ".......");
                System.out.println("Current dequeued node state -> " + nodeState.toString());
            }

            if (isGoalState(headNode)) {
                return headNode;
            } else {
                callbackFunction.onCallback(problem, headNode);
            }
        }
        
        return null;
    }

    public boolean isGoalState(Node node) {
        NodeState nodeState = node.getState();
        return nodeState.prosperity >= 100;
    }
}
