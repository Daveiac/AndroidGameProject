package no.ntnu.folk.game.views.tokens;

import no.ntnu.folk.game.models.ProjectileModel;
import sheep.graphics.Image;

public class ProjectileToken extends Token {

	/**
	 * @param model ProjectileModel for this token
	 */
	protected ProjectileToken(ProjectileModel model) {
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

	/**
	 * @return the angle of the projectile. Calculated from it's velocity.
	 */
	@Override
	protected int getRotation() {
		return (int) Math.toDegrees(Math.acos(getSpeed().getX() / getSpeed().getLength()));
	}

}
