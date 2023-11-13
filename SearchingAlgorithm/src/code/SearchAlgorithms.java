package code;

import java.util.LinkedList;

public class SearchAlgorithms {

    static void breadthFirstSearch(SearchProblem problem, Node parentNode) {
        try {
            LinkedList<Node> expandedNodes = problem.expandNode(parentNode);

            for (Node topNode : expandedNodes) {
                problem.queue.addLast(topNode);
            }
        } catch (Exception e) {
            System.err.println("Error in BF algorithm " + e.getMessage());
        }
    }

    static void depthFirstSearch(SearchProblem problem, Node parentNode) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        for (int i = expandedNodes.size() - 1; i >= 0; i--) {
            problem.queue.addFirst(expandedNodes.get(i));
        }

    }

    static void uniformCostSearch(SearchProblem problem, Node parentNode) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        for (Node topNode : expandedNodes) {
            problem.queue.add(topNode);
        }
    }

    // static void iterativeDeepeningSearch(SearchProblem problem, Node parentNode)
    // {
    // for (int depth = 0; depth < 2000; depth++) {
    // LinkedList<Node> expandedNodes = problem.expandNode(parentNode);

    // for (Node child : expandedNodes) {
    // problem.queue.addFirst(child);

    // }
    // }
    // }
    static void iterativeDeepeningSearch(SearchProblem problem, Node parentNode) {
        int defaultMaxDepth = 50;
        DeepeningSearch(problem, parentNode, defaultMaxDepth);
    }

    static void DeepeningSearch(SearchProblem problem, Node parentNode, int maxDepth) {
        for (int depth = 0; depth <= maxDepth; depth++) {
            recursiveDLS(problem, parentNode, depth);
        }
    }

    static void recursiveDLS(SearchProblem problem, Node node, int depth) {
        if (depth == 0) {
            return;
        }

        LinkedList<Node> expandedNodes = problem.expandNode(node);

        for (Node child : expandedNodes) {
            problem.queue.addFirst(child);
            recursiveDLS(problem, child, depth - 1);
        }
    }

    static void greedySearch(SearchProblem problem, Node parentNode, int heuristic) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        expandedNodes.sort((node1, node2) -> Integer.compare(heuristic(node1,
                heuristic), heuristic(node2, heuristic)));
        problem.queue.addAll(expandedNodes);
    }

    static void greedySearch2(SearchProblem problem, Node parentNode, int heuristic) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        expandedNodes.sort((node1, node2) -> Integer.compare(heuristic(node1,
                heuristic), heuristic(node2, heuristic)));
        problem.queue.addAll(expandedNodes);
    }

    static void aStarSearch(SearchProblem problem, Node parentNode, int heuristic) {
        // LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        // expandedNodes.sort((node1, node2) -> Integer.compare(heuristic(node1,
        // heuristic) + node1.getpa(),
        // heuristic(node2, heuristic) + node2.getCost()));
        // problem.queue.addAll(expandedNodes);
    }

    static void aStarSearch2(SearchProblem problem, Node parentNode, int heuristic) {
        // LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        // expandedNodes.sort((node1, node2) -> Integer.compare(heuristic(node1,
        // heuristic) + node1.getpa(),
        // heuristic(node2, heuristic) + node2.getCost()));
        // problem.queue.addAll(expandedNodes);
    }

    static int heuristic(Node node, int heuristic) {

        return 0;
    }
}