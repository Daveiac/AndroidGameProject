package no.ntnu.folk.game.gameplay.levels.controllers;

import no.ntnu.folk.game.R;

import java.lang.reflect.Field;

/**
 * Level controller. Loads levels from source folder.
 */
public class LevelController {

	private static Field[] listOfLevels;
	private static String[][] levels;
	/**
	 * Returns the levels as a list of string[] from the level list.
	 *
	 * @return levels. List with String[], getLevel[i][0] gives level name, getLevel[i][one] gives the INT value of the field in the R-class as a string.
	 */
	public static String[][] getLevels() {
		if (levels != null) {
			return levels;
		}
		listOfLevels = R.raw.class.getDeclaredFields();
		int numberOfLevels = listOfLevels.length;
		String[][] levels = new String[numberOfLevels][2];
		for (int i = 0; i < listOfLevels.length; i++) {
			levels[i][0] = listOfLevels[i].getName();
			try {
				levels[i][1] = Integer.toString(listOfLevels[i].getInt(null));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		LevelController.levels = levels;
		return levels;
	}
}
