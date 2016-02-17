/**
 * @author Ben Stearman, Chris Sandvik
 */
package Mastermind;


import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;



public class Mastermind {
	static boolean programOn = true;
	static String set;
	static KnuthAlgorithm knuth = new KnuthAlgorithm();
	
	/**
	 * Main program loop in charge of running and maintaining the game
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in); // Reading from System.in
		
		//Main program loop
		while(programOn) {
			//main menu2
			System.out.println("Let's Play Mastermind!");
			System.out.println("1. Start a game: User picks set");
			System.out.println("2. Start a game: Computer picks set");
			System.out.println("3. Statistics and Options");
			System.out.println("4. Quit");
			
			//select menu option
			int choice = 0;
			try {
				choice = reader.nextInt(); // Scans the next token of the input as an
			} catch (InputMismatchException e) {
				choice = 0;
			}
			reader.nextLine();
			
			//start game where user picks colors
			if (choice == 1) {
				System.out.println("Enter a string of 4 characters, each being the first letter of a color from:");
				System.out.println("Red, Orange, Green, Yellow, Blue, Purple");
				
				boolean setCorrect = false;
				while (!setCorrect) {
					set = reader.nextLine();
					if (setChecker(set)) {
						setCorrect = true;
					} else {
						System.out.println("This is not a valid set of characters, please try again");
					}
				}
				
				knuth.solve(set.toUpperCase());
			} 
			
			//start game where computer picks colors
			else if (choice == 2) {
				System.out.println("Enter a guess as a string of 4 characters, each being the first letter of a color from:");
				System.out.println("Red, Orange, Green, Yellow, Blue, Purple");
				
				boolean continueGame = true;
				String answer = getCompCode();
				int turnsLeft = 12;
				int[] result = new int[2];
				while (continueGame) {
					System.out.println("You have "+turnsLeft+" turns left");
					boolean setCorrect = false;
					while (!setCorrect) {
						
						set = reader.nextLine();
						if (setChecker(set)) {
							setCorrect = true;
						} else {
							System.out.println("This is not a valid set of characters, please try again");
						}
					}
					result = checkAnswer.check(set, answer);
					if(result[0] == 4){
						System.out.println("Correct!");
						continueGame = false;
					} else {
						System.out.println("Incorrect. You got " + result[0] + " black pegs and " + result[1] + " white pegs!");
						if(turnsLeft != 1){
							System.out.println("Guess again!");
						} else {
							System.out.println("The correct answer was: " + answer + ".");
							continueGame = false;
						}
					}
					//continueGame = userGame.runTurn(set.toUpperCase());
					turnsLeft--;
				}
			} 
			
			//exit program
			else if (choice == 4) {
				System.out.println("Goodbye!");
				reader.close();
				System.exit(0);
			} 
			else if (choice == 3){
				System.out.println("\t1. Perform Stats\n\t2. Display Stats\n\t3. Change Computer guess delay\n\t0. Go back");
				try {
					choice = reader.nextInt(); // Scans the next token of the input as an
				} catch (InputMismatchException e) {
					System.out.println("Invalid input.");
					choice = 0;
				}
				if(choice == 1){
					knuth.performStats();
				} else if(choice == 2){
					knuth.displayStats();
				} else if(choice == 3){
					System.out.println("Enter Computer guess delay (in milliseconds): ");
					try {
						choice = reader.nextInt(); // Scans the next token of the input as an
					} catch (InputMismatchException e) {
						System.out.println("Invalid input. Defaulting to 1500ms.");
						choice = 1500;
					}
					knuth.setSleepTime(choice);
				} else if(choice == 0) {
					System.out.println("Exiting.");
				}
				
				
			}
			//handles incorrect menu selection
			else {
				System.out.println("This is an invalid input, please try again");
			}
		}
	}
	
	/**
	 * checks sets to see if they are in the correct format
	 * @param set the set to check
	 * @return true if the set is valid, false or not
	 */
	public static boolean setChecker(String set) {
		set = set.toUpperCase();
		if (set.length() != 4) {
			return false;
		} else {
			if (set.matches("[ROGYBP]*")) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * Generates a Computer code for the user to attempt to guess.
	 * Uses modified code from KnuthAlgorithm.java
	 * @return The randomly generated computer code
	 */
	public static String getCompCode(){
		Random rand = new Random();
		String letters = new String();
		String numbers = String.valueOf(rand.nextInt(6)+1) + String.valueOf(rand.nextInt(6)+1) + String.valueOf(rand.nextInt(6)+1) + String.valueOf(rand.nextInt(6)+1);
		for (char c : numbers.toUpperCase().toCharArray()) {
			switch (c) {
			case ('1'):
				letters += "R";
				break;
			case ('2'):
				letters += "O";
				break;
			case ('3'):
				letters += "Y";
				break;
			case ('4'):
				letters += "G";
				break;
			case ('5'):
				letters += "B";
				break;
			case ('6'):
				letters += "P";
				break;
			default:
				System.out.println(c);
				break;
			}
		}
		return(letters);
	}
}