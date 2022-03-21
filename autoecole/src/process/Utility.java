package process;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.geometry.Position;

/**
 * Cette classe sert d'utilitaire pour ce projet
 * @author Rayane KHAMAILY
 * */

public class Utility {

	
	/**
	 * This methode reads image from their location
	 * @param the image path
	 * @return an image
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
	 * This method calculate The position of a point after a rotation of a certain angle
	 * @param angle of rotation and the point
	 * */
	
	public static Position turnWithAngle(Position point, int angle) {
		Position pointPosition = point;
		
		
		return pointPosition; 
	}
	
	/**
	 * This method returns the center of an object from its position and its width and height
	 * @param objectPosition
	 * 						 position of the object
	 * @param objectWidth
	 * 					  object width
	 * @param objectHeight
	 * 					   object height
	 * @return Position of the center of the object
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
	
}
