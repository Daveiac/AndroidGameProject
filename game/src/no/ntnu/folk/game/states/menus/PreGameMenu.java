package no.ntnu.folk.game.states.menus;

import android.graphics.Canvas;
import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class PreGameMenu extends MenuState {
	private final int PLAYER_COUNT = 100;
	
	//OPTIONS
	private int playerCount = 1;
	// TODO

	@Override
	protected void addMenuItems() {
		final int x = Constants.getWindowSize()[0];
		final int y = Constants.getWindowSize()[1];
		menuItems = new MenuItem[3];
		menuItems[0] = new MenuItem(Constants.START_GAME,"Start game","", x/2-x/4, x/2+x/4, 100, 200);
		menuItems[1] = new MenuItem(Constants.BACK,"Back","", x/2-x/4, x/2+x/4, 200, 300);
		menuItems[2] = new MenuItem(PLAYER_COUNT, "Add player","",x/2-x/4,x/2+x/4,300,400);
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getState()) {
			case Constants.START_GAME:
				getGame().pushState(new GameState());
				break;
			case Constants.BACK:
				getGame().popState();
				break;
			case PLAYER_COUNT:
				menuItem.setText(Integer.toString(playerCount));
				break;
			default:
				break;
		}
	}
	
	private void changePlayerCount() {
		if(playerCount == Constants.MAX_PLAYERS) playerCount = 1;
		else playerCount++;
	}

}
