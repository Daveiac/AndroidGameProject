package no.ntnu.folk.game.gameplay.models;

import no.ntnu.folk.game.R;
import no.ntnu.folk.game.constants.GameTypes;
import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.gameplay.entities.data.Teams;
import no.ntnu.folk.game.gameplay.entities.models.EntityModel;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import no.ntnu.folk.game.gameplay.entities.models.TombStoneModel;
import no.ntnu.folk.game.gameplay.levels.views.LevelToken;
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
	private ArrayList<PlayerModel> players;
	private ArrayList<ProjectileModel> projectiles;
	private ArrayList<TombStoneModel> tombStones;
	private ArrayList<EntityModel> kill;
	private ArrayList<ProjectileModel> explosions;

	// Players
	private int playerCount;
	private int startHealth;
	private PlayerModel currentPlayer;

	// Map
	private LevelModel currentLevel;
	private GameTypes gameType;
	private String levelName;

	// Game time
	private float gameTime;
	private float availablePlayerTime;
	private boolean paused;
	private int turnTimer;

	/**
	 * Create a new GameModel and initialize fields.
	 */
	public GameModel() {
		this.playerCount = GameplayConstants.DEFAULT_PLAYER_COUNT;
		this.startHealth = GameplayConstants.DEFAULT_HEALTH;
		this.currentLevel = new LevelModel(0);
		this.gameType = GameTypes.FFA;
		this.turnTimer = GameplayConstants.MIN_TURN_TIMER;
		createPlayers();
		currentPlayer = players.get(0);
		tombStones = new ArrayList<TombStoneModel>();
		kill = new ArrayList<EntityModel>();
		projectiles = new ArrayList<ProjectileModel>();
		explosions = new ArrayList<ProjectileModel>();

		// Init time variables
		gameTime = 0;
		availablePlayerTime = turnTimer;
	}

	/**
	 * Create players for this game.
	 */
	private void createPlayers() {
		players = new ArrayList<PlayerModel>(playerCount);
		ArrayList<int[]> startPos = currentLevel.getStartPositions();
		for (int i = 0; i < playerCount; i++) {
			String name = "Player " + i;
			Vector2 position = new Vector2(startPos.get(i)[0], startPos.get(i)[1]);
			Teams team;
			if (gameType.equals(GameTypes.FFA)) {
				team = Teams.getTeamFromOrdinal(i);
			} else {
				team = i < playerCount / 2 ? Teams.RED : Teams.BLUE;
			}
			PlayerModel player = new PlayerModel(name, position, team, startHealth);
			players.add(player);
		}
	}

	/**
	 * @return The list of players in this game
	 */
	public ArrayList<PlayerModel> getPlayers() {
		return this.players;
	}

	/**
	 * @return The list of projectiles in this game
	 */
	public ArrayList<ProjectileModel> getProjectiles() {
		return projectiles;
	}

	/**
	 * @return The list of levelTokens in this game
	 */
	public ArrayList<LevelToken> getLevelTokens() {
		return currentLevel.getLevelTokens();
	}

	/**
	 * Set currentPlayer to the next player. Set to first player if a the end of
	 * the list.
	 */
	public void nextPlayer() {
		getCurrentPlayer().setSpeed(0, 0);
		availablePlayerTime = turnTimer;
		int playerNumber = players.indexOf(currentPlayer);
		if (playerNumber == players.size() - 1) {
			playerNumber = 0;
		} else {
			playerNumber++;
		}
		currentPlayer = players.get(playerNumber);
		currentPlayer.getCurrentWeapon().setCold(true);
	}

	/**
	 * @return Current player
	 */
	public PlayerModel getCurrentPlayer() {
		return currentPlayer;
	}
	public void setCurrentPlayer(PlayerModel currentPlayer) {
		this.currentPlayer = currentPlayer;
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
	 * @return true if the game is paused
	 */
	public boolean isPaused() {
		return this.paused;
	}
	/**
	 * @param paused Set whether or not the game is paused
	 */
	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	/**
	 * Called when two Sprite collide.
	 *
	 * @param a The first Sprite (the sprite being listened to).
	 * @param b The other Sprite.
	 */
	@Override
	public void collided(Sprite a, Sprite b) {
		if (a instanceof ProjectileModel) {
			if (b instanceof PlayerModel) {
				addExplosion((ProjectileModel) a);
				kill.add((EntityModel) a);
				attack((PlayerModel) b, (ProjectileModel) a);
			}

		}
		if (a instanceof PlayerModel) {
			if (b instanceof LevelToken) {
				a.setSpeed(a.getSpeed().getX(), 0);
			}
		}
	}
	/**
	 * Attack a with a projectile. If the player dies, add it to the kill list and make a new tomb stone.
	 *
	 * @param player     Player that was attacked
	 * @param projectile Projectile used to attack
	 */
	private void attack(PlayerModel player, ProjectileModel projectile) {
		player.attacked(projectile.getDirectDamage());
		if (player.getHealth() <= 0) {
			kill.add(player);
			tombStones.add(new TombStoneModel(player.getName(), player.getPosition(), R.drawable.tombstone));
		}
	}

	/**
	 * Check whether the game has ended.
	 *
	 * @param playerList List of players left in the game
	 * @return true if the game is over
	 */
	public boolean isGameOver(ArrayList<PlayerModel> playerList) {
		Teams team = playerList.get(0).getTeam();
		switch (gameType) {
			case FFA:
				return players.size() <= 1;
			case TEAMS:
				for (PlayerModel p : playerList) {
					if (!team.equals(p.getTeam())) {
						return false;
					}
				}
				return true;
			default:
				return false;
		}
	}

	public int getPlayerCount() {
		return players.size();
	}
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
		createPlayers();
	}
	public int getStartHealth() {
		return startHealth;
	}
	public void setStartHealth(int startHealth) {
		this.startHealth = startHealth;
		createPlayers();
	}
	public GameTypes getGameType() {
		return gameType;
	}
	public void setGameType(GameTypes gameType) {
		this.gameType = gameType;
		createPlayers();
	}

	public ArrayList<TombStoneModel> getTombStones() {
		return tombStones;
	}
	public void setLevel(int level, String levelName) {
		this.levelName = levelName;
		currentLevel = new LevelModel(level);
		createPlayers();
	}
	public String getLevelName() {
		return levelName;
	}
	public void incrementTime(float dt) {
		gameTime += dt;
	}
	public void decrementAvailablePlayerTime(float dt) {
		availablePlayerTime -= dt;
	}
	public float getGameTime() {
		return gameTime;
	}
	public void setGameTime(int time) {
		this.availablePlayerTime = time;
	}
	public LevelModel getCurrentLevel() {
		return currentLevel;
	}
	/**
	 * @return A list of entities that will be killed.
	 */
	public ArrayList<EntityModel> getKill() {
		return kill;
	}

	public int getTurnTimer() {
		return this.turnTimer;
	}
	public void setTurnTimer(int newTimer) {
		this.turnTimer = newTimer;
	}
	public void addExplosion(ProjectileModel model) {
		if (model.getExplosion() != -1) {
			explosions.add(model);
		}
	}
	public ArrayList<ProjectileModel> getExplosions() {
		return this.explosions;
	}
}
