package no.ntnu.folk.game.gameplay.layers;

import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.WeaponModel;
import no.ntnu.folk.game.gameplay.models.GameModel;
import sheep.graphics.Image;
import sheep.math.Vector2;

import java.util.ArrayList;

/**
 * The layer that will be used to select weapons during gameplay. Just use the
 * draw and update method to get it drawn.
 *
 * @author Rune
 */
public class WeaponSelection {
	private GameModel model;
	private ArrayList<Button> weaponButtons;
	private ArrayList<WeaponModel> weaponList;
	private boolean isActive;

	/**
	 * @param model Game model. Used to get the current player.
	 */
	public WeaponSelection(GameModel model) {
		this.model = model;
		weaponList = new ArrayList<WeaponModel>();
		makeWeaponButtons();
	}

	/**
	 * Make the buttons used for selecting weapons.
	 */
	private void makeWeaponButtons() {
		float weaponListX = ProgramConstants.getWindowSize()[0] * 0.95f;
		float weaponListY = ProgramConstants.getWindowSize()[1] * 0.75f;
		weaponButtons = new ArrayList<Button>();
		for (WeaponModel wm : model.getCurrentPlayer().getWeaponList()) {
			int image = wm.getWeapon().getSelectImage();
			float offset = model.getCurrentPlayer().getWeaponList().indexOf(wm) * weaponListY * 0.07f;
			Button button = new Button(image, new Vector2(weaponListX, weaponListY - offset), false);

			weaponButtons.add(button);
			button.setEnabled(false);
		}
	}

	/**
	 * Set whether or not the menu is active.
	 * Calls setEnabled for each button.
	 *
	 * @param active true to enable, false to disable
	 */
	public void setActive(boolean active) {
		this.isActive = active;
		for (Button wb : weaponButtons) {
			wb.setEnabled(active);
		}
	}

	/**
	 * @return The buttons used for selecting weapons
	 */
	public ArrayList<Button> getWeaponButtons() {
		PlayerModel currentPlayer = model.getCurrentPlayer();
		if (!currentPlayer.getWeaponList().equals(weaponList)) {
			weaponList = currentPlayer.getWeaponList();
			makeWeaponButtons();
		}
		return weaponButtons;
	}

	/**
	 * Select weapon for the current player.
	 *
	 * @param button The button that was pressed. Contains data about the selected weapon.
	 */
	public void setWeapon(Button button) {
		int index = weaponButtons.indexOf(button);
		model.getCurrentPlayer().setCurrentWeapon(index);
		setActive(false);
	}

	/**
	 * @return Returns true if the menu is active, else false.
	 */
	public boolean isActive() {
		return this.isActive;
	}

}
