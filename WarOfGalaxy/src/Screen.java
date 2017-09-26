import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Screen extends JFrame {
	static final String title = "WarOfGalaxy";
	Image background;
	Image pointer;
	Point pointerPos;
	
	public Screen() {
		setTitle(title);
	}
	
	public void setBackground(String filename) {
		background = new ImageIcon(filename).getImage();
	}
	public void setPointer(String filename, Point pos) {
		pointer = new ImageIcon(filename).getImage();
		pointerPos = pos;
	}
}
