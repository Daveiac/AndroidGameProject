package no.ntnu.folk.game.gameplay.entities.views;

import no.ntnu.folk.game.gameplay.entities.models.WeaponModel;
import sheep.math.Vector2;

public class WeaponToken extends EntityToken {
	private int rotation;
	/**
	 * @param model WeaponModel for this token
	 * @param image ID for this image
	 */
	public WeaponToken(WeaponModel model, int image) {
		super(model, image);
	}

	// TODO

	@Override
	public String toString() {
		return "WeaponToken{" +
				"name='" + entityModel.getName() + '\'' +
				", projectile='" + ((WeaponModel) entityModel).getProjectileType() + '\'' +
				'}';
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		if (rotation > 90 && rotation < 270) {
			entityModel.setScale(1,-1);
		} else {
			entityModel.setScale(1,1);
		}
	}

	@Override
	protected float getScaleX() {
		return entityModel.getScale().getX();
	}
	@Override
	protected float getScaleY() {
		return entityModel.getScale().getY();
	}

	/**
	 * @return The rotation of the weapon. (Based on where it is pointing.)
	 */
	@Override
	protected int getRotation() {
		Vector2 aim = ((WeaponModel) entityModel).getAim();
		rotation = (int) Math.toDegrees(Math.atan(aim.getY() / aim.getX()));
		if (aim.getX() < 0 && rotation < 180) rotation += 180;
		if (aim.getX() > 0 && rotation > 180) rotation += 180;
		return rotation;
	}

}
