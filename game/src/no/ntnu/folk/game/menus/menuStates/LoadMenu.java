package no.ntnu.folk.game.menus.menuStates;

import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.menus.MenuItem;
import no.ntnu.folk.game.menus.MenuOptions;
import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class LoadMenu extends MenuState {
	// TODO

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.LOAD_GAME, position++),
				new MenuItem(MenuOptions.BACK, position++),
		};
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case LOAD_GAME:  // TODO
				//getGame().pushState(new GameState(Constants.DEFAULT_PLAYER_COUNT)); TODO must load game, can't just make a new game
				break;
			case BACK:
				getGame().popState();
				break;
			default:
				break;
		}
	}

}
