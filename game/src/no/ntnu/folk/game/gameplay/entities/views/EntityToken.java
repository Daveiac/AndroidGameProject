package no.ntnu.folk.game.gameplay.entities.views;

import android.graphics.Canvas;
import android.graphics.Matrix;
import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.gameplay.entities.models.EntityModel;
import sheep.collision.Polygon;
import sheep.collision.Rectangle;
import sheep.collision.Shape;
import sheep.graphics.Color;
import sheep.graphics.Image;

public abstract class EntityToken {
	// EntityModel
	protected final EntityModel entityModel;

	// Animation
	protected Image image;
	protected Matrix transformation = new Matrix(); // Transformation matrix. Used for drawing the images.

	/**
	 * @param entityModel The entityModel for this token
	 */
	protected EntityToken(EntityModel entityModel, int image) {
		this.entityModel = entityModel;
		this.image = new Image(image);
		float width = this.image.getWidth();
		float height = this.image.getHeight();
		Shape r = new Polygon(new float[]{
				-width / 2, -height / 2,
				width / 2, -height / 2,
				width / 2, height / 2,
				-width / 2, height / 2
		});
		entityModel.setShape(r);
		entityModel.setOffset(width/2, height/2);
	}

	/**
	 * Update entityModel, animation, and transformation
	 *
	 * @param dt Time since last update
	 */
	public void update(float dt) {
		updateTransformationMatrix();
	}
	/**
	 * Update the transformation matrix for this token
	 */
	private void updateTransformationMatrix() {
		transformation.setTranslate(-entityModel.getOffset().getX(), -entityModel.getOffset().getY());   // Use the center of the sprite as center for drawing
		transformation.postScale(getScaleX(), getScaleY());
		transformation.postRotate(getRotation());
		transformation.postTranslate(entityModel.getX(), entityModel.getY()); // getX and getY is located in Sprite
	}
	protected abstract float getScaleX();

	protected abstract float getScaleY();

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
		image.draw(canvas, transformation);
		if (ProgramConstants.isDebugging()) {
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
				entityModel.getX() - image.getWidth() / 2,
				entityModel.getY() - image.getHeight() * 3 / 4,
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
		float xmin = entityModel.getX() - image.getWidth() / 2;
		float xmax = entityModel.getX() + image.getWidth() / 2;
		float ymin = entityModel.getY() - image.getHeight() / 2;
		float ymax = entityModel.getY() + image.getHeight() / 2;
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

	public EntityModel getEntityModel() {
		return entityModel;
	}
}
