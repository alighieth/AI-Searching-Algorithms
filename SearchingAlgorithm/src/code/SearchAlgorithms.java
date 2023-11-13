package code;

import java.util.LinkedList;

public class SearchAlgorithms {

    static void breadthFirstSearch(SearchProblem problem, Node parentNode) {
        try {
            LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
            if (problem.visualize) {
                problem.printNodeExpansion(expandedNodes);
            }
            for (Node topNode : expandedNodes) {
                problem.queue.addLast(topNode);
            }
        } catch (Exception e) {
            System.err.println("Error in BF algorithm " + e.getMessage());
        }
    }

    static void depthFirstSearch(SearchProblem problem, Node parentNode) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        for (int i = expandedNodes.size()-1; i >= 0; i--) {
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