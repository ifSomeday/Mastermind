package Mastermind;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Mastermind {
	static boolean programOn = true;
	static String set;
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in); // Reading from System.in
		
		//Main program loop
		while(programOn) {
			//main menu
			System.out.println("Let's Play Mastermind!");
			System.out.println("1. Start a game: User picks set");
			System.out.println("2. Start a game: Computer picks set");
			System.out.println("3. Quit");
			
			//select menu option
			int choice = 0;
			//checks input choice for validity
			try {
				choice = reader.nextInt(); 
			} catch (InputMismatchException e) {
				choice = 0;
			}
			reader.nextLine();
			
			//start game where user picks colors
			if (choice == 1) {
				set = getGuess();
				KnuthAlgorithm knuth = new KnuthAlgorithm();
				knuth.solve(set.toUpperCase());
			}
			
			//start game where computer picks colors
			else if (choice == 2) {
				Game game = new Game();
				game.play();
			} 
			
			//exit program
			else if (choice == 3) {
				System.out.println("Goodbye!");
				System.exit(0);
			} 
			
			//handles incorrect menu selection
			else {
				System.out.println("This is an invalid input, please try again");
			}
		}
	}
	
	//Asks user for a set of pegs and validates it
	public static String getGuess() {
		Scanner reader = new Scanner(System.in); // Reading from System.in
		System.out.println("Enter a guess as a string of 4 characters, each being the first letter of a color from:");
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
		return set.toUpperCase();
	}
	
	//Displays result at end of a turn
	public static void displayResult(Key result) {
		System.out.println("There are "+result.exact+" correct colors in the correct location");
		System.out.println("There are "+result.near+" correct colors in the incorrect location\n");
	}
	
	//Displays winning output
	public static void displayVictory() {
		System.out.println("Congratulations, you got the right answer!\n");
	}
	
	//Displays losing output
	public static void displayFailure() {
		System.out.println("Sorry, you ran out of guesses. Better luck next time.\n");
	}
	
	//checks sets to see if they are in the correct format
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