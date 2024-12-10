import java.util.ArrayList;

/**
 *
 * @author Josue Lopez
 * @author Brendan Holt
 * @version 1.0
 */

public class StrategyAsQueue implements Strategy {

    public int[] algorithm(ArrayList<Node> nodes ) {
        int[] order = new int[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            order[i] = i;
        }
        return order;
    }

}

