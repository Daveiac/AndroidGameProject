package no.ntnu.folk.game.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import no.ntnu.folk.game.models.LevelModel;

/**
 * Level controller. Loads levels from properties-files as level names.
 * @author b2
 *
 */
public class LevelController {

	private File[] listOfLevels;
	private LevelModel levelmodel;

	/**
	 * Level controller constructor. Loads levels from levels folder.
	 */
	public LevelController() {
		readLevels();
	}

	/**
	 * Reads the level files from the levels folder and puts them in a list.
	 */
	private void readLevels() {
		File folder = new File("src/no.ntnu.folk.game.levels");
		listOfLevels = folder.listFiles();
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
		String level = p.getProperty("level_name");
		levelmodel.setLevel(level);
	}

	/**
	 * Returns the levels as a list of strings from the level list.
	 * @return levels. List with the names of all levels.
	 */
	public String[] getLevels() {
		int numberOfLevels = listOfLevels.length;
		String[] levels = new String[numberOfLevels];
		for (int i = 0; i < numberOfLevels; i++) {
			levels[i] = listOfLevels[i].getName();
		}
		return levels;
	}
}
