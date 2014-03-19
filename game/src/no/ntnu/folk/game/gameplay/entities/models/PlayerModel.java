package no.ntnu.folk.game.gameplay.entities.models;

import android.graphics.Canvas;
import no.ntnu.folk.game.R;
import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.gameplay.entities.data.Teams;
import no.ntnu.folk.game.gameplay.entities.data.Weapons;
import no.ntnu.folk.game.gameplay.entities.views.EntityToken;
import no.ntnu.folk.game.gameplay.entities.views.PlayerToken;
import sheep.graphics.Image;
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
	private boolean isDead;
	private Image tombStone;

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
		isDead = false;
		tombStone = new Image(R.drawable.tombstone);
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
		if (!isDead) {
			super.update(dt);
			currentWeapon.setPosition(getPosition());
			currentWeapon.update(dt);
		}
	}
	@Override
	public void draw(Canvas canvas) {
		if (!isDead) {
			super.draw(canvas);
			currentWeapon.draw(canvas);
		} else {
			tombStone.draw(canvas, this.getX(), this.getY());
		}
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

	public void setAim(float x, float y) {
		currentWeapon.setAim(x - getX(), y - getY());
	}

	public Vector2 getAim() {
		return currentWeapon.getAim();
	}

	public void attacked(int damage) {
		health -= Math.abs(damage);
	}
	/**
	 * set people to tombstone
	 */
	public void setToDead() {
		isDead = true;
	}

	public boolean getStatusIsDead() {
		return isDead;
	}
}
