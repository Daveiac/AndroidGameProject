package no.ntnu.folk.game.constants;

public class GameplayConstants {
	public static final float PLAYER_SPEED = 100;
	public static final float ACCELERATION = 0;
	// Player health
	public static final int MIN_HEALTH = 100;
	public static final int DEFAULT_HEALTH = 100;
	public static final int MAX_HEALTH = 1000;
	// Player count
	public static final int MAX_PLAYERS = 6;
	public static final int DEFAULT_PLAYER_COUNT = 2;
	// TurnTime
	public static final int MAX_TURN_TIMER = 60; // turn time in seconds
	public static final int MIN_TURN_TIMER = 20;
	// Size of the grid
	public static final int GRID_SIZE = 50;
	public static final float GRAVITY = 142;
	public static final float JUMP_FORCE = 42 << 2;
}
