package data.map.intersections;

import data.geometry.Position;
import data.map.Line;
import data.map.MapElement;

public class Crossroads extends MapElement implements Intersection {

	public Crossroads(Position position) {
		super(position);
	}

	@Override
	public Line getLine() {
		return null;
	}

	@Override
	public void setLine(Line line) {}

}
