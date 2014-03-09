package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.states.MenuState;

public class DevMenu extends MenuState {

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.TOGGLE_DEBUG, position++, Boolean.toString(Constants.isDebugging())),
				new MenuItem(MenuOptions.BACK, position++),
		};
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case TOGGLE_DEBUG:
				Constants.toggleDebugging();
				menuItem.setData(Boolean.toString(Constants.isDebugging()));
				break;
			case BACK:
				getGame().popState();
				break;
			default:
				break;
		}
	}

}
