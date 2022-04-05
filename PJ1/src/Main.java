import lemming.Lemming;
import lemming.LemmingsEvolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        LemmingsEvolved lms = new LemmingsEvolved(); // Controller class

        int gameTrials = Integer.parseInt(in.readLine()); // number of game trials
        for (int i = 0; i < gameTrials; i++) {

            // 1st Sequence
            int N1 = Integer.parseInt(in.readLine()); // Number of lemmings in the first sequence
            Lemming[] firstSeq = new Lemming[N1];
            for(int j = 0; j < N1; j++){
                String lemmingInfo = in.readLine();

                char tribe = lemmingInfo.charAt(0); // tribe
                int power = Integer.parseInt(lemmingInfo.substring(2)); // power value

                firstSeq[j] = new Lemming(tribe, power);
            }

            // 2nd Sequence
            int N2 = Integer.parseInt(in.readLine()); // Number of lemmings in the first sequence
            Lemming[] secondSeq = new Lemming[N2];
            for(int j = 0; j < N2; j++){
                String lemmingInfo = in.readLine();

                char tribe = lemmingInfo.charAt(0); // tribe
                int power = Integer.parseInt(lemmingInfo.substring(2)); // power value

                secondSeq[j] = new Lemming(tribe, power);
            }
            // processes the algorithm
            long[] output = lms.processGame(firstSeq, secondSeq, N1, N2);

            System.out.println(output[0] + " " + output[1]);
        }
    }

}
