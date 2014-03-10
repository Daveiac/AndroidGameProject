package no.ntnu.folk.game.gameplay.levels.views;

import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.Vector2;

/**
 * Used to make level tokens 
 * 
 * @author Rune
 *
 */
public class LevelToken extends Sprite{
	
	private Vector2 position;
	
	public LevelToken(Image image, int x, int y){
		
	}
	
	public Vector2 getPosition(){
		return this.position;
	}
}
