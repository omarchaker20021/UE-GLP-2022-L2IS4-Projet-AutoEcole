package process;

import config.GameConfig;
import data.geometry.Position;
import data.map.Line;
import data.map.Panel;
import data.map.City;
import data.map.Extension;
import data.map.Road;
import data.map.Turning;
import data.map.intersections.Crossroads;
import data.map.intersections.PedestrianCrossing;
import data.mobile.Car;
import data.mobile.MobileElement;
import exceptions.PanelNameException;

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
	 * 
	 * @return city
	 * 				The {@link City} with all initialized elements.
	 * **/
	
	public static City buildCity() {
		ArrayList<Road> roads = new ArrayList<Road>();
		
		ArrayList<Turning> turnings = new ArrayList<Turning>();
		
		ArrayList<Crossroads> crossroadss = new ArrayList<Crossroads>();
		
		ArrayList<Extension> extensions = new ArrayList<Extension>();
		
		Car secondaryCar = generateSecondaryCar();
		
		double x0 = GameConfig.VERTICAL_ROAD_POSITION_X;
		double y0 = GameConfig.MAP_HEIGHT - GameConfig.ROAD_WIDTH;
		
		Position position = new Position(x0, y0);
		
		int nbVerticalRoads = 1;
		int nbHorizontalRoads = 1;
		
		
		
		//Generate vertical and horizontal roads
		
		Position position2 = new Position(x0 - GameConfig.ROAD_HEIGHT - GameConfig.ROAD_WIDTH, y0 - GameConfig.ROAD_HEIGHT - GameConfig.ROAD_WIDTH);
		
		Position position3 = new Position(x0, y0 - (GameConfig.ROAD_HEIGHT + GameConfig.ROAD_WIDTH));
		
		Position position4 = new Position(x0, y0 - 2*(GameConfig.ROAD_HEIGHT + GameConfig.ROAD_WIDTH));
		
		roads.addAll(generateRoads(nbVerticalRoads, nbHorizontalRoads, position));
		
		roads.addAll(generateRoads(nbVerticalRoads, nbHorizontalRoads, position2));
		
		roads.addAll(generateRoads(nbVerticalRoads, 0, position3));
		
		roads.addAll(generateRoads(nbVerticalRoads, 0, position4));
		
		//Creation of last horizontal and vertical roads of the map(and the turning which link them).
		
		Position lastHorizontalRoadPosition = new Position(x0, y0 - GameConfig.ROAD_HEIGHT - GameConfig.ROAD_WIDTH);
		Position lastVerticalRoadPosition = new Position(x0 - GameConfig.ROAD_HEIGHT - GameConfig.ROAD_WIDTH, y0);
		
		roads.addAll(generateRoads(0, 1, lastHorizontalRoadPosition));
		roads.addAll(generateRoads(1, 0, lastVerticalRoadPosition));
		
		
		//City creation
		
		City city = new City(roads, turnings, crossroadss, extensions, secondaryCar);
		
		// Generate a crossroads at the position of intersection of 4 roads.
		
		crossroadss.addAll(generateCrossroadss(city));
		
		city.setCrossroadss(crossroadss);
		
		//Automatic generation of turnings
		
		turnings.addAll(generateTurnings(city));
		
		city.setTurnings(turnings);
		
		//Automatic generate of road extensions
		
		extensions.addAll(generateExtensions(city));
		
		city.setExtensions(extensions);
		
		//Generate panels
		
		generatePanels(crossroadss);
		
		
		return city;
	}

	/**
	 * This static method builds all {@link MobileElement}s of our simulation.
	 * 
	 * @param city
	 * 				The {@link City} of the simulation.
	 * @return manager
	 * 					A {@link MobileElementManager} object.
	 * */
	
	public static MobileElementManager buildInitMobile(City city) {
		MobileElementManager manager = new MobileElementManager(city);
		
		initializeCar(manager);
		
		return manager;
	}
	
	/**
	 * This static method initialize the {@link Car}.
	 * 
	 * @param manager
	 * 				A {@link MobileElementManager} object.
	 * 
	 * */
	
	private static void initializeCar(MobileElementManager manager){
		Position position = new Position(GameConfig.CAR_POSITION_X, GameConfig.CAR_POSITION_Y);
		
		Car car = new Car(position);
		
		manager.set(car);
		
	}
	
	/**
	 * This static method initialize secondary {@link Car}.
	 * 
	 * @param manager
	 * 				A {@link MobileElementManager} object.
	 * 
	 * */
	
	private static Car generateSecondaryCar(){
		
		double x0 = GameConfig.VERTICAL_ROAD_POSITION_X;
		double y0 = GameConfig.MAP_HEIGHT - GameConfig.ROAD_WIDTH;
		
		Position position = new Position(x0 - GameConfig.ROAD_WIDTH, 
				y0 - GameConfig.ROAD_HEIGHT - GameConfig.ROAD_WIDTH + 20);
		
		//Position lastHorizontalRoadPosition = new Position(x0,
		//			y0 - GameConfig.ROAD_HEIGHT - GameConfig.ROAD_WIDTH);
		
		Car secondaryCar = new Car(position);
		
		return secondaryCar;
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
				
				if(turning1Position != null 
						&& !Utility.doesCityElementExists(city, turning1Position)
						&& !Utility.doesTurningExists(turnings, turning1Position)) {
					turnings.add(turning1);
				}
			}
			
			if(twoTurnings.size() == 2) {
				Turning turning2 = twoTurnings.get(1);
				Position turning2Position = turning2.getPosition();
			
				if(turning2Position != null 
						&& !Utility.doesCityElementExists(city, turning2Position)
						&& !Utility.doesTurningExists(turnings, turning2Position)) {
					turnings.add(turning2);
				}
			}
			
		}
		
		return turnings;
	}
	
	/**
	 * This static method generates a {@link Turning}.
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
	 * This method generates one or two {@link Crossroads} according to 
	 * the {@link Position} of created {@link Road}s.
	 * 
	 * @param city
	 * 				The {@link City}.
	 * 
	 * @param roadPosition
	 * 						The {@link Position} of the {@link Crossroads} generated.
	 * 
	 * @return crossroads
	 * 						The generated {@link Crossroads}.
	 * 
	 * */
	
	private static ArrayList<Crossroads> generateCrossroads(City city, Position roadPosition) {
		
		ArrayList<Road> roads = city.getRoads();
		
		ArrayList<Crossroads> crossroadss = new ArrayList<Crossroads>(2);
		
		double x = roadPosition.getX();
		double y = roadPosition.getY();
		
		Position topCrossroadsPosition = new Position(x, y - GameConfig.ROAD_WIDTH);
		Position bottomCrossroadsPosition = new Position(x, y + GameConfig.ROAD_HEIGHT);
		
		if(Utility.doesRoadExists(roads, roadPosition)) {
			Road road = Utility.getRoadFromPosition(roads, roadPosition);
			int roadType = road.getAxis();
			
			if(roadType == Road.VERTICAL_AXIS) {
				
				Position topLeftHorizontalRoadPosition = new Position(x - GameConfig.ROAD_HEIGHT, y - GameConfig.ROAD_WIDTH);
				Position topRightHorizontalRoadPosition = new Position(x + GameConfig.ROAD_WIDTH, y - GameConfig.ROAD_WIDTH);
				Position topVerticalRoadPosition = new Position(x, y - GameConfig.ROAD_HEIGHT - GameConfig.ROAD_WIDTH);
				
				Position bottomLeftHorizontalRoadPosition = new Position(x - GameConfig.ROAD_HEIGHT, y + GameConfig.ROAD_HEIGHT);
				Position bottomRightHorizontalRoadPosition = new Position(x + GameConfig.ROAD_WIDTH, y + GameConfig.ROAD_HEIGHT);
				Position bottomVerticalRoadPosition = new Position(x, y + GameConfig.ROAD_HEIGHT + GameConfig.ROAD_WIDTH);
				
				
				//Use of boolean variables to make readable these if expressions.
				
				boolean a = Utility.doesRoadExists(roads, topLeftHorizontalRoadPosition);  
				boolean b = Utility.doesRoadExists(roads, topRightHorizontalRoadPosition);
				boolean c = Utility.doesRoadExists(roads, topVerticalRoadPosition);
				
				if(b && (a || c)) {
					
					//Generate pedestrian crossings.
					
					ArrayList<PedestrianCrossing> pCs = new ArrayList<PedestrianCrossing>();
					
					//vertical pedestrian crossing at the west of the crossroads position.
					
					double x1 = topCrossroadsPosition.getX();
					double y1 = topCrossroadsPosition.getY();
					
					x1 += GameConfig.ROAD_WIDTH;
					
					Position pC1Position = new Position(x1, y1);
					int pC1Axis = PedestrianCrossing.VERTICAL_AXIS;
					
					PedestrianCrossing pC1 = new PedestrianCrossing(pC1Position, pC1Axis);
					
					pCs.add(pC1);
					
					//horizontal pedestrian crossing at the south of the crossroads position.
					
					double x2 = topCrossroadsPosition.getX();
					double y2 = topCrossroadsPosition.getY();
					
					y2 += GameConfig.ROAD_WIDTH; 
					
					Position pC2Position = new Position(x2, y2);
					int pC2Axis = PedestrianCrossing.HORIZONTAL_AXIS;
					
					PedestrianCrossing pC2 = new PedestrianCrossing(pC2Position, pC2Axis);
					
					pCs.add(pC2);
					

					//Generate a crossroads.
					Crossroads crossroads1 = new Crossroads(topCrossroadsPosition, pCs);
					
					crossroadss.add(crossroads1);
					
				}
				else if(a && c) {
					
					//Generate pedestrian crossings.
					
					ArrayList<PedestrianCrossing> pCs = new ArrayList<PedestrianCrossing>();
					
					//vertical pedestrian crossing at the east of the crossroads position.
					double x1 = topCrossroadsPosition.getX();
					double y1 = topCrossroadsPosition.getY();
					
					x1 -= GameConfig.PEDESTRIAN_CROSSING_HEIGHT;
					
					Position pC1Position = new Position(x1, y1);
					int pC1Axis = PedestrianCrossing.VERTICAL_AXIS;
					
					PedestrianCrossing pC1 = new PedestrianCrossing(pC1Position, pC1Axis); 
					
					pCs.add(pC1);
					
					//horizontal pedestrian crossing at the north of the crossroads position.
					
					double x2 = topCrossroadsPosition.getX();
					double y2 = topCrossroadsPosition.getY();
					
					y2 -= GameConfig.PEDESTRIAN_CROSSING_HEIGHT;
					
					Position pC2Position = new Position(x2, y2);
					int pC2Axis = PedestrianCrossing.HORIZONTAL_AXIS;
					
					PedestrianCrossing pC2 = new PedestrianCrossing(pC2Position, pC2Axis);
					
					pCs.add(pC2);
					
					//Generate a crossroads.
					Crossroads crossroads1 = new Crossroads(topCrossroadsPosition, pCs);
					
					crossroadss.add(crossroads1);
					
				}
				
				a = Utility.doesRoadExists(roads, bottomLeftHorizontalRoadPosition);  
				b = Utility.doesRoadExists(roads, bottomRightHorizontalRoadPosition);
				boolean d = Utility.doesRoadExists(roads, bottomVerticalRoadPosition);
				

				if(b && d) {
					
					//Generate pedestrian crossings.
					
					ArrayList<PedestrianCrossing> pCs = new ArrayList<PedestrianCrossing>();
					
					//vertical pedestrian crossing at the west of the crossroads position.
					
					double x1 = bottomCrossroadsPosition.getX();
					double y1 = bottomCrossroadsPosition.getY();
					
					x1 += GameConfig.ROAD_WIDTH;
					
					Position pC1Position = new Position(x1, y1);
					int pC1Axis = PedestrianCrossing.VERTICAL_AXIS;
					
					PedestrianCrossing pC1 = new PedestrianCrossing(pC1Position, pC1Axis);
					
					pCs.add(pC1);
					
					//horizontal pedestrian crossing at the south of the crossroads position.
					
					double x2 = bottomCrossroadsPosition.getX();
					double y2 = bottomCrossroadsPosition.getY();
					
					y2 += GameConfig.ROAD_WIDTH;
					
					Position pC2Position = new Position(x2, y2);
					int pC2Axis = PedestrianCrossing.HORIZONTAL_AXIS;
					
					PedestrianCrossing pC2 = new PedestrianCrossing(pC2Position, pC2Axis);
					
					pCs.add(pC2);
					

					//Generate a crossroads.
					Crossroads crossroads2 = new Crossroads(bottomCrossroadsPosition, pCs);
					
					crossroadss.add(crossroads2);
					
				}
				
				else if(a && (b || d)) {
					
					//Generate pedestrian crossings.
					
					ArrayList<PedestrianCrossing> pCs = new ArrayList<PedestrianCrossing>();
					
					//vertical pedestrian crossing at the east of the crossroads position.
					double x1 = bottomCrossroadsPosition.getX();
					double y1 = bottomCrossroadsPosition.getY();
					
					x1 -= GameConfig.PEDESTRIAN_CROSSING_HEIGHT;
					
					Position pC1Position = new Position(x1, y1);
					int pC1Axis = PedestrianCrossing.VERTICAL_AXIS;
					
					PedestrianCrossing pC1 = new PedestrianCrossing(pC1Position, pC1Axis); 
					
					pCs.add(pC1);
					
					//horizontal pedestrian crossing at the north of the crossroads position.
					
					double x2 = bottomCrossroadsPosition.getX();
					double y2 = bottomCrossroadsPosition.getY();
					
					y2 -= GameConfig.PEDESTRIAN_CROSSING_HEIGHT;
					
					Position pC2Position = new Position(x2, y2);
					int pC2Axis = PedestrianCrossing.HORIZONTAL_AXIS;
					
					PedestrianCrossing pC2 = new PedestrianCrossing(pC2Position, pC2Axis);
					
					pCs.add(pC2);
					
					//Generate a crossroads.
					Crossroads crossroads2 = new Crossroads(bottomCrossroadsPosition, pCs);
					
					crossroadss.add(crossroads2);
					
				}
				
			}
		}
		
		
		return crossroadss;
	}
	
	/**
	 * This static method generates automatically {@link Crossroads}s accorded to {@link Road}s created
	 * in the {@link City}.
	 * 
	 * @param city
	 * 				the {@link City}.
	 * @return crossroadss
	 * 					{@link Crossroads}s of the whole {@link City}.
	 * */
	
	private static ArrayList<Crossroads> generateCrossroadss(City city){
		
		ArrayList<Crossroads> crossroadss = new ArrayList<Crossroads>();
		
		ArrayList<Road> roads = city.getRoads();
		
		for (Road road : roads) {
			Position roadPosition = road.getPosition();
			
			ArrayList<Crossroads> crossroadss2 = generateCrossroads(city, roadPosition);
			
			if(!crossroadss2.isEmpty()) {
				Crossroads crossroads1 = crossroadss2.get(0);
				
				Position crossroads1Position = crossroads1.getPosition();
				
				if(crossroads1Position != null 
						&& !Utility.doesCityElementExists(city, crossroads1Position)
						&& !Utility.doesCrossroadsExists(crossroadss, crossroads1Position)) {
					crossroadss.add(crossroads1);
				}
				if(crossroadss2.size() == 2) {
					Crossroads crossroads2 = crossroadss2.get(1);
					Position crossroads2Position = crossroads2.getPosition();
				
					if(crossroads2Position != null 
							&& !Utility.doesCityElementExists(city, crossroads2Position)
							&& !Utility.doesCrossroadsExists(crossroadss, crossroads2Position)) {
						crossroadss.add(crossroads2);
					}
				}
			}
		}
		
		return crossroadss;
	}
	
	/**
	 * This method generates an extension of a road to fill voids between 2 roads of the same axis
	 * 
	 * @param city
	 * 				The {@link City}.
	 * @param roadPosition
	 * 						{@link Road}'s {@link Position}.
	 * @return extension
	 * 					An {@link Extension}.
	 * */
	
	private static Extension generateExtension(City city, Position roadPosition) {
		
		ArrayList<Road> roads = city.getRoads();
		
		double x = roadPosition.getX();
		double y = roadPosition.getY();
		
		if(Utility.doesRoadExists(roads, roadPosition)) {
			
			Road road = Utility.getRoadFromPosition(roads, roadPosition);
			int roadType = road.getAxis();
			
			if(roadType == Road.HORIZONTAL_AXIS) {
				
				//next horizontal road position to the west
				Position nextHorizontalRoadPosition = new Position(x + GameConfig.ROAD_HEIGHT + GameConfig.ROAD_WIDTH, y);
				
				if(Utility.doesRoadExists(roads, nextHorizontalRoadPosition)) {
					
					Line line = new Line();
					
					double xExtension = x + GameConfig.ROAD_HEIGHT;
					double yExtension = y;
					
					Position extensionPosition = new Position(xExtension, yExtension);
					
					int extensionAxis = Extension.HORIZONTAL_AXIS;
					
					Extension extension = new Extension(extensionPosition, extensionAxis, line);
					
					return extension;
				}
				
				
			}
			else if(roadType == Road.VERTICAL_AXIS) {
				
				//next vertical road position to the south
				Position nextVerticalRoadPosition = new Position(x, y + GameConfig.ROAD_HEIGHT + GameConfig.ROAD_WIDTH);
				
				if(Utility.doesRoadExists(roads, nextVerticalRoadPosition)) {
					
					Line line = new Line();
					
					double xExtension = x;
					double yExtension = y + GameConfig.ROAD_HEIGHT;
					
					Position extensionPosition = new Position(xExtension, yExtension);
					
					int extensionAxis = Extension.VERTICAL_AXIS;
					
					Extension extension = new Extension(extensionPosition, extensionAxis, line);
					
					return extension;
					
				}

			}
			
		}
		
		return null;
		
	}
	
	/**
	 * This method generates {@link Extension}s automatically.
	 * 
	 * @param city
	 * 				The {@link City}.
	 * 
	 * @return extensions
	 * 						An {@link ArrayList} of {@link Extension}s.
	 * 
	 * */
	
	private static ArrayList<Extension> generateExtensions(City city) {
		ArrayList<Road> roads = city.getRoads();
		
		ArrayList<Extension> extensions = new ArrayList<Extension>();
		
		for (Road road : roads) {
			Position roadPosition = road.getPosition();

			if(generateExtension(city, roadPosition) != null) {
				
				Extension extension = generateExtension(city, roadPosition);
				
				Position extensionPosition = extension.getPosition();
				
				if(!Utility.doesExtensionExists(extensions, extensionPosition)
						&& !Utility.doesCityElementExists(city, extensionPosition)) {
					extensions.add(extension);
				}
				
			}
		}
		
		return extensions;
	}
	
	/**
	 * This method generates {@link Panel}s according to a {@link PedestrianCrossing} positions 
	 * in a {@link Crossroads}.
	 * 
	 * @param city
	 * 				The {@link City}.
	 * 
	 * @return panels
	 * 				An {@link ArrayList} of {@link Panel}s.
	 * 
	 * */
	
	private static ArrayList<Panel> generateCrossroadsPanels(Crossroads crossroads, Position crossroadsPosition) {
		
		ArrayList<Panel> panels = new ArrayList<Panel>();
	
		ArrayList<PedestrianCrossing> pedestrianCrossings = crossroads.getPedestrianCrossings();
		
		PedestrianCrossing verticalPC = pedestrianCrossings.get(0);
		PedestrianCrossing horizontalPC = pedestrianCrossings.get(1);
		
		Position verticalPCPosition = verticalPC.getPosition();
		
		double x1 = verticalPCPosition.getX();
		double y1 = verticalPCPosition.getY();
		
		Position horizontalPCPosition = horizontalPC.getPosition();
		
		double x2 = horizontalPCPosition.getX();
		double y2 = horizontalPCPosition.getY();
		
		Position panel1Position = new Position(0, 0);
		Position panel2Position = new Position(0, 0);
		
		
		if(Utility.getCrossroadsType(crossroads) == Utility.CROSSROADS_1) {
			
			panel1Position.setX(x1 - GameConfig.PEDESTRIAN_CROSSING_HEIGHT*2);
			panel1Position.setY(y1 
					+ GameConfig.PEDESTRIAN_CROSSING_WIDTH*8
					+ GameConfig.ROAD_WIDTH/2 
					- GameConfig.PANEL_WIDTH/2);
			
			panel2Position.setX(x2 
					- GameConfig.PEDESTRIAN_CROSSING_HEIGHT
					- GameConfig.PANEL_WIDTH/2);
			panel2Position.setY(y2 - GameConfig.PEDESTRIAN_CROSSING_HEIGHT*2);
		}
		else if(Utility.getCrossroadsType(crossroads) == Utility.CROSSROADS_2) {

			panel1Position.setX(x1 + GameConfig.PEDESTRIAN_CROSSING_HEIGHT);
			panel1Position.setY(y1 
					- GameConfig.PEDESTRIAN_CROSSING_WIDTH*8);
			
			panel2Position.setX(x2
					+ GameConfig.PEDESTRIAN_CROSSING_WIDTH*8
					+ GameConfig.ROAD_WIDTH/2 
					- GameConfig.PANEL_WIDTH/2);
			panel2Position.setY(y2 
					+ GameConfig.PEDESTRIAN_CROSSING_WIDTH);
			
		}
		
		try {
			
			panels.add(new Panel(Panel.STOP_PANEL, panel1Position));
			panels.add(new Panel(Panel.STOP_PANEL, panel2Position));
		
		}
		catch (PanelNameException e) {
			e.printStackTrace();
		}
		
		return panels;
	}
	
	/**
	 * This method generates all {@link City} {@link Panel}s.
	 * 
	 * @param crossroads
	 * 				An {@link ArrayList} of {@link Crossroads}s.
	 * 
	 * @return panels
	 * 				An {@link ArrayList} of {@link Panel}s.
	 * */
	
	private static void generatePanels(ArrayList<Crossroads> crossroadss){
		
		for (Crossroads crossroads : crossroadss) {
			ArrayList<Panel> panels = new ArrayList<Panel>();
			
			Position crossroadsPosition = crossroads.getPosition();
			
			panels.addAll(generateCrossroadsPanels(crossroads, crossroadsPosition));
			
			crossroads.setPanels(panels);
		}
	}
}
