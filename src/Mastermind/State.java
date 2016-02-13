package Mastermind;

import java.util.List;

/**
 * Created by benst on 2/5/2016.
 */
public class State {
    static final int NUM_POSITIONS = 4;
    static final String COLORS = "ROYGBP";
    static final int NUM_COLORS = COLORS.length();
    static final int TURNS = 12;
    Code solution;
    int turn;
    List<Turn> turnList;

    public State() {
        this.solution = new Code("TODO");
        this.turn = 1;
    }

    public int turnsLeft() {
        return TURNS - turn;
    }
}
