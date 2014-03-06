package no.ntnu.folk.game.states;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.models.PlayerModel;
import no.ntnu.folk.game.models.enums.Teams;
import no.ntnu.folk.game.views.tokens.PlayerToken;
import sheep.collision.CollisionLayer;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.game.World;

import static android.graphics.Color.BLACK;

public class GameState extends State {
	private World gameWorld;
	private GameLayer gameLayer;

	public GameState() {
		gameWorld = new World();
		gameLayer = new GameLayer();
		gameWorld.addLayer(gameLayer);
	}

	@Override
	public void update(float dt) {
		super.update(dt);    // TODO
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
		return super.onTouchDown(event);
	}
	@Override
	public boolean onTouchMove(MotionEvent event) {
		gameLayer.onTouchMove(event);
		return super.onTouchMove(event);
	}
	@Override
	public boolean onTouchUp(MotionEvent event) {
		gameLayer.onTouchUp(event);
		return super.onTouchUp(event);
	}

}
