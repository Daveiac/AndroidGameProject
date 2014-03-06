package no.ntnu.folk.game.models;

import no.ntnu.folk.game.models.enums.Weapons;

public class WeaponModel {
	private final ProjectileModel projectile;

	/**
	 * Constructing a weaponModel based on a weapon from Weapons
	 *
	 * @param weapon data for this weapon
	 */
	public WeaponModel(Weapons weapon) {
		this.projectile = new ProjectileModel(weapon.getProjectile());
	}

	// TODO

	/**
	 * @return this weapon's projectile
	 */
	public ProjectileModel getProjectile() {
		return projectile;
	}

}
