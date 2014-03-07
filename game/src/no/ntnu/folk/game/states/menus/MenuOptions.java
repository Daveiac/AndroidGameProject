package no.ntnu.folk.game.states.menus;

public enum MenuOptions {
	PRE_GAME_MENU("Start new game"),
	START_GAME("Start game"),
	LOAD_MENU("Load an old game"),
	LOAD_GAME("Load game"),
	BACK("Back"),
	PLAYER_COUNT("Player count");

	private final String label;

	MenuOptions(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
