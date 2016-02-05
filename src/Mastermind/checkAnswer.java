/**
 * 
 * @author Will Rice
 * Accompanying file to Knuth. contains the methods that compare a guess as to what the code is to the solution, and returns the relevant information.
 * Team members: Chris Sandvik and Ben Stearman
 */
package Mastermind;

public class checkAnswer {

	/**
	 * Checks using two Strings, one for the guess and one for the solution. Actual checking is done after converting to char arrays.
	 * @param a The first String to be compared
	 * @param b The second String to be compared
	 * @return An array corresponding to the colours in the correct position and the colours in the wrong position, respectively
	 */
	public static int[] check(String a, String b){
		return(check(splitString(a), splitString(b)));
	}
	
	/**
	 * Checks two char arrays, one for the guess and one for the solution.
	 * @param guess The first String (guess) to be compared
	 * @param answer The second String (solution to be compared
	 * @return An array corresponding to the colours in the correct position and the colours in the wrong position. respectively
	 */
	public static int[] check(char[] guess, char[] answer){
		int black = 0, white = 0;
		for(int i = 0; i < 4; i++){
			if(guess[i] == answer[i]){
				black++;
				guess[i] = answer[i] = 0;
			}
		}
		for(int i = 0; i < 4; i++){
			for(int j =0; j < 4; j++){
				if(guess[i] != 0 && guess[i] == answer[j] ){
					white++;
					answer[j] = 0;
					break;
				}
			}
		}
		int[] out = {black, white};
		return(out);
		
	}
	
	/**
	 * Converts a Sring to a char array.
	 * @param aString The String to be converted
	 * @return the char array created from the String
	 */
	private static char[] splitString(String aString){
		return(aString.toCharArray());
	}
	
}