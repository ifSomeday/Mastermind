package Mastermind;

/**
 * Created by benst on 2/5/2016.
 */
public class Key {
    public int exact = 0;
    public int near = 0;
    
    public Key(int e, int n) {
    	exact = e;
    	near = n;
    }

    public boolean won() {
        return (exact == 4);
    }
}