import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

class Panel_AI extends JPanel {
	
	@Override
	public void paint(Graphics g) {
		if(Screen_Game.ai != null)
			update(g);
	}
	
	@Override
	public void update(Graphics g) {
		Image drawingPaper = createImage(getWidth(), getHeight());
		Graphics gTemp = drawingPaper.getGraphics();

		gTemp.drawImage(Screen_Game.background_AI, 0, 0, this);
		
		Airplane_AI ai = Screen_Game.ai;
		gTemp.drawImage(ai.img_airplane, ai.x - ai.half_width, ai.y - ai.half_height, this);
		
		Bullet_Major m = Screen_Game.majorBullet;
		gTemp.drawImage(m.img_bullet, (int)m.x - m.half_width, (int)m.y - m.half_height, this);
		
		for(Bullet_Minor b : Screen_Game.minorBullets)
			gTemp.drawImage(b.img_bullet, (int)b.x - b.half_width, (int)b.y - b.half_height, this);

		g.drawImage(drawingPaper, 0, 0, this);
	}
}
