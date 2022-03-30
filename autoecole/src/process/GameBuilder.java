package process;

import config.GameConfig;
import data.geometry.Position;
import data.map.Line;
import data.map.City;
import data.map.Road;
import data.map.Turning;
import data.map.intersections.Crossroads;
import data.map.intersections.PedestrianCrossing;
import data.mobile.Car;
import data.mobile.MobileElement;

import java.util.ArrayList;



/**
 * 
 * Classe commentée
 * 
 * This class initialize the graphic elements of the game
 * 
 * @author Omar CHAKER
 * 
 * */

public class GameBuilder {
	
	
	/**
	 * This static method initialize the {@link City} of our simulation.
	 * **/
	
	public static City buildCity() {
		ArrayList<Road> roads = new ArrayList<Road>();
		
		ArrayList<Turning> turnings = new ArrayList<Turning>();
		
		ArrayList<Crossroads> crossroadss = new ArrayList<Crossroads>();
		
		double x0 = GameConfig.VERTICAL_ROAD_POSITION_X;
		double y0 = GameConfig.MAP_HEIGHT - GameConfig.ROAD_WIDTH;
		
		Position position = new Position(x0, y0);
		
		int nbVerticalRoads = 1;
		int nbHorizontalRoads = 1;
		
		
		
		//Generate vertical and horizontal roads
		
		Position position2 = new Position(x0 - GameConfig.ROAD_HEIGHT - GameConfig.ROAD_WIDTH, y0 - GameConfig.ROAD_HEIGHT - GameConfig.ROAD_WIDTH);
		
		
		roads.addAll(generateRoads(nbVerticalRoads, nbHorizontalRoads, position));
		
		roads.addAll(generateRoads(nbVerticalRoads, nbHorizontalRoads, position2));
		
		
		//Creation of last horizontal and vertical roads of the map(and the turning which link them).
		
		Position lastHorizontalRoadPosition = new Position(x0, y0 - GameConfig.ROAD_HEIGHT - GameConfig.ROAD_WIDTH);
		Position lastVerticalRoadPosition = new Position(x0 - GameConfig.ROAD_HEIGHT - GameConfig.ROAD_WIDTH, y0);
		
		roads.addAll(generateRoads(0, 1, lastHorizontalRoadPosition));
		roads.addAll(generateRoads(1, 0, lastVerticalRoadPosition));
		
		
		//City creation
		
		City city = new City(roads, turnings, crossroadss);
		
		// Generate a crossroads at the position of intersection of 4 roads.
		Position crossroadsPosition = new Position(x0 - GameConfig.ROAD_HEIGHT - GameConfig.ROAD_WIDTH, y0 - GameConfig.ROAD_HEIGHT - GameConfig.ROAD_WIDTH); 
				
		crossroadss.add(generateCrossroads(city, crossroadsPosition));
		
		//Automatic generation of turnings
		
		turnings.addAll(generateTurnings(city));
		
		city.setTurnings(turnings);
		
		return city;
	}

	/**
	 * This static method initialize all {@link MobileElement}s of our simulation.
	 * 
	 * @param city
	 * 				The {@link City} of the simulation.
	 * @return manager
	 * 					A {@link MobileElementManager} object.
	 * **/
	
	public static MobileElementManager buildInitMobile(City city) {
		MobileElementManager manager = new MobileElementManager(city);
		
		initializeCar(manager);
		
		return manager;
	}
	
	private static void initializeCar(MobileElementManager manager){
		Position position = new Position(GameConfig.CAR_POSITION_X, GameConfig.CAR_POSITION_Y);
		
		Car car = new Car(position);
		
		manager.set(car);
		
	}
	
	/**
	 * This static method generates horizontal and vertical {@link Road}s
	 * from an initial {@link Position}.
	 * @param nbVerticalRoads
	 * 							number of vertical {@link Road}s.
	 * @param nbHorizontalRoads
	 *							number of horizontal {@link Road}s.
	 * @param roadsPosition
	 * 						initial {@link Position} of the generated {@link Road}s.
	 * @return roads
	 * 				an {@link ArrayList} of {@link Road}s which contains all {@link Road}s generated.
	 * */
	
	
	private static ArrayList<Road> generateRoads(int nbVerticalRoads, int nbHorizontalRoads, Position roadsPosition){
		
		ArrayList<Road> roads = new ArrayList<Road>();
		
		Position roadPosition = new Position(roadsPosition.getX(), roadsPosition.getY());
		
		
		for(int iteration = 0; iteration < nbVerticalRoads; iteration++) {

			double y = roadPosition.getY();
			
			y -= GameConfig.ROAD_HEIGHT;
			
			roadPosition.setY(y);
			
			Line line = new Line();
			roads.add(new Road(new Position(roadPosition.getX(), roadPosition.getY()), Road.VERTICAL_AXIS, line));
			
		}
		
		roadPosition.setX(roadsPosition.getX());
		roadPosition.setY(roadsPosition.getY());
		
		double y = roadPosition.getY();
		
		
		for(int iteration = 0; iteration < nbHorizontalRoads; iteration++) {
			
			double x = roadPosition.getX();
			
			x -= GameConfig.ROAD_HEIGHT;
			
			roadPosition.setX(x);
			roadPosition.setY(y);
			
			Line line = new Line();
	
			roads.add(new Road(new Position(roadPosition.getX(), roadPosition.getY()), Road.HORIZONTAL_AXIS, line));
		}
		
		return roads;
	}
	
	/**
	 * This static method generates automatically {@link Turning}s accorded to the {@link Road}s created
	 * in the {@link City}.
	 * 
	 * @param city
	 * 				the {@link City}.
	 * @return turnings
	 * 					{@link Turning}s of all {@link City}.
	 * */
	
	public static ArrayList<Turning> generateTurnings(City city){
		
		ArrayList<Turning> turnings = new ArrayList<Turning>();
		
		ArrayList<Road> roads = city.getRoads();
		
		for (Road road : roads) {
			Position roadPosition = road.getPosition();
			
			ArrayList<Turning> twoTurnings = generateTurning(city, roadPosition);
			
			if(!twoTurnings.isEmpty()) {
				Turning turning1 = twoTurnings.get(0);
				
				Position turning1Position = turning1.getPosition();
				
				if(turning1Position != null && !Utility.doesTurningExists(turnings, turning1Position)) {
					turnings.add(turning1);
				}
			}
			
			if(twoTurnings.size() == 2) {
				Turning turning2 = twoTurnings.get(1);
				Position turning2Position = turning2.getPosition();
			
				if(turning2Position != null && !Utility.doesTurningExists(turnings, turning2Position)) {
					turnings.add(turning2);
				}
			}
			
		}
		
		return turnings;
	}
	
	/**
	 * This static method find the type of {@link Turning} which match with the {@link Road}s
	 * which are created from a {@link Position}.
	 * @param city
	 * 				the {@link City}.
	 * @param position
	 * 					The {@link Position} where are generated roads.
	 * @return turnings
	 * 					A turning or two.
	 * @see Turning
	 * */
	
	public static ArrayList<Turning> generateTurning(City city, Position position) {
		
		
		ArrayList<Road> roads = city.getRoads();
		
		ArrayList<Turning> turnings = new ArrayList<Turning>(2);
		
		double x = position.getX();
		double y = position.getY();
		
		Line line1 = new Line();
		Line line2 = new Line();
		
		int roadType = 2;
		
		if(Utility.doesRoadExists(roads, position)) {
			Road road = Utility.getRoadFromPosition(roads, position);
			roadType = road.getAxis();
			if(roadType == Road.VERTICAL_AXIS) {

				Position positionTopLeftHorizontalRoad = new Position(x - GameConfig.ROAD_HEIGHT, y - GameConfig.ROAD_WIDTH);
				Position positionTopRightHorizontalRoad = new Position(x + GameConfig.ROAD_WIDTH, y - GameConfig.ROAD_WIDTH);
				
				Position positionBottomLeftHorizontalRoad = new Position(x - GameConfig.ROAD_HEIGHT, y + GameConfig.ROAD_HEIGHT);
				Position positionBottomRightHorizontalRoad = new Position(x + GameConfig.ROAD_WIDTH, y + GameConfig.ROAD_HEIGHT);
				
				
				if(Utility.doesRoadExists(roads, positionTopLeftHorizontalRoad)) {
					int turningType = Turning.VR_TO_LHR;
					
					Position turningPosition = new Position(x, y - GameConfig.ROAD_WIDTH);
					if(!city.doesPositionExists(turningPosition)) {
						turnings.add(new Turning(turningPosition, turningType, line1));
					}
				}
				
				else if(Utility.doesRoadExists(roads, positionTopRightHorizontalRoad)) {
					int turningType = Turning.VR_TO_RHR;
					
					Position turningPosition = new Position(x, y - GameConfig.ROAD_WIDTH);
					
					if(!city.doesPositionExists(turningPosition)) {
						turnings.add(new Turning(turningPosition, turningType, line1));
					}
				}
				
				if(Utility.doesRoadExists(roads, positionBottomLeftHorizontalRoad)) {
					int turningType = Turning.LHR_TO_VR;
					
					Position turningPosition = new Position(x, y + GameConfig.ROAD_HEIGHT);
					
					if(!city.doesPositionExists(turningPosition)) {
						turnings.add(new Turning(turningPosition, turningType, line2));
					}
				}
				
				else if(Utility.doesRoadExists(roads, positionBottomRightHorizontalRoad)) {
					int turningType = Turning.RHR_TO_VR;
					
					Position turningPosition = new Position(x, y + GameConfig.ROAD_HEIGHT);
					
					if(!city.doesPositionExists(turningPosition)) {
						turnings.add(new Turning(turningPosition, turningType, line2));
					}
				}

				return turnings;
			}
			else if(roadType == Road.HORIZONTAL_AXIS) {
				
				Position positionLeftTopVerticalRoad = new Position(x - GameConfig.ROAD_WIDTH, y - GameConfig.ROAD_HEIGHT);
				Position positionLeftBottomVerticalRoad = new Position(x - GameConfig.ROAD_WIDTH, y + GameConfig.ROAD_WIDTH);

				Position positionRightTopVerticalRoad = new Position(x + GameConfig.ROAD_HEIGHT, y - GameConfig.ROAD_HEIGHT);
				Position positionRightBottomVerticalRoad = new Position(x + GameConfig.ROAD_HEIGHT, y + + GameConfig.ROAD_WIDTH);
				
				
				if(Utility.doesRoadExists(roads, positionLeftTopVerticalRoad)) {
					int turningType = Turning.RHR_TO_VR;
					
					Position turningPosition = new Position(x - GameConfig.ROAD_WIDTH, y);
					
					turnings.add(new Turning(turningPosition, turningType, line1));
				}
				
				else if(Utility.doesRoadExists(roads, positionLeftBottomVerticalRoad)) {
					int turningType = Turning.VR_TO_RHR;
					
					Position turningPosition = new Position(x - GameConfig.ROAD_WIDTH, y);
					
					if(!city.doesPositionExists(turningPosition)) {
						turnings.add(new Turning(turningPosition, turningType, line1));
					}
				}
				
				
				if(Utility.doesRoadExists(roads, positionRightTopVerticalRoad)) {
					int turningType = Turning.LHR_TO_VR;
					
					Position turningPosition = new Position(x + GameConfig.ROAD_HEIGHT, y);
					
					if(!city.doesPositionExists(turningPosition)) {
						turnings.add(new Turning(turningPosition, turningType, line2));
					}
				}
				
				else if(Utility.doesRoadExists(roads, positionRightBottomVerticalRoad)) {
					int turningType = Turning.VR_TO_LHR;
				
					Position turningPosition = new Position(x + GameConfig.ROAD_HEIGHT, y);
					
					if(!city.doesPositionExists(turningPosition)) {
						turnings.add(new Turning(turningPosition, turningType, line2));
					}
				}
			}	
			return turnings;
		}
		
		return null;
	}
	
	
	/**
	 * This method generates automatically {@link Crossroads}.
	 * 
	 * @param roads
	 * 				an {@link ArrayList} of {@link Road}s.
	 * 
	 * @param roadPosition
	 * 							the {@link Position} of the {@link Crossroads} generated.
	 * 
	 * @param pedestrianCrossingNumber
	 * 									a constant of {@link Crossroads}.
	 * 
	 * @return crossroads
	 * 						the generated {@link Crossroads}.
	 * 
	 * */
	
	private static Crossroads generateCrossroads(City city, Position roadPosition) {
		
		ArrayList<Road> roads = city.getRoads();
		
		ArrayList<PedestrianCrossing> pCs = new ArrayList<PedestrianCrossing>();
		
		double x = roadPosition.getX();
		double y = roadPosition.getY();
		
		/*if(Utility.doesRoadExists(roads, roadPosition)) {
			Road road = Utility.getRoadFromPosition(roads, roadPosition);
			int roadType = road.getAxis();
			
			if(roadType == Road.VERTICAL_AXIS) {
				
				Position TopLeftHorizontalRoadPosition = new Position(x - GameConfig.ROAD_HEIGHT, y - GameConfig.ROAD_WIDTH);
				Position TopRightHorizontalRoadPosition = new Position(x + GameConfig.ROAD_WIDTH, y - GameConfig.ROAD_HEIGHT);
				Position TopVerticalRoadPosition = new Position(x - GameConfig.ROAD_HEIGHT - GameConfig.ROAD_WIDTH, y);
				
				Position BottomLeftHorizontalRoadPosition = new Position(x - GameConfig.ROAD_HEIGHT, y + GameConfig.ROAD_HEIGHT);
				Position BottomRightHorizontalRoadPosition = new Position(x + GameConfig.ROAD_WIDTH, y + GameConfig.ROAD_HEIGHT);
				Position BottomVerticalRoadPosition = new Position(x + GameConfig.ROAD_HEIGHT + GameConfig.ROAD_WIDTH, y);
				
				
				if(Utility.doesRoadExists(roads, TopLeftHorizontalRoadPosition)
						&& Utility.doesRoadExists(roads, TopVerticalRoadPosition)
						&& Utility.doesRoadExists(roads, TopRightHorizontalRoadPosition)) {
					
					//vertical pedestrian crossing at the east of the crossroads position.
					double x1 = roadPosition.getX();
					double y1 = roadPosition.getY();
					
					x1 -= (GameConfig.PEDESTRIAN_CROSSING_HEIGHT + GameConfig.ROAD_WIDTH);
					
					Position pC1Position = new Position(x1, y1);
					int pC1Axis = PedestrianCrossing.VERTICAL_AXIS;
					
					PedestrianCrossing pC1 = new PedestrianCrossing(pC1Position, pC1Axis); 
					
					pCs.add(pC1);
					
					//horizontal pedestrian crossing at the north of the crossroads position.
					
					double x2 = roadPosition.getX();
					double y2 = roadPosition.getY();
					
					y2 -= GameConfig.PEDESTRIAN_CROSSING_HEIGHT;
					
					Position pC2Position = new Position(x2, y2);
					int pC2Axis = PedestrianCrossing.HORIZONTAL_AXIS;
					
					PedestrianCrossing pC2 = new PedestrianCrossing(pC2Position, pC2Axis);
					
					pCs.add(pC2);
					
					//vertical pedestrian crossing at the west of the crossroads position.
					
					double x3 = roadPosition.getX();
					double y3 = roadPosition.getY();
					
					x3 += GameConfig.ROAD_WIDTH;
					
					Position pC3Position = new Position(x3, y3);
					int pC3Axis = PedestrianCrossing.VERTICAL_AXIS;
					
					PedestrianCrossing pC3 = new PedestrianCrossing(pC3Position, pC3Axis);
					
					pCs.add(pC3);
					
					//horizontal pedestrian crossing at the south of the crossroads position.
					
					double x4 = roadPosition.getX();
					double y4 = roadPosition.getY();
					
					y4 += GameConfig.ROAD_WIDTH;
					
					Position pC4Position = new Position(x4, y4);
					int pC4Axis = PedestrianCrossing.HORIZONTAL_AXIS;
					
					PedestrianCrossing pC4 = new PedestrianCrossing(pC4Position, pC4Axis);
					
					pCs.add(pC4);
					
					
				}
				
			}
			
		}*/
		
		
		Position LeftTopVerticalRoadPosition = new Position(x, y - GameConfig.ROAD_HEIGHT);
		Position LeftBottomVerticalRoadPosition = new Position(x, y + GameConfig.ROAD_WIDTH);
		Position RightTopVerticalRoadPosition = new Position(x, y - GameConfig.ROAD_HEIGHT);
		Position RightBottomVerticalRoadPosition = new Position(x, y + GameConfig.ROAD_WIDTH);
		
		
		/*if(Utility.doesRoadExists(roads, TopVerticalRoadPosition)
				&& Utility.doesRoadExists(roads, BottomVerticalRoadPosition)
				&& Utility.doesRoadExists(roads, RightHorizontalRoadPosition)
				&& Utility.doesRoadExists(roads, LeftHorizontalRoadPosition)) {
			*/
			//Generate pedestrian crossings.
			
			//vertical pedestrian crossing at the east of the crossroads position.
			double x1 = roadPosition.getX();
			double y1 = roadPosition.getY();
			
			x1 -= GameConfig.PEDESTRIAN_CROSSING_HEIGHT;
			
			Position pC1Position = new Position(x1, y1);
			int pC1Axis = PedestrianCrossing.VERTICAL_AXIS;
			
			PedestrianCrossing pC1 = new PedestrianCrossing(pC1Position, pC1Axis); 
			
			pCs.add(pC1);
			
			//horizontal pedestrian crossing at the north of the crossroads position.
			
			double x2 = roadPosition.getX();
			double y2 = roadPosition.getY();
			
			y2 -= GameConfig.PEDESTRIAN_CROSSING_HEIGHT;
			
			Position pC2Position = new Position(x2, y2);
			int pC2Axis = PedestrianCrossing.HORIZONTAL_AXIS;
			
			PedestrianCrossing pC2 = new PedestrianCrossing(pC2Position, pC2Axis);
			
			pCs.add(pC2);
			
			//vertical pedestrian crossing at the west of the crossroads position.
			
			double x3 = roadPosition.getX();
			double y3 = roadPosition.getY();
			
			x3 += GameConfig.ROAD_WIDTH;
			
			Position pC3Position = new Position(x3, y3);
			int pC3Axis = PedestrianCrossing.VERTICAL_AXIS;
			
			PedestrianCrossing pC3 = new PedestrianCrossing(pC3Position, pC3Axis);
			
			pCs.add(pC3);
			
			//horizontal pedestrian crossing at the south of the crossroads position.
			
			double x4 = roadPosition.getX();
			double y4 = roadPosition.getY();
			
			y4 += GameConfig.ROAD_WIDTH;
			
			Position pC4Position = new Position(x4, y4);
			int pC4Axis = PedestrianCrossing.HORIZONTAL_AXIS;
			
			PedestrianCrossing pC4 = new PedestrianCrossing(pC4Position, pC4Axis);
			
			pCs.add(pC4);
		//}
		
		
		
		//Generate a crossroads.
		Crossroads crossroads = new Crossroads(roadPosition, pCs);
		
		crossroads.setPedestrianCrossings(pCs);
		
		
		
		return crossroads;
	}
	
	/**
	 * This static method generates automatically {@link Crossroads}s accorded to the {@link Road}s created
	 * in the {@link City}.
	 * 
	 * @param city
	 * 				the {@link City}.
	 * @return turnings
	 * 					{@link Turning}s of all {@link City}.
	 * */
	
	/*private static ArrayList<Crossroads> generateCrossroadss(City city){
		
		ArrayList<Crossroads> crossroadss = new ArrayList<Crossroads>();
		
		ArrayList<Road> roads = city.getRoads();
		
		for (Road road : roads) {
			Position roadPosition = road.getPosition();
			
			ArrayList<Turning> twoTurnings = generateCrossroads(city, roadPosition);
			
			if(!twoTurnings.isEmpty()) {
				Turning turning1 = twoTurnings.get(0);
				
				Position turning1Position = turning1.getPosition();
				
				if(turning1Position != null && !Utility.doesTurningExists(turnings, turning1Position)) {
					turnings.add(turning1);
				}
			}
			
			if(twoTurnings.size() == 2) {
				Turning turning2 = twoTurnings.get(1);
				Position turning2Position = turning2.getPosition();
			
				if(turning2Position != null && !Utility.doesTurningExists(turnings, turning2Position)) {
					turnings.add(turning2);
				}
			}
			
		}
		
		
	}*/
	
	
	
	
	
}
