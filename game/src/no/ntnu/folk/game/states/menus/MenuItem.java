package no.ntnu.folk.game.states.menus;

import sheep.math.BoundingBox;

public class MenuItem extends BoundingBox {
	private String label;

	public MenuItem(String label, float xmin, float xmax, float ymin, float ymax) {
		super(xmin, xmax, ymin, ymax);
		this.label = label;
	}
}
