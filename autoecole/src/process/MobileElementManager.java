package process;

import java.util.ArrayList;
import java.util.List;

import config.GameConfig;
import data.*;

public class MobileElementManager {
	
	private Car car;
	
	private Vector rotationVector = new Vector();
		
	private Map map;

	public MobileElementManager(Map map) {
		this.map = map;
		this.rotationVector = new Vector(GameConfig.MOVE_INTERVAL, 0);
	}
	
	public void set(List<Panel> panels, List<Road> roads) {
		this.map.setPanels((ArrayList<Panel>) panels);
		this.map.setRoads((ArrayList<Road>) roads);
	}
	
	public void set(Car car) {
		this.car = car;
	}
	
	public void accelerate() {
		
		double length = rotationVector.getLength();
		
		double pace = car.getPace();
		pace += length;
		car.setPace(pace);
	}
	
	public void decelerate() {
		
		double length = rotationVector.getLength();
		
		double pace = car.getPace();
		pace -= length;
		car.setPace(pace);
		
	}
	
	public void turnLeft() {
		moveMap(GameConfig.MOVE_INTERVAL, 0);
	}
	
	public void turnRight() {
		moveMap(GameConfig.MOVE_INTERVAL ,0);
	}
	
	public void moveCar() {
		
		double theta = rotationVector.getTheta();
		double pace = car.getPace();
		
		double x = pace * Math.cos(Math.toRadians(-theta + 90));
		double y = pace * Math.sin(Math.toRadians(-theta + 90));
		
		moveMap(x, y);
	}
	
	
	private void moveMap(double x, double y) {
		
		ArrayList<Road> roads = this.map.getRoads();
		ArrayList<Panel> panels = this.map.getPanels();

		
		for (Road road : roads) {
			Position position = road.getPosition();
			
			double xRoad = position.getX();
			double yRoad = position.getY();
			
			position.setX(xRoad + x);
			position.setY(yRoad + y);
			
			road.setPosition(position);
		}
		
		for(Panel panel : panels) {
			Position position = panel.getPosition();
			
			double xPanel = position.getX();
			double yPanel = position.getY();
			
			position.setX(xPanel + x);
			position.setY(yPanel + y);
			
			panel.setPosition(position);
		}
		set(panels, roads);
		
	}
	
	public Car getCar() {
		return this.car;
	}
	public Map getMap() {
		return this.map;
	}
	
	public void setRotationVector(Vector rotationVector) {
		this.rotationVector = rotationVector;
	}
	
	public Vector getRotationVector(){
		return this.rotationVector;
	}
	
	public void setRotationDegrees(double rotationDegrees) {
		rotationVector.setTheta(rotationDegrees);
	}
	public double getRotationDegrees() {
		return rotationVector.getTheta();
	}

}