package data.map.intersections;

import data.geometry.Position;
import data.map.CityElement;

public class PedestrianCrossing extends CityElement {

	public static final int HORIZONTAL_AXIS = 0;
	public static final int VERTICAL_AXIS = 1;

	private int axis;
	
	
	public PedestrianCrossing(Position position, int axis) {
		super(position);
		this.axis = axis;
	}


	public int getAxis() {
		return axis;
	}

}
