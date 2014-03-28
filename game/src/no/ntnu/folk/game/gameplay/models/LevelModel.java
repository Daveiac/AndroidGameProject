package no.ntnu.folk.game.gameplay.models;

import no.ntnu.folk.game.R;
import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.gameplay.levels.controllers.LevelController;
import no.ntnu.folk.game.gameplay.levels.controllers.LevelParser;
import no.ntnu.folk.game.gameplay.levels.views.LevelToken;

import java.util.ArrayList;

import sheep.graphics.Image;

/**
 * Model of a single level. It is created with its name as a unique ID.
 * This object contains the level tokens necessary to model the whole level.
 *
 * @author b2
 */
public class LevelModel {

	private static final Image image = new Image(R.drawable.background);
	private final int levelId;
	private int[] gridSize;
	private ArrayList<int[]> startPositions;
	private LevelToken[][] grid;
	private ArrayList<LevelToken> levelTokens;

	/**
	 * Constructor. Constructs the LevelModel by giving the name of the requested level.
	 *
	 * @param levelId int valeu for the level in R.raw
	 */
	public LevelModel(int levelId) {
		this.levelId = Integer.parseInt(LevelController.getLevels()[levelId][1]);
		gridSize = new int[2];
		startPositions = new ArrayList<int[]>();
		levelTokens = new ArrayList<LevelToken>();
		LevelParser.parseLevel(this);
	}

	public int getLevel() {
		return levelId;
	}

	public void setSize(int width, int height) {
		gridSize[0] = width;
		gridSize[1] = height;
	}

	public void addStartPosition(int x, int y) {
		int[] startPosition = {x * GameplayConstants.GRID_SIZE, y * GameplayConstants.GRID_SIZE};
		startPositions.add(startPosition);
	}

	public ArrayList<int[]> getStartPositions() {
		return startPositions;
	}

	public LevelToken[][] getGrid() {
		return this.grid;
	}

	public void setGrid(LevelToken[][] grid) {
		this.grid = grid;
	}

	public ArrayList<LevelToken> getLevelTokens() {
		return levelTokens;
	}

	public static Image getBackground() {
		return image;
	}

}
