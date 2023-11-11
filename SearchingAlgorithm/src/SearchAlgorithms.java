import java.util.LinkedList;

public class SearchAlgorithms {

    static void breadthFirstSearch(SearchProblem problem, Node parentNode) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        problem.printNodeExpansion(expandedNodes);
        for (Node operatorNode : expandedNodes) {
            problem.queue.addLast(operatorNode);
        }
    }

    static void depthFirstSearch(SearchProblem problem, Node parentNode) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        for (int i = expandedNodes.size(); i == 0; i--) {
            problem.queue.addFirst(expandedNodes.get(i));
        }

    }

    static void iterativeDeepeningSearch(SearchProblem problem, Node parentNode) {

    }

    static void uniformCostSearch(SearchProblem problem, Node parentNode) {

    }

    static void greedySearch(SearchProblem problem, Node parentNode, int heuristic) {

    }

    static void aStarSearch(SearchProblem problem, Node parentNode, int heuristic) {

    }

}