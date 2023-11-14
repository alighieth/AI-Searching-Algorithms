package code;

import java.util.Comparator;
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
        if(parentNode.getDepth() >= problem.cutOff && problem.strategy.equals(SearchProblem.SearchAlgorithms.ID)) return;
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

    static void greedySearch(SearchProblem problem, Node parentNode, int heuristic) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        problem.queue.addAll(expandedNodes);
        problem.queue.sort((node1, node2) -> Integer.compare(node1.getHeuristicFunction1(), node2.getHeuristicFunction1()));
    }

    static void greedySearch2(SearchProblem problem, Node parentNode, int heuristic) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        problem.queue.addAll(expandedNodes);
        problem.queue.sort((node1, node2) -> Integer.compare(node1.getHeuristicFunction2(), node2.getHeuristicFunction2()));
    }

    static void aStarSearch(SearchProblem problem, Node parentNode, int heuristic) {
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        problem.queue.addAll(expandedNodes);
        expandedNodes.sort((node1, node2) -> Integer.compare(node1.getHeuristicFunction1() + node1.getState().money_spent + problem.calculateActionCost(node1.getOperator()), 
                node2.getHeuristicFunction1() + node2.getState().money_spent + problem.calculateActionCost(node2.getOperator())));
    }

    static void aStarSearch2(SearchProblem problem, Node parentNode, int heuristic) {
        //System.out.println("Expanded node " + parentNode.getOperator());
        LinkedList<Node> expandedNodes = problem.expandNode(parentNode);
        problem.queue.addAll(expandedNodes);
        problem.queue.sort((node1, node2) -> Integer.compare(node1.getHeuristicFunction2() + node1.getState().money_spent, 
            node2.getHeuristicFunction2() + node2.getState().money_spent));
    }

    public static Comparator<Node> getComparatorBasedOnStrategy(SearchProblem.SearchAlgorithms strategy){

        if(strategy.equals(SearchProblem.SearchAlgorithms.GR1)) {
            return new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.getHeuristicFunction1() - o2.getHeuristicFunction1();
                }
            };
        }
        else if(strategy.equals(SearchProblem.SearchAlgorithms.GR2)) {
            return new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.getHeuristicFunction2() - o2.getHeuristicFunction2();
                }
            };
        } 
        else if(strategy.equals(SearchProblem.SearchAlgorithms.AS1)) {
            return new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.getHeuristicFunction1() - o2.getHeuristicFunction1();
                }
            };
        }
        else if(strategy.equals(SearchProblem.SearchAlgorithms.AS2)) {
            return new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.getHeuristicFunction2() - o2.getHeuristicFunction2();
                }
            };
        }

        return new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return 1;
            }
        };
    }
}