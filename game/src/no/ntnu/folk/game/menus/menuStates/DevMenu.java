package no.ntnu.folk.game.menus.menuStates;

import no.ntnu.folk.game.constants.Globals;
import no.ntnu.folk.game.menus.MenuItem;
import no.ntnu.folk.game.menus.MenuOptions;
import no.ntnu.folk.game.states.MenuState;

public class DevMenu extends MenuState {

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.TOGGLE_DEBUG, position++, Boolean.toString(Globals.isDebugging())),
				new MenuItem(MenuOptions.BACK, position++),
		};
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case TOGGLE_DEBUG:
				Globals.toggleDebugging();
				menuItem.setData(Boolean.toString(Globals.isDebugging()));
				break;
			case BACK:
				getGame().popState();
				break;
			default:
				break;
		}
	}

}
