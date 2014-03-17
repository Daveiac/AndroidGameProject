package no.ntnu.folk.game.gameplay.levels.controllers;

import java.lang.reflect.Field;
import java.util.ArrayList;

import no.ntnu.folk.game.R;

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
	 * Returns the levels as a list of string[] from the level list.
	 *
	 * @return levels. List with String[], getLevel[i][0] gives level name, getLevel[i][1] gives the INT value of the field in the R-class as a string.
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
