package process;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.Position;

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

}
