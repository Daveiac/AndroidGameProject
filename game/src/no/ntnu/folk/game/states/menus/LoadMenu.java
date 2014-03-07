package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class LoadMenu extends MenuState {
	// TODO

	@Override
	protected void addMenuItems() {
		menuItems = new MenuItem[2];
		menuItems[0] = new MenuItem(Constants.LOAD_GAME,"Load Game", 0, 100, 0, 100);
		menuItems[1] = new MenuItem(Constants.BACK,"Back", 100, 200, 0, 100);
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getState()) {
			case Constants.LOAD_GAME:  // TODO
				getGame().pushState(new GameState());
				break;
			case Constants.BACK:
				getGame().popState();
				break;
			default:
				break;
		}
	}

}
