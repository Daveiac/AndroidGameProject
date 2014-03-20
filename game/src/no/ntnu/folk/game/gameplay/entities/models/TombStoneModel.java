package no.ntnu.folk.game.gameplay.entities.models;

import no.ntnu.folk.game.gameplay.entities.views.EntityToken;
import no.ntnu.folk.game.gameplay.entities.views.TombStoneToken;
import sheep.math.Vector2;

public class TombStoneModel extends EntityModel {

	/**
	 * @param name     Name of the entityToken associated with this entityModel
	 * @param position Position of this entityModel
	 * @param image    Used when creating a token
	 */
	public TombStoneModel(String name, Vector2 position, int image) {
		super(name, position, image);
	}

	@Override
	protected EntityToken createToken(int image) {
		return new TombStoneToken(this, image);
	}

}
