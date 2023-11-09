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

    public static LinkedList<Game.Operators> getPossibleOperators(Node currentNode) {
        LinkedList<Game.Operators> possibleOperators = new LinkedList<>();
        // During delay of any request -> Build1, Build2, Wait
        // waiting only allowed if a delivery is taking place

        if(Game.deliveries == null) {
            possibleOperators.addFirst(Game.Operators.WAIT);
        } else {
            possibleOperators.addFirst(Game.Operators.REQUEST_ENERGY);            
            possibleOperators.addFirst(Game.Operators.REQUEST_FOOD);
            possibleOperators.addFirst(Game.Operators.REQUEST_MATERIALS);
        }
        
        possibleOperators.addFirst(Game.Operators.Build1);        
        possibleOperators.addFirst(Game.Operators.Build2);


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
