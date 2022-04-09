package data.map;

import java.util.ArrayList;

import config.GameConfig;
import data.geometry.Position;
import data.map.intersections.*;
import data.mobile.Car;

public class City {
	
	private ArrayList<Road> roads;
	private ArrayList<Extension> extensions;
	private ArrayList<Crossroads> crossroadss;
	private ArrayList<Turning> turnings;
	private ArrayList<Roundabout> roundabouts;
	
	private Car secondaryCar;

	public City(ArrayList<Road> roads, 
			ArrayList<Turning> turnings, 
			ArrayList<Crossroads> crossroadss, 
			ArrayList<Extension> extensions) {
		
		this.roads = roads;
		this.turnings = turnings;
		this.crossroadss = crossroadss;
		this.extensions = extensions;
	
	}
	
	public City(ArrayList<Road> roads, 
			ArrayList<Turning> turnings, 
			ArrayList<Crossroads> crossroadss, 
			ArrayList<Extension> extensions, 
			Car secondaryCar) {
		this(roads, turnings, crossroadss, extensions);
		
		this.secondaryCar = secondaryCar;
	}
	
	
	
	public ArrayList<Road> getRoads() {
		return roads;
	}

	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}
	
	public ArrayList<Roundabout> getRoundabouts() {
		return roundabouts;
	}

	public void setRoundabouts(ArrayList<Roundabout> roundabouts) {
		this.roundabouts = roundabouts;
	}
	public boolean isCarOnLeftBorder(Position position) {
		double x = position.getX();
		
		return x == 0;
	}
	
	public boolean isCarOnRightBorder(Position position) {
		double x = position.getX();
		
		return x >= GameConfig.MAP_WIDTH - GameConfig.CAR_WIDTH;
	}
	
	public boolean isCarOnTopBorder(Position position) {
		double y = position.getY();
		
		return y == 0;
	}
	
	public boolean isCarOnBottomBorder(Position position) {
		double y = position.getY();
		
		return y == GameConfig.MAP_HEIGHT - GameConfig.CAR_HEIGHT;
		
	}

	public ArrayList<Turning> getTurnings() {
		return turnings;
	}

	public void setTurnings(ArrayList<Turning> turnings) {
		this.turnings = turnings;
	}

	public ArrayList<Crossroads> getCrossroadss() {
		return crossroadss;
	}

	public void setCrossroadss(ArrayList<Crossroads> crossroadss) {
		this.crossroadss = crossroadss;
	}

	public boolean doesPositionExists(Position position) {
		
		double x = position.getX();
		double y = position.getY();
		
		for (Road road : roads) {
			
			Position roadPosition = road.getPosition();
			
			double xR = roadPosition.getX();
			double yR = roadPosition.getY();
			
			if(xR == x 
					&& yR == y) {
				
				return true;
			}
			
			
		}
		
		for (Turning turning : turnings) {
			Position turningPosition = turning.getPosition();
			
			double xT = turningPosition.getX();
			double yT = turningPosition.getY();
			
			if(xT == x 
					&& yT == y) {
				
				return true;
			}
			
		}
		
		for (Crossroads crossroads : crossroadss) {
			Position crossroadsPosition = crossroads.getPosition();
			
			double xC = crossroadsPosition.getX();
			double yC = crossroadsPosition.getY();
			
			if(xC == x 
					&& yC == y) {
				
				return true;
			}
		}
		
		return false;
	}

	public ArrayList<Extension> getExtensions() {
		return extensions;
	}

	public void setExtensions(ArrayList<Extension> extensions) {
		this.extensions = extensions;
	}

	public Car getSecondaryCar() {
		return secondaryCar;
	}

	public void setSecondaryCar(Car secondaryCar) {
		this.secondaryCar = secondaryCar;
	}

}
