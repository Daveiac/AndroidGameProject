package no.ntnu.folk.game.models.enums;

public enum Projectiles {
	BULLET(50, 0, 0),
	ROCKET(35, 20, 10); // TODO set proper values

	private final int directDamage;
	private final int areaDamage;
	private final int areaDamageRange;

	/**
	 * @param directDamage    damage dealt by being hit directly by this projectile
	 * @param areaDamage      damage dealt to entities within range of where this projectile hits
	 * @param areaDamageRange range for area damage
	 */
	Projectiles(int directDamage, int areaDamage, int areaDamageRange) {
		this.directDamage = directDamage;
		this.areaDamage = areaDamage;
		this.areaDamageRange = areaDamageRange;
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

}
