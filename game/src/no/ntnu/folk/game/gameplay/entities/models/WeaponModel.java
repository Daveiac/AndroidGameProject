package no.ntnu.folk.game.gameplay.entities.models;

import no.ntnu.folk.game.gameplay.entities.data.Projectiles;
import no.ntnu.folk.game.gameplay.entities.data.Weapons;
import no.ntnu.folk.game.gameplay.entities.views.EntityToken;
import no.ntnu.folk.game.gameplay.entities.views.WeaponToken;
import sheep.math.Vector2;

public class WeaponModel extends EntityModel {
	private final Projectiles projectileType;
	private int coolDown;
	private Vector2 aim;
	private Weapons weapon;

	/**
	 * Constructing a weaponModel based on a weapon from Weapons
	 *
	 * @param weapon data for this weapon
	 */
	public WeaponModel(Weapons weapon) {
		super(
				weapon.name(),
				new Vector2(0, 0), // Weapon should follow player
				weapon.getImage()
		);
		this.weapon = weapon;
		projectileType = weapon.getProjectile();
		aim = new Vector2(0, 0);
	}

	/**
	 * @return A new WeaponToken
	 */
	@Override
	protected EntityToken createToken(int image) {
		return new WeaponToken(this, image);
	}
	@Override
	public void update(float dt) {
		super.update(dt);
		if (coolDown != 0) {
			coolDown -= dt;
			if (coolDown < 0) {
				coolDown = 0;
			}
		}
	}

	// TODO

	/**
	 * @return The type of projectiles this weapon fires
	 */
	public Projectiles getProjectileType() {
		return projectileType;
	}

	public void startCoolDownTimer() {
		coolDown = 100; // FIXME Test value, please ignore... Should be individual for each weapon.
	}
	public boolean isCool() {
		return coolDown == 0;
	}
	public void setAim(float x, float y) {
		aim.set(x, y);
	}
	public Vector2 getAim() {
		return new Vector2(aim.getX(), aim.getY());
//		return aim; // Using this one instead has an interesting effect!
	}
	public Weapons getWeapon() {
		return weapon;
	}
}
