package no.ntnu.folk.game.states;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import no.ntnu.folk.game.Program;
import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.menus.MenuItem;
import sheep.game.State;
import sheep.graphics.Font;

import static android.graphics.Color.BLACK;

public abstract class MenuState extends State implements View.OnTouchListener {
	protected MenuItem[] menuItems;

	private MenuItem menuItemTouched = null;

	protected MenuState() {
		Program.getView().setOnTouchListener(this);
		addMenuItems();
	}

	protected abstract void addMenuItems();

	protected abstract void clickMenuItem(MenuItem menuItem);

	private MenuItem getMenuItemAt(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		for (MenuItem menuItem : menuItems) {
			if (menuItem.contains(x, y)) {
				return menuItem;
			}
		}
		return null;
	}

	@Override
	public void update(float dt) {
		super.update(dt); // Do nothing
		Program.getView().setOnTouchListener(this); // FIXME
		// Why I did this: When a state is popped, the OnTouchListener needs to be updated.
		// Unfortunately I could not find a better way to to do it
	}

	@Override
	public void draw(Canvas canvas) {
		if (canvas == null) return;
		canvas.drawColor(BLACK);

		canvas.drawText(getClass().getSimpleName(), ProgramConstants.getWindowSize()[0] / 2, ProgramConstants.getWindowSize()[1] / 28, Font.WHITE_SANS_BOLD_20);  // Temp

		for (MenuItem menuItem : menuItems) {
			menuItem.draw(canvas);
		}
	}

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
		menuItemTouched = getMenuItemAt(event);
		return super.onTouchDown(event);
	}
	public boolean onTouchMove(MotionEvent event) {
		return super.onTouchMove(event);
	}
	public boolean onTouchUp(MotionEvent event) {
		if (menuItemTouched != null && menuItemTouched.equals(getMenuItemAt(event))) {
			clickMenuItem(menuItemTouched);
		}
		menuItemTouched = null;
		return super.onTouchUp(event);
	}

}
