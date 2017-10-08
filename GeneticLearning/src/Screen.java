import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Screen extends JFrame {
	static final String title = "";
	
	JPanel listPanel;
	JPanel graphPanel;
	JPanel stagePanel;
	
	JTextField textField_EVOLUTION_NUM;
	JTextField textField_ENTITY_NUM;
	JTextField textField_CROSSOVER_RATIO;
	JTextField textField_MUTATION_RATIO;
	JTextField textField_MUTATION_EFFECT;
	
	public Screen() {
		setTitle(title);
		setSize(740, 820);
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
		learningPanel.setBounds(15, 10, 700, 300);
		learningPanel.setBorder(new TitledBorder(new LineBorder(new Color(200, 200, 200)), "학습"));
		learningPanel.setLayout(null);
		
		setListPanel(learningPanel);
		setDataField(learningPanel);
		setLearningButton(learningPanel);
		setGraphPanel(learningPanel);
		
		add(learningPanel);
	}
	private void setListPanel(JPanel learningPanel) {
		listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(listPanel,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(15, 30, 145, 240);
		scrollPane.setBorder(new TitledBorder(new LineBorder(new Color(200, 200, 200)), "학습 결과"));
		
		learningPanel.add(scrollPane);
	}
	private void setDataField(JPanel learningPanel) {
		JPanel panel_EVOLUTION_NUM = new JPanel();
		panel_EVOLUTION_NUM.setBounds(175, 40, 180, 25);
		panel_EVOLUTION_NUM.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label_EVOLUTION_NUM_1 = new JLabel("세대 수 :  ");
		panel_EVOLUTION_NUM.add(label_EVOLUTION_NUM_1);
		textField_EVOLUTION_NUM = new JTextField();
		textField_EVOLUTION_NUM.setPreferredSize(new Dimension(40, 18));
		panel_EVOLUTION_NUM.add(textField_EVOLUTION_NUM);
		JLabel label_EVOLUTION_NUM_2 = new JLabel("세대");
		panel_EVOLUTION_NUM.add(label_EVOLUTION_NUM_2);
		
		JPanel panel_ENTITY_NUM = new JPanel();
		panel_ENTITY_NUM.setBounds(175, 70, 180, 25);
		panel_ENTITY_NUM.setLayout(new FlowLayout(FlowLayout.LEFT));		
		JLabel label_ENTITY_NUM_1 = new JLabel("세대별 개체 수 :  ");
		panel_ENTITY_NUM.add(label_ENTITY_NUM_1);
		textField_ENTITY_NUM = new JTextField();
		textField_ENTITY_NUM.setPreferredSize(new Dimension(40, 18));
		panel_ENTITY_NUM.add(textField_ENTITY_NUM);
		JLabel label_ENTITY_NUM_2 = new JLabel("개");
		panel_ENTITY_NUM.add(label_ENTITY_NUM_2);
		
		JPanel panel_CROSSOVER_RATIO = new JPanel();
		panel_CROSSOVER_RATIO.setBounds(175, 100, 180, 25);
		panel_CROSSOVER_RATIO.setLayout(new FlowLayout(FlowLayout.LEFT));		
		JLabel label_CROSSOVER_RATIO_1 = new JLabel("크로스오버 확률 :  ");
		panel_CROSSOVER_RATIO.add(label_CROSSOVER_RATIO_1);
		textField_CROSSOVER_RATIO = new JTextField();
		textField_CROSSOVER_RATIO.setPreferredSize(new Dimension(40, 18));
		panel_CROSSOVER_RATIO.add(textField_CROSSOVER_RATIO);		
		JLabel label_CROSSOVER_RATIO_2 = new JLabel("%");
		panel_CROSSOVER_RATIO.add(label_CROSSOVER_RATIO_2);
		
		JPanel panel_MUTATION_RATIO = new JPanel();
		panel_MUTATION_RATIO.setBounds(175, 130, 180, 25);
		panel_MUTATION_RATIO.setLayout(new FlowLayout(FlowLayout.LEFT));		
		JLabel label_MUTATION_RATIO_1 = new JLabel("돌연변이 확률 :  ");
		panel_MUTATION_RATIO.add(label_MUTATION_RATIO_1);
		textField_MUTATION_RATIO = new JTextField();
		textField_MUTATION_RATIO.setPreferredSize(new Dimension(40, 18));
		panel_MUTATION_RATIO.add(textField_MUTATION_RATIO);		
		JLabel label_MUTATION_RATIO_2 = new JLabel("%");
		panel_MUTATION_RATIO.add(label_MUTATION_RATIO_2);
		
		JPanel panel_MUTATION_EFFECT = new JPanel();
		panel_MUTATION_EFFECT.setBounds(175, 160, 200, 25);
		panel_MUTATION_EFFECT.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label_MUTATION_EFFECT_1 = new JLabel("돌연변이 영향력 :  ");
		panel_MUTATION_EFFECT.add(label_MUTATION_EFFECT_1);
		textField_MUTATION_EFFECT = new JTextField();
		textField_MUTATION_EFFECT.setPreferredSize(new Dimension(40, 18));
		panel_MUTATION_EFFECT.add(textField_MUTATION_EFFECT);		
		JLabel label_MUTATION_EFFECT_2 = new JLabel("(1~16)");
		panel_MUTATION_EFFECT.add(label_MUTATION_EFFECT_2);

		
		learningPanel.add(panel_EVOLUTION_NUM);
		learningPanel.add(panel_ENTITY_NUM);
		learningPanel.add(panel_CROSSOVER_RATIO);
		learningPanel.add(panel_MUTATION_RATIO);
		learningPanel.add(panel_MUTATION_EFFECT);
	}
	private void setLearningButton(JPanel learningPanel) {
		JPanel panel_resetButton = new JPanel();
		panel_resetButton.setBounds(180, 192, 180, 40);
		JButton resetButton = new JButton("             초기화            ");
		resetButton.setFocusPainted(false);
		
		JPanel panel_startButton = new JPanel();
		panel_startButton.setBounds(180, 232, 180, 40);
		JButton startButton = new JButton("          학습 시작          ");
		startButton.setFocusPainted(false);
		
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				if(action.getSource().equals(resetButton)) {
					listPanel.removeAll();
					listPanel.revalidate();
					listPanel.repaint();
					
					textField_EVOLUTION_NUM.setText("");
					textField_ENTITY_NUM.setText("");
					textField_CROSSOVER_RATIO.setText("");
					textField_MUTATION_RATIO.setText("");
					textField_MUTATION_EFFECT.setText("");
					
					graphPanel.repaint();
					
					stagePanel.removeAll();
					stagePanel.revalidate();
					stagePanel.repaint();
					
					startButton.setText("          학습 시작          ");
					startButton.setEnabled(true);
				}
			}
		});
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				if(action.getSource().equals(startButton)) {
					startButton.setText("               완료               ");
					startButton.setEnabled(false);
				}
			}
		});
		
		panel_resetButton.add(resetButton);
		panel_startButton.add(startButton);
		
		learningPanel.add(panel_resetButton);
		learningPanel.add(panel_startButton);
	}
	private void setGraphPanel(JPanel learningPanel) {
		graphPanel = new JPanel() {
			public void paint(Graphics g) {
				super.paint(g);
				g.setColor(Color.black);
				g.drawLine(15, graphPanel.getHeight()-20, graphPanel.getWidth()-20, graphPanel.getHeight()-20);
				g.drawLine(graphPanel.getWidth()-25, graphPanel.getHeight()-25,
						graphPanel.getWidth()-20, graphPanel.getHeight()-20);
				g.drawLine(graphPanel.getWidth()-25, graphPanel.getHeight()-15,
						graphPanel.getWidth()-20, graphPanel.getHeight()-20);
				g.drawLine(20, 25, 20, graphPanel.getHeight()-15);
				g.drawLine(20, 25, 15, 30);
				g.drawLine(20, 25, 25, 30);
				g.drawString("Score", 30, 35);
				g.drawString("세대", graphPanel.getWidth()-45, graphPanel.getHeight()-30);
			}
		};
		graphPanel.setBounds(390, 30, 290, 240);
		graphPanel.setBorder(new TitledBorder(new LineBorder(new Color(200, 200, 200)), "그래프"));
		
		learningPanel.add(graphPanel);
	}
	
	public void setSettingPanel() {
		JPanel settingPanel = new JPanel();
		settingPanel.setBounds(15, 320, 700, 450);
		settingPanel.setBorder(new TitledBorder(new LineBorder(new Color(200, 200, 200)), "스테이지 설정"));
		settingPanel.setLayout(null);
		
		setPreviewPanel(settingPanel);
		setStagePanel(settingPanel);
		setSettingButton(settingPanel);
		
		add(settingPanel);
	}
	private void setPreviewPanel(JPanel settingPanel) {
		JPanel previewPanel = new JPanel();
		previewPanel.setBounds(15, 30, 500, 400);
		previewPanel.setBorder(new LineBorder(new Color(200, 200, 200)));
		previewPanel.setLayout(null);
		previewPanel.setBackground(new Color(30, 30, 30));
		
		JButton playButton = new JButton();
		playButton.setBounds(220, 170, 60, 60);
		playButton.setContentAreaFilled(false);
		playButton.setBorder(null);
		playButton.setIcon(new ImageIcon("playButton.png"));
		playButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				playButton.setIcon(new ImageIcon("playButton_over.png"));
			}
			public void mouseExited(MouseEvent e) {
				playButton.setIcon(new ImageIcon("playButton.png"));
			}
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		
		previewPanel.add(playButton);
		
		settingPanel.add(previewPanel);
	}
	private void setStagePanel(JPanel settingPanel) {
		stagePanel = new JPanel();
		stagePanel.setBounds(530, 30, 150, 310);
		stagePanel.setBorder(new TitledBorder(new LineBorder(new Color(200, 200, 200)), "스테이지"));
		stagePanel.setLayout(new GridLayout(10, 1));
		
		settingPanel.add(stagePanel);
	}
	private void setSettingButton(JPanel settingPanel) {
		JPanel panel_changeButton = new JPanel();
		panel_changeButton.setBounds(530, 335, 150, 40);
		JButton changeButton = new JButton("              변경               ");
		changeButton.setFocusPainted(false);
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				if(action.getSource().equals(changeButton)) {
					JPanel changePanel = new JPanel();
					changePanel.setLayout(new GridLayout(100, 1));
					
					JScrollPane scrollPane = new JScrollPane(changePanel,
							JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					
					JDialog dialog = new JDialog();
					dialog.setTitle("선택하세요");
					dialog.setSize(170, 400);
					dialog.setLocation(getLocation().x + 700, getLocation().y + 360);
					
					dialog.add(scrollPane);					
					dialog.setVisible(true);
				}
			}
		});
		panel_changeButton.add(changeButton);
		
		JPanel panel_saveButton = new JPanel();
		panel_saveButton.setBounds(530, 395, 150, 40);
		JButton saveButton = new JButton("              저장               ");
		saveButton.setFocusPainted(false);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent action) {
				if(action.getSource().equals(saveButton)) {
					
				}
			}
		});
		panel_saveButton.add(saveButton);
		
		settingPanel.add(panel_changeButton);
		settingPanel.add(panel_saveButton);
	}
}
