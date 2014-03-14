package no.ntnu.folk.game.gameplay.layers;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.gameplay.Button;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.WeaponModel;
import no.ntnu.folk.game.gameplay.models.GameModel;
import sheep.game.Layer;
import sheep.math.BoundingBox;
import sheep.math.Vector2;

import java.util.ArrayList;

/**
 * The layer that will be used to select weapons during gameplay. Just use the
 * draw and update method to get it drawn.
 *
 * @author Rune
 */
public class WeaponSelectLayer extends Layer {
	private GameModel model;
	private ArrayList<Button> weaponButtons;
	private ArrayList<WeaponModel> weaponList;
	private boolean active;

	public WeaponSelectLayer(GameModel model) {
		this.model = model;
		weaponList = new ArrayList<WeaponModel>();
		makeWeaponButtons();
		active = false;
	}

	@Override
	public void update(float dt) {
		PlayerModel currentPlayer = model.getCurrentPlayer();
		for (Button wb : weaponButtons) {
			if (wb.popPressed()) {
				currentPlayer.setCurrentWeapon(weaponButtons.indexOf(wb));
			}
		}
	}

	private void makeWeaponButtons() {
		float weaponListX = ProgramConstants.getWindowSize()[0] * 0.92f;
		float weaponListY = ProgramConstants.getWindowSize()[1] * 0.75f;
		weaponButtons = new ArrayList<Button>();
		for (WeaponModel wm : model.getCurrentPlayer().getWeaponList()) {
			int offset = model.getCurrentPlayer().getWeaponList().indexOf(wm) * 50;
			weaponButtons.add(new Button(wm.getWeapon().getImage(), wm.getWeapon().getImage(), new Vector2(weaponListX, weaponListY - offset), false));
		}
	}

	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		if (active) {
			for (Button button : weaponButtons) {
				button.draw(canvas);
			}
		}
	}

	public boolean onTouchDown(MotionEvent event) {
		for (Button button : weaponButtons) {
			if (button.contains(event.getX(), event.getY())) {
				button.touch();
			}
		}
		return true;
	}

	public boolean onTouchMove(MotionEvent event) {
		for (Button button : weaponButtons) {
			if (button.contains(event.getX(), event.getY())) {
				button.hold();
			}
		}
		return true;
	}

	public boolean onTouchUp(MotionEvent event) {
		for (Button button : weaponButtons) {
			if (button.contains(event.getX(), event.getY())) {
				button.release();
			}
		}
		return true;
	}

	public void setActive(boolean active) {
		this.active = active;
		PlayerModel currentPlayer = model.getCurrentPlayer();
		if (!currentPlayer.getWeaponList().equals(weaponList)) {
			weaponList = currentPlayer.getWeaponList();
			makeWeaponButtons();
		}
		for (Button wb : weaponButtons) {
			wb.setEnabled(active);
		}
	}
}
