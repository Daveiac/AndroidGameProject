package no.ntnu.folk.game.gameplay.models;

import no.ntnu.folk.game.constants.ProgramConstants;
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

	private final int level;
	private int[] gridSize;
	private ArrayList<int[]> startPositions;
	private ArrayList<LevelToken> tokens;
    private int[][] grid;

	/**
	 * Constructor. Constructs the LevelModel by giving the name of the requested level.
	 *
	 * @param i int valeu for the level in R.raw
	 */
	public LevelModel(int i) {
		this.level = i;
		gridSize = new int[2];
		startPositions = new ArrayList<int[]>();
		tokens = new ArrayList<LevelToken>();
		loadLevel();
        grid = makeGrid();
    }

    private int[][] makeGrid(){
        int x = gridSize[0];
        int y = gridSize[1];
        grid = new int[x][y];
        for (int i = 0; i<grid.length;i++){
            for (int j = 0; j<grid[i].length;j++){
                grid[i][j] = 0;
            }
        }
        for(LevelToken lt: getLevelTokens()){
            for (int i = (int)lt.getX(); i < lt.getX()+lt.getParameters()[0]; i++) {
                for (int j = (int)lt.getY(); j < lt.getY()+lt.getParameters()[1]; j++) {
                    grid[i][j] = 1;
                }
            }
        }
        return grid;
    }

	public int getLevel() {
		return level;
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

	public void addToken(LevelToken token) {
		tokens.add(token);
	}

	public ArrayList<LevelToken> getLevelTokens() {
		return tokens;
	}

	public ArrayList<int[]> getStartPositions() {
		return startPositions;
	}

    public int[][] getGrid(){
        return this.grid;
    }
}
