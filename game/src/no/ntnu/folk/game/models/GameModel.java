package no.ntnu.folk.game.models;

import java.util.ArrayList;

import android.os.SystemClock;

import no.ntnu.folk.game.Program;
import no.ntnu.folk.game.states.menus.PreGameMenu;

/**
 * Class used to keep track of the game-state as the game evolves.
 * 
 * @author Rune
 *
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
	
	
	public GameModel(int playerCount, int playerHealth, String level, String gameMode){
		this.playerCount = playerCount;
		this.maxHealth = playerHealth;
		this.currentLevel = level;
		this.gameMode = gameMode;
	}
	
	public ArrayList<PlayerModel> getPlayerList(){
		return this.playerList;
	}
	
	public void update(){
		long time = SystemClock.elapsedRealtime();
		long timeDiff = lastUpdateTime - time;
		gameTime += timeDiff;
		lastUpdateTime = time;
		availablePlayerTime -= timeDiff;
	}
	
	public void nextPlayer(){
		if(currentPlayer == playerCount-1) currentPlayer = 0;
		else currentPlayer++;
	}
	
	public PlayerModel getCurrentPlayer(){
		return this.playerList.get(currentPlayer);
	}
	
	/**
	 * 
	 * @return
	 */
	public Object[] getGameOptions(){
		Object[] options = {playerCount, maxHealth, currentLevel, gameMode};
		return options;
	}
	
	public boolean playerTimeUp(){
		if (availablePlayerTime <= 0) return false;
		return true;
	}
	
	public int getPlayerCount(){
		return this.playerCount;
	}
}
