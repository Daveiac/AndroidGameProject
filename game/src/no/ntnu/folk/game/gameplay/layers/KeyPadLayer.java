package no.ntnu.folk.game.gameplay.layers;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.constants.ProgramConstants;
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
		float leftKeyX = ProgramConstants.getWindowSize()[0] * 0.25f;
		float rightKeyX = ProgramConstants.getWindowSize()[0] * 0.75f;
		float swapKeyX = ProgramConstants.getWindowSize()[0]*0.6f;
		float fireKeyX = ProgramConstants.getWindowSize()[0]*0.4f;
		float keyY = ProgramConstants.getWindowSize()[1] * 0.8f;
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
			gameModel.getCurrentPlayer().setSpeed(-GameplayConstants.PLAYER_SPEED, 0); // TODO get y speed
		}
		if (rightKey.isPressed()) {
			gameModel.getCurrentPlayer().setSpeed(GameplayConstants.PLAYER_SPEED, 0); // TODO get y speed
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
		if (pauseButton.contains(event.getX(), event.getY())) {
			Game.getInstance().pushState(new PauseMenu());
		}
		for (Button button : buttons) {
			if (button.contains(event.getX(), event.getY())) {
				button.setPressed(true);
			}
		}
		return true;
	}

	public boolean onTouchMove(MotionEvent event) {
		for (Button button : buttons) {
			if (button.contains(event.getX(), event.getY())) {
				button.setPressed(true);
			} else {
				button.setPressed(false);
			}
		}
		return true;
	}
	public boolean onTouchUp(MotionEvent event) {
		for (Button button : buttons) {
			if (button.contains(event.getX(), event.getY())) {
				button.setPressed(false);
			}
		}
		return true;
	}

}
