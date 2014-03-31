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

/**
 * The menu state. It is a controller for the menu interactions and do the required actions.
 *
 */
public abstract class MenuState extends State implements View.OnTouchListener {
	protected MenuItem[] menuItems;

	private MenuItem menuItemTouched = null;
	private String name;

	/**
	 * The menu state constructor.
	 * @param name
	 */
	protected MenuState(String name) {
		this.name = name;
		Program.getView().setOnTouchListener(this);
		addMenuItems();
	}

	/**
	 * Adds the content of the menu.
	 */
	protected abstract void addMenuItems();

	/**
	 * Does the required actions depending on the item clicked.
	 * @param menuItem The item menu which is clicked.
	 */
	protected abstract void clickMenuItem(MenuItem menuItem);

	/**
	 * Returns the menu item given by the coordinates of the MotionEvent.
	 * @param event The MotionEvent of which gives the coordinates of the chosen menu item.
	 * @return Returns the chosen menu item.
	 */
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

		canvas.drawText(name, ProgramConstants.getWindowSize()[0] / 2, ProgramConstants.getWindowSize()[1] / 28, Font.WHITE_SANS_BOLD_20);  // Temp

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
