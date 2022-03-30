package process;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import data.geometry.Position;
import data.map.Road;
import data.map.Turning;
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
	
}
