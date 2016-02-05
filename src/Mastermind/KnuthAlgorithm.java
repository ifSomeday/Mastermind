/**
 * @author Will Rice
 * Implementation of Donald Knuth's mastermind algorithm. This algorithm is not the most optimal, however it is the only algorithm that guarantees a solution in 5 or less turns.
 * Team members: Chris Sandvik and Ben Stearman
 */
package Mastermind;

import java.util.ArrayList;
import java.util.Arrays;

public class KnuthAlgorithm {

	// classwide initializations
	private ArrayList<String> possibleSolutions = new ArrayList<String>();
	private ArrayList<String> allSolutions = new ArrayList<String>();
	ArrayList<String> allSolutions2 = new ArrayList<String>();
	private int numGuesses;
	private int[][] pegs = new int[14][2];
	private String solution;
	private int SLEEPTIME = 1500;

	/**
	 * Default empty constructor. Nothing needs to be initialized
	 */
	public KnuthAlgorithm() {

	}

	/**
	 * Solves a puzzle with a given solution
	 * 
	 * @param solution
	 *            The solution to the puzzle
	 * @return returns the number of guesses it took to find the answer
	 */
	public int solve(String solution) {
		this.solution = lettersToNumbers(solution);
		numGuesses = 0;
		initArrays();
		return (guess("1122", true));
	}

	/**
	 * Performs statistics on the the guessing algorithm. Solves every single
	 * possible puzzle returns maximum turn and average turns to solve the
	 * puzzles.
	 */
	@SuppressWarnings("unchecked")
	public void performStats() {
		initArrays();
		allSolutions2 = (ArrayList<String>) allSolutions.clone();
		double total = 0;
		int[] distributions = { 0, 0, 0, 0, 0 };
		for (String s : allSolutions2) {
			int tmp = quietSolve(s);
			total += tmp;
			distributions[tmp - 1]++;
		}
		System.out.println("Average guesses to solve: " + (total / 1296));
		System.out.println("Distributions: \n\tOne guess: " + distributions[0] + "\n\tTwo guesses: " + distributions[1]
				+ "\n\tThree guesses: " + distributions[2] + "\n\tFour guesses: " + distributions[3]
				+ "\n\tFive guesses: " + distributions[4]);
	}

	/**
	 * Displays the statistics that would be calculated using performStats()
	 * Since the algorithm used involves no random number generation and will
	 * perform the same for each case every time, for demonstration purposes in
	 * order to save time, this method can be run instead of performStats().
	 */
	public void displayStats() {
		System.out.println("Average guesses to solve: 4.47608024691358");
		System.out.println(
				"Distributions: \n\tOne guess: 1\n\tTwo guesses: 6\n\tThree guesses: 62\n\tFour guesses: 533\n\tFive guesses: 694");

	}

	/**
	 * Sets the sleep time in between the copmuter's guesses when running
	 * solve()
	 * 
	 * @param sleepTime
	 *            the time to sleep in milliseconds (Default 1500)
	 */
	public void setSleepTime(int sleepTime) {
		this.SLEEPTIME = sleepTime;
	}

	/**
	 * Solves a puzzle with the given solution without requiring user
	 * interaction. Used for calculating stats.
	 * 
	 * @param solution
	 *            The solution to the puzzle
	 * @return the number of guesses it took to find the answer
	 */
	private int quietSolve(String solution) {
		this.solution = solution;
		numGuesses = 0;
		initArrays();
		return (guess("1122", false));
	}

	/**
	 * Guesses a possible solution to the puzzle. Recursively calls itself until
	 * the puzzle is solved.
	 * 
	 * @param currentGuess
	 *            the algorithm's current guess as to the solution for the
	 *            puzzle
	 * @return the number of turns required to solve the puzzle
	 */
	private int guess(String currentGuess, boolean interact) {
		numGuesses++;
		System.out.println("Computer guesses: " + numbersToLetters(currentGuess));
		int[] result = checkAnswer.check(currentGuess, solution);
		allSolutions.remove(allSolutions.indexOf(currentGuess));
		if (result[0] == 4) {
			System.out.println("Solved! Solution is: " + numbersToLetters(currentGuess));
			return (numGuesses);
		} else if (interact) {
			System.out.println(
					"Incorrect. Computer receives " + result[0] + " black pegs and " + result[1] + " white pegs.");
			System.out.println("Determining optimal guess...\n");
			try {
				Thread.sleep(SLEEPTIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		possibleSolutions = removeNonMatchingCodes(possibleSolutions, currentGuess, result);
		guess(getNextGuess(), interact);
		return (numGuesses);
	}

	/**
	 * Initializes all the arrays needed for the algorithm
	 */
	@SuppressWarnings("unchecked")
	private void initArrays() {
		possibleSolutions.clear();
		for (int i = 1; i < 7; i++) {
			for (int j = 1; j < 7; j++) {
				for (int k = 1; k < 7; k++) {
					for (int m = 1; m < 7; m++) {
						possibleSolutions
								.add((String.valueOf(i) + String.valueOf(j) + String.valueOf(k) + String.valueOf(m)));
					}
				}
			}
		}
		allSolutions = (ArrayList<String>) possibleSolutions.clone();
		int k = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; i + j < 5; j++) {
				if (!(i == 3 && j == 1)) {
					pegs[k++] = new int[] { i, j };
				}
			}
		}
	}

	/**
	 * determines the optimal next guess to efficiently solved the puzzle
	 * 
	 * @return the optimal next guess
	 */
	@SuppressWarnings("unchecked")
	private String getNextGuess() {
		int removed, minimax = 0;
		ArrayList<String> nextGuess = new ArrayList<String>();
		nextGuess.addAll(possibleSolutions);
		for (String guess : allSolutions) {
			int min = Integer.MAX_VALUE;
			for (int[] peg : pegs) {
				removed = removeMatchingCodes(possibleSolutions, guess, peg).size();
				min = Math.min(min, removed);
			}
			if (min > minimax) {
				nextGuess.clear();
				nextGuess.add(guess);
				minimax = min;
			}
			if (min == minimax) {
				nextGuess.add(guess);
			}
		}
		ArrayList<String> tmpGuess = (ArrayList<String>) nextGuess.clone();
		for (String guess : nextGuess) {
			if (!possibleSolutions.contains(guess)) {
				tmpGuess.remove(tmpGuess.indexOf(guess));
			}
		}
		if (tmpGuess.isEmpty()) {
			return (nextGuess.get(0));
		} else {
			return (tmpGuess.get(0));
		}
	}

	/**
	 * removes from the given array all the codes that would return the same
	 * given answer if the guess was the puzzle's solution. The original array
	 * is not modified
	 * 
	 * @param arrayIn
	 *            the array to remove the codes from
	 * @param guess
	 *            The guess to compare to
	 * @param answer
	 *            the answer to compare to
	 * @return the array with matching codes removed
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<String> removeMatchingCodes(ArrayList<String> arrayIn, String guess, int[] answer) {
		ArrayList<String> array = (ArrayList<String>) arrayIn.clone();
		int i = 0;
		int[] tmp;
		while (i < array.size()) {
			tmp = checkAnswer.check(array.get(i), guess);
			if (Arrays.equals(tmp, answer)) {
				array.remove(i);
			} else {
				i++;
			}
		}
		return (array);
	}

	/**
	 * removes from the given array all the codes that would return a different
	 * answer if the guess was the puzzle's solution. The original array is not
	 * modified
	 * 
	 * @param arrayIn
	 *            the array to remove the codes from
	 * @param guess
	 *            The guess to compare to
	 * @param answer
	 *            the array with the matching codes removed
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<String> removeNonMatchingCodes(ArrayList<String> arrayIn, String guess, int[] answer) {
		ArrayList<String> array = (ArrayList<String>) arrayIn.clone();
		int i = 0;
		int[] tmp;
		while (i < array.size()) {
			tmp = checkAnswer.check(array.get(i), guess);
			if (Arrays.equals(tmp, answer)) {
				i++;
			} else {
				array.remove(i);
			}
		}
		return (array);
	}

	/**
	 * Converts an input code consisting of letters to numbers, because numbers
	 * are easier to work with
	 * 
	 * @param letters
	 *            the string of letters to be converted
	 * @return the converted string in numbers
	 */
	private String lettersToNumbers(String letters) {
		String numbers = new String();
		for (char c : letters.toUpperCase().toCharArray()) {
			switch (c) {
			case ('R'):
				numbers += "1";
				break;
			case ('O'):
				numbers += "2";
				break;
			case ('Y'):
				numbers += "3";
				break;
			case ('G'):
				numbers += "4";
				break;
			case ('B'):
				numbers += "5";
				break;
			case ('P'):
				numbers += "6";
				break;
			default:
				System.out.println("Error converting to number.");
				break;
			}
		}
		return (numbers);
	}

	/**
	 * Converts and input code of numbers to letters, because displaying letters
	 * is better for the user.
	 * 
	 * @param numbers
	 *            the string of numbers to be converted
	 * @return the converted string in letters
	 */
	private String numbersToLetters(String numbers) {
		String letters = new String();
		for (char c : numbers.toUpperCase().toCharArray()) {
			switch (c) {
			case ('1'):
				letters += "Red ";
				break;
			case ('2'):
				letters += "Orange ";
				break;
			case ('3'):
				letters += "Yellow ";
				break;
			case ('4'):
				letters += "Green ";
				break;
			case ('5'):
				letters += "Blue ";
				break;
			case ('6'):
				letters += "Purple ";
				break;
			default:
				System.out.println("Error converting to letter.");
				break;
			}
		}
		return (letters);
	}

}
