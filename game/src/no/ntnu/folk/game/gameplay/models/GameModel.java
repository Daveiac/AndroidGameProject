package no.ntnu.folk.game.gameplay.models;

import no.ntnu.folk.game.constants.GameTypes;
import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.gameplay.entities.data.Teams;
import no.ntnu.folk.game.gameplay.entities.models.EntityModel;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import no.ntnu.folk.game.gameplay.entities.models.TombStoneModel;
import no.ntnu.folk.game.gameplay.levels.views.LevelToken;
import sheep.math.Vector2;

import java.util.ArrayList;

/**
 * Class used to keep track of the game-state as the game evolves.
 *
 * @author Rune
 */
public class GameModel {
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
	private int turnTimer;

	private String winnerText;
	/**
	 * Create a new GameModel and initialize fields.
	 */
	public GameModel() {
		this.playerCount = GameplayConstants.DEFAULT_PLAYER_COUNT;
		this.startHealth = GameplayConstants.DEFAULT_HEALTH;
		this.currentLevel = new LevelModel(0);
		this.gameType = GameTypes.FFA;
		this.turnTimer = GameplayConstants.MIN_TURN_TIMER;
		this.tombStones = new ArrayList<TombStoneModel>();
		this.kill = new ArrayList<EntityModel>();
		this.projectiles = new ArrayList<ProjectileModel>();
		this.explosions = new ArrayList<ProjectileModel>();

		// Init time variables
		this.gameTime = 0;
		this.availablePlayerTime = turnTimer;
	}

	/**
	 * Create players for this game.
	 */
	public void initializeGame() {
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
		currentPlayer = players.get(0);
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
		currentPlayer.setCold(true);
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
	public boolean isPlayerTimeUp() {
		return availablePlayerTime <= 0;
	}
	/**
	 * @return time left of this turn for the current player
	 */
	public float getPlayerTimeLeft() {
		return this.availablePlayerTime;
	}

	/**
	 * Check whether the game has ended.
	 *
	 * @return true if the game is over
	 */
	public boolean isGameOver() {
		if (players.isEmpty()) {
			winnerText = "All players are dead, game was a draw";
			return true;
		}
		Teams team = players.get(0).getTeam();
		boolean isOver = false;
		switch (gameType) {
			case FFA:
				isOver = players.size() <= 1;
				break;
			case TEAMS:
				boolean sameTeams = true;
				for (PlayerModel p : players) {
					if (!team.equals(p.getTeam())) {
						sameTeams = false;
					}
				}
				if (sameTeams) {
					isOver = true;
				}
				break;
			default:
				isOver = false;
		}
		if (isOver) {
			if (gameType == GameTypes.FFA) {
				winnerText = "Winner is : " + players.get(0).getName();
			} else {
				winnerText = "Winner is team: " + team.toString();
			}
		}
		return isOver;
	}

	/**
	 * @return The amount of players participating.
	 */
	public int getPlayerCount() {
		return players.size();
	}

	/**
	 * Replaces the number of players participating.
	 * @param playerCount The amount of players participating.
	 */
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
	}

	/**
	 * @return The amount of health each player starts with.
	 */
	public int getStartHealth() {
		return startHealth;
	}
	
	/**
	 * Replaces the amount of health each player starts with.
	 * @param startHealth The amount of health each player starts with.
	 */
	public void setStartHealth(int startHealth) {
		this.startHealth = startHealth;
	}

	/**
	 * @return The game type to be played.
	 */
	public GameTypes getGameType() {
		return gameType;
	}
	
	/**
	 * Replaces the game type to be played.
	 * @param gameType The game type to ble played.
	 */
	public void setGameType(GameTypes gameType) {
		this.gameType = gameType;
	}

	/**
	 * @return The tomb stones corresponding the players.
	 */
	public ArrayList<TombStoneModel> getTombStones() {
		return tombStones;
	}
	
	/**
	 * Replaces the level to be played.
	 * @param level		The level to be played.
	 * @param levelName	The name of the level to be played.
	 */
	public void setLevel(int level, String levelName) {
		this.levelName = levelName;
		currentLevel = new LevelModel(level);
	}

	/**
	 * @return The name of the current level.
	 */
	public String getLevelName() {
		return levelName;
	}
	
	/**
	 * Increments the time through the game.
	 * @param dt The change in time.
	 */
	public void incrementTime(float dt) {
		gameTime += dt;
	}
	
	/**
	 * Decrements the time the player has each turn.
	 * @param dt The change in time.
	 */
	public void decrementAvailablePlayerTime(float dt) {
		availablePlayerTime -= dt;
	}

	/**
	 * @return The game time.
	 */
	public float getGameTime() {
		return gameTime;
	}
	
	/**
	 * Replaces the game time with the given time.
	 * @param time The new time for the game.
	 */
	public void setGameTime(int time) {
		this.availablePlayerTime = time;
	}
	
	/**
	 * @return The current level of the game.
	 */
	public LevelModel getCurrentLevel() {
		return currentLevel;
	}
	/**
	 * @return A list of entities that will be killed.
	 */
	public ArrayList<EntityModel> getKill() {
		return kill;
	}
	
	/**
	 * @return The time of the turn.
	 */
	public int getTurnTimer() {
		return this.turnTimer;
	}
	
	/**
	 * Replaces the time of the turn with the new time.
	 * @param newTimer New time for the turn.
	 */
	public void setTurnTimer(int newTimer) {
		this.turnTimer = newTimer;
	}
	
	/**
	 * Adds an explosion to the game model when a projectile collision is detected.
	 * @param model The projectile model which collides and explodes.
	 */
	public void addExplosion(ProjectileModel model) {
		if (model.getExplosion() != -1) {
			explosions.add(model);
		}
	}
	
	/**
	 * @return The explosions of this turn.
	 */
	public ArrayList<ProjectileModel> getExplosions() {
		return this.explosions;
	}
	
	/**
	 * @return The winner text which says who won the game.
	 */
	public String getWinnerText() {
		return this.winnerText;
	}
}
