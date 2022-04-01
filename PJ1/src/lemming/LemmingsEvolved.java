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
        if (rows <= 1 || cols <= 1)
            return new int[]{0, 0};

        int maxNumPoints = 0;
        int minNumPairs = Integer.MAX_VALUE;
        boolean foundPair = false;

        int[][] matrix = new int[rows][cols];
        int[][] pairs = new int[rows][cols];

        // GENERAL CASE
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                Lemming l1 = firstSeqTmp.get(j - 1);
                Lemming l2 = secondSeqTmp.get(i - 1);

                if (l1.getTribe() == l2.getTribe()) { // Lemmings from same tribe?
                    matrix[i][j] = matrix[i - 1][j - 1] + (l1.getPower() + l2.getPower());
                    foundPair = true;
                } else
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);

                if (matrix[i][j] == matrix[i][j - 1])
                    pairs[i][j] = pairs[i][j - 1];
                else if (matrix[i][j] == matrix[i - 1][j])
                    pairs[i][j] = pairs[i - 1][j];
                else
                    pairs[i][j] = pairs[i][j - 1] + 1;

                if (maxNumPoints < matrix[i][j]) {
                    maxNumPoints = Math.max(matrix[i][j], maxNumPoints); // updates max points
                    minNumPairs = pairs[i][j];
                } else if (maxNumPoints == matrix[i][j]) {
                    minNumPairs = Math.min(pairs[i][j], minNumPairs);
                }
            }
        }
        if(!foundPair)
            minNumPairs = 0;

        return new int[]{maxNumPoints, minNumPairs};
    }

}
