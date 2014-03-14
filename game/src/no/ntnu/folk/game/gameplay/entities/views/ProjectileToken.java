package no.ntnu.folk.game.gameplay.entities.views;

import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import no.ntnu.folk.game.gameplay.entities.models.WeaponModel;
import sheep.graphics.Image;
import sheep.math.Vector2;

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
				new Image(R.drawable.bullet_medium),
		};
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
//		return (int) Math.toDegrees(Math.acos(entityModel.getSpeed().getX() / entityModel.getSpeed().getLength()));
	}

}
