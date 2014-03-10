package no.ntnu.folk.game.gameplay.entities.views;

import android.graphics.Canvas;
import android.graphics.Matrix;
import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.gameplay.entities.models.EntityModel;
import sheep.graphics.Color;
import sheep.graphics.Image;

public abstract class EntityToken {
	// EntityModel
	protected final EntityModel entityModel;

	// Animation
	protected Image[] images;
	protected int currentFrame = 0;
	protected float animationTick = 0;
	protected float frameTime = 0.1f;
	protected Matrix transformation = new Matrix(); // Transformation matrix. Used for drawing the images.

	/**
	 * @param entityModel The entityModel for this token
	 */
	protected EntityToken(EntityModel entityModel) {
		this.entityModel = entityModel;
		entityModel.setShape(entityModel.getImageWidth(), entityModel.getImageHeight());
		setImages();
	}
	/**
	 * Set the image array (containing the animation images)
	 */
	protected abstract void setImages();

	/**
	 * Update entityModel, animation, and transformation
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
		transformation.setTranslate(-entityModel.getImageWidth() / 2, -entityModel.getImageHeight() / 2);   // Use the center of the sprite as center for drawing
		// TODO scale for mirroring
		transformation.postRotate(getRotation());
		transformation.postTranslate(entityModel.getX(), entityModel.getY()); // getX and getY is located in Sprite
	}
	/**
	 * Get the rotation of the images for tokens where this apply.
	 *
	 * @return token rotation. Tokens that does not rotate should return 0.
	 */
	protected abstract int getRotation();

	/**
	 * Draw this EntityToken
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
				entityModel.getX() - entityModel.getImageWidth() / 2,
				entityModel.getY() - entityModel.getImageHeight() * 3 / 4,
				Color.WHITE
		);
		drawOutline(canvas);
	}
	/**
	 * Draw an outline of this token.
	 *
	 * @param canvas The canvas to draw the outline on
	 */
	protected void drawOutline(Canvas canvas) {
		float xmin = entityModel.getX() - entityModel.getImageWidth() / 2;
		float xmax = entityModel.getX() + entityModel.getImageWidth() / 2;
		float ymin = entityModel.getY() - entityModel.getImageHeight() / 2;
		float ymax = entityModel.getY() + entityModel.getImageHeight() / 2;
		canvas.drawLine(xmin, ymin, xmax, ymin, Color.WHITE);
		canvas.drawLine(xmin, ymax, xmax, ymax, Color.WHITE);
		canvas.drawLine(xmin, ymin, xmin, ymax, Color.WHITE);
		canvas.drawLine(xmax, ymin, xmax, ymax, Color.WHITE);
	}

	@Override
	public String toString() {
		return "EntityToken{" +
				"name='" + entityModel.getName() + '\'' +
				'}';
	}

}
