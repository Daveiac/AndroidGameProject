package no.ntnu.folk.game.gameplay.entities.views;

import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import sheep.math.Vector2;

public class ProjectileToken extends EntityToken {

	/**
	 * @param model ProjectileModel for this token
	 * @param image ID for the image
	 */
	public ProjectileToken(ProjectileModel model, int image) {
		super(model, image);
	}

	// TODO

	/**
	 * @return the angle of the projectile. Calculated from it's velocity.
	 */
	@Override
	protected int getRotation() {
		Vector2 speed = entityModel.getSpeed();
		int rotation = (int) Math.toDegrees(Math.atan(speed.getY() / speed.getX()));
		if (speed.getX() < 0 && rotation < 180) rotation += 180;
		if (speed.getX() > 0 && rotation > 180) rotation += 180;
		return rotation;
	}

}
