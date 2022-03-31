package lemming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LemmingsEvolved {

    public LemmingsEvolved() {

    }

    public int[] processGame(List<Lemming> firstSeq, List<Lemming> secondSeq, int firstSeqSize, int secondSeqSize) {
        List<Lemming> firstSeqTmp = new ArrayList<>(firstSeq);
        List<Lemming> secondSeqTmp = new ArrayList<>(secondSeq);

        int rows = secondSeqSize + 1;
        int cols = firstSeqSize + 1;

        // BASE CASE
        // if there is a null sequence, return {0, 0}
        if (rows == 1 || cols == 1)
            return new int[]{0, 0};

        int maxNumPoints = 0;

        int[][] matrix = new int[rows][cols];

        int mapSize = rows /2;
        if(cols < rows)
            mapSize = cols/2;

        Map<Integer, Integer> pairs = new HashMap<>(mapSize);
        pairs.put(0, 0);

        // GENERAL CASE
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                Lemming l1 = firstSeqTmp.get(j - 1);
                Lemming l2 = secondSeqTmp.get(i - 1);

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

                if (maxNumPoints < matrix[i][j])
                    maxNumPoints = Math.max(matrix[i][j], maxNumPoints); // updates max points

                int current = pairs.getOrDefault(matrix[i][j], 0);
                if (current == 0 || currentNumberPairs < current)
                    pairs.put(matrix[i][j], currentNumberPairs);
            }
        }

        return new int[]{maxNumPoints, pairs.getOrDefault(maxNumPoints, 0)};
    }

}
