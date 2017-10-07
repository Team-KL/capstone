import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Screen extends JFrame {
	static final String title = "";
	
	public Screen() {
		setTitle(title);
		setSize(700, 800);
		Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();
		int mid_X = (int) (monitorSize.getWidth()/2 - getWidth()/2);
		int mid_Y = (int) (monitorSize.getHeight()/2 - getHeight()/2);
		setLocation(mid_X, mid_Y);
		setLayout(null);
		
		setLearningPanel();
		setSettingPanel();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void setLearningPanel() {
		JPanel learningPanel = new JPanel();
		learningPanel.setBounds(15, 10, 660, 300);
		learningPanel.setBorder(new TitledBorder(new LineBorder(new Color(200, 200, 200)), "학습"));
		
		add(learningPanel);
	}
	
	public void setSettingPanel() {
		JPanel settingPanel = new JPanel();
		settingPanel.setBounds(15, 320, 660, 430);
		settingPanel.setBorder(new TitledBorder(new LineBorder(new Color(200, 200, 200)), "스테이지 설정"));
		
		add(settingPanel);
	}
}
