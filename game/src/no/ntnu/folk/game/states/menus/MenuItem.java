package no.ntnu.folk.game.states.menus;

import android.graphics.Canvas;
import sheep.graphics.Color;
import sheep.math.BoundingBox;

public class MenuItem extends BoundingBox {
	private final MenuOptions option;
	private final float xmin;
	private final float xmax;
	private final float ymin;
	private final float ymax;

	public MenuItem(MenuOptions option, float xmin, float xmax, float ymin, float ymax) { // TODO make an easier to use constructor
		super(xmin, xmax, ymin, ymax);
		this.option = option;
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
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
}
