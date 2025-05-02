import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
public class ImagePaint extends JLabel{
	BufferedImage image;
	float alphaNum = 1;
	
	public ImagePaint(String fileName) {
        try {
            // Load the local image file
            File imageFile = new File(fileName); // Replace with the path to your image
            image = ImageIO.read(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaNum);
		g2d.setComposite(ac);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.drawImage(image, 0, 0, this);
		
	}
	
	public void setAlpha(float num) {
		this.alphaNum = num;
	}
	public float getAlpha() {
		return this.alphaNum;
	}

}
