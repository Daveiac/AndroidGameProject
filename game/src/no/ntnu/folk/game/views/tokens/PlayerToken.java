package no.ntnu.folk.game.views.tokens;

import no.ntnu.folk.game.models.PlayerModel;

public class PlayerToken extends Token {
	private PlayerModel model;

	/**
	 * @param model PlayerModel for this token
	 */
	public PlayerToken(PlayerModel model) {
		super(model.getName(), model.getImageSize());
		this.model = model;
		//TODO
	}

	// TODO

	/**
	 * @return The model for this token
	 */
	public PlayerModel getModel() {
		return model;
	}

}
