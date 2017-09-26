import javax.swing.JPanel;

class Bullet_Major extends Bullet {
	static final String MajorBulletFilename = "chip_black.png";
	boolean useData;

	boolean generated;

	public Bullet_Major(double x, double y, int aimX, int aimY, int speed, JPanel panel) {
		super(x, y, aimX, aimY, speed, MajorBulletFilename, panel);
		useData = false;
	}

	public void move() {
		x += modifying_X;
		y += modifying_Y;

		if(x < half_width || x > panel.getWidth() - half_width
				|| y < half_height || y > panel.getHeight() - half_height) {

			if(!generated) {
				speed = (int)(Math.random() * 3) + 2;
				setModifyingValue(Screen_Game.player.x, Screen_Game.player.y);
				makeMinors();
				useData = true;
			}
		}
		else
			generated = false;
	}

	public void makeMinors() {
		for(int i = 0; i < 3; i++) {
			double randomX = Math.random() * panel.getWidth();
			double randomY = Math.random() * panel.getHeight();
			int speed = (int)(Math.random() * 3) + 2;

			Bullet_Minor b = new Bullet_Minor(x, y, (int)randomX, (int)randomY, speed, i, panel);

			Screen_Game.minorBullets.add(b);
			generated = true;
		}
	}
}
