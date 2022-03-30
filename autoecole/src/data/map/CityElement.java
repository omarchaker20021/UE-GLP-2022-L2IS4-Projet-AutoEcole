package data.map;

import data.geometry.Position;

public abstract class CityElement {

	private Position position;
	
	public CityElement(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}


}
