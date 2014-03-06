package no.ntnu.folk.game;

import java.util.ArrayList;

public class Constants {
	private static int[] windowSize;
	private static ArrayList defaultWeapons;

	public static int[] getWindowSize() {
		return windowSize;
	}
	public static void setWindowSize(int[] windowSize) {
		Constants.windowSize = windowSize;
	}
	
	public static ArrayList getDefaultWeapons(){
		return defaultWeapons;
	}

}
