package no.ntnu.folk.game.states;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.models.PlayerModel;
import no.ntnu.folk.game.models.ProjectileModel;
import no.ntnu.folk.game.models.enums.Teams;
import sheep.collision.CollisionListener;
import sheep.game.Layer;
import sheep.game.Sprite;
import sheep.math.BoundingBox;
import sheep.math.Vector2;

import java.util.ArrayList;

public class GameLayer extends Layer implements CollisionListener {
	private ArrayList<PlayerModel> players = new ArrayList<PlayerModel>();
	private ArrayList<ProjectileModel> projectiles = new ArrayList<ProjectileModel>();

	public GameLayer() {
		this.players.add(new PlayerModel("Player 1", new Vector2(100, 100), Teams.RED));
	}

	@Override
	public void update(float dt) {
		for (PlayerModel player : players) {
			player.update(dt);
		}
		for (ProjectileModel projectile : projectiles) {
			projectile.update(dt);
		}
	}
	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		for (PlayerModel player : players) {
			player.draw(canvas);
		}
		for (ProjectileModel projectile : projectiles) {
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
