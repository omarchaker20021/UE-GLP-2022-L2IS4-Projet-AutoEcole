package data.map.intersections;

import data.geometry.Position;
import data.map.Line;
import data.map.MapElement;

/**
 * Cette classe est un classe de données d'un rond point (roundabout)
 * @author Rayane KHAMAILY
 * */

public class Roundabout extends MapElement implements Intersection {
	
	private Line line;

	
	public Roundabout(Position position, Line line) {
		super(position);
		this.line = line;
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
