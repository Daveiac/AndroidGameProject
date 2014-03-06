package no.ntnu.folk.game.views.tokens;

import android.graphics.Canvas;
import android.graphics.Matrix;
import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.models.Model;
import sheep.game.Sprite;
import sheep.graphics.Color;
import sheep.graphics.Image;

public abstract class Token extends Sprite {
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
		setImages();
	}
	/**
	 * Set the image array (containing the animation images)
	 */
	protected abstract void setImages();

	@Override
	public void update(float dt) {
		super.update(dt);
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
		transformation.postTranslate(this.getX(), this.getY()); // getX and getY is located in Sprite
	}
	/**
	 * Get the rotation of the images for tokens where this apply.
	 *
	 * @return token rotation. Tokens that does not rotate should return 0.
	 */
	protected abstract int getRotation();

	@Override
	public void draw(Canvas canvas) {
		images[currentFrame].draw(canvas, transformation);
		if (Constants.isDebugging()) {
			drawDebugInformation(canvas);
		}
	}
	/**
	 * Draw debug information using this.toString
	 * @param canvas
	 */
	protected void drawDebugInformation(Canvas canvas) {
		canvas.drawText(this.toString(),
				getX() - model.getImageWidth() / 2,
				getY() - model.getImageHeight() / 2,
				Color.WHITE);
	}

	@Override
	public String toString() {
		return "Token{" +
				"name='" + model.getName() + '\'' +
				'}';
	}
}
