package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class PauseMenu extends MenuState {
	// TODO

	@Override
	protected void addMenuItems() {
		menuItems = new MenuItem[1];
		menuItems[0] = new MenuItem(MenuOptions.START_GAME, 0, 100, 0, 100);
		// TODO
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
		case START_GAME:
			getGame().popState();
			break;
		default:
			break;
		}
	}
}
