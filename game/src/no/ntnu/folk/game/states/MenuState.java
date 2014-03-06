package no.ntnu.folk.game.states;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.states.menus.MenuItem;
import sheep.game.State;

import static android.graphics.Color.BLACK;

public abstract class MenuState extends State {
	protected MenuItem[] menuItems;

	private MenuItem menuItemTouched = null;

	protected MenuState() {
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
	}
	@Override
	public void draw(Canvas canvas) {
		if (canvas == null) return;
		canvas.drawColor(BLACK);

		for (MenuItem menuItem : menuItems) {
			menuItem.draw(canvas);
		}
	}

	@Override
	public boolean onTouchDown(MotionEvent event) {
		menuItemTouched = getMenuItemAt(event);
		return super.onTouchDown(event);
	}
	@Override
	public boolean onTouchMove(MotionEvent event) {
		return super.onTouchMove(event);
	}
	@Override
	public boolean onTouchUp(MotionEvent event) {
		if (menuItemTouched != null && menuItemTouched.equals(getMenuItemAt(event))) {
			clickMenuItem(menuItemTouched);
		}
		menuItemTouched = null;
		return super.onTouchUp(event);
	}

}
