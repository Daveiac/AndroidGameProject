package no.ntnu.folk.game.gameplay.levels.views;

public class TokenFactory {

	public static LevelToken createToken(String name, int x, int y) {
		LevelToken token = null;

		if (name.equals("Wall")) {
			//This is now deprecated, using a new level parser. NO IT'S NOT!
			token = new Wall(x, y);
		}
//		else if (name.equals("Structure")) { //TODO add sturcture if we wanted that
//			token = new Structure(new Image(R.drawable.structure));
//		}
		return token;
	}
}
