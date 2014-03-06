package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class MainMenu extends MenuState {
	// TODO

	@Override
	protected void addMenuItems() {
		menuItems = new MenuItem[1];
		menuItems[0] = new MenuItem("Start game", 0, 100, 0, 100);
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		if (menuItem == menuItems[0]) {
			getGame().pushState(new GameState());
		}
	}
}
