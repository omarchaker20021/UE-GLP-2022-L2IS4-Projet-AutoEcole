package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import config.GameConfig;
import data.geometry.Position;
import data.map.City;
import data.mobile.Car;
import process.MobileElementManager;
import process.Utility;


/**
 * 
 * Classe commentée
 * 
 * This class organize graphic {@link City} elements of the {@link City} according to an order of drawing components.
 * 
 * This class extends {@link JPanel} 
 * 
 * @author Omar CHAKER
 * 
 * @see JPanel
 * 
 * */

public class GameDisplay extends JPanel{

	private static final long serialVersionUID = 1L;
	
	
	private City city;
	private MobileElementManager manager;
	private PaintStrategy paintStrategy = new PaintStrategy();
	
	
	public GameDisplay(MobileElementManager manager, City city) {
		this.manager = manager;
		this.city = city;
	}


	/**
	 * This method paint all graphic components
	 * */
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2D =(Graphics2D)g;
		
		//Activation du antialiasing
		g2D.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		AffineTransform old = g2D.getTransform();
		
		Car car = manager.getCar();
		
		Position position = car.getPosition();
		
		//On calcule le centre de la voiture
		Position carPosition = Utility.getObjectCenter(position, GameConfig.CAR_WIDTH, GameConfig.CAR_HEIGHT*2);
		
		//On récupère le degré de rotation de la map 
		double rotationDegrees = manager.getRotationDegrees();

		g2D.rotate(Math.toRadians(rotationDegrees), carPosition.getX(), carPosition.getY());
		
		paintStrategy.paint(city, g2D);
		
		g2D.setTransform(old);
		
		
		paintStrategy.paint(car, g2D);
		
	}

}
