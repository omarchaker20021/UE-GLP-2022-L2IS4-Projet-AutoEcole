package config;

import data.map.City;

public class GameConfig {

	

	/** {@link City} width **/
	public static final int CITY_WIDTH = 3000;
	/** {@link City} height **/
	public static final int CITY_HEIGHT = 3000;
	
	/** Window width **/
	public static final int WINDOW_WIDTH = 900;
	/** Window height **/
	public static final int WINDOW_HEIGHT = 700;
	
	/** Largeur de la map **/
	public static final int MAP_WIDTH = 800;
	/** Hauteur de la map **/
	public static final int MAP_HEIGHT = 600;
	
	
	/** Largeur de la route **/
	public static final int ROAD_WIDTH = 40;
	
	// Simulation normale : 300
	// Debug : 40
	
	/** Hauteur de la route **/
	public static final int ROAD_HEIGHT = 240;

	// Simulation normale : 1800
	// Debug : 240
		
	
	/** Position x de la route **/
	public static final int VERTICAL_ROAD_POSITION_X = (MAP_WIDTH / 2) - (ROAD_WIDTH / 2);
	/** Position y de la route **/
	public static final int VERTICAL_ROAD_POSITION_Y = 0;
	
	/** Largeur de la voiture **/
	public static final int CAR_WIDTH = 91;
	/** Hauteur de la voiture **/
	public static final int CAR_HEIGHT = 160;
	
	/** Position x de la voiture **/
	public static final int CAR_POSITION_X = VERTICAL_ROAD_POSITION_X + (ROAD_WIDTH / 2) + (ROAD_WIDTH / 4) - (CAR_WIDTH / 2);
	/** Position y de la voiture **/
	public static final int CAR_POSITION_Y = MAP_HEIGHT - CAR_HEIGHT;
	
	/** Largeur de la ligne **/
	public static final int LINE_WIDTH = 8;
	/** Hauteur de la ligne **/
	public static final int LINE_HEIGHT = ROAD_HEIGHT;
	
	/** Position x de la ligne **/
	public static final int LINE_POSITION_X = VERTICAL_ROAD_POSITION_X + (ROAD_WIDTH/2) - (LINE_WIDTH/2) ;
	/** Position y de la ligne **/
	public static final int LINE_POSITION_Y = 0;
	
	/** Largeur du stop **/
	public static final int PANEL_WIDTH = ROAD_WIDTH/4;
	/** Hauteur du stop **/
	public static final int PANEL_HEIGHT = ROAD_HEIGHT/16;
	
	/** Position x du stop **/
	public static final int STOP_POSITION_X = VERTICAL_ROAD_POSITION_X + ROAD_WIDTH + (VERTICAL_ROAD_POSITION_X/2) - (PANEL_WIDTH/2); 
	/** Position y du stop **/
	public static final int STOP_POSITION_Y = 40;
	
	/** Largeur du passage piéton **/
	public static final int PEDESTRIAN_CROSSING_WIDTH = ROAD_WIDTH/10;
	/** Hauteur du passage piéton **/
	public static final int PEDESTRIAN_CROSSING_HEIGHT = (ROAD_WIDTH/10)*3;
	
	/** Rapidité du jeu **/
	public static final int GAME_SPEED = 30;
	
	/** Differentiel du mouvement en avant **/
	public static final int MOVE_INTERVAL = 1;
	
	/** Le radian de la rotation effectuée sur les objets **/
	public static final int TURN_RADIAN = 5;
	
	
	
}
