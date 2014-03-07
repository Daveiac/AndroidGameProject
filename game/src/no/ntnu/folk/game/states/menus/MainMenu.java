package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class MainMenu extends MenuState {
	public static final int START_GAME = 0;
	public static final int PRE_GAME_MENU = 1;
	public static final int LOAD_MENU = 2;

	// TODO

	@Override
	protected void addMenuItems() {
		menuItems = new MenuItem[3];
		menuItems[0] = new MenuItem(START_GAME, 0, 100, 0, 100);
		menuItems[1] = new MenuItem(PRE_GAME_MENU, 100, 200, 0, 100);
		menuItems[2] = new MenuItem(LOAD_MENU, 200, 300, 0, 100);
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getLabel()) {
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
