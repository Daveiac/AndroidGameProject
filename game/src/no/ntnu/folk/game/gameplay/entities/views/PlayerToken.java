package no.ntnu.folk.game.gameplay.entities.views;

import android.graphics.Canvas;
import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import sheep.graphics.Image;

public class PlayerToken extends EntityToken {

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
				entityModel.getX() - entityModel.getImageHeight() / 2,
				entityModel.getY() - entityModel.getImageHeight() * 3 / 4,
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

	/**
	 * @return 0 as the player does not rotate.
	 */
	@Override
	protected int getRotation() {
		return 0;
	}

}
