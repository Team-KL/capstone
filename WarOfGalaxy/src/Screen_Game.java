import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Screen_Game extends Screen {
	static final int width = 1000;
	static final int height = 400;
	static final String background_AIFilename = "backgroundAI.png";
	static final String background_PlayerFilename = "backgroundPlayer.png";
	
	static JPanel panel_AI;
	static Image background_AI;
	static JPanel panel_Player;
	static Image background_Player;
	
	static Airplane_AI ai;
	static Airplane_Player player;
	static Bullet_Major majorBullet;
	static LinkedList<Bullet_Minor> minorBullets;
	
	static boolean key_LEFT;
	static boolean key_RIGHT;
	static boolean key_UP;
	static boolean key_DOWN;
	
	static int stageNum = 1;
	LinkedList<Data> majorDataList;
	LinkedList<Data> minorDataList;
	
	public Screen_Game() {
		background_AI = new ImageIcon(background_AIFilename).getImage();
		background_Player = new ImageIcon(background_PlayerFilename).getImage();
		init();
		
		key_LEFT = false;
		key_RIGHT = false;
		key_UP = false;
		key_DOWN = false;
		
		ai = new Airplane_AI(panel_AI);
		player = new Airplane_Player(panel_Player);
		majorBullet = new Bullet_Major(100, 10, player.x, player.y, 4, panel_AI);
		minorBullets = new LinkedList<>();
		
		panel_AI.repaint();
		panel_Player.repaint();
	}
	
	public void init() {
		setSize(width+6, height+29);
		setLayout(new GridLayout(1, 2));
		
		Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();
		int mid_X = (int) (monitorSize.getWidth()/2 - getWidth()/2);
		int mid_Y = (int) (monitorSize.getHeight()/2 - getHeight()/2);
		setLocation(mid_X, mid_Y);
		
		panel_AI = new Panel_AI();
		panel_AI.setSize(width/2, height);
		panel_Player = new Panel_Player();
		panel_Player.setSize(width/2, height);
		add(panel_AI);
		add(panel_Player);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				
				if(keyCode == KeyEvent.VK_LEFT)
					key_LEFT = true;
				else if(keyCode == KeyEvent.VK_RIGHT)
					key_RIGHT = true;
				else if(keyCode == KeyEvent.VK_UP)
					key_UP = true;
				else if(keyCode == KeyEvent.VK_DOWN)
					key_DOWN = true;
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				
				if(keyCode == KeyEvent.VK_LEFT)
					key_LEFT = false;
				else if(keyCode == KeyEvent.VK_RIGHT)
					key_RIGHT = false;
				else if(keyCode == KeyEvent.VK_UP)
					key_UP = false;
				else if(keyCode == KeyEvent.VK_DOWN)
					key_DOWN = false;
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
		});
	}
	
	public void run() {
		try {
			while(true) {
				player.move();
				ai.move();
				
				majorBullet.move();
				majorBullet.checkCollision(player, player.width/2 - 2);
				majorBullet.checkCollision(ai, ai.width/2 - 2);
				
				for(Bullet_Minor b : minorBullets) {
					b.move();
					b.checkCollision(player, player.width/2 - 2);
					b.checkCollision(ai, ai.half_width/2 - 2);
				}

				if(player.destroyed)	// GameOver
					break;
				if(ai.destroyed)		// NextStage or WIN
					break;

				panel_AI.repaint();
				panel_Player.repaint();
				Thread.sleep(15);
			}
			
			if(player.destroyed) {
				Thread t = new Thread(new Runnable() {
					public void run() {
						Main.makeGameOverScreen();
					}
				});
				t.start();
				dispose();
			}

		}
		catch(Exception e) {

		}
	}
	
}
