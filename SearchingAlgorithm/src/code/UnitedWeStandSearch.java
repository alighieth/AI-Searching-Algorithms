package code;

public class UnitedWeStandSearch extends GenericSearch {
    public static String solve(String initalState, String strategy, boolean visualize) {
        GenericSearch gs = new GenericSearch();
        SearchProblem.SearchAlgorithms searchAlgorithm = SearchProblem.getSearchAlgorithms(strategy);
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
        } else if (SearchProblem.SearchAlgorithms.ID.equals(searchAlgorithm)) {
            Q_INGFunc DFCallBack = (SearchProblem searchProblem, Node parentNode) -> {
                SearchAlgorithms.depthFirstSearch(searchProblem, parentNode);
            };

            int cutoff = 0;
            while (goalNode == null) {
                problem = new SearchProblem(initalState, searchAlgorithm, visualize);
                problem.cutOff = cutoff;
                goalNode = gs.GeneralSearchFuntion(problem, DFCallBack);
                cutoff++;
            }
        } else if (SearchProblem.SearchAlgorithms.UC.equals(searchAlgorithm)) {
            Q_INGFunc UCCallBack = (SearchProblem searchProblem, Node parentNode) -> {
                SearchAlgorithms.uniformCostSearch(searchProblem, parentNode);
            };
            goalNode = gs.GeneralSearchFuntion(problem, UCCallBack);
        }

        else if (SearchProblem.SearchAlgorithms.GR1.equals(searchAlgorithm)) {
            Q_INGFunc GR1CallBack = (SearchProblem searchProblem, Node parentNode) -> {
                SearchAlgorithms.greedySearch(searchProblem, parentNode);
            };
            goalNode = gs.GeneralSearchFuntion(problem, GR1CallBack);
        }

        else if (SearchProblem.SearchAlgorithms.GR2.equals(searchAlgorithm)) {
            Q_INGFunc GR2CallBack = (SearchProblem searchProblem, Node parentNode) -> {
                SearchAlgorithms.greedySearch2(searchProblem, parentNode);
            };
            goalNode = gs.GeneralSearchFuntion(problem, GR2CallBack);
        }

        else if (SearchProblem.SearchAlgorithms.AS1.equals(searchAlgorithm)) {
            Q_INGFunc AS1CallBack = (SearchProblem searchProblem, Node parentNode) -> {
                SearchAlgorithms.aStarSearch(searchProblem, parentNode);
            };
            goalNode = gs.GeneralSearchFuntion(problem, AS1CallBack);
        }

        else if (SearchProblem.SearchAlgorithms.AS2.equals(searchAlgorithm)) {
            Q_INGFunc AS2CallBack = (SearchProblem searchProblem, Node parentNode) -> {
                SearchAlgorithms.aStarSearch2(searchProblem, parentNode);
            };
            goalNode = gs.GeneralSearchFuntion(problem, AS2CallBack);
        }
        if (goalNode == null) {
            result = "nosolution";
        } else {
            result = goalNode.getPathToGoal(visualize);
        }
        System.out.println("");
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        String initialState0 = "4;3;0,1,2,1,0,2;2,0,3,0,1,2,1,0,0,0;";
        UnitedWeStandSearch.solve(initialState0, "BF", false);
        Long rt = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("rt " + rt);
    }
}
