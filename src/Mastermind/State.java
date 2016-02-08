package Mastermind;

import java.util.ArrayList;

/**
 * Created by benst on 2/5/2016.
 */
public class State {
    static final int NUM_POSITIONS = 4;
    static final String COLORS = "ROYGBP";
    static final int NUM_COLORS = COLORS.length();
    static final int TURNS = 12;
    String solution;
    int turn;
    ArrayList<Turn> turnList = new ArrayList<Turn>();

    public State() {
        this.solution = Mastermind.getCompCode();
        this.turn = 1;
    }

    public int turnsLeft() {
        return TURNS - turn;
    }
}
