package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import config.GameConfig;
import data.geometry.Position;
import data.map.Line;
import data.map.Map;/*
import data.map.Panel;*/
import data.map.Road;
import data.mobile.Car;
import process.Utility;

public class PaintStrategy {

	/**
	 * This method paint the map
	 * */
	
	public void paint(Map map, Graphics g) {
		ArrayList<Road> roads = map.getRoads();
		
		for (Road road : roads) {
			
			char roadAxis = road.getRoadAxis();
			if(roadAxis == Road.HORIZONTAL_AXIS) {
				paintHorizontalRoad(road, g);
			}
			
			else if(roadAxis == Road.VERTICAL_AXIS) {
				paintVerticalRoad(road, g);
			}
		
		}
		
	}
	
	/**
	 * This method paint the main car
	 * @param a car and a Graphics object
	 * */	

	public void paint(Car car, Graphics g) {
		Position position = car.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		

		g.drawImage(Utility.readImage("src/images/mercedes.png"), x, y, GameConfig.CAR_WIDTH, GameConfig.CAR_HEIGHT, null);
		
	}
	
	/**
	 * This method print a white line of vertical road
	 * @param a Line object and a Graphics object
	 * */
	
	private void paintVerticalLine(Line line, Graphics g) {
		Position position = line.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		Graphics2D g2D = (Graphics2D)g;
		
		g.setColor(Color.WHITE);
		
		float[] dash = {20, 20};
		
		BasicStroke bS = new BasicStroke(GameConfig.LINE_WIDTH, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 0, dash, 0);
		g2D.setStroke(bS);
		
		g2D.drawLine(x, y, x, y + GameConfig.ROAD_HEIGHT);
		
	}
	
	/**
	 * This method print the line of a horizontal road
	 * @param a Line object and a Graphics object
	 * */
	private void paintHorizontalLine(Line line, Graphics g) {
		Position position = line.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		Graphics2D g2D = (Graphics2D)g;
		
		g.setColor(Color.WHITE);
		
		float[] dash = {20, 20};
		
		BasicStroke bS = new BasicStroke(GameConfig.LINE_WIDTH, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 0, dash, 0) ;
		g2D.setStroke(bS);
		
		g2D.drawLine(x, y, x + GameConfig.ROAD_HEIGHT, y);
		
	}
	
	
	
	/**
	 * This method print a vertical road
	 * @param the vertical road and a Graphics object
	 * */

	private void paintVerticalRoad(Road verticalRoad, Graphics g) {
		Position position = verticalRoad.getPosition();
		
		Line line = verticalRoad.getLine();
/*		Panel panel = verticalRoad.getPanel();
	*/	
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		g.setColor(Color.GRAY);
		
		g.fillRect(x, y, GameConfig.ROAD_WIDTH, GameConfig.ROAD_HEIGHT);
		
		paintVerticalLine(line, g);
		/*paintVerticalPanel(panel, g);*/
	}
	/**
	 * This method print a vertical road
	 * @param the vertical road and a Graphics object
	 * */
	
	private void paintHorizontalRoad(Road horizontalRoad, Graphics g) {
		Position position = horizontalRoad.getPosition();
		
		Line line = horizontalRoad.getLine();
/*		Panel panel = horizontalRoad.getPanel();
	*/	
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		g.setColor(Color.GRAY);
		
		g.fillRect(x, y, GameConfig.ROAD_HEIGHT, GameConfig.ROAD_WIDTH);
		paintHorizontalLine(line, g);
		/*paintHorizontalPanel(panel, g);
	*/}
	
	/**
	 * This method print a vertical panel
	 * @param a panel and a Graphics object
	 * */
	
	/*private void paintVerticalPanel(Panel verticalPanel, Graphics g) {
		Position position = verticalPanel.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		g.drawImage(Utility.readImage("src/images/stop.png"), x, y, GameConfig.PANEL_WIDTH, GameConfig.PANEL_HEIGHT, null);
	}*/
	
	/**
	 * This method print a horizontal panel
	 * @param a panel and a Graphics object
	 * */
	
	/*private void paintHorizontalPanel(Panel horizontalPanel, Graphics g) {
		Position position = horizontalPanel.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		g.drawImage(Utility.readImage("src/images/stop.png"), x, y, GameConfig.PANEL_HEIGHT, GameConfig.PANEL_WIDTH, null);
	}*/
}