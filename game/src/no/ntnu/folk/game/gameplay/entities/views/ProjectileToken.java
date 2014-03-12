package no.ntnu.folk.game.gameplay.entities.views;

import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import sheep.graphics.Image;

public class ProjectileToken extends EntityToken {

	/**
	 * @param model ProjectileModel for this token
	 */
	public ProjectileToken(ProjectileModel model) {
		super(model);
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
		return (int) Math.toDegrees(Math.acos(entityModel.getSpeed().getX() / entityModel.getSpeed().getLength()));
	}

}
