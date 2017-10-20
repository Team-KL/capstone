import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedList;

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
	
	static JPanel listPanel;
	static JPanel graphPanel;
	JPanel stagePanel;
	
	static LinkedList<Point> dots = new LinkedList<>();
	
	JTextField textField_EVOLUTION_NUM;
	JTextField textField_ENTITY_NUM;
	JTextField textField_CROSSOVER_RATIO;
	JTextField textField_MUTATION_RATIO;
	JTextField textField_MUTATION_EFFECT;
	
	JButton selectedButton;
	
	static LinkedList<Gene> candidate = new LinkedList<>();
	
	Thread t;
	
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
		textField_EVOLUTION_NUM.setText("100");
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
		textField_ENTITY_NUM.setText("100");
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
		textField_CROSSOVER_RATIO.setText("100");
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
		textField_MUTATION_RATIO.setText("3");
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
		textField_MUTATION_EFFECT.setText("3");
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
					t.stop();
					
					selectedButton = null;
					
					listPanel.removeAll();
					listPanel.revalidate();
					listPanel.repaint();
					
					textField_EVOLUTION_NUM.setText("100");
					textField_ENTITY_NUM.setText("100");
					textField_CROSSOVER_RATIO.setText("100");
					textField_MUTATION_RATIO.setText("3");
					textField_MUTATION_EFFECT.setText("3");
					
					dots.clear();
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
					
					dots.clear();
					graphPanel.repaint();
					
					int EVOLUTION_NUM = Integer.parseInt(textField_EVOLUTION_NUM.getText());
					int ENTITY_NUM = Integer.parseInt(textField_ENTITY_NUM.getText());
					double CROSSOVER_RATIO = Integer.parseInt(textField_CROSSOVER_RATIO.getText()) / 100.0;
					double MUTATION_RATIO = Integer.parseInt(textField_MUTATION_RATIO.getText()) / 100.0;
					int MUTATION_EFFECT = Integer.parseInt(textField_MUTATION_EFFECT.getText());
					
					candidate = new LinkedList<>();
					
					try {
						t = new Thread(new Runnable() {
							public void run() {
								startButton.setEnabled(false);
								startButton.setText("             학습 중            ");
								
								new Learning(EVOLUTION_NUM, ENTITY_NUM, CROSSOVER_RATIO, MUTATION_RATIO, MUTATION_EFFECT);
								
								LinkedList<Gene> copy_candidate = (LinkedList<Gene>) candidate.clone();
								copy_candidate.sort(new Comparator<Gene>() {
									public int compare(Gene g1, Gene g2) {
										return g1.score - g2.score;
									}
								});
								
								for(int i = 0; i <= 9; i++) {
									JButton button = new JButton();
									button.setFocusPainted(false);
									button.setText(copy_candidate.get(i*EVOLUTION_NUM/10).index + "세대 Score: " + copy_candidate.get(i*EVOLUTION_NUM/10).score);
									button.setBackground(new Color(220, 220, 220));
									button.addMouseListener(new MouseAdapter() {
										public void mouseClicked(MouseEvent e) {
											if(selectedButton != null) {
												selectedButton.setForeground(Color.black);
												selectedButton.setBackground(new Color(220, 220, 220));
											}
											button.setForeground(Color.white);
											button.setBackground(new Color(100, 200, 100));
											selectedButton = button;
										}
									});
									stagePanel.add(button);
								}
								stagePanel.revalidate();
								stagePanel.repaint();
								
								startButton.setText("               완료               ");
							}
						});
						t.start();
					}
					catch (Exception e) {
						
					}
					
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
				
				g.setColor(new Color(109, 207, 246));
				((Graphics2D)g).setStroke(new BasicStroke(2));
				float a = (graphPanel.getWidth()-40) / (float)Integer.parseInt(textField_EVOLUTION_NUM.getText());
				float b = (graphPanel.getHeight()-40) / 80.f;
				if(!dots.isEmpty()) {
					Point prev = dots.peekFirst();
					for(Point curr : dots) {
						int x1 = 20 + (int)(a*prev.x);
						int y1 = graphPanel.getHeight()-20 - (int)(b*prev.y);
						int x2 = 20 + (int)(a*curr.x);
						int y2 = graphPanel.getHeight()-20 - (int)(b*curr.y);
						g.drawLine(x1, y1, x2, y2);
						prev = curr;
					}
				}
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
				if(selectedButton != null) {
					String text = selectedButton.getText().replace("세대", ",");
			    	int index = Integer.parseInt(text.split(",")[0]);
			    	
			    	Gene gene = candidate.get(index);
			    	
			    	Thread t = new Thread(new Runnable() {
			    		public void run() {
			    			Simulation_forPlay panel_AI = new Simulation_forPlay();
							panel_AI.setBounds(15, 30, 500, 400);
							settingPanel.add(panel_AI);
							settingPanel.revalidate();
							settingPanel.repaint();
							
							panel_AI.reset(gene);
							panel_AI.run();
							
							settingPanel.remove(panel_AI);
							settingPanel.revalidate();
							settingPanel.repaint();
			    		}
			    	});
			    	t.start();
					
					
					
				}
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
					changePanel.setLayout(new GridLayout(candidate.size(), 1));
					
					JScrollPane scrollPane = new JScrollPane(changePanel,
							JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
					
					JDialog dialog = new JDialog();
					dialog.setTitle("선택하세요");
					dialog.setSize(170, 400);
					dialog.setLocation(getLocation().x + 700, getLocation().y + 360);
					
					for(int i = 0; i < candidate.size(); i++) {
						JButton b = new JButton(i + "세대 Score: " + candidate.get(i).score);
						b.setFocusPainted(false);
						b.setContentAreaFilled(false);
						b.addMouseListener(new MouseAdapter() {
							public void mouseEntered(MouseEvent e) {
								b.setForeground(Color.white);
								b.setContentAreaFilled(true);
								b.setBackground(new Color(100, 200, 100));
							}
							public void mouseExited(MouseEvent e) {
								b.setForeground(Color.black);
								b.setContentAreaFilled(false);
							}
							public void mouseClicked(MouseEvent e) {
								selectedButton.setText(b.getText());
								stagePanel.revalidate();
								stagePanel.repaint();
								dialog.dispose();
							}
						});
						changePanel.add(b);
					}
					
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
					try {
						PrintWriter pw = new PrintWriter("stage.txt");
						
						for (Component component : stagePanel.getComponents())
						    if (component.getClass().equals(JButton.class)) {
						    	String text = ((JButton)component).getText().replace("세대", ",");
						    	int index = Integer.parseInt(text.split(",")[0]);
						    	int[] g = candidate.get(index).g;
						    	for(int i = 0; i < g.length; i++)
						    		pw.print(g[i] + " ");
						    	pw.println();
						    	pw.println(candidate.get(index).majorDataList);
						    	pw.println(candidate.get(index).minorDataList);
						    	pw.println();
						    }
						
						pw.close();
					} catch (FileNotFoundException e) {
					}
				}
			}
		});
		panel_saveButton.add(saveButton);
		
		settingPanel.add(panel_changeButton);
		settingPanel.add(panel_saveButton);
	}
}
