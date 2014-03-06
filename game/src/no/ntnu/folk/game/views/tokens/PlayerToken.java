package no.ntnu.folk.game.views.tokens;

import android.graphics.Canvas;
import no.ntnu.folk.game.models.PlayerModel;
import sheep.graphics.Image;

public class PlayerToken extends Token {
	private PlayerModel model;

	/**
	 * @param model PlayerModel for this token
	 */
	public PlayerToken(PlayerModel model) {
		super(model.getName(), model.getImageSize());
		this.model = model;
	}

	/**
	 *  Fill the array containing the images for this token
	 */
	@Override
	protected void setImages() {
		images = new Image[0];
		// TODO Fill the image array
	}

	// TODO


	@Override
	protected void drawDebugInformation(Canvas canvas) {
		canvas.drawText(this.toString(), getX() - width / 2, getY() - height / 2, model.getTeam().getTagColor());
	}
	/**
	 * @return The model for this token
	 */
	public PlayerModel getModel() {
		return model;
	}
	/**
	 * @return 0 as the player does not rotate.
	 */
	@Override
	protected int getRotation() {
		return 0;
	}
}
