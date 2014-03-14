package no.ntnu.folk.game.gameplay.entities.views;

import android.graphics.Canvas;
import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import sheep.graphics.Image;

public class PlayerToken extends EntityToken {
	private float direction;

	/**
	 * @param model PlayerModel for this token
	 * @param image ID for the image
	 */
	public PlayerToken(PlayerModel model, int image) {
		super(model, image);
		direction = 1;
	}

	// TODO

	@Override
	protected void drawDebugInformation(Canvas canvas) {
		canvas.drawText(
				this.toString(),
				entityModel.getX() - image.getWidth() / 2,
				entityModel.getY() - image.getHeight() * 3 / 4,
				((PlayerModel) entityModel).getTeam().getTagColor()
		);
		drawOutline(canvas);
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
