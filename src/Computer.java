import java.util.ArrayList;
import java.util.Arrays;

public class Computer {

	private ArrayList<String> answers = new ArrayList<String>();
	private ArrayList<String> allAnswers = new ArrayList<String>();
	private String solution, guess;
	private ArrayList<String> goodGuesses = new ArrayList<String>();
	private int turns;
	int[] tmpResult;
	int[] result;
	
	public Computer(){
		
	}
	
	public int solvePuzzle(String solution){
		this.solution = solution;
		turns = 0;
		initializeAnswerArray();
		guess("1122");
		return(turns);
	}
	
	private void initializeAnswerArray(){
		answers.clear();
		for(int i = 1; i < 7; i++){
			for(int j = 1; j < 7; j++){
				for(int k = 1; k < 7; k++){
					for(int m = 1; m < 7; m++){
						answers.add((String.valueOf(i)+String.valueOf(j)+String.valueOf(k)+String.valueOf(m)));
					}
				}
			}
		}
		allAnswers = (ArrayList<String>) answers.clone();
	}
	
	private void guess(String guess){
		this.guess = guess;
		allAnswers.remove(allAnswers.indexOf(guess));
		turns++;
		//System.out.println("The computer guesses: " + guess + ".");
		result = checkAnswer.check(guess, solution);
		if(result[0] == 4){
			//System.out.println("Correct! Computer wins!");
			return;
		} else {
			//System.out.println("Incorrect. There were " + result[0] + " correctly coloured pegs in the right spots and " + result[1] + " correctly coloured pegs in the wrong spots");
		}
		if(answers.indexOf(guess) != -1){
			answers.remove(answers.indexOf(guess));
		}
		int i = 0;
		while(i < answers.size()){
			tmpResult = checkAnswer.check(answers.get(i),guess);
			if(Arrays.equals(result, tmpResult)){
				i++;
			} else {
				answers.remove(i);
			}
		}
		String next = getNextGuess();
		guess(next);
	}
	
	private String getNextGuess(){
		goodGuesses.clear();
		goodGuesses.add(answers.get(0));
		int maximin = 0, worstElim;
		for(String e : allAnswers){
			worstElim = checkElims(e);
			if(maximin < worstElim){
				maximin = worstElim;
				goodGuesses.clear();
				goodGuesses.add(e);
			}
			if(maximin == worstElim && worstElim > 0){
				goodGuesses.add(e);
			}
		}
		int i = 0;
		String tmpGuess = goodGuesses.get(0);
		while(i < goodGuesses.size()){
			tmpResult = checkAnswer.check(goodGuesses.get(i), this.guess);
			if(Arrays.equals(tmpResult, result)){
				i++;
			} else {
				goodGuesses.remove(i);
			}
		}
		if(goodGuesses.isEmpty()){
			return(tmpGuess);
		} else {
			return(goodGuesses.get(0));
		}
	}
	
	private int checkElims(String guess){
		int eliminated = 0, minimum = Integer.MAX_VALUE;
		int[] tmpResult, score;
		for(int j = 0; j < 5; j++){
			for(int k = 0; k + j < 5 && !(j == 3 && k == 1); k++){
				score = new int[] {j ,k};
				for(int i = 0; i < answers.size(); i++){
					tmpResult = checkAnswer.check(answers.get(i), guess);
					if(Arrays.equals(tmpResult, score)){
						eliminated++;
					}
				}
				minimum = Math.min(minimum, eliminated);
				eliminated = 0;
			}
		}
		return(minimum);
	}
	
}
