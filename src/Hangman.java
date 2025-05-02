/*
 * Written by Danielle Jenson based on Hangman game
 * Ascii Art from AsciiArtArchives.com
 * 02-2025
 * This project was built for the sake of learning and fun, enjoy
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//this class is the background guts to the hangman game
//the things that are not interacting with the user go here
//list of words is from powerlanguage on github
//https://github.com/powerlanguage/word-lists/blob/master/common-7-letter-words.txt
public class Hangman {
	private char[] word;
	private char[] letters = {'a', 'b', 'c', 'd',
			'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
			'w', 'x', 'y', 'z'};
	private String correctGuess = "";
	private char[] guessedLetters = new char[11];
	int turns = 11;
	int win;
	
	//constructor of the game - chooses the word from a bank of words
	public Hangman() {
		word = chooseWord();
		setWinCondition();
		System.out.print(word);	
		}
	
	//getter and setters for items that require
//--------------------------------------------
	
	public char[] getWord() {
		return word;
	}

	public char[] getLetters() {
		return letters;
	}

	public int getTurns() {
		return turns;
	}
//-------------------------------------------------------
//method generates a random number in the range of number of words in the file
	private char[] chooseWord() {
		int wordNumber = (int)(Math.random()*1371)+1;
		char[] word = getWord(wordNumber);
		return word;
	}
	//retrieves the word from the file from given line number
	private char[] getWord(int line) {
		String wordSt = "";
		try(BufferedReader file = new BufferedReader(new FileReader (".\\words.txt"))) {
			for (int i = 0; i < line; i++) {
				file.readLine();
			}
			wordSt = file.readLine();
			file.close();
			
		}
		catch(IOException e) {}
		
		return wordSt.toLowerCase().toCharArray();
	}
	//determines if the guess is in the word or not 
	//and removes letter from available letters
	private void getResult(char guess) {
		for (int j = 0 ; j<word.length ; j++) {
			if (word[j] == guess && !(correctGuess.contains(""+guess))) {
				correctGuess += 1;
			}
		}
		for (int i = 0 ; i < letters.length; i++) {
			if(guess == letters[i]) {
				letters[i] = ' ';
				guessedLetters[turns-1] = guess;
			}
		}
	}
	//back end of a turn - checks the guess then increments turn and checks for winner
	public boolean doTurn(char guess) {
		getResult(guess);
		turns-= 1;
		return checkForWinner();
	}
	//determines if theres a winner by seeing if the number 
	//of correct guesses matches the number of unique letters in the word
	private boolean checkForWinner() {
		if (correctGuess.length() == win) {
			return true;
		}else {
			return false;
		}

	}
	//sets the win condition to be an array of unique letters in the word
	private void setWinCondition() {
		String allLetters = "";
		for (char letter : word) {
			if (!(correctGuess.contains(""+letter))){
				allLetters += ""+letter;
			}
		}
		win = allLetters.length();
	}
	//checks if the game is lost by running out of turns
	public boolean gameOver() {
		if (turns<1) {
			return true;
		}else {
			return false;
		}		
	}
	
	public boolean validGuess(char guess) {
		boolean notGuessed = false;		
		if ((new String(guessedLetters)).contains(""+guess)) {
			notGuessed = false;
		}else {
			notGuessed = true;
		}	
		
		return notGuessed;
	}
	
}
