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
            else if(deliveries.type.equals(RequestDelivery.deliveryType.FOOD)) {
                nodeState.food += deliveries.amount;
            }
            else if(deliveries.type.equals(RequestDelivery.deliveryType.FOOD)) {
                nodeState.food += deliveries.amount;
            }
            
        }
    }

}
