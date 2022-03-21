package data.geometry;


/**
 * Classe qui represente la position des éléments graphiques
 * @author Omar CHAKER
 * */
public class Position {

	private double x;
	private double y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}
	public void setX(double d) {
		this.x = d;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	
}
