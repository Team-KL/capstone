import javax.swing.JPanel;

class Bullet_Major_forPlay extends Bullet {
	static final String MajorBulletFilename = "chip_black.png";
	boolean useData;

	boolean generated;

	public Bullet_Major_forPlay(double x, double y, int aimX, int aimY, int speed, JPanel panel) {
		super(x, y, aimX, aimY, speed, MajorBulletFilename, panel);
		useData = false;
	}

	public void move() {
		x += modifying_X;
		y += modifying_Y;

		if(x < half_width || x > panel.getWidth() - half_width
				|| y < half_height || y > panel.getHeight() - half_height) {

			if(!generated) {
				Data data = Simulation_forPlay.majorDataList.removeFirst();
				setModifyingValue(data.aim.x, data.aim.y);
				speed = data.speed;
				
				makeMinors();
				useData = true;
			}
		}
		else
			generated = false;
	}

	public void makeMinors() {
		for(int i = 0; i < 3; i++) {
			Data data = Simulation_forPlay.minorDataList.removeFirst();

			Bullet_Minor b = new Bullet_Minor(x, y, data.aim.x, data.aim.y, data.speed, i, panel);

			Simulation_forPlay.minorBullets.add(b);
			generated = true;
		}
	}
}
