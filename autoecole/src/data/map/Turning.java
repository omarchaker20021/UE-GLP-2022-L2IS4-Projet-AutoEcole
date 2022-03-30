package data.map;

import config.GameConfig;
import data.geometry.Position;

/**
 * 
 * Classe commentée
 * 
 * This class represents a Turning.
 * 
 * @see Road 
 * 
 * @author Rayane KHAMAILY
 * */

public class Turning extends Road {
	

	/** Constant for the turning which means vertical road to right horizontal road (default situation)**/
	public final static int VR_TO_RHR = 2;
	/** Constant for the turning which means vertical road to left horizontal road **/
	public final static int VR_TO_LHR = 3;
	/** Constant for the turning which means right horizontal road to vertical road **/
	public final static int RHR_TO_VR = 4;
	/** Constant for the turning which means left horizontal road to vertical road **/
	public final static int LHR_TO_VR = 5;
	
	/** Starting angle of turning.
	 * This attribute help us to draw the {@link Turning} and its {@link Line} from a fictional circle. 
	 * 
	 * @see PaintStrategy
	 * 
	 * **/
	private int angleStart;
	
	
	
	//Turning position will be at the top left corner of the square reserved
	//to the turning to be drawn
	
	
	public Turning(Position position, Line line) {
		this(position, VR_TO_RHR, line);
	}
	
	public Turning(Position position, int axis, Line line) {
		super(position);
		setAxis(axis);
		setAngleStart(axis);
		super.setLine(line);
		this.setLine();
	}
	
	@Override
	public void setPosition(Position position) {
		super.setPosition(position);
		setLine();
	}
	
	
	/**
	 * This method overrides the method in the super class {@link Road}
	 * and it calculates the {@link Position} of the {@link Turning} in function of {@link Turning} position 
	 * 
	 * @see Road
	 * 
	 * **/
	@Override
	public void setLine() {
		Line line = getLine();
		
		Position linePosition = line.getPosition();
		
		double x = this.getPosition().getX();
		double y = this.getPosition().getY();
		
	}

	public int getAngleStart() {
		return angleStart;
	}

	private void setAngleStart(int axis) {
		switch (axis){
		case VR_TO_RHR:
			this.angleStart = 90;
			break;
		case VR_TO_LHR:
			this.angleStart = 0;
			break;
		case RHR_TO_VR:
			this.angleStart = 180;
			break;
		case LHR_TO_VR:
			this.angleStart = -90;
			break;
		
		}
	}
}
