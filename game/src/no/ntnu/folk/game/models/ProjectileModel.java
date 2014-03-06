package no.ntnu.folk.game.models;

import no.ntnu.folk.game.models.enums.Projectiles;

public class ProjectileModel {
	/*
	 * Constants for the direct and area damages of this projectile. If -1 this
	 * projectile
	 */

	private int directDamage;
	private int areaDamage;
	private int areaDamageRange;

	/**
	 * Constructing a projectileModel based on a projectile from Projectiles
	 *
	 * @param projectile data for this projectile
	 */
	public ProjectileModel(Projectiles projectile) {
		this.directDamage = projectile.getDirectDamage();
		this.areaDamage = projectile.getAreaDamage();
		this.areaDamageRange = projectile.getAreaDamageRange();
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
