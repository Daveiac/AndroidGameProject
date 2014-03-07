package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class MainMenu extends MenuState {

	// TODO

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.START_GAME, position++),
				new MenuItem(MenuOptions.PRE_GAME_MENU, position++),
				new MenuItem(MenuOptions.LOAD_MENU, position++),
		};
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
