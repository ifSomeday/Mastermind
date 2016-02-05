package Mastermind;


import java.util.InputMismatchException;
import java.util.Scanner;



public class Mastermind {
	static boolean programOn = true;
	static String set;
	static KnuthAlgorithm knuth = new KnuthAlgorithm();
	
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
				int turnsLeft = 12;
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
					//continueGame = userGame.runTurn(set.toUpperCase());
					turnsLeft--;
				}
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
}