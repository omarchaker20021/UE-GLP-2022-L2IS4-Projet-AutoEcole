package process;

import java.util.ArrayList;

import data.geometry.Position;
import data.map.City;
import data.map.Panel;
import data.mobile.Car;

public class RulesManager {

	private City city;
	
	private Car car;
	
	private int errors;
	
	public RulesManager(City city) {
		this.city = city;
	}
	
	/**
	 * This method verifies if the rule of a stop {@link Panel} is respected.
	 * */
	
	public boolean isStopRespected(Position stopPosition) {
		
		Position carPosition = car.getPosition();
		double yCar = carPosition.getY();
		
		if(Utility.doesPanelExists(city, stopPosition, Panel.STOP_PANEL)) {
			double y = stopPosition.getY();
			
			while(yCar < y + 100 && yCar > y) {
				
				int pace = (int)car.getPace();
				
				if(pace == 0) {
					return true;
				}
			}

		}
		return false;
	}
	
	public boolean isCarOnRoad() {
		Position carPosition = car.getPosition();
		
		if(!city.isCarOnBottomBorder(carPosition)
				&& !city.isCarOnLeftBorder(carPosition)
				&& !city.isCarOnRightBorder(carPosition)
				&& !city.isCarOnTopBorder(carPosition)) {
			return false;
		}
		return true;
	}
}
