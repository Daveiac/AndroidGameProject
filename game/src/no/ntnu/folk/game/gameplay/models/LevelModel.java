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

	/**
	 * @return The id of the level model.
	 */
	public int getLevel() {
		return levelId;
	}

	/**
	 * Replaces the grid size of the level with the given width and height.
	 * @param width		The new width of the grid.
	 * @param height	The new height of the grid.
	 */
	public void setSize(int width, int height) {
		gridSize[0] = width;
		gridSize[1] = height;
	}

	/**
	 * Adds the available start positions for this level model.
	 * @param x	The x position.
	 * @param y	The y position.
	 */
	public void addStartPosition(int x, int y) {
		int[] startPosition = {x * GameplayConstants.GRID_SIZE, y * GameplayConstants.GRID_SIZE};
		startPositions.add(startPosition);
	}

	/**
	 * @return The available start positions for this level.
	 */
	public ArrayList<int[]> getStartPositions() {
		return startPositions;
	}

	/**
	 * @return The grid of this level.
	 */
	public LevelToken[][] getGrid() {
		return this.grid;
	}

	/**
	 * Replaces the grid of this level model with a new one.
	 * @param grid The new grid for this level model.
	 */
	public void setGrid(LevelToken[][] grid) {
		this.grid = grid;
	}

	/**
	 * @return All the level tokens that belong to this level model.
	 */
	public ArrayList<LevelToken> getLevelTokens() {
		return levelTokens;
	}

	/**
	 * @return The background image for this level model.
	 */
	public static Image getBackground() {
		return image;
	}

}
