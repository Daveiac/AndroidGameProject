package no.ntnu.folk.game.gameplay.layers;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import no.ntnu.folk.game.Program;
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

public class KeyPadLayer extends Layer implements View.OnTouchListener {
	private Button[] buttons;
	private Button leftKey;
	private Button rightKey;
	private Button upKey;
	private Button pauseButton;
	private Button swapKey;
	private Button fireKey;
	private Button endKey;
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
		Program.getView().setOnTouchListener(this);
	}
	private void createButtons(int[] windowSize) {
		Vector2 leftKeyPos = new Vector2(windowSize[0] * 0.08f, windowSize[1] * 0.90f);
		Vector2 rightKeyPos = new Vector2(windowSize[0] * 0.32f, windowSize[1] * 0.90f);
		Vector2 upKeyPos = new Vector2(windowSize[0] * 0.2f, windowSize[1] * 0.87f);
		Vector2 pauseKeyPos = new Vector2(32, 32); // TODO should not use hardcoded coordinates
		Vector2 swapKeyPos = new Vector2(windowSize[0] * 0.92f, windowSize[1] * 0.90f);
		Vector2 fireKeyPos = new Vector2(windowSize[0] * 0.76f, windowSize[1] * 0.90f);
		Vector2 endKeyPos = new Vector2(windowSize[0] * 0.60f, windowSize[1] * 0.90f);
		Vector2 unpauseKeyPos = new Vector2(windowSize[0] * 0.50f, windowSize[1] * 0.50f);
		buttons = new Button[]{
				leftKey = new Button(keypadleft, keypadleft, leftKeyPos, true),
				rightKey = new Button(keypadright, keypadright, rightKeyPos, true),
				upKey = new Button(keypadup, keypadup, upKeyPos, false),
				pauseButton = new Button(icon, icon, pauseKeyPos, false), // TODO add proper pause button
				swapKey = new Button(swapkey, swapkey, swapKeyPos, false),
				fireKey = new Button(firekey, firekey, fireKeyPos, false),
				endKey = new Button(endturn, endturn, endKeyPos, false),
		};
		unpauseButton = new Button(unpause, unpause, unpauseKeyPos, false);
		unpauseButton.disable();
	}

	@Override
	public void update(float dt) {
		PlayerModel currentPlayer = gameModel.getCurrentPlayer();
		Program.getView().setOnTouchListener(this); // FIXME
		// Why I did this: When a state is popped, the OnTouchListener needs to be updated.
		// Unfortunately I could not find a better way to to do it

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
				int[] windowSize = ProgramConstants.getWindowSize();
				currentPlayer.setAim(point.x + currentPlayer.getX() - windowSize[0] / 2, point.y + currentPlayer.getY() - windowSize[1] / 2);
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
		if (endKey.popPressed()) {
			gameModel.setGameTime(0);
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
		float aimX = currentPlayer.getAim().getX() + ProgramConstants.getWindowSize()[0] / 2;
		float aimY = currentPlayer.getAim().getY() + ProgramConstants.getWindowSize()[1] / 2;
		aimImage.draw(canvas, aimX - aimImage.getWidth() / 2, aimY - aimImage.getHeight() / 2);
	}
	private void drawButtons(Canvas canvas) {
		for (Button button : buttons) {
			button.draw(canvas);
		}
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		int pointerIndex = event.getActionIndex();
		int pointerId = event.getPointerId(pointerIndex);
		int maskedAction = event.getActionMasked();
		switch (maskedAction) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				onTouchDown(event);
				break;
			case MotionEvent.ACTION_MOVE:
				int pointerCount = event.getPointerCount();
				for (int i = 0; i < pointerCount; i++) {
					PointF point = activePointers.get(event.getPointerId(i));
					if (point != null) {
						point.set(event.getX(i), event.getY(i));
					}
				}
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
			case MotionEvent.ACTION_CANCEL:
				for (Button button : buttons) {
					if (button.contains(activePointers.get(pointerId).x, activePointers.get(pointerId).y)) {
						button.release();
					}
				}
				activePointers.remove(pointerId);
				break;
			default:
				break;
		}
		return false;
	}

	private boolean onTouchDown(MotionEvent event) {
		int pointerIndex = event.getActionIndex();
		int pointerId = event.getPointerId(pointerIndex);

		PointF point = new PointF(event.getX(pointerIndex), event.getY(pointerIndex));
		activePointers.append(pointerId, point);

		if (pauseButton.contains(point.x, point.y)) {
			gameModel.setPaused(true);
			unpauseButton.enable();
			Game.getInstance().pushState(new PauseMenu());
		}
		for (Button button : buttons) {
			if (button.contains(point.x, point.y)) {
				button.touch();
			}
		}
		for (Button button : weaponButtons) {
			if (button.contains(point.x, point.y)) {
				weaponSelection.setWeapon(button);
				break;
			}
		}
		if (gameModel.isPaused() && unpauseButton.contains(point.x, point.y)) {
			unpauseButton.touch();
		}
		return true;
	}

}
