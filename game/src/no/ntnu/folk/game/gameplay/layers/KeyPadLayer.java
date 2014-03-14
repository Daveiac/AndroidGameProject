package no.ntnu.folk.game.gameplay.layers;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.SparseArray;
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

import java.util.ArrayList;

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

	private WeaponSelection weaponSelection;
	private ArrayList<Button> weaponButtons;

	private SparseArray<PointF> activePointers;

	public KeyPadLayer(GameModel gameModel) {
		this.gameModel = gameModel;
		weaponSelection = new WeaponSelection(gameModel);
		aimImage = new Image(aim);
		createButtons(ProgramConstants.getWindowSize());
		weaponButtons = weaponSelection.getWeaponButtons();
		activePointers = new SparseArray<PointF>();
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
		unpauseButton.disable();
	}

	@Override
	public void update(float dt) {
		PlayerModel currentPlayer = gameModel.getCurrentPlayer();

		int pointerCount = activePointers.size();
		for (int i = 0; i < pointerCount; i++) {
			boolean buttonPressed = false;
			PointF point = activePointers.valueAt(i);
			for (Button button : buttons) {
				if (button.contains(point.x, point.y)) {
					button.hold();
					buttonPressed = true;
				}
			}
			for (Button button : weaponButtons) {
				if (button.contains(point.x, point.y)) {
					buttonPressed = true;
				}
			}
			if (!buttonPressed) {
				gameModel.getCurrentPlayer().setAim(point.x, point.y);
			}
		}

		boolean leftKeyPressed;
		boolean rightKeyPressed;
		if (leftKeyPressed = leftKey.popPressed()) {
			currentPlayer.setSpeed(-GameplayConstants.PLAYER_SPEED, currentPlayer.getSpeed().getY());
		}
		if (rightKeyPressed = rightKey.popPressed()) {
			currentPlayer.setSpeed(GameplayConstants.PLAYER_SPEED, currentPlayer.getSpeed().getY());
		}
		if (!leftKeyPressed && !rightKeyPressed || leftKeyPressed && rightKeyPressed) { // If none, or both, keys are pressed
			currentPlayer.setSpeed(0, currentPlayer.getSpeed().getY());
		}
		if (fireKey.popPressed()) {
			gameModel.fireWeapon();
		}
		if (swapKey.popPressed()) {
			weaponButtons = weaponSelection.getWeaponButtons();
			weaponSelection.setActive(true);
		}
		if (unpauseButton.popPressed()) {
			gameModel.setPaused(false);
			unpauseButton.disable();
		}
	}

	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		drawButtons(canvas);
		drawAim(canvas);
		for (Button button : weaponButtons) {
			button.draw(canvas);
		}
		unpauseButton.draw(canvas);
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

	public boolean onTouchDown(MotionEvent event) {
		int pointerIndex = event.getActionIndex();
		int pointerId = event.getPointerId(pointerIndex);

		PointF point = new PointF(event.getX(pointerIndex), event.getY(pointerIndex));
		activePointers.append(pointerId, point);

		boolean buttonPressed = false;
		if (pauseButton.contains(point.x, point.y)) {
			gameModel.setPaused(true);
			unpauseButton.enable();
			Game.getInstance().pushState(new PauseMenu());
		}
		for (Button button : buttons) {
			if (button.contains(point.x, point.y)) {
				buttonPressed = true;
				button.touch();
			}
		}
		for (Button button : weaponButtons) {
			if (button.contains(point.x, point.y)) {
				buttonPressed = true;
				weaponSelection.setWeapon(button);
				break;
			}
		}
		if (gameModel.isPaused() && unpauseButton.contains(point.x, point.y)) {
			buttonPressed = true;
			unpauseButton.touch();
		}
		if (!buttonPressed) {
			gameModel.getCurrentPlayer().setAim(point.x, point.y);
		}
		return true;
	}

	public boolean onTouchMove(MotionEvent event) {
		int pointerCount = event.getPointerCount();
		for (int i = 0; i < pointerCount; i++) {
			PointF point = activePointers.get(event.getPointerId(i));
			if (point != null) {
				point.set(event.getX(i), event.getY(i));
			}
		}
		return true;
	}
	public boolean onTouchUp(MotionEvent event) {
		int pointerIndex = event.getActionIndex();
		int pointerId = event.getPointerId(pointerIndex);
		for (Button button : buttons) {
			if (button.contains(activePointers.get(pointerId).x, activePointers.get(pointerId).y)) {
				button.release();
			}
		}
		activePointers.remove(pointerId);
		return true;
	}

}
