package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class MainMenu extends MenuState {

	// TODO

	@Override
	protected void addMenuItems() {
		menuItems = new MenuItem[3];
		menuItems[0] = new MenuItem(MenuOptions.START_GAME, 0, 100, 0, 100);
		menuItems[1] = new MenuItem(MenuOptions.PRE_GAME_MENU, 100, 200, 0, 100);
		menuItems[2] = new MenuItem(MenuOptions.LOAD_MENU, 200, 300, 0, 100);
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case START_GAME:
				getGame().pushState(new GameState());
				break;
			case PRE_GAME_MENU:
				getGame().pushState(new PreGameMenu());
				break;
			case LOAD_MENU:
				getGame().pushState(new LoadMenu());
				break;
			default:
				break;
		}
	}

}
