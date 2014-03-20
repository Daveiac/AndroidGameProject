package no.ntnu.folk.game.gameplay.levels.views;

public class TokenFactory {
	
	@Deprecated
	public static LevelToken createToken(String name) {
		LevelToken token = null;

		if (name.equals("Wall")) {
			//This is now deprecated, using a new level parser.
//			token = new Wall();
		} 
//		else if (name.equals("Structure")) { //TODO add sturcture if we wanted that
//			token = new Structure(new Image(R.drawable.structure));
//		}
		return token;
	}
}
