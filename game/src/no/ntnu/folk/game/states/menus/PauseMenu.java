package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.states.MenuState;

public class PauseMenu extends MenuState {
	// TODO

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.START_GAME, position++),
		};
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
