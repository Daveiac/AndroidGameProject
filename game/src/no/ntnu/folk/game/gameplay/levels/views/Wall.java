package no.ntnu.folk.game.gameplay.levels.views;

import android.graphics.Canvas;
import no.ntnu.folk.game.R;
import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.constants.ProgramConstants;
import sheep.graphics.Color;
import sheep.graphics.Image;

/**
 * Used to make level tokens
 *
 * @author b2
 */
public class Wall extends LevelToken {
	private float height;
	private float width;
	private float x;
	private float y;
	private static Image image = new Image(R.drawable.wall);
	
	public Wall(int gridX, int gridY) {
		super();
		float gridSize = GameplayConstants.GRID_SIZE;
		x = gridX * gridSize;
		y = gridY * gridSize;
		width = x + gridSize + 1;
		height = y + gridSize + 1;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		drawShadow(canvas);
		drawWall(canvas);
	}

	/**
	 * Draws a shadow of the wall behind the actual wall.
	 *
	 * @param canvas Where to draw the shadow
	 */
	public void drawShadow(Canvas canvas) {
		canvas.drawRect(x + 1, y + 1, width + 1, height + 1, Color.BLACK);
	}

	/**
	 * Draws the wall.
	 *
	 * @param canvas Where to draw the wall
	 */
	public void drawWall(Canvas canvas) {
		if(ProgramConstants.isDebugWalls()) {
			canvas.drawRect(x, y, width, height, Color.GREEN);
		} else {
			image.draw(canvas, x, y);
		}
	}
}
