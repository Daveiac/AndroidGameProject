package no.ntnu.folk.game.states;

import android.graphics.Canvas;
import no.ntnu.folk.game.gameplay.layers.GameLayer;
import no.ntnu.folk.game.gameplay.layers.KeyPadLayer;
import no.ntnu.folk.game.gameplay.models.GameModel;
import sheep.game.State;
import sheep.game.World;

import static android.graphics.Color.BLUE;

public class GameState extends State {
	private final KeyPadLayer keyPadLayer;
	private World gameWorld;

	/**
	 * Create a new game.
	 *
	 * @param model
	 */
	public GameState(GameModel model) {
		gameWorld = new World();
		keyPadLayer = new KeyPadLayer(model);
		gameWorld.addLayer(new GameLayer(model));
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
		canvas.drawColor(BLUE);

		gameWorld.draw(canvas);
	}

}
