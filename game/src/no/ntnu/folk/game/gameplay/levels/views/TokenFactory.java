package no.ntnu.folk.game.gameplay.levels.views;

import no.ntnu.folk.game.R;

import sheep.graphics.Image;

public class TokenFactory {

	public static LevelToken createToken(String name) {
		LevelToken token = null;

		if (name.equals("LevelToken")) {
			token = new Wall();
		} else if (name.equals("Structure")) {
//			token = new Structure(new Image(R.drawable.structure));
		}
		return token;
	}
}
