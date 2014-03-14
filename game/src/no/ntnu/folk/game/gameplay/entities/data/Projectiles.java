package no.ntnu.folk.game.gameplay.entities.data;

import no.ntnu.folk.game.R;

public enum Projectiles {
	BULLET(25, 0, 0, R.drawable.bullet_medium),
	ROCKET(35, 20, 10, R.drawable.bullet_big);

	private final int directDamage;
	private final int areaDamage;
	private final int areaDamageRange;
	private final int image;

	/**
	 * @param directDamage    damage dealt by being hit directly by this projectile
	 * @param areaDamage      damage dealt to entities within range of where this projectile hits
	 * @param areaDamageRange range for area damage
	 * @param image           Image ID for this projectile
	 */
	Projectiles(int directDamage, int areaDamage, int areaDamageRange, int image) {
		this.directDamage = directDamage;
		this.areaDamage = areaDamage;
		this.areaDamageRange = areaDamageRange;
		this.image = image;
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

}
