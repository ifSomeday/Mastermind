package Mastermind;


import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;



public class Mastermind {
	static KnuthAlgorithm knuth = new KnuthAlgorithm();
	static int COMPUTER_PLAYS = 1, USER_PLAYS = 2, QUIT = 3;

	Set set;
	
	public void play() {
		
		Scanner reader = new Scanner(System.in); // Reading from System.in
		
		//Main program loop
		while(true) {
			//main menu2
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
			if (choice == COMPUTER_PLAYS) {
				System.out.println("Enter a string of 4 characters, each being the first letter of a color from:");
				System.out.println("Red, Orange, Green, Yellow, Blue, Purple");

				set = getSet();
				State gs = new State(set);
				knuth.solve(set.pegs);
			} 
			
			//start game where computer picks colors
			else if (choice == USER_PLAYS) {
				State gs = new State(getRandomSet());
				System.out.println("Enter a guess as a string of 4 characters, each being the first letter of a color from:");
				System.out.println("Red, Orange, Green, Yellow, Blue, Purple");

				while (gs.turnsLeft() > 0) {
					System.out.println("You have "+gs.turnsLeft()+" turns left");
					set = getSet();
					set.Matches(gs.solution);
					gs.nextTurn();
				}
			} 
			
			//exit program
			else if (choice == QUIT) {
				System.out.println("Goodbye!");
				System.exit(0);
			} 
			
			//handles incorrect menu selection
			else {
				System.out.println("This is an invalid input, please try again");
			}
		}
	}

	public Set getSet() {
		Scanner reader = new Scanner(System.in); // Reading from System.in
		String input;
		boolean validSet = false;
		while (true) {
			input = reader.nextLine();
			if (isValidSet(input)) {
				Set set = new Set(input.toUpperCase());
				return set;
			} else {
				System.out.println("This is not a valid set of characters, please try again");
			}
		}
	}
	
	//checks sets to see if they are in the correct format
	public static boolean isValidSet(String set) {
		return (set.length()==4 & set.toUpperCase().matches("[ROGYBP]*"));
	}
	
	public static Set getRandomSet(){
		Random rand = new Random();
		String letters = new String();
		String numbers = String.valueOf(rand.nextInt(6)+1) + String.valueOf(rand.nextInt(6)+1) +
				String.valueOf(rand.nextInt(6)+1) + String.valueOf(rand.nextInt(6)+1);

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

		Set set = new Set(letters.toUpperCase());
		return set;
	}
}