package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class PreGameMenu extends MenuState {
	//OPTIONS
	private int playerCount = Constants.DEFAULT_PLAYER_COUNT;
	private String[] levelList = {"1","2"}; //TODO import levels 
	private int currentLevel = 0;
	private int currentHealth = Constants.DEFAULT_HEALTH;

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.START_GAME, position++),
				new MenuItem(MenuOptions.PLAYER_COUNT, position++, Integer.toString(playerCount)),
				new MenuItem(MenuOptions.SELECT_MAP, position++,levelList[currentLevel]),
				new MenuItem(MenuOptions.HEALTH, position++,Integer.toString(currentHealth)),
				new MenuItem(MenuOptions.TEAMS, position++),
				new MenuItem(MenuOptions.BACK, position++),
		};
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case START_GAME:
				getGame().pushState(new GameState(playerCount));
				break;
			case PLAYER_COUNT:
				changePlayerCount();
				menuItem.setData(Integer.toString(playerCount));
				break;
			case SELECT_MAP:
				nextLevel();
				menuItem.setData(levelList[currentLevel]);
				break;
			case HEALTH:
				increaseHealth();
				menuItem.setData(Integer.toString(currentHealth));
			case BACK:
				getGame().popState();
				break;
			default:
				break;
		}
	}

	private void increaseHealth() {
		if(currentHealth == Constants.MAX_HEALTH) currentHealth = 100;
		else currentHealth +=100;
	}
	private void nextLevel() {
		if(currentLevel == levelList.length-1) currentLevel = 0;
		else currentLevel++;
	}
	private void changePlayerCount() {
		if (playerCount == Constants.MAX_PLAYERS) {
			playerCount = Constants.DEFAULT_PLAYER_COUNT;
		} else {
			playerCount++;
		}
	}

}
