package no.ntnu.folk.game.gameplay.entities.views;

import java.util.Arrays;

import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import sheep.graphics.Color;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class PlayerToken extends EntityToken {
	private float direction;
	private float healthbarFrame = 5;

	/**
	 * @param model PlayerModel for this token
	 * @param image ID for the image
	 */
	public PlayerToken(PlayerModel model, int image) {
		super(model, image);
		direction = 1;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		Matrix matrix = new Matrix();
		float x = entityModel.getX();
		float height = image.getHeight();
		float width = image.getWidth() /  2;
		float y = entityModel.getY();
		matrix.postTranslate(x, y - height);
		float health = ((PlayerModel) entityModel).getHealth() * 1.0f / ((PlayerModel) entityModel).getStartHealth();
		canvas.drawRect(x - width - healthbarFrame , y - height + healthbarFrame, x + width + healthbarFrame, y - height*(1.25f) - healthbarFrame, Color.BLACK);
		canvas.drawRect(x - width, y - height, (x - width) + width*health*2, y - height*(1.25f), Color.RED);
	}

	@Override
	public String toString() {
		return "PlayerToken{name='" + entityModel.getName() + '\'' +
				", health=" + ((PlayerModel) entityModel).getHealth() +
				", coll: " + Arrays.toString(entityModel.getCollision().toArray()) +
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
