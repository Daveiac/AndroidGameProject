package no.ntnu.folk.game.gameplay.models;

import android.os.SystemClock;
import no.ntnu.folk.game.gameplay.entities.data.Teams;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import sheep.math.Vector2;

import java.util.ArrayList;

/**
 * Class used to keep track of the game-state as the game evolves.
 *
 * @author Rune
 */
public class GameModel {

	// Players
	private int playerCount;
	private int maxHealth;
	private ArrayList<PlayerModel> playerList;
	private int currentPlayer;

	//Map
	private String currentLevel;
	private String gameMode;

	//Game time
	private long gameTime;
	private long lastUpdateTime;
	private long availablePlayerTime;


	public GameModel(int playerCount, int playerHealth, String level, String gameMode) {
		this.playerCount = playerCount;
		this.maxHealth = playerHealth;
		this.currentLevel = level;
		this.gameMode = gameMode;
		playerList = new ArrayList<PlayerModel>();
		currentPlayer = 0;
		gameTime = 0;

		for (int i = 0; i < playerCount; i++) {
			String name = "Player " + i;
			Vector2 position = new Vector2(75 * (i + 1), 100 + (20 * i));
			Teams team;
			if (gameMode.equals("FFA")) { // FIXME Should not use string
				team = Teams.getTeamFromOrdinal(i);
			} else {
				team = i < playerCount / 2 ? Teams.RED : Teams.BLUE;
			}
			PlayerModel player = new PlayerModel(name, position, team);
			playerList.add(player);
		}
	}

	public ArrayList<PlayerModel> getPlayerList() {
		return this.playerList;
	}

	public void update() {
		long time = SystemClock.elapsedRealtime();
		long timeDiff = lastUpdateTime - time;
		gameTime += timeDiff;
		lastUpdateTime = time;
		availablePlayerTime -= timeDiff;
	}

	public void nextPlayer() {
		if (currentPlayer == playerCount - 1) currentPlayer = 0;
		else currentPlayer++;
	}

	public PlayerModel getCurrentPlayer() {
		return this.playerList.get(currentPlayer);
	}

	/**
	 * @return
	 */
	public Object[] getGameOptions() { // FIXME Do not use Object[]
		Object[] options = {playerCount, maxHealth, currentLevel, gameMode};
		return options;
	}

	public boolean playerTimeUp() {
		if (availablePlayerTime <= 0) return false;
		return true;
	}

	public int getPlayerCount() {
		return this.playerCount;
	}
}
