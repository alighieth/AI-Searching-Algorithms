package code;
import java.util.Stack;

public class Node {
    private NodeState state;
    private Node parent;
    private SearchProblem.Operators operator;
    private int depth;
    public RequestDelivery delivery;

    public Node(NodeState state, Node parent, SearchProblem.Operators operator, int depth, int heuristic1,
            int heuristic2, RequestDelivery delivery) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.delivery = delivery;
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

    public String getPathToGoal(boolean visualize) {
        Stack<Node> stack = new Stack<Node>();
        Node node = this;
        String result = "";
        while (node.getParent() != null) {
            stack.push(node);
            node = node.getParent();
        }
    	
        while (!stack.isEmpty()) {
        	Node topNode = stack.pop();
        	       	
        	if (visualize)
        	{
        		System.out.println("State: " + topNode.getState());
            	System.out.println("Money_Spent: " + topNode.getState().money_spent);
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
            //result += topNode.toString() + "\n";
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

    public String toString() {
    	return "Food " + state.food + " ,Energy " + state.energy + " ,Materials " + state.materials + " ,Depth " + depth + " ,Prosperity " + state.prosperity;
    }

    public String getStringRepresentation() {
        return String.format("%d-%d-%d-%d-%d-%s", state.food, state.energy, state.materials, state.prosperity, delivery == null ? 0 : delivery.delay, delivery == null ? "0" : delivery.type.name());
    } 

    public int getHeuristicFunction1() {
        return (int) (100 - this.state.prosperity) / Math.max(SearchProblem.prosperityBUILD1, SearchProblem.prosperityBUILD2);
    }

    public int getHeuristicFunction2() {
        int remainingBuild1 =  (int) (100 - this.state.prosperity) / SearchProblem.prosperityBUILD1;
        int remainingBuild2 =  (int) (100 - this.state.prosperity) / SearchProblem.prosperityBUILD2;
        int foodNeeded_1 = Math.max(remainingBuild1 * SearchProblem.foodUseBUILD1 - this.state.food, 0);
        int foodNeeded_2 = Math.max(remainingBuild2 * SearchProblem.foodUseBUILD2 - this.state.food, 0);
        return Math.min(remainingBuild1 + foodNeeded_1, remainingBuild2 + foodNeeded_2);
    }
}
