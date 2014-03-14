package no.ntnu.folk.game.gameplay;

import android.graphics.Canvas;
import sheep.graphics.Image;
import sheep.math.BoundingBox;
import sheep.math.Vector2;

public class Button {
	private final boolean canHold;
	private BoundingBox boundingBox;

	private Image idleImage;
	private Image pressedImage;

	private boolean pressed = false;

	private float x;
	private float y;

	/**
	 * @param idleImage    Image when idle
	 * @param pressedImage Image when pressed
	 * @param center       Center position
	 * @param canHold      If true, this button can be held an will still be active. If false, this button only activates on first update
	 */
	public Button(int idleImage, int pressedImage, Vector2 center, boolean canHold) {
		this.idleImage = new Image(idleImage);
		this.pressedImage = new Image(pressedImage);
		this.canHold = canHold;
		this.x = center.getX() - this.idleImage.getWidth() / 2;
		this.y = center.getY() - this.idleImage.getHeight() / 2;
		boundingBox = new BoundingBox(
				x,
				x + this.idleImage.getWidth(),
				y,
				y + this.idleImage.getHeight()
		);
	}

	/**
	 * Draw this button.
	 * @param canvas canvas to draw the image on
	 */
	public void draw(Canvas canvas) {
		if (pressed) {
			idleImage.draw(canvas, x, y);
		} else {
			pressedImage.draw(canvas, x, y);
		}
	}

	/**
	 * Returns whether or not this button is pressed.
	 * If canHold == false, pressed will be set to false.
	 *
	 * @return true if pressed
	 */
	public boolean popPressed() {
		boolean wasPressed = pressed;
		if (!canHold) {
			pressed = false;
		}
		return wasPressed;
	}
	/**
	 * This button was touched
	 */
	public void touch() {
		pressed = true;
	}
	/**
	 * Button is held (or touch input is moved into the button)
	 */
	public void hold() {
		if (canHold) {
			pressed = true;
		}
	}
	/**
	 * Button is released
	 */
	public void release() {
		pressed = false;
	}

	/**
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @return true if the coordinates are within this button
	 */
	public boolean contains(float x, float y) {
		return boundingBox.contains(x, y);
	}
}
