package no.ntnu.folk.game.gameplay.models;

import no.ntnu.folk.game.gameplay.levels.levels.LevelParser;
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
	private ArrayList<LevelToken> tokens = new ArrayList<LevelToken>();

	/**
	 * Constructor. Constructs the LevelModel by giving the name of the requested level.
	 *
	 * @param levelName Level name
	 */
	public LevelModel(String levelName) {
		this.level = levelName;
	}

	/**
	 * Loads the specific level given by the parameter levelName and sets it as the current level model.
	 *
	 * @param levelName Name of the requested level.
	 */
	public void loadLevel() {
		// TODO Auto-generated method stub
		tokens.addAll(LevelParser.parseLevel(level));
	}
}
