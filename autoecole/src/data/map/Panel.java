package data.map;

import data.geometry.Position;
import exceptions.PanelNameException;


public class Panel extends CityElement {
	
	
	/**
	 * Cette classe est une classe de données d'un panneau de signalisation
	 * */
	
	public static final int STOP_PANEL = 0;
	public static final int SPEED_LIMIT_PANEL = 1;
	public static final int PANEL2 = 2;
	
	private int type;

	public Panel(Position position) {
		super(position);
		this.type = STOP_PANEL;
	}
	
	public Panel(int type, Position position) throws PanelNameException {
		super(position);
		if(type == STOP_PANEL) {
			this.type = type;
		}
		else {
			throw new PanelNameException();
		}
	}
	
	public Panel(int type) throws PanelNameException {
		this(type, new Position(0,0));
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	

}
