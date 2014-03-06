package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class PreGameMenu extends MenuState {
	public static final String START_GAME = "Start game";
	public static final String BACK = "Back";
	// TODO

	@Override
	protected void addMenuItems() {
		menuItems = new MenuItem[2];
		menuItems[0] = new MenuItem(START_GAME, 0, 100, 0, 100);
		menuItems[1] = new MenuItem(BACK, 100, 200, 0, 100);
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getLabel()) {
			case START_GAME:
				getGame().pushState(new GameState());
				break;
			case BACK:
				getGame().popState();
				break;
			default:
				break;
		}
	}

}
