package no.ntnu.folk.game.views.tokens;

import android.graphics.Canvas;
import android.graphics.Matrix;
import sheep.game.Sprite;
import sheep.graphics.Image;

public abstract class Token extends Sprite {
	// Sprite
	protected final float width;
	protected final float height;
	protected Matrix transformation = new Matrix(); // Transformation matrix. Used for drawing the images.

	// Animation
	protected Image[] images;
	protected int currentFrame = 0;
	protected float animationTick = 0;
	protected float frameTime = 0.1f;

	// Name
	protected final String name;

	protected Token(String name, float[] imageSize) {
		this.name = name;
		this.width = imageSize[0];
		this.height = imageSize[1];
	}

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
		transformation.setTranslate(-width / 2, -height / 2);   // Use the center of the sprite as center for drawing
		transformation.postTranslate(this.getX(), this.getY()); // getX and getY is located in Sprite
	}

	@Override
	public void draw(Canvas canvas) {
		images[currentFrame].draw(canvas, transformation);
	}

}
