package process;

import config.GameConfig;
import data.geometry.Position;
import data.map.Line;
import data.map.Map;
import data.map.Road;
import data.mobile.Car;
import exceptions.AxisException;

import java.util.ArrayList;

public class GameBuilder {
	
	public static Map buildMap() {
		ArrayList<Road> roads = new ArrayList<Road>();
		
		double x0 = GameConfig.VERTICAL_ROAD_POSITION_X;
		double y0 = GameConfig.MAP_HEIGHT - GameConfig.ROAD_WIDTH;
		
		Position position = new Position(x0, y0);
		Position position2 = new Position(x0, y0 - GameConfig.ROAD_HEIGHT);
		
		//Utilisation de la méthode generateRoads()
		
		int nbVerticalRoads = 1;
		int nbHorizontalRoads = 1;
		
		roads = generateRoads(nbVerticalRoads, nbHorizontalRoads, position);
		roads.addAll(generateRoads(nbVerticalRoads, nbHorizontalRoads, position2));
		
		return new Map(roads);
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
	
	/**
	 * This static method generates horizontal and vertical roads
	 * from an initial position
	 * @param nbVerticalRoads
	 * 							number of vertical roads
	 * @param nbHorizontalRoads
	 *							number of horizontal roads
	 * @param roadsPosition
	 * 						initial position of the generated roads
	 * @exception AxisException
	 * 							this exception concerns the axis when constructing roads
	 * @return roads
	 * 				an ArrayList which contains all roads generated
	 * */
	
	
	private static ArrayList<Road> generateRoads(int nbVerticalRoads, int nbHorizontalRoads, Position roadsPosition){
		
		ArrayList<Road> roads = new ArrayList<Road>();
		
		Position roadPosition = new Position(roadsPosition.getX(), roadsPosition.getY());
		
		
		for(int iteration = 0; iteration < nbVerticalRoads; iteration++) {

			double y = roadPosition.getY();
			
			y -= (GameConfig.ROAD_HEIGHT + GameConfig.ROAD_WIDTH);
			
			roadPosition.setY(y);
			
			Line line = new Line();
			try {
				roads.add(new Road(new Position(roadPosition.getX(), roadPosition.getY()), Road.VERTICAL_AXIS, line));
			} catch (AxisException e) {
				e.printStackTrace();
			}
			
		}
		
		roadPosition.setX(roadsPosition.getX());
		roadPosition.setY(roadsPosition.getY());
		
		double y = roadPosition.getY();
		
		y -= GameConfig.ROAD_WIDTH;
		
		
		for(int iteration = 0; iteration < nbHorizontalRoads; iteration++) {
			
			double x = roadPosition.getX();
			
			x -= GameConfig.ROAD_HEIGHT;
			
			roadPosition.setX(x);
			roadPosition.setY(y);
			
			Line line = new Line();
	
			try {
				roads.add(new Road(new Position(roadPosition.getX(), roadPosition.getY()), Road.HORIZONTAL_AXIS, line));
			} catch (AxisException e) {
				e.printStackTrace();
			}
		}
		
		return roads;
	}
	
	/**
	 * This static method generates a roundabout from an initial position.
	 * 
	 * @param roundaboutPosition
	 * 						initial position of the roundabout.
	 * @return roundabout
	 * 				an ArrayList which contains all roads generated
	 * */
	/*
	private static void generateRoundabout() {
		
	}*/
}
