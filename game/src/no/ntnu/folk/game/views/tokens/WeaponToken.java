package no.ntnu.folk.game.views.tokens;

import no.ntnu.folk.game.models.WeaponModel;
import sheep.graphics.Image;

public class WeaponToken extends Token {
	private final WeaponModel model;
	private int rotation = 0; // TODO implement

	/**
	 * @param model WeaponModel for this token
	 */
	protected WeaponToken(WeaponModel model) {
		super(model.getName(), model.getImageSize());
		this.model = model;
	}

	/**
	 * Fill the array containing the images for this token
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
	public WeaponModel getModel() {
		return model;
	}

	/**
	 * @return The rotation of the weapon. (Based on where it is pointing.)
	 */
	@Override
	protected int getRotation() {
		return rotation;
	}

}
