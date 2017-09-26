import javax.swing.JPanel;

class Bullet_Minor extends Bullet {
	static final String[] MinorBulletFilename = {"chip_yellow.png", "chip_red.png", "chip_blue.png"};

	public Bullet_Minor(double x, double y, int aimX, int aimY, int speed, int fileNum, JPanel panel) {
		super(x, y, aimX, aimY, speed, MinorBulletFilename[fileNum], panel);
	}

	public void move() {
		x += modifying_X;
		y += modifying_Y;

		if(x < half_width || x > panel.getWidth() - half_width
				|| y < half_height || y > panel.getHeight() - half_height) {

			if(x < 0 || x > panel.getWidth())
				modifying_X *= -1;
			if(y < 0 || y > panel.getHeight())
				modifying_Y *= -1;
		}
	}

}
