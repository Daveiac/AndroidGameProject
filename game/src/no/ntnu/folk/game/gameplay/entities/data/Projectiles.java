package no.ntnu.folk.game.gameplay.entities.data;

import no.ntnu.folk.game.R;

/**
 * An enum class for the projectiles in-game used for creation of projectiles.
 *
 */
public enum Projectiles {
	HANDGUN_BULLET(400, 15, 0, 0, 999, R.drawable.bullet_small, -1),
	RIFLE_BULLET(500, 30, 0, 0, 3, R.drawable.bullet_medium, -1),
	ROCKET(300, 5, 20, 100, 5, R.drawable.bullet_big, R.drawable.explosion_medium),
	TURTLE(200, 5, 30, 100, 2, R.drawable.turtle, R.drawable.explosion_medium),
	GRENADE(300, 1, 30, 150, 1, R.drawable.grenade, R.drawable.explosion_large);

	private final int muzzleVelocity;
	private final int directDamage;
	private final int areaDamage;
	private final int areaDamageRange;
	private final int image;
	private final int explosionImage;
	private final int shotsEachGame;

	/**
	 * @param directDamage    damage dealt by being hit directly by this projectile
	 * @param areaDamage      damage dealt to entities within range of where this projectile hits
	 * @param areaDamageRange range for area damage
	 * @param shotsEachGame  shots each game for this projectile
	 * @param image           Image ID for this projectile
	 * @param explosionImage  Image ID for the explosion, -1 if no explosion image
	 */
	Projectiles(int muzzleVelocity, int directDamage, int areaDamage, int areaDamageRange, int shotsEachGame, int image, int explosionImage) {
		this.muzzleVelocity = muzzleVelocity;
		this.directDamage = directDamage;
		this.areaDamage = areaDamage;
		this.areaDamageRange = areaDamageRange;
		this.shotsEachGame = shotsEachGame;
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

	/**
	 * @return Shots each game for the projectile
	 */
	public int getShotsEachGame() {
		return this.shotsEachGame;
	}
}
