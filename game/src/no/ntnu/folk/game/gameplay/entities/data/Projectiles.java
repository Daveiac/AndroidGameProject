package no.ntnu.folk.game.gameplay.entities.data;

import no.ntnu.folk.game.R;

public enum Projectiles {
	HANDGUN_BULLET(400, 15, 0, 0, R.drawable.bullet_small, -1),
	RIFLE_BULLET(500, 25, 0, 0, R.drawable.bullet_medium, -1),
	ROCKET(300, 10, 20, 100, R.drawable.bullet_big, R.drawable.explosion),
	TURTLE(200, 10, 30, 100, R.drawable.turtle, R.drawable.explosion);

	private final int muzzleVelocity;
	private final int directDamage;
	private final int areaDamage;
	private final int areaDamageRange;
	private final int image;
	private final int explosionImage;

	/**
	 * @param directDamage    damage dealt by being hit directly by this projectile
	 * @param areaDamage      damage dealt to entities within range of where this projectile hits
	 * @param areaDamageRange range for area damage
	 * @param image           Image ID for this projectile
	 * @param explosionImage  Image ID for the explosion
	 */
	Projectiles(int muzzleVelocity, int directDamage, int areaDamage, int areaDamageRange, int image, int explosionImage) {
		this.muzzleVelocity = muzzleVelocity;
		this.directDamage = directDamage;
		this.areaDamage = areaDamage;
		this.areaDamageRange = areaDamageRange;
		this.image = image;
		this.explosionImage = explosionImage;
	}

	/**
	 * @return mass of the projectile
	 */
	public int getMuzzleVelocity() {
		return muzzleVelocity;
	}

	/**
	 * @return damage dealt by being hit directly by this projectile
	 */
	public int getDirectDamage() {
		return directDamage;
	}
	/**
	 * @return damage dealt to entities within range of where this projectile hits
	 */
	public int getAreaDamage() {
		return areaDamage;
	}
	/**
	 * @return range for area damage
	 */
	public int getAreaDamageRange() {
		return areaDamageRange;
	}

	/**
	 * @return Image id for this projectile
	 */
	public int getImage() {
		return image;
	}

	/**
	 * @return Image for explosions
	 */
	public int getExplosionImage() {
		return explosionImage;
	}

}
