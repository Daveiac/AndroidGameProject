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

	public ProjectileModel(Projectiles projectile) {
		this.directDamage = projectile.getDirectDamage();
		this.areaDamage = projectile.getAreaDamage();
		this.areaDamageRange = projectile.getAreaDamageRange();
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
