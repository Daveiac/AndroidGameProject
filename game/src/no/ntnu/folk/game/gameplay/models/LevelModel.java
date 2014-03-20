package no.ntnu.folk.game.gameplay.models;

import no.ntnu.folk.game.gameplay.levels.controllers.LevelController;
import no.ntnu.folk.game.gameplay.levels.controllers.LevelParser;
import no.ntnu.folk.game.gameplay.levels.views.LevelToken;
import sheep.game.Sprite;

import java.util.ArrayList;

/**
 * Model of a single level. It is created with its name as a unique ID.
 * This object contains the levelTokens necessary to model the whole level.
 *
 * @author b2
 */
public class LevelModel extends Sprite {
	private final int id;
	private int[] size;
	private ArrayList<int[]> startPositions;
	private ArrayList<LevelToken> levelTokens;

	/**
	 * Constructor. Constructs the LevelModel by giving the name of the requested level.
	 *
	 * @param levelId Level id
	 */
	public LevelModel(int levelId) {
		this.id = Integer.parseInt(LevelController.getLevels()[levelId][1]);
		size = new int[2];
		startPositions = new ArrayList<int[]>();
		levelTokens = new ArrayList<LevelToken>();
		loadLevel();
	}

	public int getId() {
		return id;
	}

	/**
	 * Loads the specific level given by the parameter levelName and sets it as the current level model.
	 */
	public void loadLevel() {
		LevelParser.parseLevel(this);
	}

	public void setSize(int width, int height) {
		size[0] = width;
		size[1] = height;
	}

	public void addStartPosition(int x, int y) {
		int[] startPosition = {x, y};
		startPositions.add(startPosition);
	}

	public void addToken(LevelToken token) {
		levelTokens.add(token);
	}

	public ArrayList<LevelToken> getLevelTokens() {
		return levelTokens;
	}

	public ArrayList<int[]> getStartPositions() {
		return startPositions;
	}

}
