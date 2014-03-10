package no.ntnu.folk.game.gameplay.models;

import android.os.SystemClock;
import no.ntnu.folk.game.gameplay.entities.data.Teams;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import sheep.math.Vector2;

import java.util.ArrayList;

/**
 * Class used to keep track of the game-state as the game evolves.
 *
 * @author Rune
 */
public class GameModel {
	// Entity lists
	private ArrayList<PlayerModel> playerList;
	private ArrayList<ProjectileModel> projectiles;

	// Players
	private int playerCount;
	private int maxHealth;
	private int currentPlayer;

	//Map
	private String currentLevel;
	private String gameMode;

	//Game time
	private long gameTime;
	private long lastUpdateTime;
	private long availablePlayerTime;

	/**
	 * @param playerCount  Number of players for the start of this game
	 * @param playerHealth Health all players starts with
	 * @param level        Level name
	 * @param gameMode     Game type (teams / ffa)
	 */
	public GameModel(int playerCount, int playerHealth, String level, String gameMode) {
		initializeFields(playerCount, playerHealth, level, gameMode);
		createPlayers();
	}
	/**
	 * Initialize the fields
	 *
	 * @param playerCount  Number of players for the start of this game
	 * @param playerHealth Health all players starts with
	 * @param level        Level name
	 * @param gameMode     Game type (teams / ffa)
	 */
	private void initializeFields(int playerCount, int playerHealth, String level, String gameMode) {
		this.playerCount = playerCount;
		this.maxHealth = playerHealth;
		this.currentLevel = level;
		this.gameMode = gameMode;
		projectiles = new ArrayList<ProjectileModel>();
		currentPlayer = 0;
		gameTime = 0;
	}
	/**
	 * Create the number of players
	 */
	private void createPlayers() {
		playerList = new ArrayList<PlayerModel>(playerCount);
		for (int i = 0; i < playerCount; i++) {
			String name = "Player " + i;
			Vector2 position = new Vector2(75 * (i + 1), 100 + (20 * i));
			Teams team;
			if (gameMode.equals("FFA")) { // FIXME Should not use string
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
	 * Update timer
	 */
	public void update() {
		long time = SystemClock.elapsedRealtime();
		long timeDiff = lastUpdateTime - time;
		gameTime += timeDiff;
		lastUpdateTime = time;
		availablePlayerTime -= timeDiff;
	}

	/**
	 * Set currentPlayer to the next player. Set to first player if a the end of the list.
	 */
	public void nextPlayer() {
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
	 * @return Game options
	 */
	public Object[] getGameOptions() { // FIXME Do not use Object[]
		Object[] options = {playerCount, maxHealth, currentLevel, gameMode};
		return options;
	}

	/**
	 * @return True if the time is up for current player
	 */
	public boolean playerTimeUp() {
		return availablePlayerTime <= 0;
	}

	/**
	 * @return Number of players when the game started
	 */
	public int getPlayerCount() {
		return this.playerCount;
	}

}
