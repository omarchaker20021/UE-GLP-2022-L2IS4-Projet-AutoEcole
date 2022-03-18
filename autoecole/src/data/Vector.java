package data;


	/**
	 * Cette classe est une classe de données sur un vecteur
	 * @author Omar CHAKER
	 * */

public class Vector {
	
	/**
	 * Les composants essentiels d'un vecteur
	 * */
	
	
	private double x;
	private double y;
	private double length;
	private double theta;
	
	
	public Vector(double x, double y, double length, double theta) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.theta = theta;
	}
	
	public Vector(double length, double theta) {
		this(0, 0, length, theta);
	}
	
	public Vector() {
		this(0, 0);
	}
	
	
	public double getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta(double d) {
		this.theta = d;
	}

}