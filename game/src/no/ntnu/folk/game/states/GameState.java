package no.ntnu.folk.game.states;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.gameplay.layers.GameLayer;
import no.ntnu.folk.game.gameplay.layers.KeyPadLayer;
import no.ntnu.folk.game.menus.menuStates.PauseMenu;
import sheep.game.State;
import sheep.game.World;

import static android.graphics.Color.BLACK;

public class GameState extends State {
	private World gameWorld;
	private GameLayer gameLayer;
	private KeyPadLayer keyLayer;

	/**
	 * Create a new game.
	 *
	 * @param playerCount Number of players
	 */
	public GameState(int playerCount) {
		gameWorld = new World();
		gameLayer = new GameLayer(playerCount);
		keyLayer = new KeyPadLayer();
		gameWorld.addLayer(gameLayer);
		gameWorld.addLayer(keyLayer);
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		gameWorld.update(dt);
	}
	@Override
	public void draw(Canvas canvas) {
		if (canvas == null) return;
		canvas.drawColor(BLACK);

		gameWorld.draw(canvas);
	}

	@Override
	public boolean onTouchDown(MotionEvent event) {
		gameLayer.onTouchDown(event);
		keyLayer.onTouchDown(event);
		return super.onTouchDown(event);
	}
	@Override
	public boolean onTouchMove(MotionEvent event) {
		if (event.getPointerCount() == 4) {
			getGame().pushState(new PauseMenu());
		} else {
			gameLayer.onTouchMove(event);
			keyLayer.onTouchMove(event);
		}
		return super.onTouchMove(event);
	}
	@Override
	public boolean onTouchUp(MotionEvent event) {
		gameLayer.onTouchUp(event);
		keyLayer.onTouchUp(event);
		return super.onTouchUp(event);
	}

}
