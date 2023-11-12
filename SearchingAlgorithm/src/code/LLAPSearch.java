package code;
public class LLAPSearch extends GenericSearch {
    public static String solve(String initalState, String strategy, boolean visualize) {
        GenericSearch gs = new GenericSearch();
        SearchProblem.SearchAlgorithms searchAlgorithm = SearchProblem.getSearchAlgo(strategy);
        SearchProblem problem = new SearchProblem(initalState, searchAlgorithm, visualize);
        Object goalNode = null;
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
            Node newGoalNode = null;
            if (goalNode instanceof Node) {
                newGoalNode = (Node) goalNode;
            }
            result = newGoalNode.getPathToGoal(visualize) + ";" + newGoalNode.getState().money_spent + ";"
                    + problem.getPossibleOperators(problem.getRoot()).size() + ";";
        }
        System.out.println("");
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        String initialState0 = "17;" +
                "49,30,46;" +
                "7,57,6;" +
                "7,1;20,2;29,2;" +
                "350,10,9,8,28;" +
                "408,8,12,13,34;";

                // "50;" +
                // "22,22,22;" +
                // "50,60,70;" +
                // "30,2;19,1;15,1;" +
                // "300,5,7,3,20;" +
                // "500,8,6,3,40;"
        LLAPSearch.solve(initialState0, "BF", false);
    }
}
