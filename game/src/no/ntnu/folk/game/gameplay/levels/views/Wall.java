package no.ntnu.folk.game.gameplay.levels.views;

import no.ntnu.folk.game.constants.GameplayConstants;
import sheep.graphics.Color;
import android.graphics.Canvas;

/**
 * Used to make level tokens
 * 
 * @author b2
 */
public class Wall extends LevelToken {
	private int gridX;
	private int gridY;
	private float height;
	private float width;
	private float x;
	private float y;

	public Wall(int gridX, int gridY) {
		super();
		this.gridX = gridX;
		this.gridY = gridY;
		float gridSize = GameplayConstants.GRID_SIZE;
		x = this.gridX * gridSize;
		y = this.gridY * gridSize;
		width = x + gridSize;
		height = y + gridSize;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		drawShadow(canvas);
		drawWall(canvas);
	}

	private void drawShadow(Canvas canvas) {
		canvas.drawRect(x + 1, y + 1, width + 1, height + 1, Color.BLACK);
	}

	private void drawWall(Canvas canvas) {
		canvas.drawRect(x, y, width, height, Color.GREEN);
	}
}
