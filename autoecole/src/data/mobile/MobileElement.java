package data.mobile;

import data.geometry.Position;
import data.map.MapElement;

/**
	 * Classe abstraite qui représente un element mobile
	 * @author Omar CHAKER
	 * */

public abstract class MobileElement extends MapElement {
	
	private double vitesse;
	
	public MobileElement(Position position) {
		super(position);
		this.vitesse = 0;
	}

	public double getVitesse() {
		return vitesse;
	}

	public void setVitesse(double vitesse) {
		this.vitesse = vitesse;
	}
	
}
