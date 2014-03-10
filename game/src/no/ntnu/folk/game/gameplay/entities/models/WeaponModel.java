package no.ntnu.folk.game.gameplay.entities.models;

import no.ntnu.folk.game.gameplay.entities.data.Projectiles;
import no.ntnu.folk.game.gameplay.entities.data.Weapons;
import no.ntnu.folk.game.gameplay.entities.views.EntityToken;
import no.ntnu.folk.game.gameplay.entities.views.WeaponToken;
import sheep.math.Vector2;

public class WeaponModel extends EntityModel {
	private final Projectiles projectileType;

	/**
	 * Constructing a weaponModel based on a weapon from Weapons
	 *
	 * @param weapon data for this weapon
	 */
	public WeaponModel(Weapons weapon) {
		super(
				weapon.name(),
				new Vector2(0, 0), // Weapon should follow player
				weapon.getImageWidth(),
				weapon.getImageHeight()
		);
		projectileType = weapon.getProjectile();
	}

	/**
	 * @return A new WeaponToken
	 */
	@Override
	protected EntityToken createToken() {
		return new WeaponToken(this);
	}

	// TODO

	/**
	 * @return The type of projectiles this weapon fires
	 */
	public Projectiles getProjectileType() {
		return projectileType;
	}

}
