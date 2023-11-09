import java.util.LinkedList;

public class Game {
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
        WAIT,
        Build1,
        Build2,
        REQUEST_FOOD,
        REQUEST_ENERGY,
        REQUEST_MATERIALS
    }

    public static int iterations = 0;
    public static LinkedList<Node> queue = new LinkedList<>();    
    public static RequestDelivery deliveries = null;

    public Game() {

    }

    public static Node requestDelivery(RequestDelivery.deliveryType deliveryType, Node parentNode) {
        int deliveryDelay = ProblemTree.delayRequestFood;
        int deliveryAmount = ProblemTree.amountRequestFood; 
        Game.Operators deliveryoOperator = Game.Operators.REQUEST_FOOD; 
        if(deliveryType.equals(RequestDelivery.deliveryType.FOOD)) {
            deliveryDelay = ProblemTree.delayRequestFood;
            deliveryAmount = ProblemTree.amountRequestFood;
            deliveryoOperator = Game.Operators.REQUEST_FOOD; 
        }
        if(deliveryType.equals(RequestDelivery.deliveryType.ENERGY)) {
            deliveryDelay = ProblemTree.delayRequestEnergy;
            deliveryAmount = ProblemTree.amountRequestEnergy;
            deliveryoOperator = Game.Operators.REQUEST_ENERGY; 
        }
        if(deliveryType.equals(RequestDelivery.deliveryType.MATERIALS)) {
            deliveryDelay = ProblemTree.delayRequestMaterials;
            deliveryAmount = ProblemTree.amountRequestMaterials;
            deliveryoOperator = Game.Operators.REQUEST_MATERIALS; 
        }
        RequestDelivery delivery = new RequestDelivery(deliveryDelay, deliveryAmount, deliveryType);

        NodeState newNodeState = parentNode.getState();
        newNodeState.energy--;        
        newNodeState.food--;
        newNodeState.materials--;


        Node newNode = new Node(newNodeState, parentNode, deliveryoOperator, Game.iterations, 0);
        Game.deliveries = delivery;

        return newNode;
    }

    public static void handleDeliveries() {
        if (Game.deliveries == null) {
            return;
        }

        deliveries.delay--;
        if(deliveries.delay == 0) {
            NodeState nodeState = ProblemTree.currentNode.getState();

            if(deliveries.type.equals(RequestDelivery.deliveryType.FOOD)) {
                nodeState.food += deliveries.amount;
            }
            else if(deliveries.type.equals(RequestDelivery.deliveryType.ENERGY)) {
                nodeState.energy += deliveries.amount;
            }
            else if(deliveries.type.equals(RequestDelivery.deliveryType.MATERIALS)) {
                nodeState.materials += deliveries.amount;
            }
            
        }
    }

}
