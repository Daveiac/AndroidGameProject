package no.ntnu.folk.game.menus.menuStates;

import no.ntnu.folk.game.menus.MenuItem;
import no.ntnu.folk.game.menus.MenuOptions;
import no.ntnu.folk.game.states.MenuState;

/**
 * The pause menu. It contains the options to accessing the developer menu, returning to main menu or continuing the current game.
 *
 */
public class PauseMenu extends MenuState {
	public PauseMenu() {
		super("Pause");
	}
	// TODO

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.DEV_MENU, position++),
				new MenuItem(MenuOptions.MAIN_MENU, position++),
				new MenuItem(MenuOptions.RETURN_TO_GAME, position++),
		};
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case DEV_MENU:
				getGame().pushState(new DevMenu());
				break;
			case MAIN_MENU:
				getGame().popState(3);
				break;
			case RETURN_TO_GAME:
				getGame().popState();
				break;
			default:
				break;
		}
	}

}
