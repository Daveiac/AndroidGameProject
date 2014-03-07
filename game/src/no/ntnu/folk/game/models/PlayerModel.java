package no.ntnu.folk.game.models;

import no.ntnu.folk.game.models.enums.Teams;
import no.ntnu.folk.game.models.enums.Weapons;

import java.util.ArrayList;

/**
 * Model for the player in the game. Use this as a model to PlayerTokens.
 *
 * @author Rune
 */
public class PlayerModel extends Model {
	private final Teams TEAM;
	private int health;
	private ArrayList<WeaponModel> weaponList;
	private WeaponModel currentWeapon;

	/**
	 * @param name Constant name for the player during gameplay, will be used to identify different players
	 * @param team The ID for the team of this player
	 */
	public PlayerModel(String name, Teams team) {
		super(name, 50, 50);
		this.TEAM = team;
		this.health = 100;
		this.weaponList = Weapons.getDefaultWeapons();
		this.currentWeapon = weaponList.get(0);
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
