package no.ntnu.folk.game.gameplay.entities.data;

import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.entities.models.WeaponModel;

import java.util.ArrayList;

public enum Weapons {
	HANDGUN(Projectiles.HANDGUN_BULLET, R.drawable.handgun),
	ROCKET_LAUNCHER(Projectiles.ROCKET, R.drawable.rocketlancher),
	RIFLE(Projectiles.RIFLE_BULLET, R.drawable.rifle);

	private final Projectiles projectile;
	private final int image;

	private static Weapons[] defaultWeapons = new Weapons[]{
			HANDGUN,
			ROCKET_LAUNCHER,
			RIFLE,
	};

	/**
	 * @param projectile The type of projectile used in this weapon
	 * @param image      Image used for drawing this weapon
	 */
	Weapons(Projectiles projectile, int image) {
		this.projectile = projectile;
		this.image = image;
	}

	/**
	 * @return List of weapons given to a player by default
	 */
	public static ArrayList<WeaponModel> getDefaultWeapons() {
		ArrayList<WeaponModel> arrayList = new ArrayList<WeaponModel>();
		for (Weapons weapon : defaultWeapons) {
			arrayList.add(new WeaponModel(weapon));
		}
		return arrayList;
	}

	/**
	 * @return Type of projectile used in this weapon
	 */
	public Projectiles getProjectile() {
		return projectile;
	}

	/**
	 * @return The image of this weapon
	 */
	public int getImage() {
		return image;
	}
}
