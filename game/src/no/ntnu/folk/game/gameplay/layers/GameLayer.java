package no.ntnu.folk.game.gameplay.layers;

import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import no.ntnu.folk.game.gameplay.levels.views.LevelToken;
import no.ntnu.folk.game.gameplay.models.GameModel;
import sheep.collision.CollisionListener;
import sheep.game.Layer;
import sheep.game.Sprite;
import sheep.math.BoundingBox;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class GameLayer extends Layer implements CollisionListener {
	private GameModel model;
	private float turnTimer;
	private String timeStringConstant = "Time"; //Fix this to language packs?
	private Paint paint;

	public GameLayer(GameModel model) {
		this.model = model;
		for (PlayerModel player : model.getPlayerList()) {
			player.addCollisionListener(this);
		}
		turnTimer = GameplayConstants.TURN_TIME;
		paint = new Paint();
		paint.setColor(Color.WHITE);
	}

	@Override
	public void update(float dt) {
		for (PlayerModel player : model.getPlayerList()) {
			player.update(dt);
		}
		for (ProjectileModel projectile : model.getProjectiles()) {
			projectile.update(dt);
		}
		turnTimer-= dt;
		if(turnTimer <= 0) {
			turnTimer = GameplayConstants.TURN_TIME;
			model.getCurrentPlayer().setSpeed(0, 0);
			model.nextPlayer();
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
		canvas.drawText(timeStringConstant+": "+((int) turnTimer), ProgramConstants.getWindowSize()[0]*0.9f, ProgramConstants.getWindowSize()[1]*0.1f, paint);
	}

	public void resetTurnTime() {
		this.turnTimer = 0;
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
