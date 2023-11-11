public class Node {
    private NodeState state;
    private Node parent;
    private SearchProblem.Operators operator;
    private int depth;
    private int pathCost;
    private int heuristic1;
    private int heuristic2;

    public Node(NodeState state, Node parent, SearchProblem.Operators operator, int depth, int pathCost, int heuristic1,
            int heuristic2) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.pathCost = pathCost;
        this.heuristic1 = heuristic1;
        this.heuristic2 = heuristic2;
    }

    public NodeState getState() {
        return state;
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

    public int getPathCost() {
        return pathCost;
    }

    public int getHeuristic1() {
        return heuristic1;
    }

    public int getHeuristic2() {
        return heuristic2;
    }

}
