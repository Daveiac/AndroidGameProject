package no.ntnu.folk.game.models.enums;

public enum Projectiles {
	BULLET(50, 0, 0),
	ROCKET(35, 20, 10); // TODO set proper values

	private final int directDamage;
	private final int areaDamage;
	private final int areaDamageRange;

	Projectiles(int directDamage, int areaDamage, int areaDamageRange) {
		this.directDamage = directDamage;
		this.areaDamage = areaDamage;
		this.areaDamageRange = areaDamageRange;
	}

	public int getDirectDamage() {
		return directDamage;
	}
	public int getAreaDamage() {
		return areaDamage;
	}
	public int getAreaDamageRange() {
		return areaDamageRange;
	}

}
