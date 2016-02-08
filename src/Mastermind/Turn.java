package Mastermind;

/**
 * Created by benst_ghk on 2/5/2016.
 */
public class Turn {
    String guess;
    Key result;

    public Turn(String solution) {
        this.guess = Mastermind.getGuess();
        int[] initResults = checkAnswer.check(guess, solution);
        this.result = new Key(initResults[0],initResults[1]);
    }
}