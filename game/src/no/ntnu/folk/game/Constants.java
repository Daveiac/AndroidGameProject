package no.ntnu.folk.game;

public class Constants {
	// Debugging
	public static final boolean IS_DEBUGGING = true;

	// Player count
	public static final int MAX_PLAYERS = 6;
	public static final int DEFAULT_PLAYER_COUNT = 1;

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
}
