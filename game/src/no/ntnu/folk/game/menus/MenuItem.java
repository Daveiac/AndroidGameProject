package no.ntnu.folk.game.menus;

import android.graphics.Canvas;
import no.ntnu.folk.game.Constants;
import sheep.graphics.Color;
import sheep.graphics.Font;
import sheep.math.BoundingBox;

public class MenuItem {
	private final BoundingBox boundingBox;
	private final MenuOptions option;
	private final float xmin;
	private final float xmax;
	private final float ymin;
	private final float ymax;

	private String data = "";

	public MenuItem(MenuOptions option, int position, String data) {
		this.option = option;
		this.data = data;
		this.xmin = Constants.getButtonWidth() / 2;
		this.xmax = Constants.getButtonWidth() / 2 * 3;
		this.ymin = Constants.getButtonHeight() * (1 + position);
		this.ymax = Constants.getButtonHeight() * (2 + position);
		boundingBox = new BoundingBox(xmin, xmax, ymin, ymax);
	}
	public MenuItem(MenuOptions option, int position) {
		this(option, position, "");
	}

	public void draw(Canvas canvas) {
		canvas.drawLine(xmin, ymin, xmax, ymin, Color.WHITE);
		canvas.drawLine(xmin, ymax, xmax, ymax, Color.WHITE);
		canvas.drawLine(xmin, ymin, xmin, ymax, Color.WHITE);
		canvas.drawLine(xmax, ymin, xmax, ymax, Color.WHITE);
		float labelOffset = 20;
		canvas.drawText(option.getLabel(), xmin + labelOffset, ymin + labelOffset, Font.WHITE_SANS_BOLD_20);
		float dataOffset = 40;
		canvas.drawText(this.getData(), xmin + dataOffset, ymin + dataOffset, Font.WHITE_SANS_BOLD_16);
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
