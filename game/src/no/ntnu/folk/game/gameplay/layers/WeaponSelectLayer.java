package no.ntnu.folk.game.gameplay.layers;

import java.util.ArrayList;

import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.Button;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.WeaponModel;
import no.ntnu.folk.game.gameplay.models.GameModel;
import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.Layer;
import sheep.math.BoundingBox;

/**
 * The layer that will be used to select weapons during gameplay. Just use the
 * draw and update method to get it drawn.
 * 
 * @author Rune
 * 
 */
public class WeaponSelectLayer extends Layer {
	private GameModel model;
	private ArrayList<Button> weaponButtons;
	private PlayerModel currentPlayer;

	public WeaponSelectLayer(GameModel model) {
		this.model = model;
	}

	@Override
	public void update(float dt) {
		if (currentPlayer.getWeaponList() != model.getCurrentPlayer().getWeaponList()) {
			makeWeaponButtons();
		}
		for(Button wb: weaponButtons){
			if(wb.isPressed()){
				model.getCurrentPlayer().setCurrentWeapon(weaponButtons.indexOf(wb));
			}
		}
	}
	
	private void makeWeaponButtons(){
		weaponButtons = new ArrayList<Button>();
		for (WeaponModel wm : model.getCurrentPlayer().getWeaponList()) {
			int offset = model.getCurrentPlayer().getWeaponList().indexOf(wm) * 50;
			weaponButtons.add(new Button(R.drawable.handgun,
					R.drawable.handgun, offset, 50)); // FIXME This is probably not smart, and the buttons will be added totaly wrong
		}
	}

	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		for (Button button : weaponButtons)
			button.draw(canvas);
	}

	public boolean onTouchDown(MotionEvent event) {
		for (Button button : weaponButtons) {
			if (button.contains(event.getX(), event.getY())) {
				button.setPressed(true);
			}
		}
		return true;
	}

	public boolean onTouchMove(MotionEvent event) {
		for (Button button : weaponButtons) {
			if (button.contains(event.getX(), event.getY())) {
				button.setPressed(true);
			} else {
				button.setPressed(false);
			}
		}
		return true;
	}

	public boolean onTouchUp(MotionEvent event) {
		for (Button button : weaponButtons) {
			if (button.contains(event.getX(), event.getY())) {
				button.setPressed(false);
			}
		}
		return true;
	}

}
