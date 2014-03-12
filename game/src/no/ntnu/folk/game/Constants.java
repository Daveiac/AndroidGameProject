package no.ntnu.folk.game;

import no.ntnu.folk.game.gameplay.levels.controllers.LevelController;

public class Constants {
	public static final float PLAYER_SPEED = 10;
	// Debugging
	public static boolean debugging = true;
	// Game options
	public static final int DEFAULT_HEALTH = 100;
	public static final int MAX_HEALTH = 1000;
	public static String[] LEVEL_LIST = {"1"}; //TODO LevelController.getLevels() // FIXME Use enum!
	private static Enum FFA, TEAMS;
	public static Enum[] GAME_TYPE = {FFA,TEAMS};
	// Player count
	public static final int MAX_PLAYERS = 6;
	public static final int DEFAULT_PLAYER_COUNT = 2;

	// Window size
	private static int[] windowSize;
	private static int buttonWidth;
	private static int buttonHeight;


	public static int[] getWindowSize() {
		return windowSize;
	}
	public static void setWindowSize(int[] windowSize) {
		Constants.windowSize = windowSize;
		Constants.buttonWidth = windowSize[0] / 2;
		Constants.buttonHeight = windowSize[1] / 8;
	}

	public static int getButtonWidth() {
		return buttonWidth;
	}
	public static int getButtonHeight() {
		return buttonHeight;
	}

	public static boolean isDebugging() {
		return debugging;
	}
	public static void toggleDebugging() {
		debugging = !debugging;
	}

}
