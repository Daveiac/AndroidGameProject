package no.ntnu.folk.game.controllers;

import no.ntnu.folk.game.models.LevelModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Level controller. Loads levels .
 * @author b2
 *
 */
public class LevelController {

	private File[] listOfLevels;
	private LevelModel levelmodel;

	/**
	 * Constructor. Constructs the level controller which loads levels from source folder.
	 */
	public LevelController() {
		readLevels();
	}

	/**
	 * Reads the level files from the levels folder and puts them in a list.
	 */
	private void readLevels() {
		File folder = new File("res/raw");
		listOfLevels = folder.listFiles();
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
