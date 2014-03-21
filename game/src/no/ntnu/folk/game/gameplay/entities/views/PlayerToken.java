package no.ntnu.folk.game.gameplay.entities.views;

import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import sheep.graphics.Image;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class PlayerToken extends EntityToken {
	private Image[] healthBar;
	private float direction;
	private Matrix healthBarMatrix;

	/**
	 * @param model PlayerModel for this token
	 * @param image ID for the image
	 */
	public PlayerToken(PlayerModel model, int image) {
		super(model, image);
		direction = 1;
		healthBar = new Image[]{
				new Image(R.drawable.healtbar100),
				new Image(R.drawable.healtbar075),
				new Image(R.drawable.healtbar050),
				new Image(R.drawable.healtbar025),
				new Image(R.drawable.healtbar010)
		};
		healthBarMatrix = new Matrix();
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		PlayerModel player = (PlayerModel) entityModel;
		healthBarMatrix.setTranslate(player.getX() - healthBar[0].getWidth() / 2, player.getY() + image.getHeight() / 2);
		int health = 100 * player.getHealth() / player.getStartHealth();
		if (health < 10) {
			healthBar[4].draw(canvas, healthBarMatrix);
		} else if (health <= 25) {
			healthBar[3].draw(canvas, healthBarMatrix);
		} else if (health <= 50) {
			healthBar[2].draw(canvas, healthBarMatrix);
		} else if (health <= 75) {
			healthBar[1].draw(canvas, healthBarMatrix);
		} else if (health > 75) {
			healthBar[0].draw(canvas, healthBarMatrix);
		}
	}

	@Override
	public String toString() {
		return "PlayerToken{name='" + entityModel.getName() + '\'' +
				", health=" + ((PlayerModel) entityModel).getHealth() +
				"}";
	}

	@Override
	protected float getScaleX() {
		if (entityModel.getSpeed().getX() != 0) {
			direction = Math.signum(entityModel.getSpeed().getX());
		}
		return direction;
	}
	@Override
	protected float getScaleY() {
		return 1;
	}
	/**
	 * @return 0 as the player does not rotate.
	 */
	@Override
	protected int getRotation() {
		return 0;
	}

}
