package no.ntnu.folk.game.models;

import sheep.game.Sprite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import no.ntnu.folk.game.views.level.LevelToken;

public class LevelModel extends Sprite {
	// TODO

	// antar mapsa blir henta som Strings i fra properties
	private String levelName;
	private ArrayList<LevelToken> tokens = new ArrayList<LevelToken>();
	
	
	public LevelModel() {
		
	}

	public String getLevel() {
		return levelName;
	}

	public void setLevel(String level) {
		this.levelName = level;
	}

	/**
	 * Loads the specific level given by the parameter levelName and sets it as the current level model.
	 * @param levelName. Name of the requested level.
	 */
	public void loadLevel(String levelName2) {
		// TODO Auto-generated method stub
		Properties p = new Properties();
		String path = "src/no.ntnu.folk.game.levels." + levelName2 + ".properties";
		try {
			p.load(new FileInputStream(path));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		levelName = p.getProperty("level_name");
	}
}
