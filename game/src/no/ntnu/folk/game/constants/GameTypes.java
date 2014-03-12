package no.ntnu.folk.game.constants;

import static no.ntnu.folk.game.R.string.*;

public enum GameTypes {
	FFA(Globals.getString(FreeForAll)),
	TEAMS(Globals.getString(Teams));

	private String text;

	GameTypes(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
