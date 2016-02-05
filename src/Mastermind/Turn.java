package Mastermind;

/**
 * Created by benst_ghk on 2/5/2016.
 */
public class Turn {
    Code guess;
    Key result;

    public Turn(Code solution) {
        this.guess = getNextGuess();
        this.result = this.guess.Compare(solution);
    }

    public Code getNextGuess() {
        Code c = new Code("TODO");
        return c;
    }
}
