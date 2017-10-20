import java.awt.Point;

import javax.swing.JPanel;

class Airplane_AI_forLearning extends Airplane{
	JPanel panel;
	
	int[] g;
	final int GENE_ELEMENT_NUM = 16;
	final int RADAR_NUM = 4;
	
	boolean key_LEFT;
	boolean key_RIGHT;
	boolean key_UP;
	boolean key_DOWN;
	
	boolean[] nearState = new boolean[RADAR_NUM];
	
	public Airplane_AI_forLearning(JPanel panel, int[] g) {
		super((int)(panel.getWidth() * 1.0/2), (int)(panel.getHeight() * 2.0/3), 4, panel);
		this.panel = panel;
		
		this.g = g;
	}
	
	public void move() {
		useRadar();
		control();
		
		if(key_LEFT && x > half_width + speed)
			x -= speed;
		if(key_RIGHT && x < panel.getWidth() - (half_width + speed))
			x += speed;
		if(key_UP && y > half_height + speed)
			y -= speed;
		if(key_DOWN && y < panel.getHeight() - (half_height + speed))
			y += speed;
	}
	
	public void useRadar() {
		Point[] points = new Point[4];
		points[0] = new Point(x - half_width, y - half_height);
		points[1] = new Point(x + half_width, y - half_height);
		points[2] = new Point(x + half_width, y + half_height);
		points[3] = new Point(x - half_width, y + half_height);

		for(int i = 0; i < nearState.length; i++)
			nearState[i] = false;

		double distance;
		for(int i = 0; i < points.length; i++) {
			double c = Math.pow(points[i].x - Simulation_forLearning.majorBullet.x, 2);
			double d = Math.pow(points[i].y - Simulation_forLearning.majorBullet.y, 2);
			distance = Math.sqrt(c + d);
			if(distance < width) {
				nearState[i] = true;
				continue;
			}
			
			for(Bullet b : Simulation_forLearning.minorBullets) {
				distance = Math.sqrt(Math.pow(points[i].x - b.x, 2) + Math.pow(points[i].y - b.y, 2));
				if(distance < width) {
					nearState[i] = true;
					break;
				}
			}
		}		
	}
	
	public void control() {
		key_LEFT = false;
		key_RIGHT = false;
		key_UP = false;
		key_DOWN = false;

		int index_L = 0;
		int index_R = GENE_ELEMENT_NUM - 1;
		for(int i = 0; i < RADAR_NUM; i++) {
			if(nearState[i])
				index_R = (index_L + index_R) / 2;
			else
				index_L = (index_L + index_R) / 2 + 1;
		}

		int action;
		if(allFalse())
			action = 0;
		else
			action = g[index_L];
		
		switch(action) {
			case 0: break;
			case 1: key_UP = true; break;
			case 2: key_UP = key_RIGHT = true; break;
			case 3: key_RIGHT = true; break;
			case 4: key_RIGHT = key_DOWN = true; break;
			case 5: key_DOWN = true; break;
			case 6: key_DOWN = key_LEFT = true; break;
			case 7: key_LEFT = true; break;
			case 8: key_LEFT = key_UP = true; break;
		}
	}
	
	public boolean allFalse() {
		for(int i = 0; i < RADAR_NUM; i++)
			if(nearState[i])
				return false;
		return true;
	}
	
}
