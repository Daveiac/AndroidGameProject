package no.ntnu.folk.game.models;

import sheep.game.Sprite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LevelModel extends Sprite {
	// TODO

	// antar mapsa blir henta som Strings i fra properties
	private String level;
	
	public LevelModel() {
		
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * Loads the specific level given by the parameter levelName and sets it as the current level model.
	 * @param levelName. Name of the requested level.
	 */
	public void loadLevel(String levelName) {
		// TODO Auto-generated method stub
		Properties p = new Properties();
		String path = "src/no.ntnu.folk.game.levels." + levelName + ".properties";
		try {
			p.load(new FileInputStream(path));
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		level = p.getProperty("level_name");
	}
}
