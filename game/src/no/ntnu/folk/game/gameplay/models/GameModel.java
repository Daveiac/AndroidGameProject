package no.ntnu.folk.game.gameplay.models;

import no.ntnu.folk.game.constants.GameTypes;
import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.gameplay.entities.data.Projectiles;
import no.ntnu.folk.game.gameplay.entities.data.Teams;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import no.ntnu.folk.game.gameplay.levels.views.LevelToken;
import sheep.collision.CollisionLayer;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.math.Vector2;

import java.util.ArrayList;

/**
 * Class used to keep track of the game-state as the game evolves.
 *
 * @author Rune
 */
public class GameModel implements CollisionListener {
	// Entity lists
	private ArrayList<PlayerModel> playerList;
	private ArrayList<ProjectileModel> projectiles;

	// Players
	private int playerCount;
	private int maxHealth;
	private int currentPlayer;

	//Map
	private LevelModel currentLevel;
	private GameTypes gameTypes;

	//Game time
	private float gameTime;
	private float availablePlayerTime;
	private boolean paused;

	/**
	 * @param playerCount  Number of players for the start of this game
	 * @param playerHealth Health all players starts with
	 * @param level        Level name
	 * @param gameTypes    Game type (teams / ffa)
	 */
	public GameModel(int playerCount, int playerHealth, String level, GameTypes gameTypes) {
		initializeFields(playerCount, playerHealth, level, gameTypes);
		createPlayers();
	}
	/**
	 * Initialize the fields
	 *
	 * @param playerCount  Number of players for the start of this game
	 * @param playerHealth Health all players starts with
	 * @param level        Level name
	 * @param gameTypes    Game type (teams / ffa)
	 */
	private void initializeFields(int playerCount, int playerHealth, String level, GameTypes gameTypes) {
		this.playerCount = playerCount;
		this.maxHealth = playerHealth;
		this.currentLevel = new LevelModel(level);
		this.gameTypes = gameTypes;
		projectiles = new ArrayList<ProjectileModel>();
		currentPlayer = 0;

		// Init time variables
		gameTime = 0;
		availablePlayerTime = GameplayConstants.TURN_TIME;
	}
	/**
	 * Create the number of players
	 */
	private void createPlayers() {
		playerList = new ArrayList<PlayerModel>(playerCount);
		float offset = ProgramConstants.getWindowSize()[0] * 0.5f / playerCount;
		float xPosition = (offset * (1 + playerCount)) / 2;
		for (int i = 0; i < playerCount; i++) {
			String name = "Player " + i;
			Vector2 position = new Vector2(xPosition + (i * offset), ProgramConstants.getWindowSize()[1] / 2);
			Teams team;
			if (gameTypes.equals(GameTypes.FFA)) {
				team = Teams.getTeamFromOrdinal(i);
			} else {
				team = i < playerCount / 2 ? Teams.RED : Teams.BLUE;
			}
			PlayerModel player = new PlayerModel(name, position, team, maxHealth);
			playerList.add(player);
		}
	}

	/**
	 * @return The list of players in this game
	 */
	public ArrayList<PlayerModel> getPlayerList() {
		return this.playerList;
	}
	/**
	 * @return The list of projectiles in this game
	 */
	public ArrayList<ProjectileModel> getProjectiles() {
		return projectiles;
	}
	/**
	 * @return The list of projectiles in this game
	 */
	public ArrayList<LevelToken> getLevelTokens() {
		return currentLevel.getLevelTokens();
	}

	/**
	 * Update timer
	 *
	 * @param dt time since last update
	 */
	public void update(float dt) {
		for (PlayerModel player : playerList) {
			player.update(dt);
		}
		for (ProjectileModel projectile : projectiles) {
			projectile.update(dt);
		}
		for (ProjectileModel projectile : projectiles) {
			for (PlayerModel player : playerList) {
				projectile.collides(player);
			}
		}
		gameTime += dt;
		availablePlayerTime -= dt;
		if (playerTimeUp()) {
			nextPlayer();
		}
	}

	/**
	 * Set currentPlayer to the next player. Set to first player if a the end of the list.
	 */
	public void nextPlayer() {
		getCurrentPlayer().setSpeed(0, 0);
		availablePlayerTime = GameplayConstants.TURN_TIME;
		if (currentPlayer == playerCount - 1) currentPlayer = 0;
		else currentPlayer++;
	}
	/**
	 * @return Current player
	 */
	public PlayerModel getCurrentPlayer() {
		return this.playerList.get(currentPlayer);
	}

	/**
	 * @return True if the time is up for current player
	 */
	public boolean playerTimeUp() {
		return availablePlayerTime <= 0;
	}

	/**
	 * @return time left of this turn for the current player
	 */
	public float playerTimeLeft() {
		return this.availablePlayerTime;
	}

	/**
	 * Fires the weapon the current player is holding
	 */
	public void fireWeapon() {
		if (getCurrentPlayer().getCurrentWeapon().isCool()) {
			Projectiles projectileType = getCurrentPlayer().getCurrentWeapon().getProjectileType();
			ProjectileModel projectile = new ProjectileModel(projectileType, getCurrentPlayer());
			projectiles.add(projectile);
			projectile.addCollisionListener(this);
			projectile.setSpeed(getCurrentPlayer().getAim());
			getCurrentPlayer().getCurrentWeapon().startCoolDownTimer();
		}
	}

	/**
	 * @param paused Set whether or not the game is paused
	 */
	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	/**
	 * @return true if the game is paused
	 */
	public boolean isPaused() {
		return this.paused;
	}

	/**
	 * Called when two Sprite collide.
	 *
	 * @param a The first Sprite (the sprite being listened to).
	 * @param b The other Sprite.
	 */
	@Override
	public void collided(Sprite a, Sprite b) {
		if (a instanceof PlayerModel) {
			if (b instanceof ProjectileModel) {
				projectiles.remove(b);
				((PlayerModel) a).attacked(((ProjectileModel) b).getDirectDamage());
			}
		}
		if (b instanceof PlayerModel) {
			if (a instanceof ProjectileModel) {
				projectiles.remove(a);
				((PlayerModel) b).attacked(((ProjectileModel) a).getDirectDamage());
			}
		}
	}
}
