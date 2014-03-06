package no.ntnu.folk.game.views.tokens;

import android.graphics.Canvas;
import no.ntnu.folk.game.models.PlayerModel;
import sheep.graphics.Image;

public class PlayerToken extends Token {

	/**
	 * @param model PlayerModel for this token
	 */
	public PlayerToken(PlayerModel model) {
		super(model);
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
		canvas.drawText(this.toString(),
				getX() - model.getImageHeight() / 2,
				getY() - model.getImageHeight() / 2,
				((PlayerModel)model).getTeam().getTagColor());
	}

	/**
	 * @return 0 as the player does not rotate.
	 */
	@Override
	protected int getRotation() {
		return 0;
	}
}
