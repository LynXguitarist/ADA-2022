package lemming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LemmingsEvolved {

    public LemmingsEvolved() {

    }

    public long[] processGame(Lemming[] firstSeq, Lemming[] secondSeq, int firstSeqSize, int secondSeqSize) {
        int rows = secondSeqSize + 1;
        int cols = firstSeqSize + 1;

        // BASE CASE
        // if there is a null sequence, return {0, 0}
        if (rows <= 1 || cols <= 1)
            return new long[]{0, 0};

        long maxNumPoints = 0;
        int minNumPairs = Integer.MAX_VALUE;
        boolean foundPair = false;

        long[][] matrix = new long[rows][cols];
        int[][] pairs = new int[rows][cols];

        // GENERAL CASE
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                Lemming l1 = firstSeq[j - 1];
                Lemming l2 = secondSeq[i - 1];

                if (l1.getTribe() == l2.getTribe()) { // Lemmings from same tribe?
                    long sol = matrix[i - 1][j - 1] + (l1.getPower() + l2.getPower());
                    int pairSol = pairs[i - 1][j - 1] + 1;

                    long value2 = matrix[i - 1][j];
                    int pair2 = pairs[i - 1][j];

                    long value3 = matrix[i][j - 1];
                    int pair3 = pairs[i][j - 1];

                    if ((sol == value2 && pair2 < pairSol) || sol < value2) {
                        sol = value2;
                        pairSol = pair2;
                    } else if ((sol == value3 && pair3 < pairSol) || sol < value3) {
                        sol = value3;
                        pairSol = pair3;
                    }

                    matrix[i][j] = sol;
                    pairs[i][j] = pairSol;
                    foundPair = true;
                } else {
                    long sol = matrix[i - 1][j];
                    int pairSol = pairs[i - 1][j];

                    long value1 = matrix[i][j - 1];
                    int pair1 = pairs[i][j - 1];

                    if ((sol == value1 && pair1 < pairSol) || sol < value1) {
                        sol = value1;
                        pairSol = pair1;
                    }

                    matrix[i][j] = sol;
                    pairs[i][j] = pairSol;
                }

                if (maxNumPoints < matrix[i][j]) {
                    maxNumPoints = Math.max(matrix[i][j], maxNumPoints); // updates max points
                    minNumPairs = pairs[i][j];
                } else if (maxNumPoints == matrix[i][j]) {
                    minNumPairs = Math.min(pairs[i][j], minNumPairs);
                }
            }
        }
        if (!foundPair)
            minNumPairs = 0;

        return new long[]{maxNumPoints, minNumPairs};
    }

}
