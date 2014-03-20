package no.ntnu.folk.game.gameplay.models;

import java.util.ArrayList;

import no.ntnu.folk.game.gameplay.levels.controllers.LevelParser;
import no.ntnu.folk.game.gameplay.levels.views.LevelToken;

/**
 * Model of a single level. It is created with its name as a unique ID.
 * This object contains the level tokens necessary to model the whole level.
 *
 * @author b2
 */
public class LevelModel {

	private final int levelId;
	private int[] gridSize;
	private ArrayList<int[]> startPositions;
	private LevelToken[][] grid;
	private ArrayList<LevelToken> levelTokens;

	/**
	 * Constructor. Constructs the LevelModel by giving the name of the requested level.
	 *
	 * @param i int valeu for the level in R.raw
	 */
	public LevelModel(int i) {
		this.levelId = i;
		gridSize = new int[2];
		startPositions = new ArrayList<int[]>();
		levelTokens = new ArrayList<LevelToken>();
		loadLevel();
    }

	public int getLevel() {
		return levelId;
	}

	/**
	 * Loads the specific level given by the parameter levelName and sets it as the current level model.
	 *
	 */
	public void loadLevel() {
		LevelParser.parseLevel(this);
	}

	public void setSize(int width, int height) {
		gridSize[0] = width;
		gridSize[1] = height;
	}

	public void addStartPosition(int x, int y) {
		int[] startPosition = {x, y};
		startPositions.add(startPosition);
	}

	public ArrayList<int[]> getStartPositions() {
		return startPositions;
	}

    public LevelToken[][] getGrid(){
        return this.grid;
    }
    
    public ArrayList<LevelToken> getLevelTokens() {
    	return levelTokens;
    }
}
