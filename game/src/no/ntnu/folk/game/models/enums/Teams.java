package no.ntnu.folk.game.models.enums;

import sheep.graphics.Color;

public enum Teams {
	BLUE(Color.BLUE),
	RED(Color.RED);

	private final Color tagColor;

	/**
	 * @param tagColor Color of name tag for members of this team
	 */
	Teams(Color tagColor) {
		this.tagColor = tagColor;
	}

	public Color getTagColor() {
		return tagColor;
	}

}
