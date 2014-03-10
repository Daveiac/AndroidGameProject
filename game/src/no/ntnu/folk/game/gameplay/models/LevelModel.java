package no.ntnu.folk.game.models;

import sheep.game.Sprite;

import java.util.ArrayList;

import no.ntnu.folk.game.levels.LevelParser;
import no.ntnu.folk.game.views.level.LevelToken;

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
	 * @param level. Level name
	 */
	public LevelModel(String levelName) {
		this.level = levelName;
	}

	/**
	 * Loads the specific level given by the parameter levelName and sets it as the current level model.
	 * @param levelName. Name of the requested level.
	 */
	public void loadLevel() {
		// TODO Auto-generated method stub
		tokens.addAll(LevelParser.parseLevel(level));
	}
}
