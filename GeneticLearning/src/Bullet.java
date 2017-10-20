import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Bullet {
	double x;
	double y;
	double modifying_X;
	double modifying_Y;
	int speed;

	Image img_bullet;

	int width;
	int height;
	int half_width;
	int half_height;
	
	JPanel panel;
	
	public Bullet(double x, double y, int aimX, int aimY, int speed, String filename, JPanel panel) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		setModifyingValue(aimX, aimY);

		img_bullet = new ImageIcon(filename).getImage();

		width = img_bullet.getWidth(panel);
		height = img_bullet.getHeight(panel);
		half_width = width / 2;
		half_height = height / 2;
		
		this.panel = panel;
	}
	
	public void setModifyingValue(int aimX, int aimY) {
		double a = aimX - x;
		double b = aimY - y;

		double ratio = Math.sqrt(a*a + b*b) / speed;
		
		modifying_X = a/ratio;
		modifying_Y = b/ratio;
	}
	
	public void checkCollision(Airplane airplane, int collisionRange) {
		double a = Math.pow(airplane.x - x, 2);
		double b = Math.pow(airplane.y - y, 2);
		double distance = Math.sqrt(a + b);

		if(distance < collisionRange)
			airplane.destroyed = true;
	}
}
