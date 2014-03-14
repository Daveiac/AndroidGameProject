package no.ntnu.folk.game.gameplay.entities.views;

import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.entities.models.WeaponModel;
import sheep.graphics.Image;
import sheep.math.Vector2;

public class WeaponToken extends EntityToken {
	private int rotation = 0; // TODO implement

	/**
	 * @param model WeaponModel for this token
	 */
	public WeaponToken(WeaponModel model) {
		super(model);
	}

	/**
	 * Fill the array containing the images for this token
	 */
	@Override
	protected void setImages() {
		images = new Image[]{
				new Image(R.drawable.handgun),
		};
	}

	// TODO


	@Override
	public String toString() {
		return "WeaponToken{" +
				"name='" + entityModel.getName() + '\'' +
				", projectile='" + ((WeaponModel) entityModel).getProjectileType() + '\'' +
				'}';
	}
	/**
	 * @return The rotation of the weapon. (Based on where it is pointing.)
	 */
	@Override
	protected int getRotation() {
		Vector2 aim = ((WeaponModel) entityModel).getAim();
		int rotation = (int) Math.toDegrees(Math.atan(aim.getY() / aim.getX()));
		if (aim.getX() < 0 && rotation < 180) rotation += 180;
		if (aim.getX() > 0 && rotation > 180) rotation += 180;
		return rotation;
	}

}
