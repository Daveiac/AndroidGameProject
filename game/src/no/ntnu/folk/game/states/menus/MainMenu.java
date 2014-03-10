package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.states.MenuState;

public class MainMenu extends MenuState {

	// TODO

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.PRE_GAME_MENU, position++),
				new MenuItem(MenuOptions.LOAD_MENU, position++),
				new MenuItem(MenuOptions.DEV_MENU, position++),
		};
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case PRE_GAME_MENU:
				getGame().pushState(new PreGameMenu());
				break;
			case LOAD_MENU:
				getGame().pushState(new LoadMenu());
				break;
			case DEV_MENU:
				getGame().pushState(new DevMenu());
				break;
			default:
				break;
		}
	}

}
