package no.ntnu.folk.game.models.enums;

import no.ntnu.folk.game.models.WeaponModel;

import java.util.ArrayList;
import java.util.Arrays;

public enum Weapons {
	HANDGUN(Projectiles.BULLET, 15, 10),
	ROCKET_LAUNCHER(Projectiles.ROCKET, 28, 16);  // TODO set proper values

	private final Projectiles projectile;
	private final float imageWidth;
	private final float imageHeight;

	private static WeaponModel[] defaultWeapons = new WeaponModel[]{
			new WeaponModel(HANDGUN),
			new WeaponModel(ROCKET_LAUNCHER),
	};

	/**
	 * @param projectile  The type of projectile used in this weapon
	 * @param imageWidth  Image width for a token of this type
	 * @param imageHeight Image height for a token of this type
	 */
	Weapons(Projectiles projectile, float imageWidth, float imageHeight) {
		this.projectile = projectile;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
	}

	/**
	 * @return List of weapons given to a player by default
	 */
	public static ArrayList<WeaponModel> getDefaultWeapons() {
		return new ArrayList<WeaponModel>(Arrays.asList(defaultWeapons));
	}

	/**
	 * @return Type of projectile used in this weapon
	 */
	public Projectiles getProjectile() {
		return projectile;
	}

	/**
	 * @return Image width for this weapon
	 */
	public float getImageWidth() {
		return imageWidth;
	}
	/**
	 * @return Image height for this weapon
	 */
	public float getImageHeight() {
		return imageHeight;
	}
}
