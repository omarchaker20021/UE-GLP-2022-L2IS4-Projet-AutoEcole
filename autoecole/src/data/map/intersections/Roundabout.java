package data.map.intersections;

import java.util.ArrayList;

import data.geometry.Position;
import data.map.Line;
import data.map.Panel;

/**
 * Cette classe est un classe de données d'un rond point (roundabout)
 * @author Rayane KHAMAILY
 * */

public class Roundabout extends Crossroads {
	
	private Line line;

	
	public Roundabout(Position position, ArrayList<PedestrianCrossing> pedestrianCrossings, Line line) {
		super(position, pedestrianCrossings);
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

	@Override
	public ArrayList<Panel> getPanels() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPanels(ArrayList<Panel> panels) {
		// TODO Auto-generated method stub
		
	}

}
