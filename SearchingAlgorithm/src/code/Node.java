package code;
import java.util.Stack;

public class Node {
    private NodeState state;
    private Node parent;
    private SearchProblem.Operators operator;
    private int depth;
    public RequestDelivery delivery;
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
        this.delivery = null;
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

    public String getPathToGoal(boolean visualize) {
        Stack<Node> stack = new Stack<Node>();
        Node node = this;
        String result = "";
        while (node.getParent() != null) {
            stack.push(node);
            node = node.getParent();
        }
        
        if (visualize) {
	    	System.out.println("State: " + node.getState());
	    	System.out.println("Money_Spent: " + node.getPathCost());
	    	System.out.println("----------------------------------");
        }
    	
        while (!stack.isEmpty()) {
        	Node topNode = stack.pop();
        	       	
        	if (visualize)
        	{
        		System.out.println("State: " + topNode.getState());
            	System.out.println("Money_Spent: " + topNode.getPathCost());
        	}
        	
            switch(topNode.getOperator()) {
                case REQUEST_FOOD:
                    result += "RequestFood,";
                    break;
                case REQUEST_MATERIALS:
                    result += "RequestMaterials,";
                    break;
                case REQUEST_ENERGY:
                    result += "RequestEnergy,";
                    break;
                case WAIT:
                    result += "WAIT,";
                    break;
                case Build1:
                    result += "BUILD1,";
                    break;
                case Build2:
                    result += "BUILD2,";
                    break;
            }
            
            if (visualize)
            {
                SearchProblem.Operators op = topNode.getOperator();
            	if ( op !=null)
            	{
            		System.out.println("Operator_Applied: " + op);
                	System.out.println("----------------------------------");
            	}
            }
            
            
        }
        return result.substring(0, result.length() - 1);
    }

}
