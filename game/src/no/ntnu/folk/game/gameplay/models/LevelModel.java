package no.ntnu.folk.game.gameplay.models;

import no.ntnu.folk.game.gameplay.levels.controllers.LevelParser;
import no.ntnu.folk.game.gameplay.levels.views.LevelToken;
import sheep.game.Sprite;

import java.util.ArrayList;

/**
 * Model of a single level. It is created with its name as a unique ID.
 * This object contains the level tokens necessary to model the whole level.
 *
 * @author b2
 */
public class LevelModel extends Sprite {
	// TODO

	private final String level;
	private int[] size;
	private ArrayList<int[]> startPositions;
	private ArrayList<LevelToken> tokens;

	/**
	 * Constructor. Constructs the LevelModel by giving the name of the requested level.
	 *
	 * @param levelName Level name
	 */
	public LevelModel(String levelName) {
		this.level = levelName;
		size = new int[2];
		startPositions = new ArrayList<int[]>();
		tokens = new ArrayList<LevelToken>();
		loadLevel();
	}

	public String getLevel() {
		return level;
	}

	/**
	 * Loads the specific level given by the parameter levelName and sets it as the current level model.
	 *
	 * @param levelName Name of the requested level.
	 */
	public void loadLevel() {
		// TODO Auto-generated method stub
		LevelParser.parseLevel(this);
	}

	public void setSize(int width, int height) {
		// TODO Auto-generated method stub
		size[0] = width;
		size[1] = height;
	}

	public void addStartPosition(int x, int y) {
		int[] startPosition = {x, y};
		startPositions.add(startPosition);
	}

	public void addToken(LevelToken token) {
		// TODO Auto-generated method stub
		tokens.add(token);
	}

	public ArrayList<int[]> getStartPositions() {
		return startPositions;
	}
}
