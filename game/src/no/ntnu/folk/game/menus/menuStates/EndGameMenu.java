package no.ntnu.folk.game.menus.menuStates;

import no.ntnu.folk.game.menus.MenuItem;
import no.ntnu.folk.game.menus.MenuOptions;
import no.ntnu.folk.game.states.MenuState;

public class EndGameMenu extends MenuState {
	// TODO

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.MAIN_MENU, position++),
		};
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case MAIN_MENU:
				getGame().popState();
				break;
			default:
				break;
		}
	}

}
