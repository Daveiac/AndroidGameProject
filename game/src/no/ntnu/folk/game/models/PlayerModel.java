package no.ntnu.folk.game.models;

import java.util.ArrayList;

import no.ntnu.folk.game.Constants;
/**
 * Model for the player in the game. Use this as a model to PlayerTokens.
 * 
 * @author Rune
 *
 */
public class PlayerModel {

	private final int TEAM_ID;
	private final String NAME;
	private int health;
	private Object currentWeapon;
	private ArrayList<Object> weaponList;

	/**
	 * 
	 * @param name Constant name for the player during gameplay, will be used to identify different players
	 * @param teamID The ID for the team of this player
	 */
	public PlayerModel(String name, int teamID) {
		this.NAME = name;
		weaponList = Constants.getDefaultWeapons();
		this.currentWeapon = weaponList.get(0);
		this.health = 100;
		this.TEAM_ID = teamID;
	}

	/**
	 * TODO change Object to whatever
	 * @param weapon The weapon you want to add to the weaponlist for this player 
	 */
	public void addToWeaponList(Object weapon) {
		if (!weaponList.contains(weapon))
			weaponList.add(weapon);
	}

	/**
	 * 
	 * @return Current health of the player
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * 
	 * @param health Set health to this player
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * 
	 * @return Return the constant NAME for this player
	 */
	public String getName() {
		return NAME;
	}

	/**
	 * 
	 * @return The weapon that is currently active for this player
	 */
	public Object getCurrWeapon() {
		return currentWeapon;
	}

	/**
	 * Sets the current weapon for a player, the weaponList should be used to find the available weapons
	 * @param weaponListPosition The position of the weapon the switch to in the WeaponList.
	 */
	public void setCurrentWeapon(int weaponListPosition) {
		currentWeapon = weaponList.get(weaponListPosition);
	}
	
	/**
	 * 
	 * @return Returns the ist of this players available weapons
	 */
	public ArrayList<Object> getWeaponList(){
		return this.weaponList;
	}
}
