import java.util.LinkedList;

interface CallbackFunction {
    void onCallback();
}

public class GenericSearch {
    public enum SearchAlgorithms{
        BF,
        DF,
        ID,
        UC,
        GR1,
        GR2,
        AS1,
        AS2
    }

    private static LinkedList<Node> queue = new LinkedList<>(); 


    public Object GeneralSearchFuntion(CallbackFunction callbackFunction) {
        if(GenericSearch.queue.isEmpty()) {
            return false;
        }
        for (int i = 0; i < queue.size() ; i++) {
            Node headNode = queue.removeFirst();

            NodeState headNodeState = headNode.getState();
            if(headNodeState.prosperity == 100) {
                return headNode;
            } else {
                callbackFunction.onCallback();
            }
        }
        return false;
    }
}
