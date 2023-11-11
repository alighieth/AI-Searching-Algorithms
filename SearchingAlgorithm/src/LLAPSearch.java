public class LLAPSearch extends GenericSearch {
    public static String solve(String initalState, SearchProblem.SearchAlgorithms strategy, boolean visualize) {
        GenericSearch gs = new GenericSearch();
        SearchProblem problem = new SearchProblem(initalState, strategy, visualize);
        Object goalNode = null;
        String result = "";

        if (SearchProblem.SearchAlgorithms.BF.equals(strategy)) {
            Q_INGFunc BFCallBack = () -> {
                SearchAlgorithms.breadthFirstSearch(problem, problem.getRoot());
            };
            goalNode = gs.GeneralSearchFuntion(problem, BFCallBack);
        }
        if (SearchProblem.SearchAlgorithms.DF.equals(strategy)) {
            Q_INGFunc DFCallBack = () -> {
                SearchAlgorithms.depthFirstSearch(problem, problem.getRoot());
            };
            goalNode = gs.GeneralSearchFuntion(problem, DFCallBack);
        }

        if (SearchProblem.SearchAlgorithms.ID.equals(strategy)) {
            Q_INGFunc IDCallBack = () -> {
                SearchAlgorithms.iterativeDeepeningSearch(problem, problem.getRoot());
            };
            goalNode = gs.GeneralSearchFuntion(problem, IDCallBack);
        }
        if (SearchProblem.SearchAlgorithms.UC.equals(strategy)) {
            Q_INGFunc UCCallBack = () -> {
                SearchAlgorithms.uniformCostSearch(problem, problem.getRoot());
            };
            goalNode = gs.GeneralSearchFuntion(problem, UCCallBack);
        }

        if (SearchProblem.SearchAlgorithms.GR1.equals(strategy)) {
            Q_INGFunc GR1CallBack = () -> {
                SearchAlgorithms.greedySearch(problem, problem.getRoot(), 0);
            };
            goalNode = gs.GeneralSearchFuntion(problem, GR1CallBack);
        }

        if (SearchProblem.SearchAlgorithms.GR2.equals(strategy)) {
            Q_INGFunc GR2CallBack = () -> {
                SearchAlgorithms.greedySearch(problem, problem.getRoot(), 0);
            };
            goalNode = gs.GeneralSearchFuntion(problem, GR2CallBack);
        }

        if (SearchProblem.SearchAlgorithms.AS1.equals(strategy)) {
            Q_INGFunc AS1CallBack = () -> {
                SearchAlgorithms.aStarSearch(problem, problem.getRoot(), 0);
            };
            goalNode = gs.GeneralSearchFuntion(problem, AS1CallBack);
        }

        if (SearchProblem.SearchAlgorithms.AS2.equals(strategy)) {
            Q_INGFunc AS2CallBack = () -> {
                SearchAlgorithms.aStarSearch(problem, problem.getRoot(), 0);
            };
            goalNode = gs.GeneralSearchFuntion(problem, AS2CallBack);
        }
        if (goalNode == null) {
            result = "NOSOLUTION";
        } else {
            Node newGoalNode = (Node) goalNode;
            result = SearchProblem.getPathToGoal(newGoalNode, visualize) + ";" + newGoalNode.getPathCost() + ";"
                    + SearchProblem.getPossibleOperators(problem.getRoot()).size() + ";";
        }
        System.out.println("");
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {

    }
}
