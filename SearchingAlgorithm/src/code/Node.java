package code;

import java.util.Stack;

public class Node {
    private Node parent;
    private Cell[][] grid;
    private SearchProblem.Operators operator;
    private int depth;
    private int cost;

    public Node(Cell[][] grid, Node parent, SearchProblem.Operators operator, int cost, int depth, int heuristic1,
            int heuristic2) {
        this.grid = grid;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.cost = cost;
    }

    public Cell[][] getState() {
        return grid;
    }

    public Node getParent() {
        return parent;
    }

    public SearchProblem.Operators getOperator() {
        return operator;
    }

    public int getDepth() {
        return depth;
    }

    public int getCost() {
        return cost;
    }

    public String getPathToGoal(boolean visualize) {
        Stack<Node> stack = new Stack<>();
        Node node = this;
        String result = "";
        int expandedNodes = 0; // Assuming you have a way to count expanded nodes

        while (node.getParent() != null) {
            stack.push(node);
            node = node.getParent();
        }

        while (!stack.isEmpty()) {
            Node topNode = stack.pop();

            switch (topNode.getOperator()) {
                case EAST:
                    result += "EAST";
                    break;
                case WEST:
                    result += "WEST";
                    break;
                case NORTH:
                    result += "NORTH";
                    break;
                case SOUTH:
                    result += "SOUTH";
                    break;
            }

            result += "_";
            // result += topNode.getState().getOrganisms().get(0).getX() + "_"
            // + topNode.getState().getOrganisms().get(0).getY();
            // result += ",";

            cost = topNode.getCost();

            expandedNodes++;

            if (visualize) {
                System.out.println("State: " + topNode.getState());
                SearchProblem.Operators op = topNode.getOperator();
                if (op != null) {
                    System.out.println("Operator Applied: " + op);
                    System.out.println("----------------------------------");
                }
            }
        }
        if (!result.isEmpty()) {
            result = result.substring(0, result.length() - 1);
        }
        String finalResult = result + ";" + cost + ";" + expandedNodes;
        return finalResult;
    }

    public int getHeuristicFunction1() {
        return 0;
    }

    public int getHeuristicFunction2() {

        return 0;
    }
}
