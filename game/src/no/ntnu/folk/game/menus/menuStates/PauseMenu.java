package no.ntnu.folk.game.menus.menuStates;

import no.ntnu.folk.game.menus.MenuItem;
import no.ntnu.folk.game.menus.MenuOptions;
import no.ntnu.folk.game.states.MenuState;

public class PauseMenu extends MenuState {
	// TODO

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.SAVE_MENU, position++),
				new MenuItem(MenuOptions.DEV_MENU, position++),
				new MenuItem(MenuOptions.MAIN_MENU, position++),
				new MenuItem(MenuOptions.RETURN_TO_GAME, position++),
		};
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case SAVE_MENU:
				getGame().pushState(new SaveMenu());
				break;
			case DEV_MENU:
				getGame().pushState(new DevMenu());
				break;
			case MAIN_MENU:
				getGame().popState(3);
				break;
			case RETURN_TO_GAME:
				getGame().popState();
				break;
			default:
				break;
		}
	}

}
