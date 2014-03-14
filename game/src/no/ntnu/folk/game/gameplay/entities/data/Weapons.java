package no.ntnu.folk.game.gameplay.entities.data;

import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.entities.models.WeaponModel;

import java.util.ArrayList;

public enum Weapons {
	HANDGUN(Projectiles.BULLET, 49, 36, R.drawable.handgun),
	ROCKET_LAUNCHER(Projectiles.ROCKET, 28, 16, R.drawable.rocketlancher);  // TODO set proper values

	private final Projectiles projectile;
	private final float imageWidth;
	private final float imageHeight;
	private final int image;

	private static Weapons[] defaultWeapons = new Weapons[]{
			HANDGUN,
			ROCKET_LAUNCHER,
	};

	/**
	 * @param projectile  The type of projectile used in this weapon
	 * @param imageWidth  Image width for a token of this type
	 * @param imageHeight Image height for a token of this type
	 */
	Weapons(Projectiles projectile, float imageWidth, float imageHeight, int image) {
		this.projectile = projectile;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
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
	/**
	 * 
	 * @return The image of this weapon
	 */
	public int getImage(){
		return image;
	}
}
