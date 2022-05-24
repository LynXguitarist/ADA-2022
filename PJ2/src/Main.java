import warriorGame.WarriorGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final String PAYS = "Pays";
    private static final String GETS = "Gets";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] C_D = in.readLine().split(" ");

        int C = Integer.parseInt(C_D[0]); // number of challenges
        int D = Integer.parseInt(C_D[1]); // number of decisions

        // Array list with C size -> C vertices
        WarriorGame wg = new WarriorGame(C); // Controller class

        for (int i = 0; i < D; i++) {
            String[] challenge = in.readLine().split(" "); // Line with C1 P V C2

            int C1 = Integer.parseInt(challenge[0]); // finished challenge
            String P = challenge[1]; // Pays or Gets energy
            int V = Integer.parseInt(challenge[2]); // amount of energy to pay or get
            int C2 = Integer.parseInt(challenge[3]); // next challenge

            if(P.equals(PAYS))
                wg.addEdge(C1, C2, V);
            else
                wg.addEdge(C1, C2, -V);

        }

        String[] S_W_E = in.readLine().split(" "); // info about the game

        int S = Integer.parseInt(S_W_E[0]); // initial challenge
        int W = Integer.parseInt(S_W_E[1]); // wizard meeting
        int E = Integer.parseInt(S_W_E[2]); // initial amount of energy

        long maxEnergy = wg.processGame(S,W,E);

        if(maxEnergy > E)
            System.out.println("Full of energy");
        else
            System.out.println(maxEnergy);
    }

}
