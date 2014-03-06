package no.ntnu.folk.game.models;

public abstract class ProjectileModel {
	/*
	 * Constants for the direct and area damages of this projectile. If -1 this
	 * projectile
	 */
	
	private int collisionDamage;
	private int areaDamage;
	private int areaEffect;

	public int getCollisionDamage() {
		return collisionDamage;
	}
	public int getAreaDamage() {
		return areaDamage;
	}
	public int getAreaEffect() {
		return areaEffect;
	}

}
