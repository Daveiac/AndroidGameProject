package no.ntnu.folk.game.models;

import java.util.ArrayList;

import no.ntnu.folk.game.Constants;

public class PlayerModel {

	private final int TEAM_ID;
	private final String NAME;
	private int health;
	private String currentWeapon;
	private ArrayList<String> weaponList;

	public PlayerModel(String name, int teamID) {
		this.NAME = name;
		this.currentWeapon = weaponList.get(0);
		this.health = 100;
		this.TEAM_ID = teamID;
		weaponList = Constants.getDefaultWeapons();
	}

	public void addToWeaponList(String weapon) {
		if (!weaponList.contains(weapon))
			weaponList.add(weapon);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return NAME;
	}


	public String getCurrWeapon() {
		return currentWeapon;
	}

	public void setCurrentWeapon(int weaponListPosition) {
		currentWeapon = weaponList.get(weaponListPosition);
	}
	
	public ArrayList<String> getWeaponList(){
		return this.weaponList;
	}
}
