package no.ntnu.folk.game.models;

public abstract class Model {
	private final String name;
	private float[] imageSize;

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
	 * @return Image size for the token associated with this model
	 */
	public float[] getImageSize() {
		return imageSize;
	}

}
