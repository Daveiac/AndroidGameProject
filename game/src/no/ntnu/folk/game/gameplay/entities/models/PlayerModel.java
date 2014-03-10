package no.ntnu.folk.game.gameplay.entities.models;

import android.graphics.Canvas;
import no.ntnu.folk.game.gameplay.entities.data.Teams;
import no.ntnu.folk.game.gameplay.entities.data.Weapons;
import no.ntnu.folk.game.gameplay.entities.views.EntityToken;
import no.ntnu.folk.game.gameplay.entities.views.PlayerToken;
import sheep.math.Vector2;

import java.util.ArrayList;

/**
 * EntityModel for the player in the game. Use this as a entityModel to PlayerTokens.
 *
 * @author Rune
 */
public class PlayerModel extends EntityModel {
	private final Teams TEAM;
	private int health;
	private ArrayList<WeaponModel> weaponList;
	private WeaponModel currentWeapon;

	/**
	 * @param name     Constant name for the player during gameplay, will be used to identify different players
	 * @param position Position for this entityModel
	 * @param team     The ID for the team of this player
	 */
	public PlayerModel(String name, Vector2 position, Teams team, int health) {
		super(name, position, 50, 50);
		this.TEAM = team;
		this.health = health;
		this.weaponList = Weapons.getDefaultWeapons();
		this.currentWeapon = weaponList.get(0);
	}

	/**
	 * @return A new PlayerToken
	 */
	@Override
	protected EntityToken createToken() {
		return new PlayerToken(this);
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		currentWeapon.setPosition(getPosition());
		currentWeapon.update(dt);
	}
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		currentWeapon.draw(canvas);
	}

	/**
	 * @param weapon The weapon you want to add to the list of weapons for this player
	 */
	public void addToWeaponList(WeaponModel weapon) {
		if (!weaponList.contains(weapon)) {
			weaponList.add(weapon);
		}
	}
	/**
	 * @return player team
	 */
	public Teams getTeam() {
		return TEAM;
	}

	/**
	 * @return Current health of the player
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health Set health to this player
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return The weapon that is currently active for this player
	 */
	public WeaponModel getCurrentWeapon() {
		return currentWeapon;
	}

	/**
	 * Sets the current weapon for a player, the weaponList should be used to find the available weapons
	 *
	 * @param weaponListPosition The position of the weapon the switch to in the WeaponList.
	 */
	public void setCurrentWeapon(int weaponListPosition) {
		currentWeapon = weaponList.get(weaponListPosition);
	}

	/**
	 * @return Returns the list of this players available weapons
	 */
	public ArrayList<WeaponModel> getWeaponList() {
		return this.weaponList;
	}

}
