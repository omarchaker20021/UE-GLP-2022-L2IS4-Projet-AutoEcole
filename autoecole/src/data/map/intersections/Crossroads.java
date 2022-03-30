package data.map.intersections;

import java.util.ArrayList;

import config.GameConfig;
import data.geometry.Position;
import data.map.Line;

public class Crossroads extends Intersection {

	/** Un carrefour de quatre routes  **/
	public static final int FOUR_PEDESTRIAN_CROSSINGS = 4;
	/** Un carrefour de trois routes  **/
	public static final int THREE_PEDESTRIAN_CROSSINGS = 3;
	
	
	private ArrayList<PedestrianCrossing> pedestrianCrossings;

	public Crossroads(Position position, ArrayList<PedestrianCrossing> pedestrianCrossings) {
		super(position);
		this.pedestrianCrossings = pedestrianCrossings;
	}

	@Override
	public Line getLine() {
		return null;
	}

	@Override
	public void setLine(Line line) {}

	public ArrayList<PedestrianCrossing> getPedestrianCrossings() {
		return pedestrianCrossings;
	}

	public void setPedestrianCrossings(ArrayList<PedestrianCrossing> pedestrianCrossings) {
		this.pedestrianCrossings = pedestrianCrossings;
	}
	
	public void setPedestrianCrossings(Position position) {
		PedestrianCrossing pC1 = pedestrianCrossings.get(0);
		PedestrianCrossing pC2 = pedestrianCrossings.get(1);
		PedestrianCrossing pC3 = pedestrianCrossings.get(2);
		PedestrianCrossing pC4 = pedestrianCrossings.get(3);
		
		double x = position.getX();
		double y = position.getY();
		
		
		pC1.setPosition(new Position(x - GameConfig.PEDESTRIAN_CROSSING_HEIGHT, y));
		pC2.setPosition(new Position(x, y - GameConfig.PEDESTRIAN_CROSSING_HEIGHT));
		pC3.setPosition(new Position(x + GameConfig.ROAD_WIDTH, y));
		pC4.setPosition(new Position(x, y + GameConfig.ROAD_WIDTH));
		
		/*pedestrianCrossings.add(pC1);
		pedestrianCrossings.add(pC2);
		pedestrianCrossings.add(pC3);
		pedestrianCrossings.add(pC4);*/
		
	}
	
	@Override
	public void setPosition(Position position) {
		setPedestrianCrossings(position);
		super.setPosition(position);

	}

}
