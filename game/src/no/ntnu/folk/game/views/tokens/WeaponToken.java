package no.ntnu.folk.game.views.tokens;

import no.ntnu.folk.game.models.WeaponModel;

public class WeaponToken extends Token {
	private final WeaponModel model;

	/**
	 * @param model WeaponModel for this token
	 */
	protected WeaponToken(WeaponModel model) {
		super(model.getName(), model.getImageSize());
		this.model = model;
	}

	// TODO

}
