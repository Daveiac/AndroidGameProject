package no.ntnu.folk.game.gameplay.layers;

import android.graphics.Canvas;
import sheep.graphics.Image;
import sheep.math.BoundingBox;
import sheep.math.Vector2;

public class Button {
	private final boolean canHold;
	private BoundingBox boundingBox;

	private Image idleImage;

	private boolean enabled = true;
	private boolean pressed = false;

	private float x;
	private float y;

	/**
	 * @param idleImage Image when idle
	 * @param center    Center position
	 * @param canHold   If true, this button can be held an will still be active. If false, this button only activates on first update
	 */
	public Button(int idleImage, Vector2 center, boolean canHold) {
		this.idleImage = new Image(idleImage);
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
	 *
	 * @param canvas canvas to draw the image on
	 */
	public void draw(Canvas canvas) {
		if (enabled) {
			idleImage.draw(canvas, x, y);
		}
	}

	/**
	 * Returns whether or not this button is pressed.
	 * If canHold == false, pressed will be set to false.
	 *
	 * @return true if pressed AND the button is enabled.
	 */
	public boolean popPressed() {
		boolean wasPressed = pressed;
		pressed = false;
		return wasPressed && isEnabled();
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

	/**
	 * @return Returns true if this button is enabled, else return false.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Enables this button.
	 */
	public void enable() {
		enabled = true;
	}

	/**
	 * Disables this button.
	 */
	public void disable() {
		enabled = false;
	}

	/**
	 * Enables or disables this button by the given parameter.
	 *
	 * @param enabled The status of button
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Vector2 getPosition() {
		return new Vector2(x,y);
	}
}
