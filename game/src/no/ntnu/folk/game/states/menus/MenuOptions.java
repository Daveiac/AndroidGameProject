package no.ntnu.folk.game.states.menus;

public enum MenuOptions {
	PRE_GAME_MENU("Start new game"),
	PLAYER_COUNT("Player count"),
	SELECT_MAP("Select map"),
	HEALTH("Max health"),
	TEAMS("Team game or FFA"),
	START_GAME("Start game"),

	SAVE_MENU("Save menu"),
	SAVE_GAME("Save game"),
	LOAD_MENU("Load an old game"),
	LOAD_GAME("Load game"),

	MAIN_MENU("Return to main menu"),
	RETURN_TO_GAME("Return to game"),
	BACK("Back"),

	DEV_MENU("Developer menu"),
	TOGGLE_DEBUG("Toggle debug"),
	;

	private final String label;

	MenuOptions(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
