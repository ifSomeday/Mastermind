package Mastermind;

/**
 * Created by benst on 2/5/2016.
 */
public class Game {
	
	State gs;
	public Game() {
		gs = new State();
	}
    public void play() {
        while (gs.turnsLeft() > 0) {
            Turn t = new Turn(gs.solution);
            gs.turnList.add(t);
            gs.turn++;
            if(t.result.won()) {
            	Mastermind.displayVictory();
                break;
            }
            Mastermind.displayResult(t.result);
        }
        if(gs.turnsLeft() == 0) {
        	Mastermind.displayFailure();
        	System.out.println(gs.solution);
        }
    }
}