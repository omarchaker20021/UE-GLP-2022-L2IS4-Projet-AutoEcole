package data;

import java.util.ArrayList;

import config.GameConfig;

public class Map {
	
	private ArrayList<Road> roads;
	private ArrayList<Panel> panels;

	public Map(ArrayList<Road> roads, ArrayList<Panel> panels) {
		this.roads = roads;
		this.panels = panels;
	}
	
	public Map() {}
	
	
	
	public ArrayList<Road> getRoads() {
		return roads;
	}

	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}

	public ArrayList<Panel> getPanels() {
		return panels;
	}

	public void setPanels(ArrayList<Panel> panels) {
		this.panels = panels;
	}

	public boolean isOnLeftBorder(Position position) {
		int x = position.getX();
		
		return x == 0;
	}
	
	public boolean isOnRightBorder(Position position) {
		int x = position.getX();
		
		return x == GameConfig.MAP_WIDTH;
	}
	public boolean isOnTopBorder(Position position) {
		int y = position.getY();
		
		return y == 0;
	}
	
	public boolean isOnBottomBorder(Position position) {
		int y = position.getY();
		
		return y == GameConfig.MAP_HEIGHT;
		
	}
	

}
