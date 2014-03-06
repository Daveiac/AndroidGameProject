package no.ntnu.folk.game.views.tokens;

import no.ntnu.folk.game.models.ProjectileModel;

public class ProjectileToken extends Token {
	private final ProjectileModel model;

	/**
	 * @param model ProjectileModel for this token
	 */
	protected ProjectileToken(ProjectileModel model) {
		super(model.getName(), model.getImageSize());
		this.model = model;
	}

	// TODO

}
