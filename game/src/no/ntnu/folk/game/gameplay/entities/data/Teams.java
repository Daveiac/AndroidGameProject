package no.ntnu.folk.game.gameplay.entities.data;

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

	/**
	 * @return Team color. Use for drawing name tags etc.
	 */
	public Color getTagColor() {
		return tagColor;
	}

}
