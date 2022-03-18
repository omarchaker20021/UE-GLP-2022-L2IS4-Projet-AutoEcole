package data;

import config.GameConfig;
import exceptions.AxisException;

/**
 * Classe qui represente une route
 * @author Rayane KHAMAILY
 * */

public class Road extends MapElement {
	
	
	/** This constant defines the axis of the road which is vertical **/
	public final static char VERTICAL_AXIS = 'V';
	/** This constant defines the axis of the road which is horizontal **/
	public final static char HORIZONTAL_AXIS = 'H';
	
	private char roadAxis;
	private Line line;
	
	public Road(Position position, Line line) {
		super(position);
		this.roadAxis = VERTICAL_AXIS;
		this.line = line;
		setLine();
	}
	
	public Road(Position position, char axis, Line line) throws AxisException {
		super(position);
		if(axis == VERTICAL_AXIS || axis == HORIZONTAL_AXIS) {
			this.roadAxis = axis;
			this.line = line;
			setLine();
		}
		else
			throw new AxisException();
	}

	public void setPosition(Position position) {
		super.setPosition(position);
		setLine();
	}
	
	private void setLine(){
		Position linePosition = this.line.getPosition();
		
		double x = this.getPosition().getX();
		double y = this.getPosition().getY();
		
		if(this.roadAxis == VERTICAL_AXIS) {
			linePosition.setX(x + (GameConfig.ROAD_WIDTH/2) - (GameConfig.LINE_WIDTH/2));
			linePosition.setY(y);
		}
		
		if(this.roadAxis == HORIZONTAL_AXIS) {
			linePosition.setX(x);
			linePosition.setY(y + (GameConfig.ROAD_WIDTH/2) - (GameConfig.LINE_WIDTH/2));
		}
		
		this.line.setPosition(linePosition);
		
	}
	
	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}
	
	
	public char getRoadAxis() {
		return this.roadAxis;
	}

}
