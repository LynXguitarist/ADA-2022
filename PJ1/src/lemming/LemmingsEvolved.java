package lemming;

public class LemmingsEvolved {

    public LemmingsEvolved() {

    }

    public long[] processGame(Lemming[] firstSeq, Lemming[] secondSeq, int firstSeqSize, int secondSeqSize) {
        int rows = secondSeqSize + 1;
        int cols = firstSeqSize + 1;

        // BASE CASE
        if (rows <= 1 || cols <= 1) // if there is a null sequence, return {0, 0}
            return new long[]{0, 0};

        long maxNumPoints = 0;
        int minNumPairs = Integer.MAX_VALUE;
        boolean foundPair = false;

        long[][] points = new long[rows][cols]; // matrix for points
        int[][] pairs = new int[rows][cols]; // matrix for pairs

        // GENERAL CASE
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                Lemming l1 = firstSeq[j - 1]; // gets the lemming in the first sequence
                Lemming l2 = secondSeq[i - 1]; // gets the lemming in the second sequence

                long leftValue = points[i - 1][j]; // left value -> position (i - 1, j)
                int leftPair = pairs[i - 1][j]; // left pair -> position (i - 1, j)

                long topValue = points[i][j - 1]; // top value -> position (i, j - 1)
                int topPair = pairs[i][j - 1]; // top pair -> position (i, j - 1)

                if (l1.getTribe() == l2.getTribe()) { // Lemmings from same tribe?
                    long sol = points[i - 1][j - 1] + (l1.getPower() + l2.getPower()); // solution
                    int pairSol = pairs[i - 1][j - 1] + 1; // pair solution

                    // Saves the better option between (i-1j)(j-1), (i-1)(j) and (i)(j-1)
                    // Better option = max points and min pairs
                    if ((sol == leftValue && leftPair < pairSol) || sol < leftValue) {
                        sol = leftValue;
                        pairSol = leftPair;
                    } else if ((sol == topValue && topPair < pairSol) || sol < topValue) {
                        sol = topValue;
                        pairSol = topPair;
                    }

                    // saves the best solution for pairs and maxPoints
                    points[i][j] = sol;
                    pairs[i][j] = pairSol;
                    foundPair = true;
                } else {
                    // Saves the better option between (i-1j)(j-1), (i-1)(j) and (i)(j-1)
                    // Better option = max points and min pairs
                    if ((leftValue == topValue && topPair < leftPair) || leftValue < topValue) {
                        points[i][j] = topValue;
                        pairs[i][j] = topPair;
                    } else {
                        points[i][j] = leftValue;
                        pairs[i][j] = leftPair;
                    }
                }

                if (maxNumPoints < points[i][j]) {
                    maxNumPoints = points[i][j]; // updates max points
                    minNumPairs = pairs[i][j]; // updates min pairs needed
                } else if (maxNumPoints == points[i][j])
                    minNumPairs = Math.min(pairs[i][j], minNumPairs); // updates min pairs needed
            }
        }

        if (!foundPair) // if has no pairs, minPairs = 0
            minNumPairs = 0;

        return new long[]{maxNumPoints, minNumPairs};
    }

}
