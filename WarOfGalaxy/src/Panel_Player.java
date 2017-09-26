import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

class Panel_Player extends JPanel {

	@Override
	public void paint(Graphics g) {
		if(Screen_Game.player != null)
			update(g);
	}
	
	@Override
	public void update(Graphics g) {
		Image drawingPaper = createImage(getWidth(), getHeight());
		Graphics gTemp = drawingPaper.getGraphics();

		gTemp.drawImage(Screen_Game.background_Player, 0, 0, this);

		Airplane_Player player = Screen_Game.player;
		gTemp.drawImage(player.img_airplane, player.x - player.half_width, player.y - player.half_height, this);
		
		Bullet_Major m = Screen_Game.majorBullet;
		gTemp.drawImage(m.img_bullet, (int)m.x - m.half_width, (int)m.y - m.half_height, this);
		
		for(Bullet_Minor b : Screen_Game.minorBullets)
			gTemp.drawImage(b.img_bullet, (int)b.x - b.half_width, (int)b.y - b.half_height, this);
		
		gTemp.setColor(Color.red);
		gTemp.setFont(new Font("±¼¸²", Font.BOLD, 15));
		gTemp.drawString("" + Screen_Game.stageNum, getWidth()-240, 50);
		
		gTemp.setColor(Color.yellow);
		gTemp.setFont(new Font("±¼¸²", Font.BOLD, 15));
		gTemp.drawString("" + (Screen_Game.minorBullets.size() + 1), getWidth()-80, 50);

		g.drawImage(drawingPaper, 0, 0, this);
	}
}
