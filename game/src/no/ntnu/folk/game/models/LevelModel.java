package no.ntnu.folk.game.models;

import sheep.game.Sprite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import no.ntnu.folk.game.levels.LevelParser;
import no.ntnu.folk.game.views.level.LevelToken;

/**
 * Model of the level.
 * @author b2
 *
 */
public class LevelModel extends Sprite {
	// TODO

	// antar mapsa blir henta som Strings i fra properties
	private String levelName;
	private ArrayList<LevelToken> tokens = new ArrayList<LevelToken>();
	
	
	public LevelModel() {
		
	}

	/**
	 * Loads the specific level given by the parameter levelName and sets it as the current level model.
	 * @param levelName. Name of the requested level.
	 */
	public void loadLevel(String level) {
		// TODO Auto-generated method stub
		tokens.addAll(LevelParser.parseLevel(level));
	}
}
