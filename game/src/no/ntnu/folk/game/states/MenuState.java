package no.ntnu.folk.game.states;

import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.State;

public abstract class MenuState extends State {

	protected abstract void clickMenuItem( /* TODO parameters */);

	@Override
	public void update(float dt) {
		super.update(dt);    // TODO
	}
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);    // TODO
	}
	@Override
	public boolean onTouchDown(MotionEvent event) {
		return super.onTouchDown(event);    // TODO
	}
	@Override
	public boolean onTouchMove(MotionEvent event) {
		return super.onTouchMove(event);    // TODO
	}
	@Override
	public boolean onTouchUp(MotionEvent event) {
		return super.onTouchUp(event);    // TODO
	}

}
