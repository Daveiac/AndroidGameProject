package no.ntnu.folk.game.models;

import no.ntnu.folk.game.models.enums.Projectiles;

import java.util.ArrayList;
import java.util.Arrays;

public enum Weapons {
	HANDGUN(Projectiles.BULLET),
	ROCKET_LAUNCHER(Projectiles.ROCKET);

	private Projectiles projectile;

	private static WeaponModel[] defaultWeapons = new WeaponModel[] {
			new WeaponModel(HANDGUN),
			new WeaponModel(ROCKET_LAUNCHER),
	};

	Weapons(Projectiles projectile) {
		this.projectile = projectile;
	}

	public static ArrayList<WeaponModel> getDefaultWeapons() {
		return new ArrayList<>(Arrays.asList(defaultWeapons));
	}

	public Projectiles getProjectile() {
		return projectile;
	}

}
