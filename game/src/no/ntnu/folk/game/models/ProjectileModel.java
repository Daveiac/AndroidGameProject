package no.ntnu.folk.game.models;

import no.ntnu.folk.game.models.enums.Projectiles;
import no.ntnu.folk.game.views.tokens.ProjectileToken;
import no.ntnu.folk.game.views.tokens.Token;
import sheep.math.Vector2;

public class ProjectileModel extends Model {
	private int directDamage;
	private int areaDamage;
	private int areaDamageRange;

	/**
	 * Constructing a projectileModel based on a projectile from Projectiles
	 *
	 * @param projectile data for this projectile
	 */
	public ProjectileModel(Projectiles projectile, Vector2 position) {
		super(
				projectile.name(),
				position,
				projectile.getImageWidth(),
				projectile.getImageHeight()
		);
		this.directDamage = projectile.getDirectDamage();
		this.areaDamage = projectile.getAreaDamage();
		this.areaDamageRange = projectile.getAreaDamageRange();
	}

	/**
	 * @return A new ProjectileToken
	 */
	@Override
	protected Token createToken() {
		return new ProjectileToken(this);
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
