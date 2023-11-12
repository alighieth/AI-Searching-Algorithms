package code;
import java.util.LinkedList;

public class SearchAlgorithms {

    static void breadthFirstSearch(SearchProblem problem, Node parentNode) {
        if(problem.visualize) {
            System.out.println("Running " + SearchProblem.SearchAlgorithms.BF.name());
            System.out.println("All possible actions as children to this node " + parentNode.getOperator());
        }
        
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        if(problem.visualize) {
            problem.printNodeExpansion(expandedNodes);
        }
        
        for (Node operatorNode : expandedNodes) {
            problem.queue.addLast(operatorNode);
        }
    }

    static void depthFirstSearch(SearchProblem problem, Node parentNode) {
        if(problem.visualize) {
            System.out.println("Running " + SearchProblem.SearchAlgorithms.DF.name());
        }

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