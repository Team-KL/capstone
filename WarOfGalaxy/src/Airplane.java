import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Airplane {
	static final String AirplaneFilename = "airplane.png";
	int x;
	int y;
	int speed;
	
	Image img_airplane;
	
	int width;
	int height;
	int half_width;
	int half_height;
	
	boolean destroyed;
	
	public Airplane(int x, int y, int speed, JPanel panel) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		
		img_airplane = new ImageIcon(AirplaneFilename).getImage();
		
		width = img_airplane.getWidth(panel);
		height = img_airplane.getHeight(panel);
		half_width = width / 2;
		half_height = height / 2;
		
		destroyed = false;
	}
}