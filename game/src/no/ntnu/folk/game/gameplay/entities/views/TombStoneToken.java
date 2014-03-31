package no.ntnu.folk.game.gameplay.entities.views;

import no.ntnu.folk.game.gameplay.entities.models.EntityModel;

/**
 * The tombstone token class is a view of the tombstone model class which it represents.
 *
 */
public class TombStoneToken extends EntityToken {

	/**
	 * @param entityModel The entityModel for this token
	 * @param image
	 */
	public TombStoneToken(EntityModel entityModel, int image) {
		super(entityModel, image);
	}

	@Override
	protected float getScaleX() {
		return 1;
	}
	@Override
	protected float getScaleY() {
		return 1;
	}
	@Override
	protected int getRotation() {
		return 0;
	}

}
