package data;

public abstract class MapElement {

	private Position position;
	
	public MapElement(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}


}
