package process;

import config.GameConfig;
import data.Car;
import data.Line;
import data.Map;
import data.Panel;
import data.Position;
import data.Road;
import exceptions.AxisException;
import exceptions.PanelNameException;

import java.util.ArrayList;

public class GameBuilder {
	
	public static Map buildMap() {
		ArrayList<Road> roads = new ArrayList<Road>();
		ArrayList<Panel> panels = new ArrayList<Panel>();
		
		
		// On va initialiser un parcours composé de 4 routes (2 verticales et 2 horizontales)
		int x0 = GameConfig.VERTICAL_ROAD_POSITION_X;
		int y0 = GameConfig.VERTICAL_ROAD_POSITION_Y;
		
		Position positionRoad1 = new Position(x0, y0);
		Position positionRoad2 = new Position(x0 - GameConfig.ROAD_HEIGHT + 200, y0);
		Position positionRoad3 = new Position(x0 - GameConfig.ROAD_HEIGHT + 200, y0);
		Position positionRoad4 = new Position(x0 - GameConfig.ROAD_HEIGHT + 200, y0 + GameConfig.ROAD_HEIGHT - 200);
		//On affiche un panneau stop
		int xStop = GameConfig.STOP_POSITION_X;
		int yStop = GameConfig.STOP_POSITION_Y;
		Position stopPosition = new Position(xStop, yStop);
		
		Road road1;
		Line line1 = new Line();
		Road road2;
		Line line2 = new Line();
		Road road3;
		Line line3 = new Line();
		Road road4;
		Line line4 = new Line();
		
		Panel stop;
		
		try {
			road1 = new Road(positionRoad1, Road.VERTICAL_AXIS, line1);
			road2 = new Road(positionRoad2, Road.HORIZONTAL_AXIS, line2);
			road3 = new Road(positionRoad3, Road.VERTICAL_AXIS, line3);
			road4 = new Road(positionRoad4, Road.HORIZONTAL_AXIS, line4);
			
			roads.add(road1);
			roads.add(road2);
			roads.add(road3);
			roads.add(road4);
			
			stop = new Panel("stop", stopPosition);
			
			panels.add(stop);
		
		}
		catch (AxisException | PanelNameException e) {
			e.printStackTrace();
		}
		
		return new Map(roads, panels);
	}

	public static MobileElementManager buildInitMobile(Map map) {
		MobileElementManager manager = new MobileElementManager(map);
		
		initializeCar(manager);
		
		return manager;
	}
	
	private static void initializeCar(MobileElementManager manager){
		Position position = new Position(GameConfig.CAR_POSITION_X, GameConfig.CAR_POSITION_Y);
		
		Car car = new Car(position);
		
		manager.set(car);
		
	}

}
