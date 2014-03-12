package no.ntnu.folk.game.constants;

import android.content.res.Resources;

public class Globals {
	// Debugging
	public static boolean debugging = true;

	// Window size
	private static int[] windowSize;
	private static int buttonWidth;
	private static int buttonHeight;
	private static Resources resources;


	public static int[] getWindowSize() {
		return windowSize;
	}
	public static void setWindowSize(int[] windowSize) {
		Globals.windowSize = windowSize;
		Globals.buttonWidth = windowSize[0] / 2;
		Globals.buttonHeight = windowSize[1] / 8;
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


	public static String getString(int id) {
		return resources.getString(id);
	}

	public static void setResources(Resources resources) {
		Globals.resources = resources;
	}
}
