public class Node {
    private NodeState state;
    private Node parent;
    private String operator;
    private int depth;
    private double pathCost;

    public Node(NodeState state, Node parent, String operator, int depth, double pathCost) {
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

    public String getOperator() {
        return operator;
    }

    public int getDepth() {
        return depth;
    }

    public double getPathCost() {
        return pathCost;
    }
}
