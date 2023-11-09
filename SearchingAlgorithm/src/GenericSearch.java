import java.util.LinkedList;

interface CallbackFunction {
    void onCallback();
}

public class GenericSearch {

    public Object GeneralSearchFuntion(CallbackFunction callbackFunction) {
        if (Game.queue.isEmpty()) {
            return false;
        }
        for (int i = 0; i < Game.queue.size(); i++) {
            Node headNode = Game.queue.removeFirst();

            NodeState headNodeState = headNode.getState();
            if (headNodeState.prosperity == 100) {
                return headNode;
            } else {
                callbackFunction.onCallback();
            }
        }
        return false;
    }

    public static LinkedList<Node> getPossibleOperators(Node currentNode) {
        LinkedList<Node> possibleOperators = new LinkedList<>();
        // During delay of any request -> Build1, Build2, Wait
        
        if (Game.deliveries.size() > 0) {
            
        }
        if (condition) {
            
        }
        if (condition) {
            
        }
        if (condition) {
            
        }
        if (condition) {
            
        }
        if (condition) {
            
        }

        return possibleOperators;
    }


    public static LinkedList<Node> getPathToGoal(Node inputNode) {
        LinkedList<Node> pathToGoal = new LinkedList<>();

        Node currentNode = inputNode;
        pathToGoal.addFirst(currentNode);
        while (currentNode != null) {
            pathToGoal.addFirst(currentNode.getParent());
            currentNode = currentNode.getParent();
        }
        
        return pathToGoal;
    }

    // Get all possible nodes under parent and create their node with the parent relation

}
