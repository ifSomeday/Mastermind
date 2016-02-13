package Mastermind;

/**
 * Created by benst on 2/5/2016.
 */
public class Key {
    int exact = 0;
    int near = 0;

    public boolean won() {
        return (exact == 4);
    }
}
