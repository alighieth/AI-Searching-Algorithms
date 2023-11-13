package code;

public class LLAPSearch extends GenericSearch {
    public static String solve(String initalState, String strategy, boolean visualize) {
        GenericSearch gs = new GenericSearch();
        SearchProblem.SearchAlgorithms searchAlgorithm = SearchProblem.getSearchAlgo(strategy);
        SearchProblem problem = new SearchProblem(initalState, searchAlgorithm, visualize);
        Node goalNode = null;
        String result = "";

        if (SearchProblem.SearchAlgorithms.BF.equals(searchAlgorithm)) {
            Q_INGFunc BFCallBack = (SearchProblem searchProblem, Node parentNode) -> {
                SearchAlgorithms.breadthFirstSearch(searchProblem, parentNode);
            };
            goalNode = gs.GeneralSearchFuntion(problem, BFCallBack);
        } else if (SearchProblem.SearchAlgorithms.DF.equals(searchAlgorithm)) {
            Q_INGFunc DFCallBack = (SearchProblem searchProblem, Node parentNode) -> {
                SearchAlgorithms.depthFirstSearch(searchProblem, parentNode);
            };
            goalNode = gs.GeneralSearchFuntion(problem, DFCallBack);
        }

        else if (SearchProblem.SearchAlgorithms.ID.equals(searchAlgorithm)) {
            Q_INGFunc IDCallBack = (SearchProblem searchProblem, Node parentNode) -> {
                SearchAlgorithms.iterativeDeepeningSearch(searchProblem, parentNode);
            };
            goalNode = gs.GeneralSearchFuntion(problem, IDCallBack);
        } else if (SearchProblem.SearchAlgorithms.UC.equals(searchAlgorithm)) {
            Q_INGFunc UCCallBack = (SearchProblem searchProblem, Node parentNode) -> {
                SearchAlgorithms.uniformCostSearch(searchProblem, parentNode);
            };
            goalNode = gs.GeneralSearchFuntion(problem, UCCallBack);
        }

        else if (SearchProblem.SearchAlgorithms.GR1.equals(searchAlgorithm)) {
            Q_INGFunc GR1CallBack = (SearchProblem searchProblem, Node parentNode) -> {
                SearchAlgorithms.greedySearch(searchProblem, parentNode, 0);
            };
            goalNode = gs.GeneralSearchFuntion(problem, GR1CallBack);
        }

        else if (SearchProblem.SearchAlgorithms.GR2.equals(searchAlgorithm)) {
            Q_INGFunc GR2CallBack = (SearchProblem searchProblem, Node parentNode) -> {
                SearchAlgorithms.greedySearch(searchProblem, parentNode, 0);
            };
            goalNode = gs.GeneralSearchFuntion(problem, GR2CallBack);
        }

        else if (SearchProblem.SearchAlgorithms.AS1.equals(searchAlgorithm)) {
            Q_INGFunc AS1CallBack = (SearchProblem searchProblem, Node parentNode) -> {
                SearchAlgorithms.aStarSearch(searchProblem, parentNode, 0);
            };
            goalNode = gs.GeneralSearchFuntion(problem, AS1CallBack);
        }

        else if (SearchProblem.SearchAlgorithms.AS2.equals(searchAlgorithm)) {
            Q_INGFunc AS2CallBack = (SearchProblem searchProblem, Node parentNode) -> {
                SearchAlgorithms.aStarSearch(searchProblem, parentNode, 0);
            };
            goalNode = gs.GeneralSearchFuntion(problem, AS2CallBack);
        }
        if (goalNode == null) {
            result = "NOSOLUTION";
        } else {
            problem.printAttr();
            result = goalNode.getPathToGoal(visualize) + ";" + goalNode.getState().money_spent + ";"
                    + problem.nodesExpandedCounter;
        }
        System.out.println("");
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        String initialState0 =  "30;" +
        "30,25,19;" +
        "90,120,150;" +
        "9,2;13,1;11,1;" +
        "3195,11,12,10,34;" +
        "691,7,8,6,15;";

        LLAPSearch.solve(initialState0, "BF", true);
    }
}
