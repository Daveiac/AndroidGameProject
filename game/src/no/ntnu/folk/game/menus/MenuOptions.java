package no.ntnu.folk.game.menus;

/**
 * An enum class for the different options in the menu.
 * @author The_Alchemist
 *
 */
public enum MenuOptions {
	PRE_GAME_MENU("Start new game"),
	PLAYER_COUNT("Player count"),
	SELECT_MAP("Select map"),
	HEALTH("Max health"),
	GAME_TYPE("Game type"),
	START_GAME("Start game"),
	TURN_TIMER("Turn timer"),

	MAIN_MENU("Return to main menu"),
	RETURN_TO_GAME("Return to game"),
	BACK("Back"),

	DEV_MENU("Developer menu"),
	UNLIMITED_FIRE("Unlimited fire"),
	TOGGLE_DEBUG("Toggle debug"), 
	DEBUG_WALLS("Remove wall texture")
	;

	private final String label;

	/**
	 * The MenuOptions constructor. The label decides which option is chosen.
	 * @param label The label of the given option.
	 */
	MenuOptions(String label) {
		this.label = label;
	}

	/**
	 * @return The label of the given option.
	 */
	public String getLabel() {
		return label;
	}

}
