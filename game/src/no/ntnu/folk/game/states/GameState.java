package no.ntnu.folk.game.states;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.gameplay.layers.GameLayer;
import no.ntnu.folk.game.gameplay.layers.KeyPadLayer;
import no.ntnu.folk.game.gameplay.models.GameModel;
import sheep.game.Game;
import sheep.game.State;
import sheep.game.World;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;

public class GameState extends State {
	private World gameWorld;

	/**
	 * Create a new game.
	 *
	 * @param model
	 */
	public GameState(GameModel model, Game game) {
		gameWorld = new World();
		gameWorld.addLayer(new GameLayer(model));
		gameWorld.addLayer(new KeyPadLayer(model, game));
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
