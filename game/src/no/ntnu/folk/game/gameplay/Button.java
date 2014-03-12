package no.ntnu.folk.game.gameplay;

import android.graphics.Canvas;
import sheep.graphics.Image;
import sheep.math.BoundingBox;

public class Button {
	private BoundingBox boundingBox;

	private Image idleImage;
	private Image pressedImage;

	private boolean pressed = false;

	private float x;
	private float y;

	public Button(int idleImage, int pressedImage, float centerX, float centerY) {
		this.idleImage = new Image(idleImage);
		this.pressedImage = new Image(pressedImage);
		this.x = centerX - this.idleImage.getWidth() / 2;
		this.y = centerY - this.idleImage.getHeight() / 2;
		boundingBox = new BoundingBox(
				x,
				x + this.idleImage.getWidth(),
				y,
				y + this.idleImage.getHeight()
		);
	}

	public void draw(Canvas canvas) {
		if (pressed) {
			idleImage.draw(canvas, x, y);
		} else {
			pressedImage.draw(canvas, x, y);
		}
	}

	public boolean isPressed() {
		return this.pressed;
	}
	public void setPressed(boolean isPressed) {
		this.pressed = isPressed;
	}

	public boolean contains(float x, float y) {
		return boundingBox.contains(x, y);
	}
}
