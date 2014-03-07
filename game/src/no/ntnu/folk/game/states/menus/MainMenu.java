package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class MainMenu extends MenuState {

	// TODO

	@Override
	protected void addMenuItems() {
		menuItems = new MenuItem[3];
		menuItems[0] = new MenuItem(Constants.START_GAME,"Start game","", 0, 100, 0, 100);
		menuItems[1] = new MenuItem(Constants.PRE_GAME_MENU,"Options","", 100, 200, 0, 100);
		menuItems[2] = new MenuItem(Constants.LOAD_MENU,"Load menu","", 200, 300, 0, 100);
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getState()) {
			case Constants.START_GAME:
				getGame().pushState(new GameState());
				break;
			case Constants.PRE_GAME_MENU:
				getGame().pushState(new PreGameMenu());
				break;
			case Constants.LOAD_MENU:
				getGame().pushState(new LoadMenu());
				break;
			default:
				break;
		}
	}

}
