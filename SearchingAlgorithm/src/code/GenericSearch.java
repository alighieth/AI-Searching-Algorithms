package code;

interface Q_INGFunc {
    void onCallback(SearchProblem problem, Node parentNode);
}
public class GenericSearch {

    public Node GeneralSearchFuntion(SearchProblem problem, Q_INGFunc callbackFunction) {
        while (!problem.queue.isEmpty() || !problem.priorityQueue.isEmpty()) {
            Node headNode = null;
            if(problem.strategy.equals(SearchProblem.SearchAlgorithms.AS1) || 
            problem.strategy.equals(SearchProblem.SearchAlgorithms.AS2) || problem.strategy.equals(SearchProblem.SearchAlgorithms.GR1) 
             || problem.strategy.equals(SearchProblem.SearchAlgorithms.GR2)) {
                headNode = problem.priorityQueue.remove();
            } else {
                headNode = problem.queue.removeFirst();
            }
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
