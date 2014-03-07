package no.ntnu.folk.game;

public class Constants {
	private static int[] windowSize;
	private static final boolean debugging = true;
	public static final int START_GAME = 0;
	public static final int PRE_GAME_MENU = 1;
	public static final int LOAD_MENU = 2;
	public static final int LOAD_GAME = 3;
	public static final int BACK = 4;
	
	public static final int MAX_PLAYERS = 6;

	public static int[] getWindowSize() {
		return windowSize;
	}
	public static void setWindowSize(int[] windowSize) {
		Constants.windowSize = windowSize;
	}

	public static boolean isDebugging() {
		return debugging;
	}
}
