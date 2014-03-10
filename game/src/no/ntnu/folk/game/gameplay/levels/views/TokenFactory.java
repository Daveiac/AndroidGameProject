package no.ntnu.folk.game.gameplay.levels.views;

public class TokenFactory {

	public static LevelToken createToken(String name) {
		LevelToken token = null;

		if (name.equals("LevelToken")) {
			token = new Wall();
		}
		else if (name.equals("Structure")) {
//			token = new Structure(null, 0, 0);     // TODO fix
		}
		return token;
	}
}
