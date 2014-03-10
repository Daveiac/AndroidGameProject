package no.ntnu.folk.game.views.level;

/**
 * Used to make level tokens 
 * 
 * @author b2
 *
 */
public class Wall extends LevelToken {
	
	private int x;
	private int y;
	private int height;
	private int width;
	
	public Wall() {
		
	}

	@Override
	protected void getParameters(String key, int value) {
		
		switch (key.charAt(0)) {
		case 'x': this.x = value; break;
		case 'y': this.y = value; break;
		case 'h': this.height = value; break;
		case 'w': this.width = value; break;
		}
	}
}
