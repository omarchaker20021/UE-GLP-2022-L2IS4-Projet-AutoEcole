package gui;

import java.awt.Graphics;

import javax.swing.JPanel;

import data.*;
import process.MobileElementManager;

public class GameDisplay extends JPanel{

	private static final long serialVersionUID = 1L;
	
	
	private Map map;
	private MobileElementManager manager;
	private PaintStrategy paintStrategy = new PaintStrategy();
	
	
	
	public GameDisplay(MobileElementManager manager, Map map) {
		this.manager = manager;
		this.map = map;
	}
	
	
	/**
	 * This method paint all graphic components
	 * */
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		paintStrategy.paint(map, g);
		
		Car car = manager.getCar();
		paintStrategy.paint(car, g);
		
	}
	
	
	

}
