package no.ntnu.folk.game.models.enums;

public enum Projectiles {
	BULLET(50, 0, 0, 5, 3),
	ROCKET(35, 20, 10, 10, 6); // TODO set proper values

	private final int directDamage;
	private final int areaDamage;
	private final int areaDamageRange;
	private float imageWidth;
	private float imageHeight;

	/**
	 * @param directDamage    damage dealt by being hit directly by this projectile
	 * @param areaDamage      damage dealt to entities within range of where this projectile hits
	 * @param areaDamageRange range for area damage
	 * @param imageWidth      Image width for a token of this type
	 * @param imageHeight     Image height for a token of this type
	 */
	Projectiles(int directDamage, int areaDamage, int areaDamageRange, float imageWidth, float imageHeight) {
		this.directDamage = directDamage;
		this.areaDamage = areaDamage;
		this.areaDamageRange = areaDamageRange;
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
