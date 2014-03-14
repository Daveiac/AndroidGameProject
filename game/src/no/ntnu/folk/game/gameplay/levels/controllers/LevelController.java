package no.ntnu.folk.game.gameplay.levels.controllers;

import java.lang.reflect.Field;

import android.util.Log;

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
	    listOfLevels = R.raw.class.getFields();
	}
	
	public void listRaw(){
	    Field[] fields=R.raw.class.getFields();
	    for(int count=0; count < fields.length; count++){
	        Log.i("Raw Asset: ", fields[count].getName());
	    }
	}

	/**
	 * Returns the levels as a list of strings from the level list.
	 *
	 * @return levels. List with the names of all levels.
	 */
	public static String[] getLevels() {
		readLevels();
	    int numberOfLevels = listOfLevels.length;
	    String[] levels = new String[numberOfLevels];
	    for (int i = 0; i < listOfLevels.length; i++) {
	    	levels[i] = listOfLevels[i].getName();
	    }
		return levels;
	}
}
