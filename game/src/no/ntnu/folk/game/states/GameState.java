package no.ntnu.folk.game.states;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.models.PlayerModel;
import no.ntnu.folk.game.models.enums.Teams;
import no.ntnu.folk.game.views.tokens.PlayerToken;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.game.State;

import static android.graphics.Color.BLACK;

public class GameState extends State implements CollisionListener {
	private PlayerToken player = new PlayerToken(new PlayerModel("Player 1", Teams.RED));

	// TODO

	@Override
	public void update(float dt) {
		super.update(dt);    // TODO
	}
	@Override
	public void draw(Canvas canvas) {
		if (canvas == null) return;
		canvas.drawColor(BLACK);

		// TODO
	}

	@Override
	public boolean onTouchDown(MotionEvent event) {
		return super.onTouchDown(event);    // TODO
	}
	@Override
	public boolean onTouchMove(MotionEvent event) {
		return super.onTouchMove(event);    // TODO
	}
	@Override
	public boolean onTouchUp(MotionEvent event) {
		return super.onTouchUp(event);    // TODO
	}

	@Override
	public void collided(Sprite a, Sprite b) {
		// TODO
	}

}
