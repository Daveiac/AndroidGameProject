package no.ntnu.folk.game.gameplay.entities.data;

import no.ntnu.folk.game.R;

public enum Projectiles {
	BULLET(50, 0, 0, R.drawable.bullet_medium, 32, 16),
	ROCKET(35, 20, 10, R.drawable.bullet_big, 38, 19);

	private final int directDamage;
	private final int areaDamage;
	private final int areaDamageRange;
	private final int image;
	private final float imageWidth;
	private final float imageHeight;

	/**
	 * @param directDamage    damage dealt by being hit directly by this projectile
	 * @param areaDamage      damage dealt to entities within range of where this projectile hits
	 * @param areaDamageRange range for area damage
	 * @param image           Image ID for this projectile
	 * @param imageWidth      Image width for a token of this type
	 * @param imageHeight     Image height for a token of this type
	 */
	Projectiles(int directDamage, int areaDamage, int areaDamageRange, int image, float imageWidth, float imageHeight) {
		this.directDamage = directDamage;
		this.areaDamage = areaDamage;
		this.areaDamageRange = areaDamageRange;
		this.image = image;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
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
	 * @return Image width for this projectile
	 */
	public float getImageWidth() {
		return imageWidth;
	}
	/**
	 * @return Image height for this projectile
	 */
	public float getImageHeight() {
		return imageHeight;
	}
}
