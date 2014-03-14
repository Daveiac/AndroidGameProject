package no.ntnu.folk.game.gameplay.layers;

import android.graphics.Canvas;
import android.view.MotionEvent;
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
import sheep.math.Vector2;

import static no.ntnu.folk.game.R.drawable.*;

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
		aimImage = new Image(aim);
		createButtons(ProgramConstants.getWindowSize());
	}
	private void createButtons(int[] windowSize) {
		Vector2 leftKeyPos = new Vector2(windowSize[0] * 0.08f, windowSize[1] * 0.90f);
		Vector2 rightKeyPos = new Vector2(windowSize[0] * 0.32f, windowSize[1] * 0.90f);
		Vector2 upKeyPos = new Vector2(windowSize[0] * 0.2f, windowSize[1] * 0.87f);
		Vector2 pauseKeyPos = new Vector2(32, 32); // TODO should not use hardcoded coordinates
		Vector2 swapKeyPos = new Vector2(windowSize[0] * 0.92f, windowSize[1] * 0.90f);
		Vector2 fireKeyPos = new Vector2(windowSize[0] * 0.76f, windowSize[1] * 0.90f);
		Vector2 unpauseKeyPos = new Vector2(windowSize[0] * 0.50f, windowSize[1] * 0.50f);
		buttons = new Button[]{
				leftKey = new Button(keypadleft, keypadleft, leftKeyPos, true),
				rightKey = new Button(keypadright, keypadright, rightKeyPos, true),
				upKey = new Button(keypadup, keypadup, upKeyPos, false),
				pauseButton = new Button(icon, icon, pauseKeyPos, false), // TODO add proper pause button
				swapKey = new Button(swapkey, swapkey, swapKeyPos, false),
				fireKey = new Button(firekey, firekey, fireKeyPos, false),
		};
		unpauseButton = new Button(unpause, unpause, unpauseKeyPos, false);
	}

	@Override
	public void update(float dt) {
		PlayerModel currentPlayer = gameModel.getCurrentPlayer();
		if (leftKey.popPressed()) {
			gameModel.getCurrentPlayer().setSpeed(-GameplayConstants.PLAYER_SPEED, gameModel.getCurrentPlayer().getSpeed().getY());
		}
		if (rightKey.popPressed()) {
			gameModel.getCurrentPlayer().setSpeed(GameplayConstants.PLAYER_SPEED, gameModel.getCurrentPlayer().getSpeed().getY());
		}
		if (!leftKey.popPressed() && !rightKey.popPressed()) {
			currentPlayer.setSpeed(0, currentPlayer.getSpeed().getY());
		}
		if (fireKey.popPressed()) {
			gameModel.fireWeapon();
		}
		if (swapKey.popPressed()) {
			swapKeyIsPressed = !swapKeyIsPressed;
		}
		if (unpauseButton.popPressed()) {
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
				button.touch();
			}
		}
		if (gameModel.isPaused() && unpauseButton.contains(event.getX(), event.getY())) {
			buttonPressed = true;
			unpauseButton.touch();
		}
		if (!buttonPressed) {
			gameModel.getCurrentPlayer().setAim(event.getX(), event.getY());
		}
		return true;
	}

	public boolean onTouchMove(MotionEvent event) {
		for (Button button : buttons) {
			if (button.contains(event.getX(), event.getY())) {
				button.hold();
			} else {
				gameModel.getCurrentPlayer().setAim(event.getX(), event.getY());
			}
		}
		return true;
	}
	public boolean onTouchUp(MotionEvent event) {
		for (Button button : buttons) {
			if (button.contains(event.getX(), event.getY())) {
				button.release();
			}
		}
		return true;
	}

}
