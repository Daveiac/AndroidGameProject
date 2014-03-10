package no.ntnu.folk.game.gameplay.entities.data;

import sheep.graphics.Color;

public enum Teams {
	BLUE(Color.BLUE),
	RED(Color.RED),
	GREEN(Color.GREEN),
	CYAN(new Color(0, 255, 255)),
	MAGENTA(new Color(255, 0, 255)),
	YELLOW(new Color(255, 255, 0)),
//	WHITE(Color.WHITE),
	;

	private final Color tagColor;

	/**
	 * @param tagColor Color of name tag for members of this team
	 */
	Teams(Color tagColor) {
		this.tagColor = tagColor;
	}

	public static Teams getTeamFromOrdinal(int ordinal) {
		return values()[ordinal];
	}

	/**
	 * @return Team color. Use for drawing name tags etc.
	 */
	public Color getTagColor() {
		return tagColor;
	}

}
