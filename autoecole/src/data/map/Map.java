package data.map;

import java.util.ArrayList;

import config.GameConfig;
import data.geometry.Position;
import data.map.intersections.Intersection;

public class Map {
	
	private ArrayList<Road> roads;
	private ArrayList<Intersection> intersections;

	public Map(ArrayList<Road> roads) {
		this.roads = roads;
	}
	
	public Map() {}
	
	
	
	public ArrayList<Road> getRoads() {
		return roads;
	}

	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
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
	

}
