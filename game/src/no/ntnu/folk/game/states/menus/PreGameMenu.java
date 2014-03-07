package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class PreGameMenu extends MenuState {
	//OPTIONS
	private int playerCount = Constants.DEFAULT_PLAYER_COUNT;

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.START_GAME, position++),
				new MenuItem(MenuOptions.PLAYER_COUNT, position++, Integer.toString(Constants.DEFAULT_PLAYER_COUNT)),
				new MenuItem(MenuOptions.BACK, position++),
		};
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case START_GAME:
				getGame().pushState(new GameState());
				break;
			case PLAYER_COUNT:
				changePlayerCount();
				menuItem.setData(Integer.toString(playerCount));
				break;
			case BACK:
				getGame().popState();
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
