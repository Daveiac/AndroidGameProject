package no.ntnu.folk.game.menus;

import no.ntnu.folk.game.constants.Globals;

import static no.ntnu.folk.game.R.string.*;

public enum MenuOptions {
	PRE_GAME_MENU(Globals.getString(PreGameMenu)),
	PLAYER_COUNT(Globals.getString(PlayerCount)),
	SELECT_MAP(Globals.getString(SelectMap)),
	HEALTH(Globals.getString(MaxHealth)),
	GAME_TYPE(Globals.getString(GameType)),
	START_GAME(Globals.getString(StartGame)),

	SAVE_MENU(Globals.getString(SaveMenu)),
	SAVE_GAME(Globals.getString(SaveGame)),
	LOAD_MENU(Globals.getString(LoadMenu)),
	LOAD_GAME(Globals.getString(LoadGame)),

	MAIN_MENU(Globals.getString(MainMenu)),
	RETURN_TO_GAME(Globals.getString(ReturnToGame)),
	BACK(Globals.getString(Back)),

	DEV_MENU(Globals.getString(DevMenu)),
	TOGGLE_DEBUG(Globals.getString(ToggleDebug)),;

	private final String label;

	MenuOptions(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
