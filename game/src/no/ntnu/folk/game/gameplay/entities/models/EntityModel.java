package no.ntnu.folk.game.gameplay.entities.models;

import android.graphics.Canvas;
import no.ntnu.folk.game.gameplay.entities.views.EntityToken;
import sheep.game.Sprite;
import sheep.math.Vector2;

public abstract class EntityModel extends Sprite {
	private final EntityToken entityToken;

	private final String name;
	private float[] imageSize;

	/**
	 * @param name        Name of the entityToken associated with this entityModel
	 * @param position    Position of this entityModel
	 * @param imageWidth  Width of the image for the entityToken associated with this entityModel
	 * @param imageHeight Height of the image for the entityToken associated with this entityModel
	 */
	protected EntityModel(String name, Vector2 position, float imageWidth, float imageHeight) {
		this.name = name;
		this.setPosition(position);
		imageSize = new float[]{imageWidth, imageHeight};
		this.entityToken = createToken();
	}
	/**
	 * @return Create a entityToken of the correct type
	 */
	protected abstract EntityToken createToken();

	@Override
	public void update(float dt) {
		super.update(dt);
		entityToken.update(dt);
	}
	@Override
	public void draw(Canvas canvas) {
		entityToken.draw(canvas);
	}
	/**
	 * @return Name of this entityModel
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Image width for the entityToken associated with this entityModel
	 */
	public float getImageWidth() {
		return imageSize[0];
	}
	/**
	 * @return Image height for the entityToken associated with this entityModel
	 */
	public float getImageHeight() {
		return imageSize[1];
	}

}
