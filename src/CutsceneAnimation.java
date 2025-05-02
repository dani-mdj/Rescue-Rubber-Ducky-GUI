
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CutsceneAnimation extends JPanel {
    private BufferedImage[] frames;
    private int currentFrame = 0;
    private final int FRAME_DELAY = 100; // Delay in milliseconds per frame
	private boolean animationFinished = false;
	private BufferedImage finalImage;

    public CutsceneAnimation() {
        loadFrames();

    }

    public void playScene() {
        Thread animationThread = new Thread(() -> {
            while (currentFrame < frames.length){
                nextFrame();
                repaint();
                try {
                    Thread.sleep(FRAME_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            animationFinished = true;
            repaint();
        });
        animationThread.start();
    }
    
    private void loadFrames() {
        frames = new BufferedImage[18]; // Adjust the number based on available images
        for (int i = 1; i < frames.length; i++) {
            try {
                frames[i] = ImageIO.read(new File("teleporterEnd" + i + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                finalImage = ImageIO.read(new File("teleporterEnd18.png")); // Static image after animation
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void nextFrame() {
        currentFrame = (currentFrame + 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (animationFinished && finalImage != null) {
            g.drawImage(finalImage, 0, 0, getWidth(), getHeight(), this);
        } else if (currentFrame<frames.length) {
            g.drawImage(frames[currentFrame], 0, 0, getWidth(), getHeight(), this);
        }
    }
}
