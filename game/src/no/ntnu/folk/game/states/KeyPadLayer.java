package no.ntnu.folk.game.states;

import no.ntnu.folk.game.R;
import no.ntnu.folk.game.Constants;
import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.Layer;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.input.TouchListener;
import sheep.math.BoundingBox;
import no.ntnu.folk.game.models.GameModel;

public class KeyPadLayer extends Layer implements TouchListener{
	private Constants constats;
	
	private Image leftKeyImage = new Image(R.drawable.keypadleft);
	private Image rightKeyImage = new Image(R.drawable.keypadright);
	
	private Sprite leftKeySprite;
	private Sprite rightKeySprite;	
	
	private boolean leftKeyPressed;
	private boolean rightKeyPressed;
	private final float x;
	private final float y;
	private GameModel gameModel;
	
	public KeyPadLayer(GameModel gameModel){
		this.gameModel = gameModel;
		this.x = constats.getWindowSize()[0];
		this.y = constats.getWindowSize()[1];
		leftKeySprite = new Sprite(leftKeyImage);
		rightKeySprite = new Sprite(rightKeyImage);
		
	}

	@Override
	public void update(float dt) {
		if(leftKeyPressed){
			gameModel.getCurrentPlayer().setSpeed(-10, 0);
		}
		if(rightKeyPressed){
			gameModel.getCurrentPlayer().setSpeed(10, 0);
		}
	}

	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		
		leftKeyImage.draw(canvas, x/9, y/10*9);
		rightKeyImage.draw(canvas, x/9+leftKeyImage.getWidth()+10, y/10*9);
	}
	@Override
	public boolean onTouchDown(MotionEvent event) {
		if(event.getX() <= x/9 + leftKeyImage.getWidth() && event.getX() >= x/9 - leftKeyImage.getWidth()){
			if(event.getY() <= y/10*9 + 15 && event.getY() >= y/10*9 - 15)
				leftKeyPressed = true;
		}
		if(event.getX() <= x/9 + 2*leftKeyImage.getWidth()&& event.getX() >= x/9 + leftKeyImage.getWidth()){
			if(event.getY() <= y/10*9 + 15 && event.getY() >= y/10*9 - 15)
				rightKeyPressed = true;
		}
		return true;
	}
	@Override
	public boolean onTouchMove(MotionEvent event) {
		if(!leftKeySprite.getBoundingBox().contains(event.getX(), event.getY())){
			System.out.println("left Key moved out");
			leftKeyPressed = false;
		}
		if(!rightKeySprite.getBoundingBox().contains(event.getX(), event.getY())){
			System.out.println("right key moved out");
			rightKeyPressed = false;
		}
		return true;
	}
	@Override
	public boolean onTouchUp(MotionEvent event) {
		leftKeyPressed = false;
		rightKeyPressed = false;
		return true;
	}
	
//	public boolean getLeftKeyPressed(){
//		return leftKeyPressed;
//	}
//	
//	public boolean getRightKeyPressed(){
//		return rightKeyPressed;
//	}

}
