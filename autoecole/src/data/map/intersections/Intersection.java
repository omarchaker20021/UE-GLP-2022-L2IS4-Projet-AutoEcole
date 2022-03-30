package data.map.intersections;

import java.util.ArrayList;

import data.geometry.Position;
import data.map.Line;
import data.map.CityElement;
import data.map.Panel;

public abstract class Intersection extends CityElement {
	
	private ArrayList<Panel> panels;
	
	
	public Intersection(Position position) {
		super(position);
	}

	
	public ArrayList<Panel> getPanels(){
		return this.panels;
	}
	
	public void setPanels(ArrayList<Panel> panels) {
		this.panels = panels;
	}
	
	public abstract Line getLine();

	public abstract void setLine(Line line);

}
