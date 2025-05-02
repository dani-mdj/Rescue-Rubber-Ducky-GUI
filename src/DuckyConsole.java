/*
 * Written by Danielle Jenson based on Hangman game
 * Ascii Art from AsciiArtArchives.com
 * 02-2025
 * This project was built for the sake of learning and fun, enjoy
 */
import java.util.Scanner;

import hangmanInterface.IHangmanUI;


/*
 * This class interacts with the console to act as a user interface for the game
 */
public class DuckyConsole implements IHangmanUI {
	Hangman hang;
	Scanner userInput = new Scanner(System.in);
	
	//constructor initializes hangman game
	public DuckyConsole(Hangman hangman) {
		hang = hangman;
	}
	
	//"draws" the interface to the console
	public void drawBoard() {
		System.out.print(imageString());
		System.out.println();
		drawCountDown();
		drawPassword();
		
		for (int i = 0; i < 73; i++) {
			System.out.print("_");
		}
		drawLetters();
	}
	
	//imagery for the console - ascii art from ascii art archives
	private String imageString() {
		
		String image = "                                                    \r\n"  
				+ "    ____              ____________                    _,,_______________\r\n"
				+ "   ||  ||     _      (__((__((___()                  //|                |\r\n"
				+ "   ||__||   >(.)__  (__((__((___()()________________// |  "
				+ String.format("Attemps: %2s",(11-hang.getTurns()))
				+ "   |\r\n"
				+ "   [ -=.]`)  (___/ (__((__((___()()()---------------'  |________________|\r\n"
				+ "   ====== 0 \r\n";
		
		return image;
		
	}
	
	private void drawCountDown() {
		System.out.printf("%s TRIES REMAINING\n", hang.getTurns());
	}
	
	//drawing letters that haven't been guessed yet
	private void drawLetters() {
		System.out.printf("\n\n%43s\n", "Available Letters");
		for (int i = 0; i < 73; i++) {
			System.out.print("_");
		}
		System.out.println("\n");
		char[] letters = hang.getLetters();
		for(int i = 0; i< letters.length; i++) {
			System.out.printf("%5s", letters[i]);
			if (i == 12) {
				System.out.println();
			}
			
		}
	}
	
	//drawing the word that player is guessing letters for
	private void drawPassword() {
		System.out.println("Enter Your Password To Stop Countdown:\n");
		char[] word = hang.getWord();
		
		for(char character : word) {
			for (char letter: hang.getLetters()) {
				if(letter == character) {
					System.out.print("___ ");
					break;
				}else if (letter == 'z'){
					System.out.printf(" %s  ",character);
				}
			}
	
		}
		System.out.println();
	}
	
	//asking the player for their guess
	//method only takes the first character entered and nothing else
	//if they don't guess a valid character it will keep asking
	//does not check if letter is already guessed 
	public char getInput() {
		char guess = 'A';
		System.out.println("\n\nGuess a letter in your password:");
		boolean notValidGuess = true;
		while (notValidGuess) {
			try {
				guess = userInput.next().charAt(0);
				userInput.nextLine();
				if(hang.validGuess(guess)) {
					notValidGuess = false;
				}
				if(!Character.isLetter(guess) ) {
					System.out.print("This password can only contain letters. Enter a letter!");
				}else {
					System.out.print("Already guessed that letter, try again");
				}
			}catch(Exception e) {
				System.out.println("invalid guess, guess a letter");
			}
		}
		return guess;
		
		
	}
	//method runs the player turn in console

	//celebrating victory with a message
	public void celebrate() {
		System.out.print("\nYOU DID IT\nTHE RUBBER DUCK IS SAVED\n\n");
	}
	//method makes an explosion or tree depending on what you see
	
	public void gameOver() {
		boom();
	}
	
	private void boom() {
		System.out.print("\n\n\n          _ ._  _ , _ ._\r\n"
				+ "        (_ ' ( `  )_  .__)\r\n"
				+ "      ( (  (    )   `)  ) _)\r\n"
				+ "     (__ (_   (_ . _) _) ,__)\r\n"
				+ "         `~~`\\ ' . /`~~`\r\n"
				+ "              ;   ;\r\n"
				+ "              /   \\\r\n"
				+ "_____________/_ __ \\_____________\n\n");
		
		System.out.printf("Your password was \n%s\n try not to forget your duck next time.\n\n", new String(hang.getWord()));
	}
	//introduction to the game
	public void introduction() {
		System.out.print("OH NO!\n"
				+ "You were so frustrated with your code you decided to blow up your computer!\n "
				+ "However you realized you left behind your favorite rubber duck *gasp*!\n\n"
				+ "You can't seem to remember the password to stop the explosion\n"
				+ "but you allowed 11 tries to guess the letters.\n\n"
				+ "You can do this! Save the Rubber Duck!\n\n"
				+ "GOOD LUCK\n\n");
		for(int i = 0; i<75; i++) {
			System.out.print("_");
		}
		System.out.println("\nHit enter to begin");
		userInput.nextLine();
	}
	//ask if the user would like to play again
	public boolean playAgain() {
		System.out.print("Want to play again? (y for yes, any key for quit)");
		String answer = userInput.nextLine();
		if (answer.equalsIgnoreCase("y")) {
			return true;
		}else {
			userInput.close();
			System.out.println("Thanks for playing!");
			System.out.println("Game by Danielle Jenson");
			return false;
		}
	}
	

	

}
