package no.ntnu.folk.game.views.tokens;

import no.ntnu.folk.game.R;
import no.ntnu.folk.game.models.WeaponModel;
import sheep.graphics.Image;
import sheep.math.Vector2;

public class WeaponToken extends Token {
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
				new Image(R.drawable.bullet_medium), // TODO proper image
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
