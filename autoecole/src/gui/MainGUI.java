package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import config.GameConfig;
import data.Map;
import process.GameBuilder;
import process.MobileElementManager;

public class MainGUI extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6073686091925901120L;
	
	private static final Dimension framePreferredDimension = new Dimension(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
	private static final Dimension mapPreferredDimension = new Dimension(GameConfig.MAP_WIDTH, GameConfig.MAP_HEIGHT);
	
	private Map map;
	
	private MobileElementManager manager;
	
	private GameDisplay dashboard;
	

	public MainGUI(String title) {
		super(title);
		init();
	}
	
	public void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		
		/** Bordures des panels **/
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
		
		
		JPanel panel1 = new JPanel();
		JButton button1 = new JButton(" Button ");
		panel1.setPreferredSize(new Dimension(100, 100));
		panel1.setLayout(new FlowLayout());
		panel1.add(button1);
		contentPane.add(panel1, BorderLayout.EAST);
		panel1.setBorder(lineBorder);
		
		JPanel panel2 = new JPanel();
		JButton button2 = new JButton(" Button ");
		JTextField textField = new JTextField();
		textField.setColumns(10);
		panel2.setPreferredSize(new Dimension(100, 100));
		panel2.setLayout(new FlowLayout());
		panel2.add(textField);
		panel2.add(button2);
		contentPane.add(panel2, BorderLayout.SOUTH);
		panel2.setBorder(lineBorder);
		
		
		map = GameBuilder.buildMap();
		manager = GameBuilder.buildInitMobile(map);
		dashboard = new GameDisplay(manager, map);
		dashboard.setBorder(lineBorder);
		
		dashboard.setPreferredSize(mapPreferredDimension);
		dashboard.setBackground(Color.GREEN);
		contentPane.add(dashboard, BorderLayout.CENTER);
		
		KeyControls keyControls = new KeyControls();
		textField.addKeyListener(keyControls);
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setPreferredSize(framePreferredDimension);
		setResizable(true);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(GameConfig.GAME_SPEED);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			dashboard.repaint();
		}
	}
	
	private class KeyControls implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			char typeChar = e.getKeyChar();
			switch(typeChar) {
			case 'z':
				manager.moveStraight();
				dashboard.repaint();
				break;
			case 'q':
				manager.turnLeft();
				dashboard.repaint();
				break;
			case 'd':
				manager.turnRight();
				dashboard.repaint();
				break;
			default : 
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}
}
