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
    public static int iterations = 0;
    public static LinkedList<Node> queue = new LinkedList<>();    
    public static LinkedList<Node> deliveries = new LinkedList<>();

    public Game() {

    }

}
