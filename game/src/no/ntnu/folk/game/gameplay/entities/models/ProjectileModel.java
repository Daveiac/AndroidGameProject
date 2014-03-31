package no.ntnu.folk.game.gameplay.entities.models;

import no.ntnu.folk.game.gameplay.entities.data.Projectiles;
import no.ntnu.folk.game.gameplay.entities.views.EntityToken;
import no.ntnu.folk.game.gameplay.entities.views.ProjectileToken;
import sheep.math.Vector2;

/**
 * The projectile model class. Contains the data of the specific model determined by the Projectiles class.
 *
 */
public class ProjectileModel extends EntityModel {
	private int directDamage;
	private int areaDamage;
	private int areaDamageRange;
	private int explosionImage;

	/**
	 * Constructing a projectileModel based on a projectile from Projectiles
	 *
	 * @param projectile data for this projectile
	 * @param player     Player that fired this projectile
	 */
	public ProjectileModel(Projectiles projectile, PlayerModel player) {
		super(
				projectile.name(),
				new Vector2(player.getPosition().getX(), player.getPosition().getY()),
				projectile.getImage()
		);
		this.directDamage = projectile.getDirectDamage();
		this.areaDamage = projectile.getAreaDamage();
		this.areaDamageRange = projectile.getAreaDamageRange();
		this.explosionImage = projectile.getExplosionImage();
		this.setMask(player.getMask());
		this.setGroup(player.getGroup());
	}

	/**
	 * @return A new ProjectileToken
	 */
	@Override
	protected EntityToken createToken(int image) {
		return new ProjectileToken(this, image);
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
	/**
	 * @return a image for explosion
	 */
	public int getExplosion() {
		return explosionImage;
	}
}
