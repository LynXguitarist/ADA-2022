package lemming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LemmingsEvolved {

    public LemmingsEvolved() {

    }

    public int[] processGame(List<Lemming> firstSeq, List<Lemming> secondSeq) {
        int rows = secondSeq.size() + 1;
        int cols = firstSeq.size() + 1;

        // if there is a null sequence, return {0, 0}
        if (rows == 1 || cols == 1)
            return new int[]{0, 0};

        int maxNumPoints = Integer.MIN_VALUE;

        int[][] matrix = new int[rows][cols];
        Map<Integer, Integer> pairs = new HashMap<>();
        pairs.put(0, 0);

        /**
        for (int i = 0; i < rows; i++)
            matrix[i][0] = 0;

        for (int i = 0; i < cols; i++)
            matrix[0][i] = 0;
        */

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                Lemming l1 = firstSeq.get(j - 1);
                Lemming l2 = secondSeq.get(i - 1);

                int currentNumberPairs = 0;

                if (l1.getTribe() == l2.getTribe()) { // Lemmings from same tribe?
                    matrix[i][j] = matrix[i - 1][j - 1] + (l1.getPower() + l2.getPower());
                    currentNumberPairs = pairs.get(matrix[i - 1][j - 1]) + 1;

                } else if (matrix[i - 1][j] > matrix[i][j - 1]) {
                    matrix[i][j] = matrix[i - 1][j];
                    currentNumberPairs = pairs.get(matrix[i - 1][j]);
                } else {
                    matrix[i][j] = matrix[i][j - 1];
                    currentNumberPairs = pairs.get(matrix[i][j - 1]);
                }

                if (maxNumPoints <= matrix[i][j])
                    maxNumPoints = Math.max(matrix[i][j], maxNumPoints); // updates max points

                int current = pairs.getOrDefault(matrix[i][j], 0);
                if (current == 0 || currentNumberPairs < current)
                    pairs.put(matrix[i][j], currentNumberPairs);
            }
        }

        return new int[]{maxNumPoints, pairs.get(maxNumPoints)};
    }
}
