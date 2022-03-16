package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import config.GameConfig;
import data.Car;
import data.Line;
import data.Map;
import data.Panel;
import data.Position;
import data.Road;
import process.Utility;

public class PaintStrategy {

	/**
	 * This method paint the map
	 * */
	
	public void paint(Map map, Graphics g) {
		ArrayList<Road> roads = map.getRoads();
		ArrayList<Panel> panels = map.getPanels();
		
		
		for (Panel panel : panels) {
			paintPanel(panel, g);
		}
		
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
		
		int x = position.getX();
		int y = position.getY();
		
		g.drawImage(Utility.readImage("src/images/mercedes.png"), x, y, GameConfig.CAR_WIDTH, GameConfig.CAR_HEIGHT, null);
		
	}
	
	/**
	 * This method print a white line of vertical road
	 * @param a Line object and a Graphics object
	 * */
	
	private void paintVerticalLine(Line line, Graphics g) {
		Position position = line.getPosition();
		
		int x = position.getX();
		int y = position.getY();
		
		Graphics2D g2D = (Graphics2D)g;
		
		g.setColor(Color.WHITE);
		
		float[] dash = {20, 20};
		
		BasicStroke bS = new BasicStroke(GameConfig.LINE_WIDTH, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 0, dash, 0) ;
		g2D.setStroke(bS);
		
		g2D.drawLine(x, y, x, y + GameConfig.ROAD_HEIGHT);
		
	}
	
	/**
	 * This method print the line of a horizontal road
	 * @param a Line object and a Graphics object
	 * */
	private void paintHorizontalLine(Line line, Graphics g) {
		Position position = line.getPosition();
		
		int x = position.getX();
		int y = position.getY();
		
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
		
		int x = position.getX();
		int y = position.getY();
		
		g.setColor(Color.GRAY);
		
		g.fillRect(x, y, GameConfig.ROAD_WIDTH, GameConfig.ROAD_HEIGHT);
		
		paintVerticalLine(verticalRoad.getLine(), g);
	}

	/**
	 * This method print a vertical road
	 * @param the vertical road and a Graphics object
	 * */
	
	private void paintHorizontalRoad(Road horizontalRoad, Graphics g) {
		Position position = horizontalRoad.getPosition();
		Line line = horizontalRoad.getLine();
		
		int x = position.getX();
		int y = position.getY();
		
		g.setColor(Color.GRAY);
		
		g.fillRect(x, y, GameConfig.ROAD_HEIGHT, GameConfig.ROAD_WIDTH);
		paintHorizontalLine(line, g);
	}
	
	/**
	 * This method print a Stop panel
	 * @param a panel and a Graphics object
	 * */
	
	private void paintPanel(Panel stop, Graphics g) {
		Position position = stop.getPosition();
		
		int x = position.getX();
		int y = position.getY();
		
		g.drawImage(Utility.readImage("src/images/stop.png"), x, y, GameConfig.STOP_WIDTH, GameConfig.STOP_HEIGHT, null);
	}
}
