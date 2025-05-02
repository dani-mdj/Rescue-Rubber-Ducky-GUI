package hangmanInterface;

public interface IHangmanUI {
	abstract void drawBoard();
	void introduction();
	boolean playAgain();
	void celebrate();
	void gameOver();
	char getInput();

}
