package lemming;

import java.util.List;

public class LemmingsEvolved {

    public LemmingsEvolved(){

    }

    // TODO:
    //  - List, maybe array?
    //  - Min pairs
    public int[] processGame(List<Lemming> firstSeq, List<Lemming> secondSeq){
        int rows = secondSeq.size() + 1;
        int cols = firstSeq.size() + 1;

        // if there is a null sequence, return {0, 0}
        if(rows == 1 || cols == 1)
            return new int[]{0, 0};

        int minNumPairs = Integer.MAX_VALUE;
        int maxNumPoints = Integer.MIN_VALUE;

        int[][] matrix = new int[rows][cols];
        System.out.println("rows =" + rows + " cols =" + cols);

        for(int i = 0; i < rows; i++)
            matrix[i][0] = 0;

        for(int i = 0; i < cols; i++)
            matrix[0][i] = 0;

        for(int i = 1; i < rows; i++){
            for(int j = 1; j < cols; j++){
                Lemming l1 = firstSeq.get(j - 1);
                Lemming l2 = secondSeq.get(i - 1);

                if(l1.getTribe() == l2.getTribe()) // Lemmings from same tribe?
                    matrix[i][j] = matrix[i-1][j-1] + (l1.getPower() + l2.getPower());
                else
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j - 1]);

                if(maxNumPoints < matrix[i][j])
                    maxNumPoints = Math.max(matrix[i][j], maxNumPoints); // updates max points
            }
        }

        return new int[]{maxNumPoints, minNumPairs};
    }
}
