package no.ntnu.folk.game.views.level;

public class TokenFactory {

	public static LevelToken createToken(String name) {
		LevelToken token = null;

		if (name.equals("LevelToken")) {
			token = new Wall();
		}
		else if (name.equals("Structure")) {
			token = new Structure();
		}
		return token;
	}
}
