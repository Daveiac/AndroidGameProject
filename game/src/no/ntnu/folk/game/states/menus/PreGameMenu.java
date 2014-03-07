package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class PreGameMenu extends MenuState {
	// TODO

	@Override
	protected void addMenuItems() {
		menuItems = new MenuItem[2];
		menuItems[0] = new MenuItem(MenuOptions.START_GAME, 0, 100, 0, 100);
		menuItems[1] = new MenuItem(MenuOptions.BACK, 100, 200, 0, 100);
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case START_GAME:
				getGame().pushState(new GameState());
				break;
			case BACK:
				getGame().popState();
				break;
			default:
				break;
		}
	}

}
