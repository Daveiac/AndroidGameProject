package no.ntnu.folk.game.menus.menuStates;

import no.ntnu.folk.game.menus.MenuItem;
import no.ntnu.folk.game.menus.MenuOptions;
import no.ntnu.folk.game.states.MenuState;

public class SaveMenu extends MenuState {
	// TODO

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.SAVE_GAME, position++),
				new MenuItem(MenuOptions.BACK, position++),
		};
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case SAVE_GAME:
				// TODO
				break;
			case BACK:
				getGame().popState();
				break;
			default:
				break;
		}
	}

}
