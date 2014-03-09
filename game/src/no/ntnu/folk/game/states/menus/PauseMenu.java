package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.states.MenuState;

public class PauseMenu extends MenuState {
	// TODO

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.SAVE_MENU, position++),
				new MenuItem(MenuOptions.RETURN_TO_GAME, position++),
				new MenuItem(MenuOptions.MAIN_MENU, position++),
		};
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case SAVE_MENU:
				getGame().pushState(new SaveMenu());
				break;
			case RETURN_TO_GAME:
				getGame().popState();
				break;
			case MAIN_MENU:
				getGame().popState(2);
			default:
				break;
		}
	}

}
