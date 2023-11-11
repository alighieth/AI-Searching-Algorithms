public class Node {
    private NodeState state;
    private Node parent;
    private SearchProblem.Operators operator;
    private int depth;
    private double pathCost;

    public Node(NodeState state, Node parent, SearchProblem.Operators operator, int depth, double pathCost) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.pathCost = pathCost;
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

    public double getPathCost() {
        return pathCost;
    }
}
