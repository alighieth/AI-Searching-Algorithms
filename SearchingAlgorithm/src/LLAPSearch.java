public class LLAPSearch extends GenericSearch {
    public static String solve(String initalState, SearchProblem.SearchAlgorithms strategy, boolean visualize) {
        GenericSearch gs = new GenericSearch();
        SearchProblem problem = new SearchProblem(initalState, strategy, visualize);
        Object goalNode = null;
        String result = "";

        if (SearchProblem.SearchAlgorithms.BF.equals(strategy)) {
            Q_INGFunc BFCallBack = () -> {
                SearchAlgorithms.breadthFirstSearch(problem, problem.currentNode);
            };
            goalNode = gs.GeneralSearchFuntion(problem, BFCallBack);
        } else if (SearchProblem.SearchAlgorithms.DF.equals(strategy)) {
            Q_INGFunc DFCallBack = () -> {
                SearchAlgorithms.depthFirstSearch(problem, problem.currentNode);
            };
            goalNode = gs.GeneralSearchFuntion(problem, DFCallBack);
        }

        else if (SearchProblem.SearchAlgorithms.ID.equals(strategy)) {
            Q_INGFunc IDCallBack = () -> {
                SearchAlgorithms.iterativeDeepeningSearch(problem, problem.currentNode);
            };
            goalNode = gs.GeneralSearchFuntion(problem, IDCallBack);
        } else if (SearchProblem.SearchAlgorithms.UC.equals(strategy)) {
            Q_INGFunc UCCallBack = () -> {
                SearchAlgorithms.uniformCostSearch(problem, problem.currentNode);
            };
            goalNode = gs.GeneralSearchFuntion(problem, UCCallBack);
        }

        else if (SearchProblem.SearchAlgorithms.GR1.equals(strategy)) {
            Q_INGFunc GR1CallBack = () -> {
                SearchAlgorithms.greedySearch(problem, problem.currentNode, 0);
            };
            goalNode = gs.GeneralSearchFuntion(problem, GR1CallBack);
        }

        else if (SearchProblem.SearchAlgorithms.GR2.equals(strategy)) {
            Q_INGFunc GR2CallBack = () -> {
                SearchAlgorithms.greedySearch(problem, problem.currentNode, 0);
            };
            goalNode = gs.GeneralSearchFuntion(problem, GR2CallBack);
        }

        else if (SearchProblem.SearchAlgorithms.AS1.equals(strategy)) {
            Q_INGFunc AS1CallBack = () -> {
                SearchAlgorithms.aStarSearch(problem, problem.currentNode, 0);
            };
            goalNode = gs.GeneralSearchFuntion(problem, AS1CallBack);
        }

        else if (SearchProblem.SearchAlgorithms.AS2.equals(strategy)) {
            Q_INGFunc AS2CallBack = () -> {
                SearchAlgorithms.aStarSearch(problem, problem.currentNode, 0);
            };
            goalNode = gs.GeneralSearchFuntion(problem, AS2CallBack);
        } 
        if (goalNode == null || (boolean) goalNode == false) {
            result = "NOSOLUTION";
        } else {
            Node newGoalNode = null;
            if (goalNode instanceof Node) {
                newGoalNode = (Node) goalNode;
            }
            result = SearchProblem.getPathToGoal(newGoalNode, visualize) + ";" + newGoalNode.getPathCost() + ";"
                    + problem.getPossibleOperators(problem.getRoot()).size() + ";";
        }
        System.out.println("");
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        LLAPSearch.solve("50;" +
                "22,22,22;" +
                "50,60,70;" +
                "30,2;19,1;15,1;" +
                "300,5,7,3,20;" +
                "500,8,6,3,40;", SearchProblem.SearchAlgorithms.BF, true);
    }
}
