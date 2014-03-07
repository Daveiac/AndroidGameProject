package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class PreGameMenu extends MenuState {
	//OPTIONS
	private int playerCount = 1;
	// TODO

	@Override
	protected void addMenuItems() {
		final int x = Constants.getWindowSize()[0];
		final int y = Constants.getWindowSize()[1];
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.START_GAME, x / 2 - x / 4, x / 2 + x / 4, 100, 200),
				new MenuItem(MenuOptions.BACK, x / 2 - x / 4, x / 2 + x / 4, 200, 300),
				new MenuItem(MenuOptions.PLAYER_COUNT, x / 2 - x / 4, x / 2 + x / 4, 300, 400),
		};
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case START_GAME:
				getGame().pushState(new GameState());
				break;
			case BACK:
				getGame().popState();
				break;
			case PLAYER_COUNT:
				menuItem.setData(Integer.toString(playerCount));
				break;
			default:
				break;
		}
	}

	private void changePlayerCount() {
		if (playerCount == Constants.MAX_PLAYERS) {
			playerCount = 1;
		} else {
			playerCount++;
		}
	}

}
