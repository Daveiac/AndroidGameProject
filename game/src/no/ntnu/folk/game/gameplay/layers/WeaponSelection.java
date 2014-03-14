package no.ntnu.folk.game.gameplay.layers;

import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.gameplay.Button;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.WeaponModel;
import no.ntnu.folk.game.gameplay.models.GameModel;
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

	public WeaponSelection(GameModel model) {
		this.model = model;
		weaponList = new ArrayList<WeaponModel>();
		makeWeaponButtons();
	}

	private void makeWeaponButtons() {
		float weaponListX = ProgramConstants.getWindowSize()[0] * 0.92f;
		float weaponListY = ProgramConstants.getWindowSize()[1] * 0.75f;
		weaponButtons = new ArrayList<Button>();
		for (WeaponModel wm : model.getCurrentPlayer().getWeaponList()) {
			int offset = model.getCurrentPlayer().getWeaponList().indexOf(wm) * 50;
			Button button = new Button(wm.getWeapon().getImage(), wm.getWeapon().getImage(), new Vector2(weaponListX, weaponListY - offset), false);
			weaponButtons.add(button);
			button.setEnabled(false);
		}
	}

	public void setActive(boolean active) {
		for (Button wb : weaponButtons) {
			wb.setEnabled(active);
		}
	}

	public ArrayList<Button> getWeaponButtons() {
		PlayerModel currentPlayer = model.getCurrentPlayer();
		if (!currentPlayer.getWeaponList().equals(weaponList)) {
			weaponList = currentPlayer.getWeaponList();
			makeWeaponButtons();
		}
		return weaponButtons;
	}

	public void setWeapon(Button button) {
		int index = weaponButtons.indexOf(button);
		model.getCurrentPlayer().setCurrentWeapon(index);
		setActive(false);
	}
}
