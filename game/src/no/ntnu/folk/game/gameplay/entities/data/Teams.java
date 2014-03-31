package no.ntnu.folk.game.gameplay.entities.data;

import no.ntnu.folk.game.R;
import sheep.graphics.Color;

/**
 * An enum class for the teams in-game used for creation of teams.
 *
 */
public enum Teams {
	BLUE(Color.BLUE, R.drawable.player0),
	RED(Color.RED, R.drawable.player1),
	GREEN(Color.GREEN, R.drawable.player2),
	CYAN(new Color(0, 255, 255), R.drawable.player3),
	MAGENTA(new Color(255, 0, 255), R.drawable.player4),
	YELLOW(new Color(255, 255, 0), R.drawable.player5),
//	WHITE(Color.WHITE),
	;

	private final Color tagColor;
	private int playerImage;

	/**
	 * @param tagColor Color of name tag for members of this team
	 */
	Teams(Color tagColor, int playerImage) {
		this.tagColor = tagColor;
		this.playerImage = playerImage;
	}

	/**
	 * @param ordinal An int to achieve the targetable team.
	 * @return Team given by the ordinal
	 */
	public static Teams getTeamFromOrdinal(int ordinal) {
		return values()[ordinal];
	}

	/**
	 * @return Team color. Use for drawing name tags etc.
	 */
	public Color getTagColor() {
		return tagColor;
	}
	/**
	 * @return Image ID for this team
	 */
	public int getImage() {
		return playerImage;
	}
}
