package no.ntnu.folk.game.constants;

public class GameplayConstants {
	public static final float PLAYER_SPEED = 10;
	// Game options
	public static final int DEFAULT_HEALTH = 100;
	public static final int MAX_HEALTH = 1000;
	// Player count
	public static final int MAX_PLAYERS = 6;
	public static final int DEFAULT_PLAYER_COUNT = 2;
	public static String[] LEVEL_LIST = {"1"}; //TODO LevelController.getLevels()
	// Game types
	public static final GameTypes[] GAME_TYPE = {GameTypes.FFA, GameTypes.TEAMS};
}
