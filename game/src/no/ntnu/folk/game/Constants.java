package no.ntnu.folk.game;

public class Constants {
	private static int[] windowSize;
	private static final boolean debugging = true;

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
