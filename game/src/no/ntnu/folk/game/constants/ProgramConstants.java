package no.ntnu.folk.game.constants;

public class ProgramConstants {
	// Debugging
	public static boolean debugging = false;
	private static boolean unlimitedFire = false;
	private static boolean debugWalls = false;

	// Window size
	private static int[] windowSize;
	private static int buttonWidth;
	private static int buttonHeight;


	public static int[] getWindowSize() {
		return windowSize;
	}
	public static void setWindowSize(int[] windowSize) {
		ProgramConstants.windowSize = windowSize;
		ProgramConstants.buttonWidth = windowSize[0] / 2;
		ProgramConstants.buttonHeight = windowSize[1] / 8;
	}

	/**
	 * @return The button width
	 */
	public static int getButtonWidth() {
		return buttonWidth;
	}

	/**
	 * @return The button height
	 */
	public static int getButtonHeight() {
		return buttonHeight;
	}

	/**
	 * @return True if debugging is on, else return false if it is off.
	 */
	public static boolean isDebugging() {
		return debugging;
	}

	/**
	 * Toggles the debugging option to either on or off depending on the previous condition.
	 */
	public static void toggleDebugging() {
		debugging = !debugging;
	}

	/**
	 * @return True if unlimited fire option is on, else return false if it is not.
	 */
	public static boolean isUnlimitedFire() {
		return unlimitedFire;
	}
	
	/**
	 * Toggles the unlimited fire option to either on or off depending on the previous condition when debugging.
	 */
	public static void toggleUnlimitedFire() {
		unlimitedFire = !unlimitedFire;
	}

	/**
	 * @return True if debugging walls option is on, else return false if it is not.
	 */
	public static boolean isDebugWalls() {
		return debugWalls;
	}
	
	/**
	 * Toggles the debugging walls option to either on or off depending on the previous condition when debugging.
	 */
	public static void toggleDebugWalls() {
		debugWalls = !debugWalls;
	}
}
