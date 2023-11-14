package code;

import java.util.LinkedList;

public class SearchAlgorithms {

    static void breadthFirstSearch(SearchProblem problem, Node parentNode) {
            LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
            problem.queue.addAll(expandedNodes);
    }

    static void depthFirstSearch(SearchProblem problem, Node parentNode) {
        if(parentNode.getDepth() >= problem.cutOff && problem.strategy.equals(SearchProblem.SearchAlgorithms.ID)) return;
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        for (int i = expandedNodes.size() - 1; i >= 0; i--) {
            problem.queue.add(expandedNodes.get(i));
        }

    }

    static void uniformCostSearch(SearchProblem problem, Node parentNode) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        problem.queue.addAll(expandedNodes);
    }

    static void greedySearch(SearchProblem problem, Node parentNode) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        problem.queue.addAll(expandedNodes);
    }

    static void greedySearch2(SearchProblem problem, Node parentNode) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        problem.queue.addAll(expandedNodes);
    }

    static void aStarSearch(SearchProblem problem, Node parentNode) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        problem.queue.addAll(expandedNodes);
    }

    static void aStarSearch2(SearchProblem problem, Node parentNode) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        problem.queue.addAll(expandedNodes);
    }

    
}