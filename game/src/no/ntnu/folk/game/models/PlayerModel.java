package no.ntnu.folk.game.models;

import java.util.ArrayList;

import no.ntnu.folk.game.Constants;

public class PlayerModel {

	private String name;
	private int health;
	private String currentWeapon;
	private ArrayList weaponList;

	public PlayerModel(String name, String currentWeapon) {
		this.name = name;
		this.currentWeapon = currentWeapon;
		this.health = 100;
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
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrWeapon() {
		return currentWeapon;
	}

	public void setCurrWeapon(String currentWeapon) {
		this.currentWeapon = currentWeapon;
	}

}
