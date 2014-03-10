package no.ntnu.folk.game.gameplay.entities.views;

import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.entities.models.WeaponModel;
import sheep.graphics.Image;

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

	/**
	 * @return The rotation of the weapon. (Based on where it is pointing.)
	 */
	@Override
	protected int getRotation() {
		return rotation;
	}

}
