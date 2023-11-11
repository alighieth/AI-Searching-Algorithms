import java.util.LinkedList;

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
        WAIT,
        Build1,
        Build2,
        REQUEST_FOOD,
        REQUEST_ENERGY,
        REQUEST_MATERIALS
    }

    public int iterations = 0;
    public LinkedList<Node> queue;
    public RequestDelivery deliveries;

    private Node root;
    public Node currentNode;
    public int initialProsperity;
    public int initialFood, initialMaterials, initialEnergy;
    public int unitPriceFood, unitPriceMaterials, unitPriceEnergy;
    public int amountRequestFood, delayRequestFood;
    public int amountRequestMaterials, delayRequestMaterials;
    public int amountRequestEnergy, delayRequestEnergy;
    public int priceBUILD1, materialsUseBUILD1, energyUseBUILD1;
    public int priceBUILD2, materialsUseBUILD2, energyUseBUILD2;
    public static int prosperityBUILD1, prosperityBUILD2, foodUseBUILD1, foodUseBUILD2;
    public int nodesCounter = 0;
    public SearchAlgorithms strategy;

    public SearchProblem(String inputString, SearchAlgorithms strategy, boolean visualize) {
        this.queue = new LinkedList<>();
        this.deliveries = null;
        setAttributesFromString(inputString);
        if (visualize) {

            System.out.println();
            System.out.println("Initial Conditions:");
            System.out.println("-----------------------");
            System.out.println("initialProsperity: " + initialProsperity);
            System.out.println("initialFood: " + initialFood);
            System.out.println("initialMaterials: " + initialMaterials);
            System.out.println("initialEnergy: " + initialEnergy);

            System.out.println("unitPriceFood: " + unitPriceFood);
            System.out.println("unitPriceMaterials: " + unitPriceMaterials);
            System.out.println("unitPriceEnergy: " + unitPriceEnergy);

            System.out.println("amountRequestFood: " + amountRequestFood);
            System.out.println("delayRequestFood: " + delayRequestFood);

            System.out.println("amountRequestMaterials: " + amountRequestMaterials);
            System.out.println("delayRequestMaterials: " + delayRequestMaterials);

            System.out.println("amountRequestEnergy: " + amountRequestEnergy);
            System.out.println("delayRequestEnergy: " + delayRequestEnergy);

            System.out.println("priceBUILD1: " + priceBUILD1);
            System.out.println("foodUseBUILD1: " + foodUseBUILD1);
            System.out.println("materialsUseBUILD1: " + materialsUseBUILD1);
            System.out.println("energyUseBUILD1: " + energyUseBUILD1);
            System.out.println("prosperityBUILD1: " + prosperityBUILD1);

            System.out.println("priceBUILD2: " + priceBUILD2);
            System.out.println("foodUseBUILD2: " + foodUseBUILD2);
            System.out.println("materialsUseBUILD2: " + materialsUseBUILD2);
            System.out.println("energyUseBUILD2: " + energyUseBUILD2);
            System.out.println("prosperityBUILD2: " + prosperityBUILD2);
            System.out.println("-------------------------------------------");
        }
        NodeState initialState = new NodeState(
                initialProsperity,
                initialFood,
                initialMaterials,
                initialEnergy,
                0);
        this.root = new Node(initialState, null, null, 0, 0, 0, 0);
        this.queue.add(root);
        this.strategy = strategy;
    }

    public void setAttributesFromString(String inputString) {
        String[] parts = inputString.split(";");
        if (parts.length == 8) {
            this.initialProsperity = Integer.parseInt(parts[0]);

            String[] initalFoodMatEnrgy = parts[1].split(",");
            this.initialFood = Integer.parseInt(initalFoodMatEnrgy[0]);
            this.initialMaterials = Integer.parseInt(initalFoodMatEnrgy[1]);
            this.initialEnergy = Integer.parseInt(initalFoodMatEnrgy[2]);

            String[] initialUnitPrices = parts[2].split(",");
            this.unitPriceFood = Integer.parseInt(initialUnitPrices[0]);
            this.unitPriceMaterials = Integer.parseInt(initialUnitPrices[1]);
            this.unitPriceEnergy = Integer.parseInt(initialUnitPrices[2]);

            String[] initialRequestFood = parts[3].split(",");
            this.amountRequestFood = Integer.parseInt(initialRequestFood[0]);
            this.delayRequestFood = Integer.parseInt(initialRequestFood[1]);

            String[] initialRequestMaterial = parts[4].split(",");
            this.amountRequestMaterials = Integer.parseInt(initialRequestMaterial[0]);
            this.delayRequestMaterials = Integer.parseInt(initialRequestMaterial[1]);

            String[] initialRequestEnergy = parts[5].split(",");
            this.amountRequestEnergy = Integer.parseInt(initialRequestEnergy[0]);
            this.delayRequestEnergy = Integer.parseInt(initialRequestEnergy[1]);

            String[] initialBuild1 = parts[6].split(",");
            this.priceBUILD1 = Integer.parseInt(initialBuild1[0]);
            SearchProblem.foodUseBUILD1 = Integer.parseInt(initialBuild1[1]);
            this.materialsUseBUILD1 = Integer.parseInt(initialBuild1[2]);
            this.energyUseBUILD1 = Integer.parseInt(initialBuild1[3]);
            SearchProblem.prosperityBUILD1 = Integer.parseInt(initialBuild1[4]);

            String[] initialBuild2 = parts[7].split(",");
            this.priceBUILD2 = Integer.parseInt(initialBuild2[0]);
            SearchProblem.foodUseBUILD2 = Integer.parseInt(initialBuild2[1]);
            this.materialsUseBUILD2 = Integer.parseInt(initialBuild2[2]);
            this.energyUseBUILD2 = Integer.parseInt(initialBuild2[3]);
            SearchProblem.prosperityBUILD2 = Integer.parseInt(initialBuild2[4]);

        } else {
            System.err.println("Input string has an incorrect number of parts.");
        }
    }

    public LinkedList<Node> applyOperators(LinkedList<SearchProblem.Operators> pOperators, Node parentNode) {
        LinkedList<Node> operatorNodes = new LinkedList<>();

        for (SearchProblem.Operators operator : pOperators) {
            if (operator.equals(Operators.REQUEST_ENERGY)) {
                operatorNodes.add(requestDelivery(RequestDelivery.deliveryType.ENERGY, parentNode,
                        parentNode.getState().depth + 1));
            } else if (operator.equals(Operators.REQUEST_FOOD)) {
                operatorNodes.add(requestDelivery(RequestDelivery.deliveryType.FOOD, parentNode,
                        parentNode.getState().depth + 1));
            } else if (operator.equals(Operators.REQUEST_MATERIALS)) {
                operatorNodes.add(requestDelivery(RequestDelivery.deliveryType.MATERIALS, parentNode,
                        parentNode.getState().depth + 1));
            } else if (operator.equals(Operators.Build1)) {
                operatorNodes.add(build1(parentNode, parentNode.getState().depth + 1));
            } else if (operator.equals(Operators.Build2)) {
                operatorNodes.add(build2(parentNode, parentNode.getState().depth + 1));
            } else if (operator.equals(Operators.WAIT)) {
                operatorNodes.add(wait(parentNode, parentNode.getState().depth + 1));
            }
        }

        return operatorNodes;
    }

    public Node requestDelivery(RequestDelivery.deliveryType deliveryType, Node parentNode, int depth) {
        int deliveryDelay = this.delayRequestFood;
        int deliveryAmount = this.amountRequestFood;
        SearchProblem.Operators deliveryoOperator = SearchProblem.Operators.REQUEST_FOOD;
        if (deliveryType.equals(RequestDelivery.deliveryType.FOOD)) {
            deliveryDelay = this.delayRequestFood;
            deliveryAmount = this.amountRequestFood;
            deliveryoOperator = SearchProblem.Operators.REQUEST_FOOD;
        }
        if (deliveryType.equals(RequestDelivery.deliveryType.ENERGY)) {
            deliveryDelay = this.delayRequestEnergy;
            deliveryAmount = this.amountRequestEnergy;
            deliveryoOperator = SearchProblem.Operators.REQUEST_ENERGY;
        }
        if (deliveryType.equals(RequestDelivery.deliveryType.MATERIALS)) {
            deliveryDelay = this.delayRequestMaterials;
            deliveryAmount = this.amountRequestMaterials;
            deliveryoOperator = SearchProblem.Operators.REQUEST_MATERIALS;
        }
        RequestDelivery delivery = new RequestDelivery(deliveryDelay, deliveryAmount, deliveryType);

        NodeState newNodeState = parentNode.getState();
        newNodeState.energy--;
        newNodeState.food--;
        newNodeState.materials--;

        Node newNode = new Node(newNodeState, parentNode, deliveryoOperator, depth, 0, 0, 0);
        this.deliveries = delivery;

        return newNode;
    }

    public Node wait(Node parentNode, int depth) {
        NodeState newNodeState = parentNode.getState();
        newNodeState.energy--;
        newNodeState.food--;
        newNodeState.materials--;

        Node newNode = new Node(newNodeState, parentNode, SearchProblem.Operators.WAIT, depth, 0, 0, 0);
        handleDeliveries(newNode);
        return newNode;
    }

    public Node build1(Node parentNode, int depth) {
        NodeState parNodeState = parentNode.getState();
        NodeState newNodeState = new NodeState(parNodeState.prosperity, parNodeState.food, parNodeState.materials, parNodeState.energy, parNodeState.money_spent);
        newNodeState.energy -= this.energyUseBUILD1;
        newNodeState.food -= SearchProblem.foodUseBUILD1;
        newNodeState.materials -= this.materialsUseBUILD1;
        newNodeState.prosperity += SearchProblem.prosperityBUILD1;
        newNodeState.money_spent -= this.priceBUILD1;

        System.out.println("Old propsperity -> " + parentNode.getState().prosperity + " Prosperity after building 1 -> " + newNodeState.prosperity );

        Node newNode = new Node(newNodeState, parentNode, SearchProblem.Operators.Build1, depth, 0, 0, 0);
        return newNode;
    }

    public Node build2(Node parentNode, int depth) {
        NodeState parNodeState = parentNode.getState();
        NodeState newNodeState = new NodeState(parNodeState.prosperity, parNodeState.food, parNodeState.materials, parNodeState.energy, parNodeState.money_spent);
        newNodeState.energy -= this.energyUseBUILD2;
        newNodeState.food -= SearchProblem.foodUseBUILD2;
        newNodeState.materials -= this.materialsUseBUILD2;
        newNodeState.prosperity += SearchProblem.prosperityBUILD2;
        newNodeState.money_spent -= this.priceBUILD2;
        
        System.out.println("Old propsperity -> " + parentNode.getState().prosperity + " Prosperity after building 2 -> " + newNodeState.prosperity );

        Node newNode = new Node(newNodeState, parentNode, SearchProblem.Operators.Build2, depth, 0, 0, 0);
        return newNode;
    }

    public boolean isBuildOperatorValid(Node parent, Operators operator) {
        NodeState parentState = parent.getState();
        if (operator == Operators.Build1) {
            return parentState.food >= SearchProblem.foodUseBUILD1
                    && parentState.energy >= this.energyUseBUILD1 && parentState.materials >= this.materialsUseBUILD1;
        } else if (operator == Operators.Build2) {
            return parentState.food >= SearchProblem.foodUseBUILD2
                    && parentState.energy >= this.energyUseBUILD2 && parentState.materials >= this.materialsUseBUILD2;
        } else {
            return false;
        }

    }

    public void handleDeliveries(Node currNode) {
        if (deliveries == null) {
            return;
        }
        // A price of a resource is paid when the resource is used not when requested.
        deliveries.delay--;
        if (deliveries.delay == 0) {
            NodeState nodeState = currNode.getState();

            if (deliveries.type.equals(RequestDelivery.deliveryType.FOOD) && nodeState.food < 50) {
                nodeState.food += deliveries.amount;
                nodeState.food = nodeState.food >= 50 ? 50 : nodeState.food;
            } else if (deliveries.type.equals(RequestDelivery.deliveryType.ENERGY) && nodeState.energy < 50) {
                nodeState.energy += deliveries.amount;
                nodeState.energy = nodeState.energy >= 50 ? 50 : nodeState.energy;
            } else if (deliveries.type.equals(RequestDelivery.deliveryType.MATERIALS) && nodeState.materials < 50) {
                nodeState.materials += deliveries.amount;
                nodeState.materials = nodeState.materials >= 50 ? 50 : nodeState.materials;
            }

        }
        deliveries = null;
    }

    public Node getRoot() {
        return root;
    }

    public LinkedList<Node> expandNode(Node currNode) {
        LinkedList<Node> children = new LinkedList<>();

        System.out.println(".........................................");
        System.out.println("Node Operator in expansion " + currNode.getOperator());
        System.out.println("Initial Food " + currNode.getState().food);
        System.out.println("Initial Energy " + currNode.getState().energy);
        System.out.println("Initial Materials " + currNode.getState().materials);
        System.out.println(".........................................");

        if (currNode.getState().energy < 1 || currNode.getState().food < 1 || currNode.getState().materials < 1)
            return children;
        LinkedList<SearchProblem.Operators> possibleOperators = getPossibleOperators(currNode);
        children = applyOperators(possibleOperators, currNode);

        return children;
    }

    public LinkedList<SearchProblem.Operators> getPossibleOperators(Node currentNode) {
        LinkedList<SearchProblem.Operators> possibleOperators = new LinkedList<>();
        // During delay of any request -> Build1, Build2, Wait
        // waiting only allowed if a delivery is taking place

        possibleOperators.addLast(SearchProblem.Operators.Build1);
        possibleOperators.addLast(SearchProblem.Operators.Build2);

        if (this.deliveries == null) {
            possibleOperators.addLast(SearchProblem.Operators.WAIT);
        } else if (currentNode.getOperator() == null || this.deliveries != null) {
            possibleOperators.addLast(SearchProblem.Operators.REQUEST_FOOD);
            possibleOperators.addLast(SearchProblem.Operators.REQUEST_MATERIALS);
            possibleOperators.addLast(SearchProblem.Operators.REQUEST_ENERGY);
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

    public void printNodeExpansion(LinkedList<Node> nodeExp) {
        for (Node node : nodeExp) {
            System.out.println("Node operator " + node.getOperator());
        }
    }
}
