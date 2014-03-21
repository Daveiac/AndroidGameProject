package no.ntnu.folk.game.constants;

public class ProgramConstants {
	// Debugging
	public static boolean debugging = true;
	private static boolean unlimitedFire = false;

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

	public static boolean isUnlimitedFire() {
		return unlimitedFire;
	}
	public static void toggleUnlimitedFire() {
		unlimitedFire = !unlimitedFire;
	}
}
