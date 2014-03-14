package no.ntnu.folk.game.gameplay.layers;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.R;
import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.constants.ProgramConstants;
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
	private Button upKey;
	private Button pauseButton;
	private Button swapKey;
	private Button fireKey;
	private Button unpauseButton;

	private Image aimImage;
	private GameModel gameModel;
	private WeaponSelectLayer weaponSelectLayer;
	private boolean swapKeyIsPressed;


	public KeyPadLayer(GameModel gameModel) {
		weaponSelectLayer = new WeaponSelectLayer(gameModel);
		this.gameModel = gameModel;
		float leftKeyX = ProgramConstants.getWindowSize()[0] * 0.08f;
		float rightKeyX = ProgramConstants.getWindowSize()[0] * 0.32f;
		float upKeyX = ProgramConstants.getWindowSize()[0] * 0.2f;
		float swapKeyX = ProgramConstants.getWindowSize()[0] * 0.92f;
		float fireKeyX = ProgramConstants.getWindowSize()[0] * 0.76f;
		float keyY = ProgramConstants.getWindowSize()[1] * 0.90f;
		float upKeyY = ProgramConstants.getWindowSize()[1] * 0.87f;
		float unPauseX = ProgramConstants.getWindowSize()[0] * 0.50f;
		float unPauseY = ProgramConstants.getWindowSize()[1] * 0.50f;
		aimImage = new Image(R.drawable.aim);
		buttons = new Button[]{
				leftKey = new Button(R.drawable.keypadleft, R.drawable.keypadleft, leftKeyX, keyY),
				rightKey = new Button(R.drawable.keypadright, R.drawable.keypadright, rightKeyX, keyY),
				upKey = new Button(R.drawable.keypadup, R.drawable.keypadup, upKeyX, upKeyY),
				pauseButton = new Button(R.drawable.icon, R.drawable.icon, 32, 32), // TODO add proper pause button
				swapKey = new Button(R.drawable.swapkey, R.drawable.swapkey, swapKeyX, keyY),
				fireKey = new Button(R.drawable.firekey, R.drawable.firekey, fireKeyX, keyY),
		};
		unpauseButton = new Button(R.drawable.unpause, R.drawable.unpause, unPauseX, unPauseY);
	}

	@Override
	public void update(float dt) {
		PlayerModel currentPlayer = gameModel.getCurrentPlayer();
		if (leftKey.isPressed()) {
			gameModel.getCurrentPlayer().setSpeed(-GameplayConstants.PLAYER_SPEED, gameModel.getCurrentPlayer().getSpeed().getY());
		}
		if (rightKey.isPressed()) {
			gameModel.getCurrentPlayer().setSpeed(GameplayConstants.PLAYER_SPEED, gameModel.getCurrentPlayer().getSpeed().getY());
		}
		if (!leftKey.isPressed() && !rightKey.isPressed()) {
			currentPlayer.setSpeed(0, currentPlayer.getSpeed().getY());
		}
		if (fireKey.isPressed()) {
			gameModel.fireWeapon();
		}
		if (swapKey.isPressed()) {
			swapKeyIsPressed = !swapKeyIsPressed;
		}
		if (unpauseButton.isPressed()) {
			gameModel.pauseGame();
		}
	}

	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		drawButtons(canvas);
		drawAim(canvas);
		if (swapKeyIsPressed) {
			weaponSelectLayer.draw(canvas, box);
		}
		if (gameModel.isPaused()) {
			unpauseButton.draw(canvas);
		}
	}
	private void drawAim(Canvas canvas) {
		PlayerModel currentPlayer = gameModel.getCurrentPlayer();
		float aimX = currentPlayer.getAim().getX() + currentPlayer.getX();
		float aimY = currentPlayer.getAim().getY() + currentPlayer.getY();
		aimImage.draw(canvas, aimX, aimY);
	}
	private void drawButtons(Canvas canvas) {
		for (Button button : buttons) {
			button.draw(canvas);
		}
	}

	/**
	 * If any of buttons are pressed like pauseButton, fire , move or swap weapons,
	 * this methods will take care of whats gonna happen
	 *
	 * @param event
	 * @return true value which does nothing
	 */
	public boolean onTouchDown(MotionEvent event) {
		boolean buttonPressed = false;
		if (pauseButton.contains(event.getX(), event.getY())) {
			gameModel.pauseGame();
			Game.getInstance().pushState(new PauseMenu());
		}
		for (Button button : buttons) {
			if (button.contains(event.getX(), event.getY())) {
				buttonPressed = true;
				button.setPressed(true);
			}
		}
		if (unpauseButton.contains(event.getX(), event.getY())) {
			unpauseButton.setPressed(true);
		}
		if (!buttonPressed) {
			gameModel.getCurrentPlayer().setAim(event.getX(), event.getY());
		}
		return true;
	}

	public boolean onTouchMove(MotionEvent event) {
		for (Button button : buttons) {
			if (button.contains(event.getX(), event.getY())) {
				button.setPressed(true);
			} else {
				button.setPressed(false);
				gameModel.getCurrentPlayer().setAim(event.getX(), event.getY());
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
		unpauseButton.setPressed(false);
		return true;
	}

}
