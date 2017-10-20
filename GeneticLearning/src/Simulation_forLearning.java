import java.awt.Image;
import java.util.LinkedList;

import javax.swing.JPanel;

public class Simulation_forLearning extends JPanel {
	static Image background_AI;

	static Airplane_AI_forLearning ai;
	static Bullet_Major majorBullet;
	static LinkedList<Bullet_Minor> minorBullets;

	static LinkedList<Data> majorDataList;
	static LinkedList<Data> minorDataList;

	public Simulation_forLearning() {
		setSize(500, 400);
	}

	public void reset(Gene gene) {
		ai = new Airplane_AI_forLearning(this, gene.g);
		majorBullet = new Bullet_Major(100, 10, ai.x, ai.y, 4, this);
		minorBullets = new LinkedList<>();

		majorDataList = new LinkedList<>();
		minorDataList = new LinkedList<>();
	}

	public void run() {
		try {
			while(true) {
				ai.move();

				majorBullet.move();
				majorBullet.checkCollision(ai, ai.width/2 - 4);

				for(Bullet_Minor b : minorBullets) {
					b.move();
					b.checkCollision(ai, ai.half_width/2 - 3);
				}
				if(ai.destroyed)
					break;
				
			}
			
		}
		catch(Exception e) {
			
		}
	}
	
}
