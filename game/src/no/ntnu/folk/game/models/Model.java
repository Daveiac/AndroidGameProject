package no.ntnu.folk.game.models;

import android.graphics.Canvas;
import no.ntnu.folk.game.views.tokens.Token;
import sheep.game.Sprite;
import sheep.math.Vector2;

public abstract class Model extends Sprite {
	private final Token token;

	private final String name;
	private float[] imageSize;

	/**
	 * @param name        Name of the token associated with this model
	 * @param position    Position of this model
	 * @param imageWidth  Width of the image for the token associated with this model
	 * @param imageHeight Height of the image for the token associated with this model
	 */
	protected Model(String name, Vector2 position, float imageWidth, float imageHeight) {
		this.name = name;
		this.setPosition(position);
		imageSize = new float[]{imageWidth, imageHeight};
		this.token = createToken();
	}
	protected abstract Token createToken();

	@Override
	public void update(float dt) {
		super.update(dt);
		token.update(dt);
	}
	@Override
	public void draw(Canvas canvas) {
		token.draw(canvas);
	}
	/**
	 * @return Name of this model
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return Image width for the token associated with this model
	 */
	public float getImageWidth() {
		return imageSize[0];
	}
	/**
	 * @return Image height for the token associated with this model
	 */
	public float getImageHeight() {
		return imageSize[1];
	}

}
