package data.map.intersections;

import data.geometry.Position;
import data.map.Line;
import data.map.MapElement;

public interface Intersection {

	public Line getLine();

	public void setLine(Line line);

}
