package Mastermind;

/**
 * Created by benst on 2/5/2016.
 */
public class Game {
    State gs;

    public void play() {
        while (gs.turnsLeft() > 0) {
            Turn t = new Turn(gs.solution);
            gs.turnList.add(t);
            if(t.result.won()) {
                break;
            }
            displayResult(t);
        }
    }

    public void displayResult(Turn turn) {
        System.out.println("TODO");
    }
}
