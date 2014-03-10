package no.ntnu.folk.game.gameplay.layers;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.gameplay.entities.data.Teams;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import sheep.collision.CollisionListener;
import sheep.game.Layer;
import sheep.game.Sprite;
import sheep.math.BoundingBox;
import sheep.math.Vector2;

import java.util.ArrayList;

public class GameLayer extends Layer implements CollisionListener {
	private ArrayList<PlayerModel> players;
	private ArrayList<ProjectileModel> projectiles = new ArrayList<ProjectileModel>();

	private int currentPlayer = 0;

	public GameLayer(int playerCount) {
		players = new ArrayList<PlayerModel>();
		for (int i = 0; i < playerCount; i++) {
			String name = "Player " + i;
			Vector2 position = new Vector2(75 * (i + 1), 100 + (20 * i));
			Teams team = i < playerCount / 2 ? Teams.RED : Teams.BLUE;
			PlayerModel player = new PlayerModel(name, position, team);
			players.add(player);
			player.addCollisionListener(this);
		}
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
