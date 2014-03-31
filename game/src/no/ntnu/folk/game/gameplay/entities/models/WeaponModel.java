package no.ntnu.folk.game.gameplay.entities.models;

import no.ntnu.folk.game.gameplay.entities.data.Projectiles;
import no.ntnu.folk.game.gameplay.entities.data.Weapons;
import no.ntnu.folk.game.gameplay.entities.views.EntityToken;
import no.ntnu.folk.game.gameplay.entities.views.WeaponToken;
import sheep.math.Vector2;

/**
 * The weapon model class. Contains the data of the specific model determined by the Weapons class.
 *
 */
public class WeaponModel extends EntityModel {
	private final Projectiles projectileType;
	private Vector2 aim;
	private Weapons weapon;
	private int ammo;

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
		this.ammo = weapon.getProjectile().getShotsEachGame();
		aim = new Vector2(10, 0);
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
	}

	/**
	 * @return The type of projectiles this weapon fires
	 */
	public Projectiles getProjectileType() {
		return projectileType;
	}

	/**
	 * Sets the position of the crosshair.
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public void setAim(float x, float y) {
		aim.set(x, y);
		if (aim.getLength() == 0) {
			aim.set(10, 0);
		}
	}
	
	/**
	 * @return Vector with the crosshair coordinates.
	 */
	public Vector2 getAim() {
		return new Vector2(aim.getX(), aim.getY());
//		return aim; // Using this one instead has an interesting effect!
	}

	/**
	 * @return Which type of weapon this is.
	 */
	public Weapons getWeapon() {
		return weapon;
	}
	public boolean isAmmo() {
		return this.ammo > 0;
	}
	public int getAmmo() {
		return this.ammo;
	}
	public void reduceAmmo(){
		this.ammo--;
	}
}
