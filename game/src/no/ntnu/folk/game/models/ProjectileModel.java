package no.ntnu.folk.game.models;

public abstract class ProjectileModel {
	/*
	 * Constants for the direct and area damages of this projectile. If -1 this
	 * projectile
	 */
	
	private int collisionDamage;

	public abstract int getCollisionDamage();
	public abstract int getAreaDamage();
	public abstract int getAreaEffect();
}
