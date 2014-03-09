package no.ntnu.folk.game.views.tokens;

import android.graphics.Canvas;
import android.graphics.Matrix;
import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.models.Model;
import sheep.graphics.Color;
import sheep.graphics.Image;

public abstract class Token {
	// Model
	protected final Model model;

	// Animation
	protected Image[] images;
	protected int currentFrame = 0;
	protected float animationTick = 0;
	protected float frameTime = 0.1f;
	protected Matrix transformation = new Matrix(); // Transformation matrix. Used for drawing the images.

	/**
	 * @param model The model for this token
	 */
	protected Token(Model model) {
		this.model = model;
		model.setShape(model.getImageWidth(), model.getImageHeight());
		setImages();
	}
	/**
	 * Set the image array (containing the animation images)
	 */
	protected abstract void setImages();

	/**
	 * Update model, animation, and transformation
	 *
	 * @param dt Time since last update
	 */
	public void update(float dt) {
		updateAnimation(dt);
		updateTransformationMatrix();
	}
	/**
	 * Update the animation for this token.
	 *
	 * @param dt Time passed
	 */
	private void updateAnimation(float dt) {
		animationTick += dt;
		while (animationTick >= frameTime) {
			animationTick -= frameTime;
			currentFrame++;
		}
		currentFrame %= images.length;
	}
	/**
	 * Update the transformation matrix for this token
	 */
	private void updateTransformationMatrix() {
		transformation.setTranslate(-model.getImageWidth() / 2, -model.getImageHeight() / 2);   // Use the center of the sprite as center for drawing
		// TODO scale for mirroring
		transformation.postRotate(getRotation());
		transformation.postTranslate(model.getX(), model.getY()); // getX and getY is located in Sprite
	}
	/**
	 * Get the rotation of the images for tokens where this apply.
	 *
	 * @return token rotation. Tokens that does not rotate should return 0.
	 */
	protected abstract int getRotation();

	/**
	 * Draw this Token
	 *
	 * @param canvas The canvas to draw this token on
	 */
	public void draw(Canvas canvas) {
		images[currentFrame].draw(canvas, transformation);
		if (Constants.isDebugging()) {
			drawDebugInformation(canvas);
		}
	}
	/**
	 * Draw debug information using this.toString
	 *
	 * @param canvas
	 */
	protected void drawDebugInformation(Canvas canvas) {
		canvas.drawText(
				this.toString(),
				model.getX() - model.getImageWidth() / 2,
				model.getY() - model.getImageHeight() * 3 / 4,
				Color.WHITE
		);
		drawOutline(canvas);
	}
	protected void drawOutline(Canvas canvas) {
		float xmin = model.getX() - model.getImageWidth() / 2;
		float xmax = model.getX() + model.getImageWidth() / 2;
		float ymin = model.getY() - model.getImageHeight() / 2;
		float ymax = model.getX() + model.getImageHeight() / 2;
		canvas.drawLine(xmin, ymin, xmax, ymin, Color.WHITE);
		canvas.drawLine(xmin, ymax, xmax, ymax, Color.WHITE);
		canvas.drawLine(xmin, ymin, xmin, ymax, Color.WHITE);
		canvas.drawLine(xmax, ymin, xmax, ymax, Color.WHITE);
	}

	@Override
	public String toString() {
		return "Token{" +
				"name='" + model.getName() + '\'' +
				'}';
	}

}
