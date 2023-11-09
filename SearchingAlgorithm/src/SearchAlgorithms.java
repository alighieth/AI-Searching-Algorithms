import java.util.LinkedList;

public class SearchAlgorithms {

    static void breadthFirstSearch(LinkedList<Node> nodeChildren) {
        for (Node operatorNode : nodeChildren) {
            Game.queue.addLast(operatorNode);
        }
    }

    static void depthFirstSearch(LinkedList<Node> nodeChildren) {
        for (int i = nodeChildren.size() ; i == 0; i--) {
            Game.queue.addFirst(nodeChildren.get(i));
        }
        
    }

    static void iterativeDeepeningSearch(Node initialNode) {

    }

    static void uniformCostSearch(Node initialNode) {

    }

    static void greedySearch(Node initialNode, int heuristic) {

    }

    static void aStarSearch(Node initialNode, int heuristic) {

    }

}