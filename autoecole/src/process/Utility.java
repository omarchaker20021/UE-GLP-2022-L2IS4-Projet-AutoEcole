package process;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import config.GameConfig;
import data.geometry.Position;
import data.map.City;
import data.map.CityElement;
import data.map.Extension;
import data.map.Panel;
import data.map.Road;
import data.map.Turning;
import data.map.intersections.Crossroads;
import data.map.intersections.PedestrianCrossing;
import gui.GameDisplay;
import gui.PaintStrategy;

/**
 * 
 * Classe commentée
 * 
 * This class contains all static methods that we need to create methods in 
 * {@link MobileElementManager}, {@link PaintStrategy}, {@link GameBuilder}, {@link GameDisplay}
 * 
 * @author Rayane KHAMAILY
 * 
 * */

public class Utility {
	
	/** A {@link Crossroads} with a top horizontal {@link PedestrianCrossing} and left vertical {@link PedestrianCrossing} **/

	public static final int CROSSROADS_1 = 1;

	/** A {@link Crossroads} with a bottom horizontal {@link PedestrianCrossing} and right vertical {@link PedestrianCrossing} **/
	
	public static final int CROSSROADS_2 = 2;
	
	/**
	 * This method reads image from their location.
	 * 
	 * @param filePath
	 * 				the image path.
	 * 
	 * @return an {@link Image}.
	 * */
	public static Image readImage(String filePath) {
		try {
			return ImageIO.read(new File(filePath));
		} catch (IOException e) {
			System.err.println("-- Can not read the image file ! --");
			return null;
		}
	}
	
	/**
	 * This method returns the center of an object from its {@link Position} and its width and height
	 * @param objectPosition
	 * 						 {@link Position} of the object
	 * @param objectWidth
	 * 					  object width
	 * @param objectHeight
	 * 					   object height
	 * @return {@link Position} of the center of the object
	 * */

	public static Position getObjectCenter(Position objectPosition, int objectWidth, int objectHeight) {
		double x = objectPosition.getX();
		double y = objectPosition.getY();
		
		x += objectWidth/2;
		y += objectHeight/2;
		
		return new Position(x, y);
	}
	
	public static double getVectorRadianWithCosinus(double ab, double bc) {
		//On considere deux vecteurs ab horizontal et ac vertical
		//et bc est la somme de ab et ac
		// on trouve le cos(b) avec l'operation ab/ac
		double cosb = ab/bc;
		return Math.acos(cosb);
	}
	
	
	/**
	 * This static method verifies if a {@link Road} exists from a searched {@link Position}
	 * 
	 * @param roads
	 * 				{@link ArrayList} of {@link Road}s.
	 * 
	 * @param searchedPosition
	 *  						{@link Position} of the searched {@link Road}.
	 * 
	 * @return
	 * 			a boolean, true if the {@link Road} was found, false if not.
	 * */
	
	public static boolean doesRoadExists(ArrayList<Road> roads, Position searchedPosition) {
		for (Road road : roads) {
			Position position = road.getPosition();
			
			double x = position.getX();
			double y = position.getY();
			
			if(x == searchedPosition.getX() && y == searchedPosition.getY()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This static method return an existing {@link Road} in the {@link ArrayList} of {@link Road}s from a {@link Position}
	 * 
	 * @param roads
	 * 				{@link ArrayList} of {@link Road}s.
	 * 
	 * @param searchedPosition
	 *  						{@link Position} of the searched {@link Road}.
	 * 
	 * @return road
	 * 				{@link Road} searched from the "searchedPosition".
	 * */
	
	public static Road getRoadFromPosition(ArrayList<Road> roads, Position searchedPosition) {
		for (Road road : roads) {
			Position position = road.getPosition();
			
			double x = position.getX();
			double y = position.getY();
			
			if(x == searchedPosition.getX() && y == searchedPosition.getY()) {
				return road;
			}
		}
		return null;
	}
	
	/**
	 * This static method return an existing {@link Turning} in the {@link ArrayList} of {@link Turning}s from a {@link Position}
	 * 
	 * @param turnings
	 * 				{@link ArrayList} of {@link Turning}s.
	 * 
	 * @param searchedPosition
	 *  						{@link Position} of the searched {@link Turning}
	 * 
	 * @return turning
	 * 				{@link Turning} searched from the "searchedPosition"
	 * */
	
	public static boolean doesTurningExists(ArrayList<Turning> turnings, Position searchedPosition) {
		for (Turning turning : turnings) {
			Position position = turning.getPosition();
			
			double x = position.getX();
			double y = position.getY();
			
			if(x == searchedPosition.getX() && y == searchedPosition.getY()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This static method detect the type of a {@link Crossroads}.
	 * 
	 * @param crossroadss
	 * 						An {@link ArrayList} of {@link Crossroads}.
	 * @param searchedPosition
	 * 							The {@link Position} where it finds out if the {@link Crossroads} exists.
	 * 
	 * @return a boolean : True if a {@link Crossroads} exists in that {@link Position}, false if not.
	 * 
	 * */
	
	public static boolean doesCrossroadsExists(ArrayList<Crossroads> crossroadss, Position searchedPosition) {
		
		for (Crossroads crossroads : crossroadss) {
			
			Position position = crossroads.getPosition();
			
			double x = position.getX();
			double y = position.getY();
			
			if(x == searchedPosition.getX() && y == searchedPosition.getY() ) {
				return true;
			}
		}
		
		return false;
		
	}
	
	
	/**
	 * This method verifies if a {@link CityElement} exists in the {@link City}
	 * 
	 * @param 
	 * */
	
	public static boolean doesCityElementExists(City city, Position position) {
		ArrayList<Turning> turnings = city.getTurnings();
		
		ArrayList<Road> roads = city.getRoads();
		
		ArrayList<Crossroads> crossroadss = city.getCrossroadss();
		
		ArrayList<Extension> extensions = city.getExtensions();
		
		return (doesTurningExists(turnings, position) 
				|| doesRoadExists(roads, position) 
				|| doesCrossroadsExists(crossroadss, position)
				|| doesExtensionExists(extensions, position));
		
	}
	
	/**
	 * This static method detect the type of a {@link Crossroads}.
	 * 
	 * @param crossroadss
	 * 						An {@link ArrayList} of {@link Crossroads}.
	 * @param searchedPosition
	 * 							The {@link Position} where it finds out if the {@link Crossroads} exists.
	 * 
	 * @return a boolean : True if a {@link Crossroads} exists in that {@link Position}, false if not.
	 * 
	 * */
	
	public static boolean doesExtensionExists(ArrayList<Extension> extensions, Position searchedPosition) {
		
		double xSearchedPosition = searchedPosition.getX();
		double ySearchedPosition = searchedPosition.getY();
		
		for (Extension extension : extensions) {
			Position position = extension.getPosition();
			
			double x = position.getX();
			double y = position.getY();
			
			if(x == xSearchedPosition 
					&& y == ySearchedPosition) {
				return true;
			}
			
		}
		return false;
	}
	
	
	/**
	 * This method returns the type of crossroads
	 * 
	 * */
	
	public static int getCrossroadsType(Crossroads crossroads) {
		Position crossroadsPosition = crossroads.getPosition();
		
		double x = crossroadsPosition.getX();
		double y = crossroadsPosition.getY();
		
		ArrayList<PedestrianCrossing> pedestrianCrossings = crossroads.getPedestrianCrossings();
		
		PedestrianCrossing pC1 = pedestrianCrossings.get(0);
		PedestrianCrossing pC2 = pedestrianCrossings.get(1);
		
		
		
		double x1 = pC1.getPosition().getX();

		double y2 = pC2.getPosition().getY();
	
		if(x1 == x + GameConfig.ROAD_WIDTH 
				&& y2 == y + GameConfig.ROAD_WIDTH){
			return CROSSROADS_2;
		}
		else if(x1 == x - GameConfig.PEDESTRIAN_CROSSING_HEIGHT
				&& y2 == y - GameConfig.PEDESTRIAN_CROSSING_HEIGHT){
			return CROSSROADS_1;
		}
		return 0;
	}
	
	/**
	 * This method verifies if a {@link Panel} exists.
	 * 
	 * @param city
	 * 				The {@link City}.
	 * 
	 * @param searchedPosition
	 * 							The {@link Position} from where the method searches the
	 * {@link Panel} existence.
	 * 
	 * @param panelType
	 * 					The {@link Panel} type.
	 * 
	 * @return True : if the {@link Panel} exists, false : if not.
	 * 
	 * */
	
	public static boolean doesPanelExists(City city, 
			Position searchedPosition,
			int panelType) {
		
		ArrayList<Crossroads> crossroadss = city.getCrossroadss();
		
		double xSearchedPosition = searchedPosition.getX();
		double ySearchedPosition = searchedPosition.getY();
		
		for (Crossroads crossroads : crossroadss) {
			
			ArrayList<Panel> panels = crossroads.getPanels();
			
			for (Panel panel : panels) {
				Position position = panel.getPosition();
				
				double x = position.getX();
				double y = position.getY();
				
				if(x == xSearchedPosition 
						&& y == ySearchedPosition
						&& panel.getType() == panelType) {
					return true;
				}
				
			}
			
		}
		return false;
	}
}
