package no.ntnu.folk.game.states.menus;

import android.graphics.Canvas;
import no.ntnu.folk.game.Constants;
import sheep.graphics.Color;
import sheep.math.BoundingBox;

public class MenuItem {
	private final BoundingBox boundingBox;
	private final MenuOptions option;
	private final float xmin;
	private final float xmax;
	private final float ymin;
	private final float ymax;

	private String data; // TODO

	public MenuItem(MenuOptions option, int position) { // TODO make an easier to use constructor
		this.option = option;
		this.xmin = Constants.getButtonWidth() / 2;
		this.xmax = Constants.getButtonWidth() / 2 * 3;
		this.ymin = Constants.getButtonHeight() * (1 + position);
		this.ymax = Constants.getButtonHeight() * (2 + position);
		boundingBox = new BoundingBox(xmin, xmax, ymin, ymax);
	}

	public void draw(Canvas canvas) {
		canvas.drawLine(xmin, ymin, xmax, ymin, Color.WHITE);
		canvas.drawLine(xmin, ymax, xmax, ymax, Color.WHITE);
		canvas.drawLine(xmin, ymin, xmin, ymax, Color.WHITE);
		canvas.drawLine(xmax, ymin, xmax, ymax, Color.WHITE);
		float offset = 20;
		canvas.drawText(option.getLabel(), xmin + offset, ymin + offset, Color.WHITE);
	}

	public MenuOptions getOption() {
		return option;
	}

	public void setData(String data) {
		this.data = data;
	}
	public String getData() {
		return data;
	}
	public boolean contains(float x, float y) {
		return boundingBox.contains(x, y);
	}
}
