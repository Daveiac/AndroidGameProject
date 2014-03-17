package no.ntnu.folk.game.gameplay.entities.views;

import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import sheep.math.Vector2;

public class ProjectileToken extends EntityToken {

	private int rotation;
	/**
	 * @param model ProjectileModel for this token
	 * @param image ID for the image
	 */
	public ProjectileToken(ProjectileModel model, int image) {
		super(model, image);
	}

	// TODO

	@Override
	protected float getScaleX() {
		return 1;
	}
	@Override
	protected float getScaleY() {
		if (rotation > 90 && rotation < 270) {
			return -1;
		} else {
			return 1;
		}
	}

	/**
	 * @return the angle of the projectile. Calculated from it's velocity.
	 */
	@Override
	protected int getRotation() {
		Vector2 speed = entityModel.getSpeed();
		rotation = (int) Math.toDegrees(Math.atan(speed.getY() / speed.getX()));
		if (speed.getX() < 0 && rotation < 180) rotation += 180;
		if (speed.getX() > 0 && rotation > 180) rotation += 180;
		return rotation;
	}

}
