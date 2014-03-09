package no.ntnu.folk.game.models;

import sheep.game.Sprite;

public abstract class Model extends Sprite {
	private final String name;
	private float[] imageSize;

	/**
	 * @param name        Name of the token associated with this model
	 * @param imageWidth  Width of the image for the token associated with this model
	 * @param imageHeight Height of the image for the token associated with this model
	 */
	protected Model(String name, float imageWidth, float imageHeight) {
		this.name = name;
		imageSize = new float[]{imageWidth, imageHeight};
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
