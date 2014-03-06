package no.ntnu.folk.game.models;

import no.ntnu.folk.game.models.enums.Weapons;

public class WeaponModel {
	private final ProjectileModel projectile;

	public WeaponModel(Weapons weapon) {
		this.projectile = new ProjectileModel(weapon.getProjectile());
	}

	// TODO

}
