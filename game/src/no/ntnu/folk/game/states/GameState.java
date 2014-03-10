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
	private GameLayer gameLayer;
	private KeyPadLayer keyLayer;
	private GameModel model;

	/**
	 * Create a new game.
	 *
	 * @param model
	 */
	public GameState(GameModel model) {
		this.model = model;
		gameWorld = new World();
		gameLayer = new GameLayer(model);
		keyLayer = new KeyPadLayer(model);
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

}
