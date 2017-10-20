import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Simulation_forPlay extends JPanel {
	static final String background_AIFilename = "backgroundAI.png";

	static Image background_AI;

	static Airplane_AI_forPlay ai;
	static Bullet_Major_forPlay majorBullet;
	static LinkedList<Bullet_Minor> minorBullets;

	static LinkedList<Data> majorDataList;
	static LinkedList<Data> minorDataList;
	
	public Simulation_forPlay() {
		background_AI = new ImageIcon(background_AIFilename).getImage();
	}
	
	public void reset(Gene gene) {
		ai = new Airplane_AI_forPlay(this, gene.g);
		majorBullet = new Bullet_Major_forPlay(100, 10, ai.x, ai.y, 4, this);
		minorBullets = new LinkedList<>();

		majorDataList = (LinkedList<Data>) gene.majorDataList.clone();
		minorDataList = (LinkedList<Data>) gene.minorDataList.clone();

		repaint();
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

				repaint();
				Thread.sleep(15);
				
				if(ai.destroyed) {
					ai.img_airplane = new ImageIcon("bomb1.png").getImage();
					repaint();
					Thread.sleep(100);
					ai.img_airplane = new ImageIcon("bomb2.png").getImage();
					repaint();
					Thread.sleep(100);
					ai.img_airplane = new ImageIcon("bomb3.png").getImage();
					repaint();
					Thread.sleep(100);
					ai.img_airplane = new ImageIcon("bomb4.png").getImage();
					repaint();
					Thread.sleep(100);
					ai.img_airplane = new ImageIcon("bomb5.png").getImage();
					repaint();
					Thread.sleep(1000);
					break;
				}
			}
		}
		catch(Exception e) {
			
		}
	}
	
	@Override
	public void paint(Graphics g) {
		if(ai != null)
			update(g);
	}

	@Override
	public void update(Graphics g) {
		Image drawingPaper = createImage(getWidth(), getHeight());
		Graphics gTemp = drawingPaper.getGraphics();

		gTemp.drawImage(background_AI, 0, 0, this);

		gTemp.drawImage(ai.img_airplane, ai.x - ai.half_width, ai.y - ai.half_height, this);

		Bullet_Major_forPlay m = majorBullet;
		gTemp.drawImage(m.img_bullet, (int)m.x - m.half_width, (int)m.y - m.half_height, this);

		for(Bullet_Minor b : minorBullets)
			gTemp.drawImage(b.img_bullet, (int)b.x - b.half_width, (int)b.y - b.half_height, this);
		
		gTemp.setColor(Color.yellow);
		gTemp.setFont(new Font("±¼¸²", Font.BOLD, 15));
		gTemp.drawString("" + (minorBullets.size() + 1), getWidth()-80, 50);

		g.drawImage(drawingPaper, 0, 0, this);
	}
}
