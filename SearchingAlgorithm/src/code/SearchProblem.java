package code;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

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
        EAST, WEST, NORTH, SOUTH
    }

    public PriorityQueue<Node> queue;
    private Node root;
    public Node currentNode;
    public SearchAlgorithms strategy;
    public boolean visualize;
    public int nodesExpandedCounter = 0;
    public int cutOff;
    HashSet<String> visitedStates;

    public SearchProblem(String input, SearchAlgorithms strategy, boolean visualize) {
        this.visualize = visualize;
        if (visualize) {
            printAttr();
        }
        Cell[][] grid = Grid.genGrid(input);
        this.root = new Node(grid, null, null, 0, 0, 0, 0);
        this.strategy = strategy;
        this.queue.add(root);
        this.visitedStates = new HashSet<>();
        this.cutOff = -1;
    }

    public void printAttr() {
        System.out.println();
        System.out.println("Initial Conditions:");
        System.out.println("-----------------------");

        System.out.println("-----------------------");
    }

    public Node getRoot() {
        return root;
    }

    public int calculateActionCost(Organism organism, int numberOfCells) {
        return organism.getSize() * numberOfCells;
    }

    public LinkedList<Node> expandNode(Node currNode) {
        List<Organism> nodeOrganisms = (List<Organism>) Grid.getOrganisms(currNode.getState());
        LinkedList<Node> children = new LinkedList<>();

        for (Cell organism : nodeOrganisms) {
            LinkedList<SearchProblem.Operators> possibleOperators = getPossibleOperators(currNode, organism);
            for (Operators operator : possibleOperators) {
                Organism o = moveOrganism(currNode, operator, organism);
                Grid newGrid = new Grid(currNode.getState().getWidth(), currNode.getState().getHeight(), nodeOrganisms,
                        currNode.getState().getObstacles());
                newGrid.removeOrganism(o.getX(), o.getY());
                newGrid.addOrganism(o);
                Node child = new Node(currNode.getState(), currNode.getParent(), operator, currNode.getCost() + 1,
                        currentNode.getDepth() + 1, 0, 0);
                children.add(child);
            }
        }
        this.nodesExpandedCounter++;
        return children;
    }

    public Organism moveOrganism(Node currentNode, Operators operator, Organism organism) {
        // move until you hit an obstacle or an organism
        int x = organism.getX();
        int y = organism.getY();

        if (operator == Operators.EAST) {
            while (x < currentNode.getState().length && currentNode.getState()[x][y] == null) {
                x++;
            }
            if (x + 1 < currentNode.getState().length && currentNode.getState()[x][y] instanceof Organism) {
                organism = merge(organism, currentNode.getState()[x + 1][y]);
                x++;
            }
            x--;
        } else if (operator == Operators.WEST) {
            while (x >= 0 && currentNode.getState()[x][y] == null) {
                x--;
            }
            if (x + 1 < currentNode.getState().length && currentNode.getState()[x][y] instanceof Organism) {
                organism = merge(organism, currentNode.getState()[x - 1][y]);
                x++;
            }
            x++;
        } else if (operator == Operators.NORTH) {
            while (y < currentNode.getState()[0].length && currentNode.getState()[x][y] == null) {
                y++;
            }
            if (x + 1 < currentNode.getState().length && currentNode.getState()[x][y] instanceof Organism) {
                organism = merge(organism, currentNode.getState()[x][y - 1]);
                x++;
            }
            y--;
        } else if (operator == Operators.SOUTH) {
            while (y >= 0 && currentNode.getState()[x][y] == null) {
                y--;
            }
            if (x + 1 < currentNode.getState().length && currentNode.getState()[x][y] instanceof Organism) {
                organism = merge(organism, currentNode.getState()[x][y + 1]);
                x++;
            }
            y++;
        }
        return new Organism(x, y);
    }

    public Organism merge(Organism organism, Cell cell) {
        Organism o = new Organism(cell.getX(), cell.getY());
        o.setSize();
        return o;
    }

    public LinkedList<SearchProblem.Operators> getPossibleOperators(Node currentNode, Organism organism) {
        LinkedList<SearchProblem.Operators> possibleOperators = new LinkedList<>();

        if (isOperatorValid(currentNode, Operators.EAST, organism)) {
            possibleOperators.addLast(SearchProblem.Operators.EAST);
        }

        if (isOperatorValid(currentNode, Operators.NORTH, organism)) {
            possibleOperators.addLast(SearchProblem.Operators.NORTH);
        }

        if (isOperatorValid(currentNode, Operators.WEST, organism)) {
            possibleOperators.addLast(SearchProblem.Operators.WEST);
        }
        if (isOperatorValid(currentNode, Operators.SOUTH, organism)) {
            possibleOperators.addLast(SearchProblem.Operators.SOUTH);
        }

        return possibleOperators;
    }

    public boolean isOperatorValid(Node parent, Operators operator, Organism organism) {
        int size = 0;
        int i = 0;
        if (operator == Operators.EAST) {
            size = parent.getState().length;

            for (i = organism.getX(); i < size; i++) {
                if (organism.getX() + 1 == i && parent.getState()[i][organism.getY()] instanceof Obstacles)
                    return false;

            }
            if (i == size)
                return false;
            else if (operator == Operators.WEST) {
                size = parent.getState().length;
                for (i = organism.getX(); i >= 0; i--) {
                    if (organism.getX() + 1 == i && parent.getState()[i][organism.getY()] instanceof Obstacles)
                        return false;
                }
                if (i == -1)
                    return false;
            } else if (operator == Operators.NORTH) {
                size = parent.getState()[0].length;
                for (i = organism.getY(); i >= 0; i--) {
                    if (organism.getY() + 1 == i && parent.getState()[organism.getX()][i] instanceof Obstacles)
                        return false;
                }
                if (i == -1)
                    return false;
            }
        } else if (operator == Operators.SOUTH) {
            size = parent.getState()[0].length;
            for (i = organism.getY(); i < size; i++) {
                if (organism.getY() + 1 == i && parent.getState()[organism.getX()][i] instanceof Obstacles)
                    return false;

            }
            if (i == size)
                return false;
        }
        return true;
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

    public static SearchProblem.SearchAlgorithms getSearchAlgorithms(String strategy) {
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
