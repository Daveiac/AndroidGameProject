package no.ntnu.folk.game.gameplay.layers;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.gameplay.entities.data.Teams;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import no.ntnu.folk.game.gameplay.models.GameModel;
import sheep.collision.CollisionListener;
import sheep.game.Layer;
import sheep.game.Sprite;
import sheep.math.BoundingBox;
import sheep.math.Vector2;

import java.util.ArrayList;

public class GameLayer extends Layer implements CollisionListener {

	private ArrayList<ProjectileModel> projectiles = new ArrayList<ProjectileModel>();

	private int currentPlayer = 0;
	private GameModel model;

	public GameLayer(GameModel model) {
		this.model = model;
		for (PlayerModel player : model.getPlayerList()) {
			player.addCollisionListener(this);
		}
	}

	@Override
	public void update(float dt) {
		for (PlayerModel player : model.getPlayerList()) {
			player.update(dt);
		}
		for (ProjectileModel projectile : projectiles) {
			projectile.update(dt);
		}
	}
	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		for (PlayerModel player : model.getPlayerList()) {
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

	/**
	 * Called when two Sprite collide.
	 *
	 * @param a The first Sprite (the sprite being listened to).
	 * @param b The other Sprite.
	 */
	@Override
	public void collided(Sprite a, Sprite b) {
		// TODO
	}

}
