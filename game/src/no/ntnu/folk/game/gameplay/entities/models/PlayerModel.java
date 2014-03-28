package no.ntnu.folk.game.gameplay.entities.models;

import android.graphics.Canvas;
import no.ntnu.folk.game.constants.GameplayConstants;
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
	private Teams TEAM;
	private final int startHealth;
	private int health;
	private ArrayList<WeaponModel> weaponList;
	private WeaponModel currentWeapon;

	private boolean cold;
	private boolean firedWeapon;

	/**
	 * @param name     Constant name for the player during gameplay, will be used to identify different players
	 * @param position Position for this entityModel
	 * @param team     The ID for the team of this player
	 */
	public PlayerModel(String name, Vector2 position, Teams team, int health) {
		super(name, position, team.getImage());
		this.TEAM = team;
		this.startHealth = health;
		this.health = health;
		this.weaponList = Weapons.getDefaultWeapons();
		this.currentWeapon = weaponList.get(0);
		setAim(position.getX() + 100, position.getY() + 100); // FIXME temporary values for start position
		addGroup(team.ordinal());
		addMask(team.ordinal());
		setAcceleration(0, GameplayConstants.ACCELERATION);
		cold = true;
		firedWeapon = false;
	}

	/**
	 * @return A new PlayerToken
	 */
	@Override
	protected EntityToken createToken(int image) {
		return new PlayerToken(this, image);
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
	 * @return player team
	 */
	public Teams getTeam() {
		return TEAM;
	}

	/**
	 * @return Health at the start of the game
	 */
	public int getStartHealth() {
		return startHealth;
	}

	/**
	 * @return Current health of the player
	 */
	public int getHealth() {
		return health;
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

	/**
	 * Sets the aim for the player's weapon.
	 *
	 * @param x coordinate of touch input
	 * @param y coordinate of touch input
	 */
	public void setAim(float x, float y) {
		currentWeapon.setAim(x, y);
	}

	/**
	 * @return Vector for the direction of the aim.
	 */
	public Vector2 getAim() {
		return currentWeapon.getAim();
	}

	/**
	 * @param damage The amount subtracted from the player's health.
	 */
	public void attacked(int damage) {
		health -= Math.abs(damage);
	}

	public boolean isCold() {
		return cold;
	}
	public void setCold(boolean cold) {
		this.cold = cold;
	}

	public boolean isFiredWeapon() {
		return firedWeapon;
	}

	public void setFiredWeapon(boolean firedWeapon) {
		this.firedWeapon = firedWeapon;
	}
}
