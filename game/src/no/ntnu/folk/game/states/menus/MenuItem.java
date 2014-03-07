package no.ntnu.folk.game.states.menus;

import android.graphics.Canvas;
import sheep.graphics.Color;
import sheep.math.BoundingBox;

public class MenuItem extends BoundingBox {
	private String label;
	private String text;
	private final int gameState;
	private final float xmin;
	private final float xmax;
	private final float ymin;
	private final float ymax;

	public MenuItem(int gameState,String label,String text, float xmin, float xmax, float ymin, float ymax) { // TODO make an easier to use constructor
		super(xmin, xmax, ymin, ymax);
		this.gameState = gameState;
		this.label = label;
		this.text = text;
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
		float labelOffset = 20;
		float textOffset = (xmax-xmin)/2;
		canvas.drawText(label, xmin + labelOffset, ymin + labelOffset, Color.WHITE);
		canvas.drawText(text, xmin + textOffset, ymin + labelOffset, Color.WHITE);
	}
	
	public Integer getState() {
		return gameState;
	}
	public void setText(String text) {
		this.text = text;
	}
}
