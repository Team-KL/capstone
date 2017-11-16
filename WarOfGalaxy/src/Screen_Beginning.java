import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

class Screen_Beginning extends Screen {
	static final int width = 900;
	static final int height = 500;
	static final String backgroundFilename = "WarOfGalaxy.png";
	static final String pointerFilename = "pointer.png";
	
	Point startPos = new Point(330, 280);
	Point quitPos = new Point(330, 335);
	
	Clip clip;
	
	public Screen_Beginning() {
		setBackground(backgroundFilename);
		setPointer(pointerFilename, startPos);
		init();
		
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream("WarOfGalaxy_lounge.wav")));
			clip = AudioSystem.getClip();
			clip.open(ais);
			clip.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		setSize(width, height);
		
		Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();
		int mid_X = (int) (monitorSize.getWidth()/2 - getWidth()/2);
		int mid_Y = (int) (monitorSize.getHeight()/2 - getHeight()/2);
		setLocation(mid_X, mid_Y);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_UP){
					pointerPos = startPos;
					repaint();
				}
				else if(keyCode == KeyEvent.VK_DOWN){
					pointerPos = quitPos;
					repaint();
				}
				else if(keyCode == KeyEvent.VK_ENTER){
					if(pointerPos == startPos) {
						Thread t = new Thread(new Runnable() {
							public void run() {
								Main.makeGameScreen();
							}
						});
						t.start();
					}
					
					clip.stop();
					clip.close();
					
					dispose();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		if(background != null && pointer != null)
			update(g);
	}
	
	@Override
	public void update(Graphics g) {
		g.drawImage(background, 0, 0, this);
		g.drawImage(pointer, pointerPos.x, pointerPos.y, this);
	}
}
