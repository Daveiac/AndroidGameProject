package no.ntnu.folk.game.states;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import no.ntnu.folk.game.Program;
import no.ntnu.folk.game.constants.ProgramConstants;
import sheep.game.State;
import sheep.graphics.Font;

import static android.graphics.Color.BLACK;

public class LevelEditor extends State implements View.OnTouchListener {

	public void draw(Canvas canvas) {
		if (canvas == null) return;
		canvas.drawColor(BLACK);

		canvas.drawText(getClass().getSimpleName(), ProgramConstants.getWindowSize()[0] / 2, ProgramConstants.getWindowSize()[1] / 28, Font.WHITE_SANS_BOLD_20);  // Temp
	}

	public void update(float dt) {
		Program.getView().setOnTouchListener(this); // FIXME
		// Why I did this: When a state is popped, the OnTouchListener needs to be updated.
		// Unfortunately I could not find a better way to to do it
	}

	// Might need to be extended to support more complex input
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		int maskedAction = event.getActionMasked();
		switch (maskedAction) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				onTouchDown(event);
				break;
			case MotionEvent.ACTION_MOVE:
				onTouchMove(event);
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
			case MotionEvent.ACTION_CANCEL:
				onTouchUp(event);
				break;
			default:
				break;
		}
		return false;
	}

	public boolean onTouchDown(MotionEvent event) {
		// TODO
		return super.onTouchDown(event);
	}
	public boolean onTouchMove(MotionEvent event) {
		// TODO
		return super.onTouchMove(event);
	}
	public boolean onTouchUp(MotionEvent event) {
		// TODO
		return super.onTouchUp(event);
	}
}
