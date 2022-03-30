package process;

import java.util.ArrayList;

import config.GameConfig;
import data.geometry.Position;
import data.geometry.Vector;
import data.map.City;
import data.map.Road;
import data.map.Turning;
import data.map.intersections.Crossroads;
import data.mobile.Car;

public class MobileElementManager {
	
	private Car car;
	
	private Vector rotationVector = new Vector();
	
	private double distance;
		
	private City city;

	public MobileElementManager(City city) {
		this.city = city;
		this.rotationVector = new Vector(GameConfig.MOVE_INTERVAL, 0);
		this.distance = 0;
	}
	
	public void set(ArrayList<Road> roads, ArrayList<Turning> turnings, ArrayList<Crossroads> crossroadss) {
		this.city.setRoads(roads);
		this.city.setTurnings(turnings);
		this.city.setCrossroadss(crossroadss);
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
			moveCity(GameConfig.MOVE_INTERVAL, 0);
		}
	}
	
	public void turnRight() {
		
		double carPace = car.getPace();
		if(carPace < 1) {
			moveCity(GameConfig.MOVE_INTERVAL ,0);
		}
	}
	
	public void moveCar() {
		double pace = car.getPace();
		if((int) pace != 0) {
			double theta = rotationVector.getTheta();
			
			double x = pace * Math.cos(Math.toRadians(-theta + 90));
			double y = pace * Math.sin(Math.toRadians(-theta + 90));
			
			moveCity(x, y);
			this.distance += pace / 6000;
		}
	}
	
	
	private void moveCity(double x, double y) {
		
		ArrayList<Road> roads = this.city.getRoads();
		ArrayList<Turning> turnings = this.city.getTurnings();
		ArrayList<Crossroads> crossroadss = this.city.getCrossroadss();

		
		for (Road road : roads) {
			Position position = road.getPosition();
			
			double xRoad = position.getX();
			double yRoad = position.getY();
			
			position.setX(xRoad + x);
			position.setY(yRoad + y);
			
			road.setPosition(position);
		}
		
		for (Turning turning : turnings) {
			Position position1 = turning.getPosition();
			
			double xTurning = position1.getX();
			double yTurning = position1.getY();
			
			position1.setX(xTurning + x);
			position1.setY(yTurning + y);
			
			turning.setPosition(position1);
		}
		
		for (Crossroads crossroads : crossroadss) {
			Position position2 = crossroads.getPosition();
			
			double xCrossroads = position2.getX();
			double yCrossroads = position2.getY();
			
			position2.setX(xCrossroads + x);
			position2.setY(yCrossroads + y);
			
			crossroads.setPosition(position2);
		}
		
		set(roads, turnings, crossroadss);
		
	}
	
	
	public Car getCar() {
		return this.car;
	}
	public City getCity() {
		return this.city;
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

	public double getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

}