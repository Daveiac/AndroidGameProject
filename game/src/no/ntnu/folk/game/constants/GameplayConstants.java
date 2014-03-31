package no.ntnu.folk.game.constants;

public class GameplayConstants {
	
	/**
	 * The moving speed of the player.
	 */
	public static final float PLAYER_SPEED = 100;
	
	/**
	 * The acceleration of the player.
	 */
	public static final float ACCELERATION = 0;
	
	/**
	 * The explosion radius of projectiles on hit.
	 */
	public static final int EXPLOSION_LENGTH = 10;
	
	// Player health
	/**
	 * The minimum health set to a player.
	 */
	public static final int MIN_HEALTH = 100;
	
	/**
	 * The default health given to a player.
	 */
	public static final int DEFAULT_HEALTH = 100;
	
	/**
	 * The maximum health given to a player.
	 */
	public static final int MAX_HEALTH = 1000;
	
	// Player count
	/**
	 * The maximum number of players currently playing together in the game.
	 */
	public static final int MAX_PLAYERS = 6;
	
	/**
	 * The default number of players set up to play together.
	 */
	public static final int DEFAULT_PLAYER_COUNT = 2;
	
	// TurnTime
	/**
	 * The maximum time given to each player each turn.
	 */
	public static final int MAX_TURN_TIMER = 60; // turn time in seconds

	/**
	 * The minimum time given to each player each turn.
	 */
	public static final int MIN_TURN_TIMER = 20;
	
	/**
	 * The time when the turn timer will be viewed over the head of the player.
	 */
	public static final int HEAD_TIMER_START = 5;
	
	// Size of the grid
	/**
	 * The grid size of the level.
	 */
	public static final int GRID_SIZE = 50;
	
	/**
	 * The gravity set in the game.
	 */
	public static final float GRAVITY = 142;
	
	/**
	 * The jump force in the game (fit to counter GRAVITY).
	 */
	public static final float JUMP_FORCE = 42 << 2;
}
