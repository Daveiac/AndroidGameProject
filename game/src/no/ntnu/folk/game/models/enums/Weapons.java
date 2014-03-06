package no.ntnu.folk.game.models.enums;

import no.ntnu.folk.game.models.WeaponModel;

import java.util.ArrayList;
import java.util.Arrays;

public enum Weapons {
	HANDGUN(Projectiles.BULLET),
	ROCKET_LAUNCHER(Projectiles.ROCKET);

	private final Projectiles projectile;

	private static WeaponModel[] defaultWeapons = new WeaponModel[]{
			new WeaponModel(HANDGUN),
			new WeaponModel(ROCKET_LAUNCHER),
	};

	/**
	 * @param projectile The type of projectile used in this weapon
	 */
	Weapons(Projectiles projectile) {
		this.projectile = projectile;
	}

	/**
	 * @return List of weapons given to a player by default
	 */
	public static ArrayList<WeaponModel> getDefaultWeapons() {
		return new ArrayList<>(Arrays.asList(defaultWeapons));
	}

	/**
	 * @return Type of projectile used in this weapon
	 */
	public Projectiles getProjectile() {
		return projectile;
	}

}
