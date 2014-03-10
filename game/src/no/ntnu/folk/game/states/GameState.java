package no.ntnu.folk.game.states;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.Button;
import no.ntnu.folk.game.gameplay.layers.GameLayer;
import no.ntnu.folk.game.gameplay.layers.KeyPadLayer;
import no.ntnu.folk.game.gameplay.models.GameModel;
import no.ntnu.folk.game.menus.menuStates.PauseMenu;
import sheep.game.State;
import sheep.game.World;

import static android.graphics.Color.BLACK;

public class GameState extends State {
	private World gameWorld;
	private KeyPadLayer keyPadLayer;

	/**
	 * Create a new game.
	 *
	 * @param model
	 */
	public GameState(GameModel model) {
		gameWorld = new World();
		gameWorld.addLayer(new GameLayer(model));
		keyPadLayer = new KeyPadLayer(model);
		gameWorld.addLayer(keyPadLayer);
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
		return keyPadLayer.onTouchDown(event);
	}
	@Override
	public boolean onTouchMove(MotionEvent event) {
		return keyPadLayer.onTouchMove(event);
	}
	@Override
	public boolean onTouchUp(MotionEvent event) {
		return keyPadLayer.onTouchUp(event);
	}
}
