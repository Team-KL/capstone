import javax.swing.JPanel;

class Airplane_Player extends Airplane {
	JPanel panel;
	
	public Airplane_Player(JPanel panel) {
		super((int)(panel.getWidth() * 1.0/2), (int)(panel.getHeight() * 2.0/3), 4, panel);
		this.panel = panel;
	}
	
	public void move() {
		if(Screen_Game.key_LEFT && x > half_width + speed)
			x -= speed;
		if(Screen_Game.key_RIGHT && x < panel.getWidth() - (half_width + speed))
			x += speed;
		if(Screen_Game.key_UP && y > half_height + speed)
			y -= speed;
		if(Screen_Game.key_DOWN && y < panel.getHeight() - (half_height + speed))
			y += speed;
	}
}
