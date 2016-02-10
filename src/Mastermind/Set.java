package Mastermind;

/**
 * Created by benst on 2/5/2016.
 */
public class Set {
    public String pegs;

    public Set(String codePegs) {
        this.pegs = codePegs;
    }

    public Key Matches(Set aSet) {
        Key k = new Key();
        boolean pegUsed[] = new boolean[4];

        String guess = this.pegs;
        String solution = aSet.pegs;

        // unique match counting (all key pegs)
        for(int i=0; i<guess.length(); i++) {
            for(int j = 0; j< solution.length(); j++){
                if (!pegUsed[j] & guess.charAt(i) == solution.charAt(j)) {
                    pegUsed[j] = true; // remove matched peg from further matching
                    k.near++;
                }
            }
        }

        // move exact match counts from near to exact (black key pegs)
        for(int i=0; i<guess.length(); i++) {
            if (guess.charAt(i) == solution.charAt(i)) {
                k.near--;
                k.exact++;
            }
        }

        return k;
    }
}
