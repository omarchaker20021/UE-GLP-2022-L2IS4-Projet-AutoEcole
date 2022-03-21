package data.map.intersections;

import data.geometry.Position;
import data.map.Line;
import data.map.MapElement;

public class Turning extends MapElement implements Intersection {
	
	private Line line;
	
	public Turning(Position position) {
		super(position);
	}
	
	@Override
	public Line getLine() {
		return line;
	}

	@Override
	public void setLine(Line line) {
		this.line = line;
	}

}
