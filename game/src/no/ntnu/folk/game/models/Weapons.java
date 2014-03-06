package no.ntnu.folk.game.models;

import java.util.ArrayList;
import java.util.Arrays;

public enum Weapons {
	WEAPON1, // TODO names, values etc.
	WEAPON2;

	private static WeaponModel[] defaultWeapons = new WeaponModel[] {
			new WeaponModel(WEAPON1)
	};
	public static ArrayList<WeaponModel> getDefaultWeapons() {
		return new ArrayList<>(Arrays.asList(defaultWeapons));
	}

}
