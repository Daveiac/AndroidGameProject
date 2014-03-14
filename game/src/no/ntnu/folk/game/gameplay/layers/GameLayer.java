package no.ntnu.folk.game.gameplay.layers;

import android.graphics.Canvas;
import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import no.ntnu.folk.game.gameplay.levels.views.LevelToken;
import no.ntnu.folk.game.gameplay.models.GameModel;
import sheep.collision.CollisionListener;
import sheep.game.Layer;
import sheep.game.Sprite;
import sheep.graphics.Color;
import sheep.math.BoundingBox;

public class GameLayer extends Layer implements CollisionListener {
	private GameModel model;

	public GameLayer(GameModel model) {
		this.model = model;
		for (PlayerModel player : model.getPlayerList()) {
			player.addCollisionListener(this);
		}
	}

	@Override
	public void update(float dt) {
		if (!model.isPaused()) {
			for (PlayerModel player : model.getPlayerList()) {
				player.update(dt);
			}
			for (ProjectileModel projectile : model.getProjectiles()) {
				projectile.update(dt);
			}
			model.update(dt);
			if (model.playerTimeUp()) {
				model.nextPlayer();
			}
		}
	}

	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		for (LevelToken levelToken : model.getLevelTokens()) {
			levelToken.draw(canvas);
		}
		for (PlayerModel player : model.getPlayerList()) {
			player.draw(canvas);
		}
		for (ProjectileModel projectile : model.getProjectiles()) {
			projectile.draw(canvas);
		}
		canvas.drawText(
				"Time left: " + ((int) model.playerTimeLeft()),
				ProgramConstants.getWindowSize()[0] * 0.9f,
				ProgramConstants.getWindowSize()[1] * 0.1f,
				Color.WHITE
		);
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
