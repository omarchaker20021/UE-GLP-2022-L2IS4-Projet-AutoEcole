package data.map.intersections;

import java.util.ArrayList;

import data.geometry.Position;
import data.map.Line;
import data.map.Panel;

public class Crossroads extends Intersection {
	
	public Crossroads(Position position, ArrayList<PedestrianCrossing> pedestrianCrossings) {
		super(position, pedestrianCrossings);
	}

	@Override
	public Line getLine() {
		return null;
	}

	@Override
	public void setLine(Line line) {}
	
	public void setPedestrianCrossings(Position position) {
		
		Position crossroadsPosition = getPosition();

		ArrayList<PedestrianCrossing> pedestrianCrossings = getPedestrianCrossings();
		
		double x = position.getX();
		double y = position.getY();
		
		double dx = x - crossroadsPosition.getX();
		double dy = y - crossroadsPosition.getY();
		
		for (PedestrianCrossing pedestrianCrossing : pedestrianCrossings) {
			Position pedestrianCrossingPosition = pedestrianCrossing.getPosition(); 
			
			double xPedestrianCrossing = pedestrianCrossingPosition.getX();
			double yPedestrianCrossing = pedestrianCrossingPosition.getY();
			
			pedestrianCrossing.setPosition(new Position(xPedestrianCrossing + dx, yPedestrianCrossing + dy));
		}
		
		setPedestrianCrossings(pedestrianCrossings);
		
	}
	
	public void setPanels(Position position) {
		Position crossroadsPosition = getPosition();

		ArrayList<Panel> panels = getPanels();
		
		double x = position.getX();
		double y = position.getY();
		
		double dx = x - crossroadsPosition.getX();
		double dy = y - crossroadsPosition.getY();
		
		for (Panel panel : panels) {
			Position panelPosition = panel.getPosition(); 
			
			double xPanel = panelPosition.getX();
			double yPanel = panelPosition.getY();
			
			panel.setPosition(new Position(xPanel + dx, yPanel + dy));
		
		}
		
		setPanels(panels);
		
	}
	
	@Override
	public void setPosition(Position position) {
		setPedestrianCrossings(position);
		setPanels(position);
		super.setPosition(position);
	}

}
