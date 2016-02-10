package Mastermind;

/**
 * Created by benst_ghk on 2/5/2016.
 */
public class Turn {
    Set guess;
    Key result;

    public Turn(Set solution) {
        this.result = this.guess.Matches(solution);
    }

    public Set getNextGuess() {
        Set c = new Set("TODO");
        return c;
    }
}
