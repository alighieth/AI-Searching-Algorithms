package code;
import java.util.HashSet;
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

    public LinkedList<Node> queue;
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
    public SearchAlgorithms strategy;
    public boolean visualize;
    public int nodesExpandedCounter = 0;
    HashSet<String> visitedStates;

    public SearchProblem(String inputString, SearchAlgorithms strategy, boolean visualize) {
        this.queue = new LinkedList<>();
        this.visualize = visualize;
        setAttributesFromString(inputString);
        if (visualize) {
            printAttr();
        }
        NodeState initialState = new NodeState(
                initialProsperity,
                initialFood,
                initialMaterials,
                initialEnergy,
                0);
        this.root = new Node(initialState, null, null, 0, 0, 0, null);
        this.queue.add(root);
        this.strategy = strategy;
        this.visitedStates = new HashSet<>();
    }

    public void printAttr() {
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

        try {

        
        for (SearchProblem.Operators operator : pOperators) {
            if(parentNode.getState().money_spent + calculateActionCost(operator) <= 100000) {
                if (operator.equals(Operators.REQUEST_ENERGY)) {
                    Node newNode = requestDelivery(RequestDelivery.deliveryType.ENERGY, parentNode);
                    if(!visitedStates.contains(newNode.getStringRepresentation())) {
                        operatorNodes.add(newNode);
                        visitedStates.add(newNode.getStringRepresentation());
                    }
                } else if (operator.equals(Operators.REQUEST_FOOD)) {
                    Node newNode = requestDelivery(RequestDelivery.deliveryType.FOOD, parentNode);
                    if(!visitedStates.contains(newNode.getStringRepresentation())) {
                        operatorNodes.add(newNode);
                        visitedStates.add(newNode.getStringRepresentation());
                    }
                } else if (operator.equals(Operators.REQUEST_MATERIALS)) {
                    Node newNode = requestDelivery(RequestDelivery.deliveryType.MATERIALS, parentNode);
                    if(!visitedStates.contains(newNode.getStringRepresentation())) {
                        operatorNodes.add(newNode);
                        visitedStates.add(newNode.getStringRepresentation());
                    }
                } else if (operator.equals(Operators.Build1)) {
                    Node newNode = build1(parentNode);
                    if(!visitedStates.contains(newNode.getStringRepresentation())) {
                        operatorNodes.add(newNode);
                        visitedStates.add(newNode.getStringRepresentation());
                    }
                } else if (operator.equals(Operators.Build2)) {
                    Node newNode = build2(parentNode);
                    if(!visitedStates.contains(newNode.getStringRepresentation())) {
                        operatorNodes.add(newNode);
                        visitedStates.add(newNode.getStringRepresentation());
                    }
                } else if (operator.equals(Operators.WAIT)) {
                    Node newNode = wait(parentNode);
                    if(!visitedStates.contains(newNode.getStringRepresentation())) {
                        operatorNodes.add(newNode);
                        visitedStates.add(newNode.getStringRepresentation());
                    }
                }
            }
            
        }

        return operatorNodes;

        } catch (Exception e) {

            return operatorNodes;
        }
    }

    public Node requestDelivery(RequestDelivery.deliveryType deliveryType, Node parentNode) {
        int deliveryDelay = this.delayRequestFood;
        int deliveryAmount = this.amountRequestFood;
        SearchProblem.Operators deliveryoOperator = SearchProblem.Operators.REQUEST_FOOD;
        if (deliveryType.equals(RequestDelivery.deliveryType.FOOD)) {
            deliveryDelay = this.delayRequestFood;
            deliveryAmount = this.amountRequestFood;
            deliveryoOperator = SearchProblem.Operators.REQUEST_FOOD;
        }
        else if (deliveryType.equals(RequestDelivery.deliveryType.ENERGY)) {
            deliveryDelay = this.delayRequestEnergy;
            deliveryAmount = this.amountRequestEnergy;
            deliveryoOperator = SearchProblem.Operators.REQUEST_ENERGY;
        }
        else if (deliveryType.equals(RequestDelivery.deliveryType.MATERIALS)) {
            deliveryDelay = this.delayRequestMaterials;
            deliveryAmount = this.amountRequestMaterials;
            deliveryoOperator = SearchProblem.Operators.REQUEST_MATERIALS;
        }
        RequestDelivery delivery = new RequestDelivery(deliveryDelay, deliveryAmount, deliveryType);

        NodeState parentNodeState = parentNode.getState();
        NodeState newNodeState = new NodeState(parentNodeState.prosperity, parentNodeState.food, parentNodeState.materials, parentNodeState.energy, parentNodeState.money_spent);
        newNodeState.energy = Math.max(parentNode.getState().energy-1, 0);
        newNodeState.food = Math.max(parentNode.getState().food-1, 0);
        newNodeState.materials = Math.max(parentNode.getState().materials-1, 0);
        newNodeState.money_spent = parentNode.getState().money_spent + calculateActionCost(deliveryoOperator);

        int depth = parentNode.getDepth() + 1;
        Node newNode = new Node(newNodeState, parentNode, deliveryoOperator, depth, 0, 0, delivery);
        newNode.delivery = delivery;
        return newNode;
    }

    public Node build1(Node parentNode) {
        NodeState parNodeState = parentNode.getState();
        NodeState newNodeState = new NodeState(parNodeState.prosperity, parNodeState.food, parNodeState.materials, parNodeState.energy, parNodeState.money_spent);
        newNodeState.energy = Math.max(parNodeState.energy - this.energyUseBUILD1, 0) ;
        newNodeState.food = Math.max(parNodeState.food - SearchProblem.foodUseBUILD1, 0) ; ;
        newNodeState.materials = Math.max(parNodeState.materials - this.materialsUseBUILD1, 0) ; ;
        newNodeState.prosperity += SearchProblem.prosperityBUILD1;
        newNodeState.money_spent = parentNode.getState().money_spent + calculateActionCost(Operators.Build1);

        int depth = parentNode.getDepth() + 1;

        Node newNode = new Node(newNodeState, parentNode, SearchProblem.Operators.Build1, depth, 0, 0, parentNode.delivery);
        return newNode;
    }

    public Node build2(Node parentNode) {
        NodeState parNodeState = parentNode.getState();
        NodeState newNodeState = new NodeState(parNodeState.prosperity, parNodeState.food, parNodeState.materials, parNodeState.energy, parNodeState.money_spent);
        newNodeState.energy = Math.max(parNodeState.energy - this.energyUseBUILD2, 0) ;
        newNodeState.food = Math.max(parNodeState.food - SearchProblem.foodUseBUILD2, 0) ; ;
        newNodeState.materials = Math.max(parNodeState.materials - this.materialsUseBUILD2, 0) ; ;
        newNodeState.prosperity = parNodeState.prosperity + SearchProblem.prosperityBUILD2;
        newNodeState.money_spent = parNodeState.money_spent + calculateActionCost(Operators.Build2);
        
        int depth = parentNode.getDepth() + 1;
        Node newNode = new Node(newNodeState, parentNode, SearchProblem.Operators.Build2, depth, 0, 0, parentNode.delivery);
        return newNode;
    }

    public boolean isOperatorValid(Node parent, Operators operator) {
        NodeState parentState = parent.getState();
        if (operator.equals(Operators.Build1)) {
            return parentState.food >= SearchProblem.foodUseBUILD1
                    && parentState.energy >= this.energyUseBUILD1 && parentState.materials >= this.materialsUseBUILD1;
        } else if (operator.equals(Operators.Build2)) {
            return parentState.food >= SearchProblem.foodUseBUILD2
                    && parentState.energy >= this.energyUseBUILD2 && parentState.materials >= this.materialsUseBUILD2;
        } 
        else if(operator.equals(Operators.REQUEST_FOOD) || operator.equals(Operators.REQUEST_ENERGY) 
        || operator.equals(Operators.REQUEST_MATERIALS) || operator.equals(Operators.WAIT)){
            return parentState.food >= 1
                    && parentState.energy >= 1 && parentState.materials >= 1;
        }
        
        return false;
    }

    public Node wait(Node parentNode) {
        NodeState parNodeState = parentNode.getState();
        NodeState newNodeState = new NodeState(parNodeState.prosperity, parNodeState.food, parNodeState.materials, parNodeState.energy, parNodeState.money_spent);
        newNodeState.energy--;
        newNodeState.food--;
        newNodeState.materials--;
        newNodeState.money_spent = parNodeState.money_spent + calculateActionCost(Operators.WAIT);
        
        int depth = parentNode.getDepth() + 1;
        Node nodeWithDelivery = new Node(newNodeState, parentNode, SearchProblem.Operators.WAIT, depth, 0, 0, parentNode.delivery);

        return handleDeliveries(nodeWithDelivery);
    }

    public Node handleDeliveries(Node nodeWithDelivery) {
        if (nodeWithDelivery.delivery == null || nodeWithDelivery.delivery.delay == 0) {
           return nodeWithDelivery;
        }
        // A price of a resource is paid when the resource is used not when requested.
        nodeWithDelivery.delivery.delay--;
        if (nodeWithDelivery.delivery.delay == 0) {
            NodeState nodeState = nodeWithDelivery.getState();
            if (nodeWithDelivery.delivery.type.equals(RequestDelivery.deliveryType.FOOD) ) {
                nodeState.food = Math.min(nodeState.food + nodeWithDelivery.delivery.amount, 50); 
            } else if (nodeWithDelivery.delivery.type.equals(RequestDelivery.deliveryType.ENERGY)  ) {
                nodeState.energy = Math.min(nodeState.energy + nodeWithDelivery.delivery.amount, 50);
            } else if (nodeWithDelivery.delivery.type.equals(RequestDelivery.deliveryType.MATERIALS))  {
                nodeState.materials = Math.min(nodeState.materials + nodeWithDelivery.delivery.amount, 50); 
            }
            nodeWithDelivery.delivery = null;
        }
        return nodeWithDelivery;
    }

    public Node getRoot() {
        return root;
    }

    public int calculateActionCost(Operators action) {
	    switch (action) {
	      case REQUEST_FOOD:  return unitPriceEnergy + unitPriceFood + unitPriceMaterials;
	      case REQUEST_MATERIALS:  return unitPriceEnergy + unitPriceFood + unitPriceMaterials;
	      case REQUEST_ENERGY:  return unitPriceEnergy + unitPriceFood + unitPriceMaterials;
	      case WAIT:
	        return unitPriceEnergy + unitPriceFood + unitPriceMaterials;
	      case Build1:
	        return (
	          priceBUILD1 +
	          foodUseBUILD1 *
	          unitPriceFood +
	          energyUseBUILD1 *
	          unitPriceEnergy +
	          materialsUseBUILD1 *
	          unitPriceMaterials
	        );
        case Build2:
	         return (
	          priceBUILD2 +
	          foodUseBUILD2 *
	          unitPriceFood +
	          energyUseBUILD2 *
	          unitPriceEnergy +
	          materialsUseBUILD2 *
	          unitPriceMaterials
	        );
	      default: return 0;
	       
	    }
	  }


    public LinkedList<Node> expandNode(Node currNode) {
        LinkedList<Node> children = new LinkedList<>();

        if (currNode.getState().energy < 1 || currNode.getState().food < 1 || currNode.getState().materials < 1)
            return children;
        this.nodesExpandedCounter++;
        LinkedList<SearchProblem.Operators> possibleOperators = getPossibleOperators(currNode);
        children = applyOperators(possibleOperators, currNode);

        return children;
    }

    public LinkedList<SearchProblem.Operators> getPossibleOperators(Node currentNode) {
        LinkedList<SearchProblem.Operators> possibleOperators = new LinkedList<>();
        // During delay of any request -> Build1, Build2, Wait
        // waiting only allowed if a delivery is taking place

        if(isOperatorValid(currentNode, Operators.Build1)) {
            possibleOperators.addLast(SearchProblem.Operators.Build1);
        }

        if(isOperatorValid(currentNode, Operators.Build2)) {
            possibleOperators.addLast(SearchProblem.Operators.Build2);
        }

        if ((currentNode.delivery != null && currentNode.delivery.delay > 0) && currentNode.getOperator() != null) {
            if(isOperatorValid(currentNode, Operators.WAIT)) {
                possibleOperators.addLast(SearchProblem.Operators.WAIT);
            }
        } else if (currentNode.getOperator() == null || currentNode.delivery == null) {
            
            if(isOperatorValid(currentNode, Operators.REQUEST_FOOD)) {
                possibleOperators.addLast(SearchProblem.Operators.REQUEST_FOOD);
            }
            if(isOperatorValid(currentNode, Operators.REQUEST_MATERIALS)) {
                possibleOperators.addLast(SearchProblem.Operators.REQUEST_MATERIALS);
            }
            if(isOperatorValid(currentNode, Operators.REQUEST_ENERGY)) {
                possibleOperators.addLast(SearchProblem.Operators.REQUEST_ENERGY);
            }
            
            
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
