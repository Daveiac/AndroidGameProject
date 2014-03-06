package no.ntnu.folk.game.views.tokens;

import no.ntnu.folk.game.models.ProjectileModel;
import sheep.graphics.Image;

public class ProjectileToken extends Token {
	private final ProjectileModel model;

	/**
	 * @param model ProjectileModel for this token
	 */
	protected ProjectileToken(ProjectileModel model) {
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

	/**
	 * @return The model for this token
	 */
	public ProjectileModel getModel() {
		return model;
	}
	/**
	 * @return the angle of the projectile. Calculated from it's velocity.
	 */
	@Override
	protected int getRotation() {
		return (int) Math.toDegrees(Math.acos(getSpeed().getX() / getSpeed().getLength()));
	}

}
