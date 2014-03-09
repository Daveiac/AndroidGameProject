package no.ntnu.folk.game.states;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.models.PlayerModel;
import no.ntnu.folk.game.models.enums.Teams;
import no.ntnu.folk.game.views.tokens.PlayerToken;
import no.ntnu.folk.game.views.tokens.ProjectileToken;
import sheep.collision.CollisionListener;
import sheep.game.Layer;
import sheep.game.Sprite;
import sheep.math.BoundingBox;
import sheep.math.Vector2;

import java.util.ArrayList;

public class GameLayer extends Layer implements CollisionListener {
	private ArrayList<PlayerToken> players = new ArrayList<PlayerToken>();
	private ArrayList<ProjectileToken> projectiles = new ArrayList<ProjectileToken>();

	public GameLayer() {
		this.players.add(new PlayerToken(new PlayerModel("Player 1", Teams.RED), new Vector2(100, 100)));
	}

	@Override
	public void update(float dt) {
		for (PlayerToken player : players) {
			player.update(dt);
		}
		for (ProjectileToken projectile : projectiles) {
			projectile.update(dt);
		}
	}
	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		for (PlayerToken player : players) {
			player.draw(canvas);
		}
		for (ProjectileToken projectile : projectiles) {
			projectile.draw(canvas);
		}
	}


	public void onTouchDown(MotionEvent event) {
		// TODO
	}
	public void onTouchMove(MotionEvent event) {
		// TODO
	}
	public void onTouchUp(MotionEvent event) {
		// TODO
	}


	@Override
	public void collided(Sprite a, Sprite b) {
		// TODO
	}

}
