package no.ntnu.folk.game.menus;

import android.graphics.Canvas;
import no.ntnu.folk.game.constants.ProgramConstants;
import sheep.graphics.Color;
import sheep.graphics.Font;
import sheep.math.BoundingBox;

/**
 * Used to create buttons in menus, to be used in MenuStates
 */
public class MenuItem {
	private final BoundingBox boundingBox;
	private final MenuOptions option;
	private final float xmin;
	private final float xmax;
	private final float ymin;
	private final float ymax;

	private String data = "";

	/**
	 *
	 * @param option what option with button is for
	 * @param position position of the new menu button
	 * @param data what this button should say
	 */
	public MenuItem(MenuOptions option, int position, String data) {
		this.option = option;
		this.data = data;
		this.xmin = ProgramConstants.getButtonWidth() / 2;
		this.xmax = ProgramConstants.getButtonWidth() / 2 * 3;
		this.ymin = ProgramConstants.getButtonHeight() * (1 + position);
		this.ymax = ProgramConstants.getButtonHeight() * (2 + position);
		boundingBox = new BoundingBox(xmin, xmax, ymin, ymax);
	}
	public MenuItem(MenuOptions option, int position) {
		this(option, position, "");
	}

	/**
	 * Draws the button in the menu
	 * @param canvas canvas to draw on
	 */
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

	/**
	 *
	 * @return the option for this button
	 */
	public MenuOptions getOption() {
		return option;
	}

	/**
	 *
	 * @param data, set the text for this button
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 *
	 * @return test for this button
	 */
	public String getData() {
		return data;
	}

	/**
	 *
	 * @param x x-cord
	 * @param y y-cord
	 * @return  true if x,y is within this button
	 */
	public boolean contains(float x, float y) {
		return boundingBox.contains(x, y);
	}
}
