package no.ntnu.folk.game.gameplay.layers;

import static no.ntnu.folk.game.R.drawable.aim;
import static no.ntnu.folk.game.R.drawable.endturn;
import static no.ntnu.folk.game.R.drawable.keypadleft;
import static no.ntnu.folk.game.R.drawable.keypadright;
import static no.ntnu.folk.game.R.drawable.keypadup;
import static no.ntnu.folk.game.R.drawable.shootbutton;
import static no.ntnu.folk.game.R.drawable.swapbutton;
import static no.ntnu.folk.game.R.drawable.pausegame;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import no.ntnu.folk.game.Program;
import no.ntnu.folk.game.constants.Direction;
import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.models.GameModel;
import no.ntnu.folk.game.menus.menuStates.PauseMenu;
import no.ntnu.folk.game.states.GameState;
import sheep.game.Game;
import sheep.game.Layer;
import sheep.graphics.Font;
import sheep.graphics.Image;
import sheep.math.BoundingBox;
import sheep.math.Vector2;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

/**
 * The key pad layer of the game. It contains all the user interactions and its actions as well as drawing the user interface.
 *
 */
public class KeyPadLayer extends Layer implements View.OnTouchListener {
	private Button[] buttons;
	private Button leftKey;
	private Button rightKey;
	private Button upKey;
	private Button swapKey;
	private Button fireKey;
	private Button endKey;
	private Button pauseKey;

	private Image aimImage;
	private GameState gameState;
	private GameModel gameModel;

	private WeaponSelection weaponSelection;
	private ArrayList<Button> weaponButtons;

	private float buttonOverlayHeight;

	private ConcurrentHashMap<Integer, PointF> activePointers;

	/**
	 * Constructor of the given game state and game model.
	 * @param gameState The game state
	 * @param gameModel The game model
	 */
	public KeyPadLayer(GameState gameState, GameModel gameModel) {
		this.gameState = gameState;
		this.gameModel = gameModel;
		weaponSelection = new WeaponSelection(gameModel);
		aimImage = new Image(aim);
		createButtons(ProgramConstants.getWindowSize());
		weaponButtons = weaponSelection.getWeaponButtons();
		activePointers = new ConcurrentHashMap<Integer, PointF>();
		Program.getView().setOnTouchListener(this);
	}

	/**
	 * Creates the buttons used for user interactions.
	 * @param windowSize The size of the window
	 */
	private void createButtons(int[] windowSize) {
		Vector2 leftKeyPos = new Vector2(windowSize[0] * 0.08f, windowSize[1] * 0.90f);
		Vector2 rightKeyPos = new Vector2(windowSize[0] * 0.32f, windowSize[1] * 0.90f);
		Vector2 upKeyPos = new Vector2(windowSize[0] * 0.2f, windowSize[1] * 0.87f);
		Vector2 pauseKeyPos = new Vector2(windowSize[0] * 0.50f, windowSize[1] * 0.90f);
		Vector2 swapKeyPos = new Vector2(windowSize[0] * 0.92f, windowSize[1] * 0.90f);
		Vector2 fireKeyPos = new Vector2(windowSize[0] * 0.76f, windowSize[1] * 0.90f);
		Vector2 endKeyPos = new Vector2(windowSize[0] * 0.60f, windowSize[1] * 0.90f);
		buttons = new Button[]{
				leftKey = new Button(keypadleft, leftKeyPos, true),
				rightKey = new Button(keypadright, rightKeyPos, true),
				upKey = new Button(keypadup, upKeyPos, false),
				swapKey = new Button(swapbutton, swapKeyPos, false),
				fireKey = new Button(shootbutton, fireKeyPos, false),
				endKey = new Button(endturn, endKeyPos, false),
				pauseKey = new Button(pausegame, pauseKeyPos, false),
		};
	}

	@Override
	public void update(float dt) {
		Program.getView().setOnTouchListener(this); // FIXME
		// Why I did this: When a state is popped, the OnTouchListener needs to be updated.
		// Unfortunately I could not find a better way to to do it

		updateButtons();
		useButtons();
	}

	/**
	 * Update weapons from the active pointers
	 */
	private void updateButtons() {
		PlayerModel currentPlayer = gameModel.getCurrentPlayer();
		for (PointF point : activePointers.values()) {
			boolean buttonPressed = false;
			if (point == null) continue;
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
				if (point.y < buttonOverlayHeight) {
					int[] windowSize = ProgramConstants.getWindowSize();
					currentPlayer.setAim(point.x - windowSize[0] / 2.0f, point.y - windowSize[1] / 2.0f);
				}
			}
		}
	}

	/**
	 * Use all pressed buttons
	 */
	private void useButtons() {
		PlayerModel currentPlayer = gameModel.getCurrentPlayer();
		// Direction keys
		boolean leftKeyPressed = leftKey.popPressed();
		boolean rightKeyPressed = rightKey.popPressed();
		if (leftKeyPressed && rightKeyPressed || !leftKeyPressed && !rightKeyPressed) { // If none, or both, keys are pressed
			currentPlayer.setSpeed(0, currentPlayer.getSpeed().getY());
		} else if (leftKeyPressed && !currentPlayer.getCollision().contains(Direction.LEFT)) {
			currentPlayer.setSpeed(-GameplayConstants.PLAYER_SPEED, currentPlayer.getSpeed().getY());
			currentPlayer.setAim(-Math.abs(currentPlayer.getAim().getX()), currentPlayer.getAim().getY());
		} else if (rightKeyPressed && !currentPlayer.getCollision().contains(Direction.RIGHT)) {
			currentPlayer.setSpeed(GameplayConstants.PLAYER_SPEED, currentPlayer.getSpeed().getY());
			currentPlayer.setAim(Math.abs(currentPlayer.getAim().getX()), currentPlayer.getAim().getY());
		}
		if (upKey.popPressed() && currentPlayer.getCollision().contains(Direction.DOWN)) {
			currentPlayer.setSpeed(currentPlayer.getSpeed().getX(), -GameplayConstants.JUMP_FORCE);
		}
		// Fire
		if (fireKey.popPressed()) {
			gameState.fireWeapon();
		}
		// Swap weapon
		if (swapKey.popPressed()) {
			if (weaponSelection.isActive()) {
				weaponSelection.setActive(false);
			} else {
				weaponButtons = weaponSelection.getWeaponButtons();
				weaponSelection.setActive(true);
			}
		}
		// End turn
		if (endKey.popPressed()) {
			if (!gameModel.getCurrentPlayer().isWeaponFired()) {
				weaponSelection.setActive(false);
				gameModel.nextPlayer();
			}
		}
		// Pause
		if (pauseKey.popPressed()) {
			Game.getInstance().pushState(new PauseMenu());
		}
	}

	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		drawOverlay(canvas);
		drawButtons(canvas);
		drawAim(canvas);
		drawWeaponSelect(canvas);
	}

	public void drawWeaponSelect(Canvas canvas) {
		for (Button button : weaponButtons) {
			button.draw(canvas);
			if (button.isEnabled()) {
				int ammo = gameModel.getCurrentPlayer().getWeaponList().get(weaponButtons.indexOf(button)).getAmmo();
				if(ammo == -1){
					canvas.drawText("-", button.getPosition().getX() - 30, button.getPosition().getY() + 25, Font.WHITE_SANS_BOLD_20);
				}
				else{
					canvas.drawText(""+ammo, button.getPosition().getX() - 30, button.getPosition().getY() + 25, Font.WHITE_SANS_BOLD_20);
				}
			}
		}
	}

	/**
	 * Draws the overlay
	 * @param canvas The canvas of the layer.
	 */
	private void drawOverlay(Canvas canvas) {
		int ws[] = ProgramConstants.getWindowSize();
		buttonOverlayHeight = ws[1] * 0.8f;
		float buttonOverlayLeft = 0;
		float buttonOverlayBot = ws[1];
		float buttonOverlayRight = ws[0];
		Paint p = new Paint();
		p.setColor(Color.BLACK);
		canvas.drawRect(buttonOverlayLeft, buttonOverlayHeight, buttonOverlayRight, buttonOverlayBot, p);
	}

	/**
	 * Draws the crosshair.
	 * @param canvas The canvas of the layer.
	 */
	private void drawAim(Canvas canvas) {
		PlayerModel currentPlayer = gameModel.getCurrentPlayer();
		float aimX = currentPlayer.getAim().getX() + ProgramConstants.getWindowSize()[0] / 2;
		float aimY = currentPlayer.getAim().getY() + ProgramConstants.getWindowSize()[1] / 2;
		if (aimY > buttonOverlayHeight) {
			aimY = buttonOverlayHeight;
		}
		aimImage.draw(canvas, aimX - aimImage.getWidth() / 2, aimY - aimImage.getHeight() / 2);
	}

	/**
	 * Draws the buttons used for user interactions.
	 * @param canvas The canvas of the layer.
	 */
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

	/**
	 * Called when a on touch down event is dispatched to a view. This allows listeners to get a chance to respond before the target view.
	 * @param event The MotionEvent object containing full information about the event.
	 * @return True if the listener has consumed the event, false otherwise.
	 */
	private boolean onTouchDown(MotionEvent event) {
		int pointerIndex = event.getActionIndex();
		int pointerId = event.getPointerId(pointerIndex);

		PointF point = new PointF(event.getX(pointerIndex), event.getY(pointerIndex));
		activePointers.put(pointerId, point);

		for (Button button : buttons) {
			if (button.contains(point.x, point.y)) {
				button.touch();
			}
		}
		for (Button button : weaponButtons) {
			if (button.contains(point.x, point.y) && button.isEnabled()) {
				weaponSelection.setWeapon(button);
				break;
			}
		}
		return true;
	}

}
