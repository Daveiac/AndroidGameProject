package no.ntnu.folk.game.gameplay.layers;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.Button;
import no.ntnu.folk.game.gameplay.models.GameModel;
import sheep.game.Layer;
import sheep.input.TouchListener;
import sheep.math.BoundingBox;

public class KeyPadLayer extends Layer implements TouchListener {
	private Button[] buttons;
	private Button leftKey;
	private Button rightKey;

	private GameModel gameModel;

	public KeyPadLayer(GameModel gameModel) {
		this.gameModel = gameModel;
		float leftKeyX = ProgramConstants.getWindowSize()[0] * 0.25f;
		float rightKeyX = ProgramConstants.getWindowSize()[0] * 0.75f;
		float keyY = ProgramConstants.getWindowSize()[1] * 0.8f;
		buttons = new Button[]{
				leftKey = new Button(R.drawable.keypadleft, R.drawable.keypadleft, leftKeyX, keyY),
				rightKey = new Button(R.drawable.keypadright, R.drawable.keypadright, rightKeyX, keyY),
		};
	}

	@Override
	public void update(float dt) {
		if (leftKey.isPressed()) {
			gameModel.getCurrentPlayer().setSpeed(-GameplayConstants.PLAYER_SPEED, 0);
		}
		if (rightKey.isPressed()) {
			gameModel.getCurrentPlayer().setSpeed(GameplayConstants.PLAYER_SPEED, 0);
		}
	}

	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		for (Button button : buttons) {
			button.draw(canvas);
		}
	}

	@Override
	public boolean onTouchDown(MotionEvent event) {
		for (Button button : buttons) {
			if (button.contanis(event.getX(), event.getY())) {
				button.setPressed(true);
			}
		}
		return true;
	}
	@Override
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
	@Override
	public boolean onTouchUp(MotionEvent event) {
		for (Button button : buttons) {
			if (button.contanis(event.getX(), event.getY())) {
				button.setPressed(false);
			}
		}
		return true;
	}

}
