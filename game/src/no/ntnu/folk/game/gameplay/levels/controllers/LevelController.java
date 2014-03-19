package no.ntnu.folk.game.gameplay.levels.controllers;

import no.ntnu.folk.game.R;

import java.lang.reflect.Field;

/**
 * Level controller. Loads levels .
 *
 * @author b2
 */
public class LevelController {

	private static Field[] listOfLevels;

	/**
	 * Constructor. Constructs the level controller which loads levels from source folder.
	 */
	public LevelController() {
		readLevels();
	}

	/**
	 * Reads the level files from the levels folder and puts them in a list.
	 */
	private static void readLevels() {
		listOfLevels = R.raw.class.getDeclaredFields();
	}

	/**
	 * Returns the levels as a list of strings from the level list.
	 *
	 * @return levels. List with the names of all levels.
	 */
	public static String[][] getLevels() {
		readLevels();
		int numberOfLevels = listOfLevels.length;
		String[][] levels = new String[numberOfLevels][2];
		for (int i = 0; i < listOfLevels.length; i++) {
			levels[i][0] = listOfLevels[i].getName();
			try {
				levels[i][1] = Integer.toString(listOfLevels[i].getInt(null));
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return levels;
	}
}
