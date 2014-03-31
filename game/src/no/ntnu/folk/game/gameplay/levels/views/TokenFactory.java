package no.ntnu.folk.game.gameplay.levels.views;

public class TokenFactory {
	/**
	 * This method creates a level token depending on the character input
	 * 
	 * @param c The character that determines the token
	 * @param x The x position of the token
	 * @param y The y position of the token
	 * @return
	 */
	
	public static LevelToken createToken(char c, int x, int y) {
		LevelToken token = null;
		switch (c) {
		case 'w':
			token = new Wall(x, y);
			break;
		default:
			break;
		}
		return token;
	}
}
