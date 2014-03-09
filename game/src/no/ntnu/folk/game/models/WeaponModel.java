package no.ntnu.folk.game.models;

import no.ntnu.folk.game.models.enums.Projectiles;
import no.ntnu.folk.game.models.enums.Weapons;
import no.ntnu.folk.game.views.tokens.Token;
import no.ntnu.folk.game.views.tokens.WeaponToken;
import sheep.math.Vector2;

public class WeaponModel extends Model {

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
	@Override
	protected Token createToken() {
		return new WeaponToken(this);
	}

	// TODO

	public Projectiles getProjectileType() {
		return projectileType;
	}

}
