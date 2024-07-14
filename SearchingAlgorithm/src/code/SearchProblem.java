package code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class SearchProblem {
    public enum SearchAlgorithms {
        BF,
        DF,
        ID,
        UC,
        GR1,
        GR2,
        AS1,
        AS2
    }

    public enum Operators {
        EAST, WEST, NORTH, SOUTH
    }

    public PriorityQueue<Node> queue;
    private Node root;
    public Node currentNode;
    public SearchAlgorithms strategy;
    public boolean visualize;
    public int nodesExpandedCounter = 0;
    public int cutOff;
    HashSet<String> visitedStates;

    public SearchProblem(Grid grid, SearchAlgorithms strategy, boolean visualize) {
        this.visualize = visualize;
        if (visualize) {
            printAttr();
        }

        this.root = new Node(grid, null, null, 0, 0, 0, 0);
        this.strategy = strategy;
        this.queue.add(root);
        this.visitedStates = new HashSet<>();
        this.cutOff = -1;
    }

    public void printAttr() {
        System.out.println();
        System.out.println("Initial Conditions:");
        System.out.println("-----------------------");

        System.out.println("-------------------------------------------");
    }

    public LinkedList<Node> applyOperators(LinkedList<SearchProblem.Operators> pOperators, Node parentNode) {
        LinkedList<Node> operatorNodes = new LinkedList<>();

        try {
            ArrayList<Organism> newOrganisms = new ArrayList<>();
            for (Organism organism : parentNode.getState().getOrganisms()) {
                Organism o = null;
                for (Operators operator : pOperators) {
                    if (operator == Operators.EAST) {
                        o = new Organism(organism.getX() + 1, organism.getY());

                    }
                    if (operator == Operators.WEST) {
                        o = new Organism(organism.getX() - 1, organism.getY());

                    }
                    if (operator == Operators.NORTH) {
                        o = new Organism(organism.getX(), organism.getY() + 1);

                    }
                    if (operator == Operators.SOUTH) {
                        o = new Organism(organism.getX(), organism.getY() - 1);

                    }
                    newOrganisms.add(o);

                }
                Grid g = new Grid(parentNode.getState().getWidth(), parentNode.getState().getHeight(),
                        newOrganisms, parentNode.getState().getObstacles());
                Node n = new Node(g, parentNode, parentNode.getOperator(), parentNode.getCost(), parentNode.getDepth(),
                        parentNode.getHeuristicFunction1(), parentNode.getHeuristicFunction2());
                operatorNodes.add(n);
            }

            return operatorNodes;

        } catch (Exception e) {

            return operatorNodes;
        }
    }

    public boolean isOperatorValid(Node parent, Operators operator) {
        // parent.getOperator()
        return true;
    }

    public Node getRoot() {
        return root;
    }

    public int calculateActionCost(Operators action) {
        return 0;
    }

    public LinkedList<Node> expandNode(Node currNode) {
        LinkedList<Node> children = new LinkedList<>();

        this.nodesExpandedCounter++;
        LinkedList<SearchProblem.Operators> possibleOperators = getPossibleOperators(currNode);
        children = applyOperators(possibleOperators, currNode);

        return children;
    }

    public LinkedList<SearchProblem.Operators> getPossibleOperators(Node currentNode) {
        LinkedList<SearchProblem.Operators> possibleOperators = new LinkedList<>();

        if (isOperatorValid(currentNode, Operators.EAST)) {
            possibleOperators.addLast(SearchProblem.Operators.EAST);
        }

        if (isOperatorValid(currentNode, Operators.NORTH)) {
            possibleOperators.addLast(SearchProblem.Operators.NORTH);
        }

        if (isOperatorValid(currentNode, Operators.WEST)) {
            possibleOperators.addLast(SearchProblem.Operators.WEST);
        }
        if (isOperatorValid(currentNode, Operators.SOUTH)) {
            possibleOperators.addLast(SearchProblem.Operators.SOUTH);
        }

        return possibleOperators;
    }

    public static LinkedList<Node> getPathToGoal(Node inputNode, boolean visualize) {
        LinkedList<Node> pathToGoal = new LinkedList<>();

        Node currentNode = inputNode;
        pathToGoal.addFirst(currentNode);
        while (currentNode != null) {
            pathToGoal.addFirst(currentNode.getParent());
            currentNode = currentNode.getParent();
        }

        return pathToGoal;
    }

    public static SearchProblem.SearchAlgorithms getSearchAlgo(String strategy) {
        SearchProblem.SearchAlgorithms strategyAlgo = SearchProblem.SearchAlgorithms.AS1;

        if (SearchProblem.SearchAlgorithms.BF.name().equals(strategy)) {
            strategyAlgo = SearchProblem.SearchAlgorithms.BF;
        } else if (SearchProblem.SearchAlgorithms.DF.name().equals(strategy)) {
            strategyAlgo = SearchProblem.SearchAlgorithms.DF;
        }

        else if (SearchProblem.SearchAlgorithms.ID.name().equals(strategy)) {
            strategyAlgo = SearchProblem.SearchAlgorithms.ID;
        } else if (SearchProblem.SearchAlgorithms.UC.name().equals(strategy)) {
            strategyAlgo = SearchProblem.SearchAlgorithms.UC;
        }

        else if (SearchProblem.SearchAlgorithms.GR1.name().equals(strategy)) {
            strategyAlgo = SearchProblem.SearchAlgorithms.GR1;
        }

        else if (SearchProblem.SearchAlgorithms.GR2.name().equals(strategy)) {
            strategyAlgo = SearchProblem.SearchAlgorithms.GR2;
        }

        else if (SearchProblem.SearchAlgorithms.AS1.name().equals(strategy)) {
            strategyAlgo = SearchProblem.SearchAlgorithms.AS1;
        }

        else if (SearchProblem.SearchAlgorithms.AS2.name().equals(strategy)) {
            strategyAlgo = SearchProblem.SearchAlgorithms.AS2;
        }

        return strategyAlgo;
    }

}
