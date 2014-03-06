package no.ntnu.folk.game.views.tokens;

import no.ntnu.folk.game.models.ProjectileModel;
import sheep.graphics.Image;
import sheep.math.Vector2;

public class ProjectileToken extends Token {

	/**
	 * @param model ProjectileModel for this token
	 */
	protected ProjectileToken(ProjectileModel model, Vector2 position) {
		super(model, position);
	}

	/**
	 * Fill the array containing the images for this token
	 */
	@Override
	protected void setImages() {
		images = new Image[]{
				// TODO Fill the image array
		};
	}

	// TODO

	/**
	 * @return the angle of the projectile. Calculated from it's velocity.
	 */
	@Override
	protected int getRotation() {
		return (int) Math.toDegrees(Math.acos(getSpeed().getX() / getSpeed().getLength()));
	}

}
