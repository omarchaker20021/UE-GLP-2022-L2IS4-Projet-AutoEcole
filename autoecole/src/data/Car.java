package data;

public class Car extends MobileElement {
	
	private double pace;

	public Car(Position position) {
		super(position);
		this.pace = 0;
	}

	public double getPace() {
		return pace;
	}

	public void setPace(double pace) {
		this.pace = pace;
	}

}
