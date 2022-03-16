package process;

import java.util.ArrayList;
import java.util.List;

import config.GameConfig;
import data.*;

public class MobileElementManager {
	
	private Car car;
	
	private Map map;

	public MobileElementManager(Map map) {
		this.map = map;
	}
	
	public void set(List<Panel> panels, List<Road> roads) {
		this.map.setPanels((ArrayList<Panel>) panels);
		this.map.setRoads((ArrayList<Road>) roads);
	}
	
	public void set(Car car) {
		this.car = car;
	}
	
	public void moveStraight() {
		
		ArrayList<Road> roads = this.map.getRoads();
		ArrayList<Panel> panels = this.map.getPanels();

		
		Position carPosition = car.getPosition();
		
		int yCar = carPosition.getY(); 
		
		for (Road road : roads) {
			Position position = road.getPosition();
			
			int yRoad = position.getY();
			
			if (yCar <= GameConfig.CITY_HEIGHT){
				position.setY(yRoad + GameConfig.MOVE_INTERVAL);
				road.setPosition(position);
			}
		}
		
		for(Panel panel : panels) {
			Position position = panel.getPosition();
			
			int yPanel = position.getY();
			
			if (yCar <= GameConfig.CITY_HEIGHT){
				position.setY(yPanel + GameConfig.MOVE_INTERVAL);
				panel.setPosition(position);
			}
		}
		set(panels, roads);
		
	}
	
	public void turnLeft() {
		Position position = car.getPosition();
		
		int x = position.getX();
		
		if(x >= GameConfig.VERTICAL_ROAD_POSITION_X + GameConfig.MOVE_INTERVAL) {
			position.setX(x - GameConfig.MOVE_INTERVAL);
			car.setPosition(position);
		}
		
	}
	
	public void turnRight() {
		Position position = car.getPosition();
		
		int x = position.getX();
		
		if(x <= GameConfig.VERTICAL_ROAD_POSITION_X + GameConfig.ROAD_WIDTH - GameConfig.MOVE_INTERVAL) {
			position.setX(x + GameConfig.MOVE_INTERVAL);
			car.setPosition(position);
		}
		
	}
	
	public void backReverse() {
		List<Panel> panels = this.map.getPanels();
		List<Road> roads = this.map.getRoads();
		
		Position carPosition = car.getPosition();
		
		int yCar = carPosition.getY(); 
		
		if(yCar < GameConfig.ROAD_HEIGHT) {
			for (Road road : roads) {
				
				Position position = road.getPosition();
				int yRoad = position.getY();
				position.setY(yRoad + GameConfig.MOVE_INTERVAL);
				road.setPosition(position);
			
			}
			
			for(Panel panel : panels) {
				
				Position position = panel.getPosition();
				int yPanel = position.getY();
				position.setY(yPanel + GameConfig.MOVE_INTERVAL);
				panel.setPosition(position);
				
			}	
		}
	}
	
	public Car getCar() {
		return this.car;
	}
	public Map getMap() {
		return this.map;
	}

}