package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.util.ArrayList;

import config.GameConfig;
import data.geometry.Position;
import data.map.Line;
import data.map.City;
import data.map.Extension;
import data.map.Panel;
import data.map.Road;
import data.map.Turning;
import data.map.intersections.Crossroads;
import data.map.intersections.PedestrianCrossing;
import data.mobile.Car;
import process.Utility;


/**
 * 
 * Classe commentée
 * 
 * This class contains all static methods which paint {@link City} graphic components.
 * 
 * @author Rayane KHAMAILY
 * 
 * @see Graphics
 * @see Graphics2D
 * @see Utility
 * 
 * */

public class PaintStrategy {
	
	/** dash parameter of setStroke() **/
	private static float[] DEFAULT_DASH = {40, 40};
	

	/**
	 * This method paint the {@link City} and all its elements.
	 * 
	 * 
	 * @param city
	 * 				the {@link City}.
	 * 
	 * @param g
	 * 			a {@link Graphics} object.
	 * 
	 * @see City
	 * 
	 * @see Graphics
	 * 
	 * */
	
	public void paint(City city, Graphics g) {
		ArrayList<Road> roads = city.getRoads();
		ArrayList<Turning> turnings = city.getTurnings();
		ArrayList<Crossroads> crossroadss = city.getCrossroadss();
		ArrayList<Extension> extensions = city.getExtensions();
		Car secondaryCar = city.getSecondaryCar();
		
		//Paint roads
		
		for (Road road : roads) {
			
			int roadAxis = road.getAxis();
			if(roadAxis == Road.HORIZONTAL_AXIS) {
				paintHorizontalRoad(road, g);
			}
			
			else if(roadAxis == Road.VERTICAL_AXIS) {
				paintVerticalRoad(road, g);
			}
		
		}
		
		//Paint turnings
		
		for (Turning turning : turnings) {
			int turningAxis = turning.getAxis();
			switch (turningAxis) {
			case Turning.VR_TO_RHR :
				paintVRHTurning(turning, g);
				break;
			case Turning.VR_TO_LHR :
				paintVLHTurning(turning, g);
				break;
			case Turning.RHR_TO_VR :
				paintRHVTurning(turning, g);
				break;
			case Turning.LHR_TO_VR :
				paintLHVTurning(turning, g);
				break;
			}
			
		}
		
		//Paint crossroads
		
		for (Crossroads crossroads : crossroadss) {
			paintCrossroads(crossroads, g);
		}
		
		//Paint extensions
		
		for (Extension extension : extensions) {
			paintExtension(extension, g);
		}
		
		//Paint the secondary car
		
		paintSecondaryCar(secondaryCar, g);
		
	}
	
	/**
	 * This method paint the main {@link Car}.
	 * 
	 * @param car
	 * 			A {@link Car}. 
	 * 
	 * @param g
	 * 			A {@link Graphics} object
	 * 
	 * 
	 * */	

	public void paint(Car car, Graphics g) {
		Position position = car.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		

		g.drawImage(Utility.readImage("src/images/mercedes.png"), x, y, GameConfig.CAR_WIDTH, GameConfig.CAR_HEIGHT, null);
		
	}
	
	
	/**
	 * This method paint the secondary {@link Car}.
	 * 
	 * @param secondaryCar
	 * 			A {@link Car}. 
	 * 
	 * @param g
	 * 			A {@link Graphics} object
	 * 
	 * 
	 * */	

	private void paintSecondaryCar(Car secondaryCar, Graphics g) {
		Position position = secondaryCar.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		Graphics2D g2D = (Graphics2D)g;
		
		Position centerCar = Utility.getObjectCenter(position, GameConfig.ROAD_WIDTH/3, GameConfig.ROAD_HEIGHT/11);

		g2D.rotate(Math.toRadians(90), centerCar.getX(), centerCar.getY());
		g.drawImage(Utility.readImage("src/images/mercedes.png"), x, y, GameConfig.ROAD_WIDTH/3, GameConfig.ROAD_HEIGHT/11, null);
		
	}
	
	
	/**
	 * This method print a vertical {@link Line}.
	 * 
	 * @param line
	 * 				a vertical {@link Line}.
	 * 
	 * @param g
	 * 			a {@link Graphics} object.
	 * */
	
	private void paintVerticalLine(Line line, Graphics g) {
		Position position = line.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		Graphics2D g2D = (Graphics2D)g;
		
		setLineStroke(DEFAULT_DASH, g2D);
		
		g2D.drawLine(x, y, x, y + GameConfig.ROAD_HEIGHT);
		
	}
	
	/**
	 * This method print a horizontal {@link Line}.
	 * @param line
	 * 				a horizontal {@link Line}.
	 * 
	 * @param g
	 * 			a {@link Graphics} object.
	 * */
	private void paintHorizontalLine(Line line, Graphics g) {
		Position position = line.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		Graphics2D g2D = (Graphics2D)g;
		
		setLineStroke(DEFAULT_DASH, g2D);
		
		g2D.drawLine(x, y, x + GameConfig.ROAD_HEIGHT, y);
		
	}
	
	
	
	/**
	 * This method print a vertical {@link Road}.
	 * 
	 * @param verticalPanel
	 * 						a vertical {@link Road}.
	 * 
	 * @param g
	 * 			a {@link Graphics} object.
	 * */
	

	private void paintVerticalRoad(Road verticalRoad, Graphics g) {
		Position position = verticalRoad.getPosition();
		
		Line line = verticalRoad.getLine();	
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		g.setColor(Color.GRAY);
		
		g.fillRect(x, y, GameConfig.ROAD_WIDTH, GameConfig.ROAD_HEIGHT);
		
		paintVerticalLine(line, g);
	}
	
	
	/**
	 * This method print a horizontal {@link Road}.
	 * @param horizontalRoad
	 * 						a horizontal {@link Road}.
	 * 
	 * @param g
	 * 			a {@link Graphics} object.
	 * */
	
	private void paintHorizontalRoad(Road horizontalRoad, Graphics g) {
		Position position = horizontalRoad.getPosition();
		
		Line line = horizontalRoad.getLine();

		int x = (int)position.getX();
		int y = (int)position.getY();
		
		g.setColor(Color.GRAY);
		
		g.fillRect(x, y, GameConfig.ROAD_HEIGHT, GameConfig.ROAD_WIDTH);
		paintHorizontalLine(line, g);
	}
	
	/**
	 * This method print a vertical {@link Panel}.
	 * 
	 * @param verticalPanel
	 * 						a vertical {@link Panel}.
	 * 
	 * @param g
	 * 			a {@link Graphics} object.
	 * */
	
	private void paintVerticalPanel(Panel verticalPanel, int axis, Graphics g) {
		
		Position position = verticalPanel.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();

		Graphics2D g2D = (Graphics2D)g;
		
		AffineTransform old = g2D.getTransform();
		
		Position centerPanel = Utility.getObjectCenter(position, GameConfig.PANEL_WIDTH, GameConfig.PANEL_HEIGHT);
		
		double xPanel = centerPanel.getX();
		double yPanel = centerPanel.getY();
		
		if(axis == 1 ) {
			g2D.rotate(Math.toRadians(90), xPanel, yPanel);
			g2D.drawImage(Utility.readImage("src/images/stop.png"), x, y, GameConfig.PANEL_WIDTH, GameConfig.PANEL_HEIGHT, null);
		}
		
		else if(axis == 2) {
			g2D.rotate(Math.toRadians(270), xPanel, yPanel);
			g2D.drawImage(Utility.readImage("src/images/stop.png"), x, y, GameConfig.PANEL_WIDTH, GameConfig.PANEL_HEIGHT, null);
		}
		
		g2D.setTransform(old);
		
	}
	
	/**
	 * This method print a horizontal {@link Panel}.
	 * @param horizontalPanel
	 * 							a horizontal {@link Panel}.
	 * 
	 * @param g
	 * 			a {@link Graphics} object.
	 * */
	
	private void paintHorizontalPanel(Panel horizontalPanel, int axis, Graphics g) {
		Position position = horizontalPanel.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		Graphics2D g2D = (Graphics2D)g;
		
		AffineTransform old = g2D.getTransform();
		
		Position centerPanel = Utility.getObjectCenter(position, GameConfig.PANEL_WIDTH, GameConfig.PANEL_HEIGHT);
		
		double xPanel = centerPanel.getX();
		double yPanel = centerPanel.getY();
		 
		
		if(axis == 1) {
			g2D.rotate(Math.toRadians(180), xPanel, yPanel);
			g2D.drawImage(Utility.readImage("src/images/stop.png"), x, y, GameConfig.PANEL_WIDTH, GameConfig.PANEL_HEIGHT, null);
		}
		
		else if(axis == 2) {
			g2D.rotate(Math.toRadians(0), xPanel, yPanel);
			g2D.drawImage(Utility.readImage("src/images/stop.png"), x, y, GameConfig.PANEL_WIDTH, GameConfig.PANEL_HEIGHT, null);
			
		}
		
		g2D.setTransform(old);
	}
	
	/**
	 * This method print a VR_TO_RHR {@link Turning}.
	 * 
	 * @param vRHTurning
	 * 					a VRH {@link Turning}.
	 * @param g
	 * 			a {@link Graphics} object.
	 * 
	 * @see Turning
	 * 
	 * */
	
	private void paintVRHTurning(Turning vRHTurning, Graphics g) {
		Position position = vRHTurning.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		
		int angleStart = vRHTurning.getAngleStart(); 
		
		Graphics2D g2D = (Graphics2D)g;
		Arc2D arc2D = new Arc2D.Double(x, y, GameConfig.ROAD_WIDTH*2, GameConfig.ROAD_WIDTH*2, angleStart, 90, Arc2D.PIE);
		g2D.setPaint(Color.GRAY);
		g2D.fill(arc2D);
		
		// Draw the line
		setLineStroke(DEFAULT_DASH, g2D);
		g2D.drawArc(x + GameConfig.ROAD_WIDTH/2 - GameConfig.LINE_WIDTH/2, y + GameConfig.ROAD_WIDTH/2 - GameConfig.LINE_WIDTH/2, GameConfig.ROAD_WIDTH, GameConfig.ROAD_WIDTH, angleStart, 90);
	}
	
	
	/**
	 * This method print a VR_TO_LHR {@link Turning}.
	 * 
	 * @param vLHTurning 
	 * 					a VLH {@link Turning}.
	 * 
	 * @param g
	 * 			a {@link Graphics} object.
	 * 
	 * @see Turning
	 * 
	 * */
	
	
	private void paintVLHTurning(Turning vLHTurning, Graphics g) {
		Position position = vLHTurning.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		x -= GameConfig.ROAD_WIDTH;
		
		int angleStart = vLHTurning.getAngleStart();
		
		Graphics2D g2D = (Graphics2D)g;
		Arc2D arc2D = new Arc2D.Double(x, y, GameConfig.ROAD_WIDTH*2, GameConfig.ROAD_WIDTH*2, angleStart, 90, Arc2D.PIE);
		g2D.setPaint(Color.GRAY);
		g2D.fill(arc2D);
		
		// Draw the line
		setLineStroke(DEFAULT_DASH, g2D);
		g2D.drawArc(x + GameConfig.ROAD_WIDTH/2 - GameConfig.LINE_WIDTH/2, y + GameConfig.ROAD_WIDTH/2 - GameConfig.LINE_WIDTH/2, GameConfig.ROAD_WIDTH, GameConfig.ROAD_WIDTH, angleStart, 90);
		
	}
	
	
	/**
	 * This method print a RHR_TO_VR {@link Turning}.
	 * 
	 * @param rHVTurning
	 * 				a RHV {@link Turning}.
	 * 
	 * @param g
	 * 			a {@link Graphics} object.
	 * 
	 * @see Turning
	 * 
	 * */
	
	
	private void paintRHVTurning(Turning rHVTurning, Graphics g) {
		Position position = rHVTurning.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		y -= GameConfig.ROAD_WIDTH;
		
		int angleStart = rHVTurning.getAngleStart();
		
		Graphics2D g2D = (Graphics2D)g;
		Arc2D arc2D = new Arc2D.Double(x, y, GameConfig.ROAD_WIDTH*2, GameConfig.ROAD_WIDTH*2, angleStart, 90, Arc2D.PIE);
		g2D.setPaint(Color.GRAY);
		g2D.fill(arc2D);
		
		// Draw the line
		
		setLineStroke(DEFAULT_DASH, g2D);
		g2D.drawArc(x + GameConfig.ROAD_WIDTH/2 - GameConfig.LINE_WIDTH/2, y + GameConfig.ROAD_WIDTH/2 - GameConfig.LINE_WIDTH/2, GameConfig.ROAD_WIDTH, GameConfig.ROAD_WIDTH, angleStart, 90);
	}
	
	
	
	/**
	 * This method print a LHR_TO_VR {@link Turning}.
	 * 
	 * @param lHVTurning
	 * 					a LHV {@link Turning}.
	 * 
	 * @param g
	 * 			a {@link Graphics} object.
	 * 
	 * @see Turning
	 * 
	 * */
	
	
	private void paintLHVTurning(Turning lHVTurning, Graphics g) {
		Position position = lHVTurning.getPosition();
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		x -= GameConfig.ROAD_WIDTH;
		y -= GameConfig.ROAD_WIDTH;
		
		int angleStart = lHVTurning.getAngleStart();
		
		Graphics2D g2D = (Graphics2D)g;
		Arc2D arc2D = new Arc2D.Double(x, y, GameConfig.ROAD_WIDTH*2, GameConfig.ROAD_WIDTH*2, angleStart, 90, Arc2D.PIE);
		g2D.setPaint(Color.GRAY);
		g2D.fill(arc2D);
		
		// Draw the line
		
		setLineStroke(DEFAULT_DASH, g2D);
		
		g2D.drawArc(x + GameConfig.ROAD_WIDTH/2 - GameConfig.LINE_WIDTH/2, y + GameConfig.ROAD_WIDTH/2 - GameConfig.LINE_WIDTH/2, GameConfig.ROAD_WIDTH, GameConfig.ROAD_WIDTH, angleStart, 90);
	}
	
	
	/**
	 * This method creates a {@link BasicStroke} to draw the attribute {@link Line} of a {@link Turning} object.
	 * 
	 * @param dash
	 * 				the array representing the dashing pattern.
	 * 
	 * @param g2D
	 * 				a {@link Graphics} object.
	 * 
	 * @see Turning
	 * 
	 * @see BasicStroke
	 * 
	 * */
	
	
	private static void setLineStroke(float[] dash, Graphics2D g2D) {
		BasicStroke bS = new BasicStroke(GameConfig.LINE_WIDTH, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND, 0, dash, 0);
		g2D.setStroke(bS);
		g2D.setColor(Color.WHITE);
	}
	
	
	/**
	 * This method print a {@link Crossroads}.
	 * 
	 * @param crossroads
	 * 					a {@link Crossroads} object.
	 * 
	 * @param g
	 * 			a {@link Graphics} object.
	 * 
	 * */
	
	private void paintCrossroads(Crossroads crossroads, Graphics g) {
		Position position = crossroads.getPosition();
		
		ArrayList<Panel> panels = crossroads.getPanels(); 
		
		Panel pC1 = panels.get(0);
		Panel pC2 = panels.get(1);
		
		int x = (int)position.getX();
		int y = (int)position.getY();
		
		
		//Draw the crossroads
		g.setColor(Color.GRAY);
		
		g.fillRect(x, y, GameConfig.ROAD_WIDTH, GameConfig.ROAD_WIDTH);
		
		//Generate pedestrian crossings
		for (PedestrianCrossing pedestrianCrossing : crossroads.getPedestrianCrossings()) {
			if(pedestrianCrossing.getAxis() == PedestrianCrossing.HORIZONTAL_AXIS) {
				paintHorizontalPedesrianCrossing(pedestrianCrossing, g);
			}
			else if(pedestrianCrossing.getAxis() == PedestrianCrossing.VERTICAL_AXIS){
				paintVerticalPedesrianCrossing(pedestrianCrossing, g);
			}
		}
		
		
		
		if(Utility.getCrossroadsType(crossroads) == Utility.CROSSROADS_1) {
			paintVerticalPanel(pC1, 1, g);
			paintHorizontalPanel(pC2, 1, g);
		}
		else if(Utility.getCrossroadsType(crossroads) == Utility.CROSSROADS_2) {
			paintVerticalPanel(pC1, 2, g);
			paintHorizontalPanel(pC2, 2, g);
		}

		
	}
	
	
	/**
	 * This method print a horizontal {@link PedestrianCrossing}.
	 * 
	 * @param pedestrianCrossing
	 * 							a {@link PedestrianCrossing}.
	 * 
	 * @param g
	 * 			a {@link Graphics} object.
	 * */
	
	
	private void paintHorizontalPedesrianCrossing(PedestrianCrossing pedestrianCrossing, Graphics g) {
		Position position = pedestrianCrossing.getPosition();
		
		int xPedestrianCrossing = (int)position.getX();
		int yPedestrianCrossing = (int)position.getY();
		
		
		//To draw the pedestrian crossing we draw many white rectangles  
		for(int iteration = 0 ; iteration < 8; iteration++) {
			g.setColor(Color.WHITE);
			g.fillRect(xPedestrianCrossing, yPedestrianCrossing, GameConfig.PEDESTRIAN_CROSSING_WIDTH, GameConfig.PEDESTRIAN_CROSSING_HEIGHT);
			
			xPedestrianCrossing += GameConfig.PEDESTRIAN_CROSSING_WIDTH + GameConfig.PEDESTRIAN_CROSSING_WIDTH/4;
		}
		
	}
	
	/**
	 * This method print a vertical {@link PedestrianCrossing}.
	 * 
	 * @param pedestrianCrossing
	 * 							a {@link PedestrianCrossing}.
	 * 
	 * @param g
	 * 			a {@link Graphics} object.
	 * */
	
	
	private void paintVerticalPedesrianCrossing(PedestrianCrossing pedestrianCrossing, Graphics g) {
		Position position = pedestrianCrossing.getPosition();
		
		int xPedestrianCrossing = (int)position.getX();
		int yPedestrianCrossing = (int)position.getY();
		
		
		//To draw the pedestrian crossing we draw many white rectangles  
		for(int iteration = 0 ; iteration < 8; iteration++) {
			g.setColor(Color.WHITE);
			g.fillRect(xPedestrianCrossing, yPedestrianCrossing, GameConfig.PEDESTRIAN_CROSSING_HEIGHT, GameConfig.PEDESTRIAN_CROSSING_WIDTH);
			
			yPedestrianCrossing += GameConfig.PEDESTRIAN_CROSSING_WIDTH + GameConfig.PEDESTRIAN_CROSSING_WIDTH/4;
		}
		
	}
	
	/**
	 * This method print a road {@link Extension}.
	 * 
	 * @param extension
	 * 					The {@link Extension}.
	 * @param g
	 * 			a {@link Graphics} object.
	 * 
	 * */
	
	private void paintExtension(Extension extension, Graphics g) {
		
		Position extensionPosition = extension.getPosition();
		
		
		int x = (int)extensionPosition.getX();
		int y = (int)extensionPosition.getY();
		
		g.setColor(Color.GRAY);
		
		g.fillRect(x, y, GameConfig.ROAD_WIDTH, GameConfig.ROAD_WIDTH);		
		
		int extensionAxis = extension.getAxis();
		
		Line line = extension.getLine();
		
		if(extensionAxis == Extension.HORIZONTAL_AXIS) {
			paintExtensionHorizontalLine(line, g);
		}
		
		else if(extensionAxis == Extension.VERTICAL_AXIS){
			paintExtensionVerticalLine(line, g);
		}
		
	}
	
	
	/**
	 * This method print the {@link Line} of a vertical extension.
	 * 
	 * @param line
	 * 				A {@link Line}.
	 * 
	 * @param g
	 * 			A {@link Graphics} object.
	 * 
	 * */
	
	private void paintExtensionVerticalLine(Line line, Graphics g) {

		Position linePosition = line.getPosition();
		
		int x = (int)linePosition.getX();
		int y = (int)linePosition.getY();
		
		Graphics2D g2D = (Graphics2D)g;
		
		setLineStroke(DEFAULT_DASH, g2D);
		
		g2D.drawLine(x, y, x, y + GameConfig.ROAD_WIDTH);
	} 
	
	/**
	 * This method print the {@link Line} of a horizontal extension.
	 * 
	 * @param line
	 * 				A {@link Line}.
	 * 
	 * @param g
	 * 			A {@link Graphics} object.
	 * 
	 * */
	
	private void paintExtensionHorizontalLine(Line line, Graphics g) {

		Position linePosition = line.getPosition();
		
		int x = (int)linePosition.getX();
		int y = (int)linePosition.getY();
		
		Graphics2D g2D = (Graphics2D)g;
		
		setLineStroke(DEFAULT_DASH, g2D);
		
		g2D.drawLine(x, y, x + GameConfig.ROAD_WIDTH, y);
	}

}