import lemming.Lemming;
import lemming.LemmingsEvolved;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        LemmingsEvolved lms = new LemmingsEvolved(); // Controller class

        int gameTrials = Integer.parseInt(in.readLine()); // number of game trials
        for (int i = 0; i < gameTrials; i++) {
            List<Lemming> firstSeq = new LinkedList<>();
            List<Lemming> secondSeq = new LinkedList<>();

            // 1st Sequence
            int N1 = Integer.parseInt(in.readLine()); // Number of lemmings in the first sequence
            for(int j = 0; j < N1; j++){
                String[] lemmingInfo = in.readLine().split(" ");

                char tribe = lemmingInfo[0].charAt(0); // tribe
                int power = Integer.parseInt(lemmingInfo[1]); // power value

                firstSeq.add(new Lemming(tribe, power));
            }

            // 2nd Sequence
            int N2 = Integer.parseInt(in.readLine()); // Number of lemmings in the first sequence
            for(int j = 0; j < N2; j++){
                String[] lemmingInfo = in.readLine().split(" ");

                char tribe = lemmingInfo[0].charAt(0); // tribe
                int power = Integer.parseInt(lemmingInfo[1]); // power value

                secondSeq.add(new Lemming(tribe, power));
            }
            // processes the algorithm
            int[] output = lms.processGame(firstSeq, secondSeq);

            System.out.println(output[0] + " " + output[1]);
        }
    }

}
