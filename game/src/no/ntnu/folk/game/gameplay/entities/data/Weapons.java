package no.ntnu.folk.game.gameplay.entities.data;

import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.entities.models.WeaponModel;

import java.util.ArrayList;

/**
 * An enum class for the weapons in-game used for creation of weapons.
 *
 */
public enum Weapons {
	HANDGUN(Projectiles.HANDGUN_BULLET, R.drawable.handgun, R.drawable.handgun_select),
	ROCKET_LAUNCHER(Projectiles.ROCKET, R.drawable.rocketlancher, R.drawable.rocketlancher_select),
	RIFLE(Projectiles.RIFLE_BULLET, R.drawable.rifle, R.drawable.rifle_select),
	TURTLEGUN(Projectiles.TURTLE, R.drawable.turtlegun, R.drawable.turtlegun_select),
	GRENADE(Projectiles.GRENADE, R.drawable.grenade, R.drawable.grenade_select);

	private final Projectiles projectile;
	private final int image;
	private final int selectImage;

	private static Weapons[] defaultWeapons = new Weapons[]{
			HANDGUN,
			ROCKET_LAUNCHER,
			RIFLE,
			TURTLEGUN,
			GRENADE
	};

	/**
	 * @param projectile The type of projectile used in this weapon
	 * @param image      Image used for drawing this weapon
	 */
	Weapons(Projectiles projectile, int image, int selectImage) {
		this.projectile = projectile;
		this.image = image;
		this.selectImage = selectImage;
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
	public int getSelectImage() {
		return this.selectImage;
	}
}
