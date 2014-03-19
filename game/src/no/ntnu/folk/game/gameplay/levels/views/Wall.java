package no.ntnu.folk.game.gameplay.levels.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import no.ntnu.folk.game.constants.ProgramConstants;

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
    public int[] getParameters() {
        int[] p = new int[2];
        p[0]= width;
        p[1] = height;
        return p;
    }

    @Override
	public void draw(Canvas canvas) {
		
		super.draw(canvas);
		
		Paint p = new Paint();
        float grid = ProgramConstants.gridSize;
		
		// Shadow
		p.setColor(Color.BLACK);
		canvas.drawRect(getX()*ProgramConstants.gridSize+1, getY()+1, getX()*grid+width*grid+1, getY()*grid+height*grid+1, p);
		
		// Wall
		p.setColor(Color.rgb(0, 102, 0));
		canvas.drawRect(getX()*grid, getY()*grid, getX()*grid+width*grid, getY()*grid+height*grid, p);
	}
}
