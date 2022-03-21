package process;

import java.util.ArrayList;

import config.GameConfig;
import data.geometry.Position;
import data.geometry.Vector;
import data.map.Map;
import data.map.Road;
import data.mobile.Car;

public class MobileElementManager {
	
	private Car car;
	
	private Vector rotationVector = new Vector();
	
	private int distance;
		
	private Map map;

	public MobileElementManager(Map map) {
		this.map = map;
		this.rotationVector = new Vector(GameConfig.MOVE_INTERVAL, 0);
		this.distance = 0;
	}
	
	public void set(ArrayList<Road> roads) {
		this.map.setRoads(roads);
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
		
		double carPace = car.getPace();
		if(carPace < 1) {
			moveMap(GameConfig.MOVE_INTERVAL, 0);
		}
	}
	
	public void turnRight() {
		
		double carPace = car.getPace();
		if(carPace < 1) {
			moveMap(GameConfig.MOVE_INTERVAL ,0);
		}
	}
	
	public void moveCar() {
		double pace = car.getPace();
		if((int) pace != 0) {
			double theta = rotationVector.getTheta();
			
			double x = pace * Math.cos(Math.toRadians(-theta + 90));
			double y = pace * Math.sin(Math.toRadians(-theta + 90));
			
			moveMap(x, y);
			this.distance += pace;
		}
	}
	
	
	private void moveMap(double x, double y) {
		
		ArrayList<Road> roads = this.map.getRoads();

		
		for (Road road : roads) {
			Position position = road.getPosition();
			
			double xRoad = position.getX();
			double yRoad = position.getY();
			
			position.setX(xRoad + x);
			position.setY(yRoad + y);
			
			road.setPosition(position);
		}
		
		set(roads);
		
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

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

}