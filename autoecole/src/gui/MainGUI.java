package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import config.GameConfig;
import data.map.City;
import process.GameBuilder;
import process.MobileElementManager;

public class MainGUI extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6073686091925901120L;
	
	private static final Dimension framePreferredDimension = new Dimension(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
	private static final Dimension mapPreferredDimension = new Dimension(GameConfig.MAP_WIDTH, GameConfig.MAP_HEIGHT);
	
	private City city;
	
	private MobileElementManager manager;
	
	private GameDisplay dashboard;
	
	private JPanel infoPanel;
	
	private JLabel pace = new JLabel();
	private JLabel distance = new JLabel();
	private JLabel errors = new JLabel();

	private JButton button2 = new JButton(" Start ");
	
	private boolean stop = true;
	
	private MainGUI instance = this;
	
	public MainGUI(String title) {
		super(title);
		init();
	}
	
	public void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		
		/** Bordures des panels **/
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);

		
		
		JPanel panel2 = new JPanel();
		button2.addActionListener(new ActionStart());
		button2.setFocusable(false);
		panel2.setPreferredSize(new Dimension(100, 100));
		panel2.setLayout(new FlowLayout());
		panel2.add(button2);
		contentPane.add(panel2, BorderLayout.SOUTH);
		panel2.setBorder(lineBorder);
		
		
		city = GameBuilder.buildCity();
		manager = GameBuilder.buildInitMobile(city);
		dashboard = new GameDisplay(manager, city);
		dashboard.setBorder(lineBorder);
		
		infoPanel = new JPanel();
		pace.setText("Pace : " + (int)manager.getCar().getPace() + "km/h");
		distance.setText("Distance : " + (int)manager.getDistance() + "km");
		errors.setText("Errors : ");
		
		infoPanel.setLayout(new FlowLayout());
		infoPanel.setPreferredSize(new Dimension(100, 100));
		infoPanel.setBorder(lineBorder);
		infoPanel.add(pace);
		infoPanel.add(distance);
		infoPanel.add(errors);
		
		contentPane.add(infoPanel, BorderLayout.EAST);
		
		dashboard.setPreferredSize(mapPreferredDimension);
		dashboard.setBackground(Color.GREEN);
		contentPane.add(dashboard, BorderLayout.CENTER);
		
		KeyControls keyControls = new KeyControls();
		addKeyListener(keyControls);
		setFocusable(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setPreferredSize(framePreferredDimension);
		setResizable(true);
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				Thread.sleep(GameConfig.GAME_SPEED);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
			pace.setText("Pace : " + (int)manager.getCar().getPace() + "km/h");
			distance.setText("Distance : " + (int)manager.getDistance() + "km");
			manager.moveCar();
			dashboard.repaint();
			infoPanel.repaint();
		}
	}
	
	private class ActionStart implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(stop) {
				stop = false;
				button2.setText(" Stop ");
				Thread chronoThread = new Thread(instance);
				chronoThread.start();
			}
			else{
				button2.setText(" Start ");
				stop = true;
			}
		}
		
	}
	
	
	private class KeyControls implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			char typeChar = '0';
			if(!stop) {
				typeChar = e.getKeyChar();
			}
			switch(typeChar) {
			case 'z':
				manager.accelerate();
				break;
			case 'q':
				manager.turnLeft();
				manager.setRotationDegrees(manager.getRotationDegrees() + GameConfig.TURN_RADIAN);
				break;
			case 'd':
				manager.turnRight();
				manager.setRotationDegrees(manager.getRotationDegrees() - GameConfig.TURN_RADIAN);
				break;
			case 's':
				manager.decelerate();
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
