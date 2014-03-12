package no.ntnu.folk.game.gameplay.levels.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Used to make level tokens
 *
 * @author b2
 */
public class Wall extends LevelToken {
	private int height;
	private int width;

	protected Wall() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setParameters(String key, int value) {
		switch (key.charAt(0)) {
		case 'h':
			this.height = value;
			break;
		case 'w':
			this.width = value;
			break;
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		
		super.draw(canvas);
		
		Paint p = new Paint();
		
		// Shadow
		p.setColor(Color.BLACK);
		canvas.drawRect(getX()+1, getY()+1, getX()+width+1, getY()+height+1, p);
		
		// Wall
		p.setColor(Color.rgb(0, 102, 0));
		canvas.drawRect(getX(), getY(), getX()+width, getY()+height, p);
	}
}
