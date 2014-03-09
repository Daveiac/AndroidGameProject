package no.ntnu.folk.game.views.tokens;

import android.graphics.Canvas;
import no.ntnu.folk.game.R;
import no.ntnu.folk.game.models.PlayerModel;
import sheep.graphics.Image;
import sheep.math.Vector2;

public class PlayerToken extends Token {

	/**
	 * @param model PlayerModel for this token
	 */
	public PlayerToken(PlayerModel model) {
		super(model);
	}

	/**
	 * Fill the array containing the images for this token
	 */
	@Override
	protected void setImages() {
		images = new Image[]{
				new Image(R.drawable.player0),
//				new Image(R.drawable.player1),
		};
	}

	// TODO


	@Override
	protected void drawDebugInformation(Canvas canvas) {
		canvas.drawText(
				this.toString(),
				model.getX() - model.getImageHeight() / 2,
				model.getY() - model.getImageHeight() * 3 / 4,
				((PlayerModel) model).getTeam().getTagColor());
	}

	/**
	 * @return 0 as the player does not rotate.
	 */
	@Override
	protected int getRotation() {
		return 0;
	}

}
