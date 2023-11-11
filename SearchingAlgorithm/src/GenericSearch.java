import java.util.LinkedList;
interface CallbackFunction {
    void onCallback();
}

public class GenericSearch {

    public Object GeneralSearchFuntion(CallbackFunction callbackFunction) {
        if (SearchProblem.queue.isEmpty()) {
            return false;
        }
        for (int i = 0; i < SearchProblem.queue.size(); i++) {
            Node headNode = SearchProblem.queue.removeFirst();

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

    

    // Get all possible nodes under parent and create their node with the parent relation

}
