package data.map;

import data.geometry.Position;

/**
 * Classe qui represente la ligne blanche
 * @author Rayane KHAMAILY
 * */

public class Line extends CityElement {

	public Line(Position position) {
		super(position);
	}
	public Line() {
		super(new Position(0,0));
	}
	
}
