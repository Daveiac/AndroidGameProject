package no.ntnu.folk.game.gameplay.layers;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.Button;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.models.GameModel;
import no.ntnu.folk.game.menus.menuStates.PauseMenu;
import sheep.game.Game;
import sheep.game.Layer;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

public class KeyPadLayer extends Layer {
	private Button[] buttons;
	private Button leftKey;
	private Button rightKey;
	private Button pauseButton;
	private Button swapKey;
	private Button fireKey;

	private Image aimImage;
	private GameModel gameModel;


	public KeyPadLayer(GameModel gameModel) {
		this.gameModel = gameModel;
		float leftKeyX = Constants.getWindowSize()[0] * 0.25f;
		float rightKeyX = Constants.getWindowSize()[0] * 0.75f;
		float swapKeyX = Constants.getWindowSize()[0]*0.6f;
		float fireKeyX = Constants.getWindowSize()[0]*0.4f;
		float keyY = Constants.getWindowSize()[1] * 0.8f;
		aimImage = new Image(R.drawable.aim);
		buttons = new Button[]{
				leftKey = new Button(R.drawable.keypadleft, R.drawable.keypadleft, leftKeyX, keyY),
				rightKey = new Button(R.drawable.keypadright, R.drawable.keypadright, rightKeyX, keyY),
				pauseButton = new Button(R.drawable.icon, R.drawable.icon, 32, 32), // TODO add proper pause button
				swapKey = new Button(R.drawable.swapkey, R.drawable.swapkey, swapKeyX,keyY),
				fireKey = new Button(R.drawable.firekey, R.drawable.firekey, fireKeyX, keyY),
		};
	}

	@Override
	public void update(float dt) {
		PlayerModel currentPlayer = gameModel.getCurrentPlayer();
		if (leftKey.isPressed()) {
			currentPlayer.setSpeed(-Constants.PLAYER_SPEED, currentPlayer.getSpeed().getY());
		}
		if (rightKey.isPressed()) {
			currentPlayer.setSpeed(Constants.PLAYER_SPEED, 0);
		}
		if (!leftKey.isPressed() && !rightKey.isPressed()) {
			currentPlayer.setSpeed(0, currentPlayer.getSpeed().getY());
		}
		if(fireKey.isPressed()){
			System.out.println("fire button works");
			gameModel.fireWeapon();
		}
	}

	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		for (Button button : buttons) {
			button.draw(canvas);
		}
		aimImage.draw(canvas, gameModel.getCurrentPlayer().getAimPositionX(),
				gameModel.getCurrentPlayer().getAimPositionY());
	}
/**
 * If any of buttons are pressed like pauseButton, fire , move or swap weapons, 
 * this methods will take care of whats gonna happen
 * @param event
 * @return true value which does nothing
 */
	public boolean onTouchDown(MotionEvent event) {
		if (pauseButton.contanis(event.getX(), event.getY())) {
			Game.getInstance().pushState(new PauseMenu());
		}
		for (Button button : buttons) {
			if (button.contanis(event.getX(), event.getY())) {
				button.setPressed(true);
			}
		}
		return true;
	}

	public boolean onTouchMove(MotionEvent event) {
		for (Button button : buttons) {
			if (button.contanis(event.getX(), event.getY())) {
				button.setPressed(true);
			} else {
				button.setPressed(false);
			}
		}
		return true;
	}
	public boolean onTouchUp(MotionEvent event) {
		for (Button button : buttons) {
			if (button.contanis(event.getX(), event.getY())) {
				button.setPressed(false);
			}
		}
		return true;
	}

}
