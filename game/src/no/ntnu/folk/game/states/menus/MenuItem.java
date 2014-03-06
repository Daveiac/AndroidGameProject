package no.ntnu.folk.game.states.menus;

import android.graphics.Canvas;
import sheep.graphics.Color;
import sheep.math.BoundingBox;

public class MenuItem extends BoundingBox {
	private String label;
	private final float xmin;
	private final float xmax;
	private final float ymin;
	private final float ymax;

	public MenuItem(String label, float xmin, float xmax, float ymin, float ymax) { // TODO make an easier to use constructor
		super(xmin, xmax, ymin, ymax);
		this.label = label;
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
		canvas.drawText(label, xmin + offset, ymin + offset, Color.WHITE);
	}
	public String getLabel() {
		return label;
	}
}
