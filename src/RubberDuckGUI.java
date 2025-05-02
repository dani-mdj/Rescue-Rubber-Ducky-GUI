import hangmanInterface.IHangmanUI;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RubberDuckGUI implements ActionListener {
	private Hangman hang;
	private JFrame frame;
	JPanel panel1;
	JPanel panel2;
	JLayeredPane panelbg;
	private int width = 1000;
	private int height=1000;
	ArrayList <JButton> letters = new ArrayList <JButton>();
	char guess =' ';
	ArrayList <JLabel> word = new ArrayList<JLabel>();
	ImagePaint img;
	
	public RubberDuckGUI(Hangman hang) {
		this.hang=hang;
		createBoard();
		createKeys();
		createWord();
		//createBackground();
        frame.pack();
        frame.setVisible(true);
	}
	
	private void createWord() {
		for (char letter : hang.getWord()) {
			JLabel label = new JLabel();
			label.setText("   ");
			label.setFont(new Font("MV Boli",Font.PLAIN, 24));
			label.setBackground(Color.white);
			label.setOpaque(true);
			panel1.add(label);
			word.add(label);
		}
		
	}

	private void createBackground() {
		ImagePaint bgImg = new ImagePaint("teleporter_and_duck.png");
		bgImg.setBounds(0, 0, 1000, 800);
		
		panelbg.add(bgImg, JLayeredPane.DEFAULT_LAYER);
		
	}

	private void createKeys() {
		for (char letter : hang.getLetters()) {
			JButton start = new JButton(""+letter);
			start.setPreferredSize(new Dimension(50, 50));
	        start.setBackground(Color.gray);
	        start.addActionListener(this);
	        panel2.add(start);
	        letters.add(start);
		}
	}

	private void createBoard() {
		frame = new JFrame();
		frame.setSize(width, height);
		frame.setTitle("Save Rubber Ducky");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.black);
		
        panelbg = new JLayeredPane();
        panelbg.setPreferredSize(new Dimension(1000, 780));
        frame.setContentPane(panelbg);
        
        createBackground();
        
        panel1 = new JPanel(new FlowLayout());
        panel1.setBounds(300, 50, 400, 200);
        panel1.setOpaque(false);
        
        panel2 = new JPanel(new FlowLayout());
        panel2.setOpaque(false);
        panel2.setBounds(110, 650, 800, 200);
           
        this.img = new ImagePaint("C:\\Users\\dmdje\\Pictures\\Random Graphics\\star3.png");
        img.setAlpha(0);
        img.setBounds(350,400,600,600);
        
        panelbg.add(panel1, JLayeredPane.MODAL_LAYER);
        panelbg.add(panel2, JLayeredPane.MODAL_LAYER);
        panelbg.add(img, JLayeredPane.PALETTE_LAYER);

	}


	public void gameOver() {
		panelbg.removeAll();
		CutsceneAnimation scene = new CutsceneAnimation();
		panelbg.add(scene);
		scene.setBounds(0,0,1000,800);
		frame.repaint();
		scene.playScene();
		frame.setVisible(true);
		

		 JLabel label = new JLabel("<html><div style='text-align:center;'>Your favorite rubber duck has been<br>"
		 		+ "lost to the mysteries of physics.<br>Your password was: "
				 + new String(hang.getWord()) +"</div></html>", SwingConstants.CENTER);
		 
		 label.setBackground(Color.black);
		 label.setForeground(Color.lightGray);
		 label.setFont(new Font("Arial", Font.PLAIN, 36));
		 label.setHorizontalAlignment(SwingConstants.CENTER);
		 label.setOpaque(true);
		 label.setBounds(0, 600, 1000, 200);
		 panelbg.add(label, JLayeredPane.PALETTE_LAYER);
	}


	public void actionPerformed(ActionEvent e) {
		guess = e.getActionCommand().charAt(0);
		boolean isWinner = hang.doTurn(guess);
		if (isWinner) {
			celebrate();
			
		}else if (hang.gameOver()) {
			gameOver();
		}
		else {
			doturn();
			updateBoard();
		}
		
	}

	private void doturn() {
		img.setAlpha((float)(img.getAlpha()+0.09));

	}

	private void celebrate() {
		panelbg.removeAll();
		ImagePaint bgImg = new ImagePaint("duckysaved.png");
		bgImg.setBounds(0, 0, 1000, 800);
		JLabel label = new JLabel("<html><div style='text-align:center;'>Great work! Ducky is Saved!<br>"
		 		+ "Your password was: "
				 + new String(hang.getWord()) +"</div></html>", SwingConstants.CENTER);
			 
		label.setBackground(Color.black);
		label.setForeground(Color.lightGray);
		label.setFont(new Font("Arial", Font.PLAIN, 36));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setOpaque(true);
		label.setBounds(0, 600, 1000, 200);
		panelbg.add(label, JLayeredPane.PALETTE_LAYER);
		
		panelbg.add(bgImg, JLayeredPane.DEFAULT_LAYER);
	}
	
	private void updateBoard() {
		char[] theWord = hang.getWord();
		for (int i = 0 ; i< theWord.length; i++) {
			if (guess == theWord[i]) {
				word.get(i).setText(" "+ guess +" ");
			}
		}
		for (JButton button : letters) {
			if (button.getActionCommand().charAt(0) == guess) {
				button.setEnabled(false);
				button.setBackground(new Color(59,59,59));
				
			}
		}	
		frame.repaint();
	}
}
