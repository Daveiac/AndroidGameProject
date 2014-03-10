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

	private ArrayList<PlayerModel> playerList;
	private long gameTime;
	private long lastUpdateTime;
	private long currentPlayerTime;
	
	
	public GameModel(){
		gameTime = 0;
		currentPlayerTime = 0;
		lastUpdateTime = SystemClock.elapsedRealtime();
	}
	
	public ArrayList<PlayerModel> getPlayerList(){
		return this.playerList;
	}
	
	public void update(){
		long time = SystemClock.elapsedRealtime();
		gameTime = time - lastUpdateTime;
		lastUpdateTime = time;
		currentPlayerTime = time - lastUpdateTime;
	}
}
