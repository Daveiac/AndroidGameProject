package no.ntnu.folk.game.constants;

/**
 * An enum class to determine the different game types in the game.
 *
 */
public enum GameTypes {
	FFA("Free For All"), TEAMS("Teams");
	private String text;

	/**
	 * The enum constructor.
	 * @param text
	 */
	GameTypes(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return text;
	}
}
