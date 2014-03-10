package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

public class PreGameMenu extends MenuState {
	//OPTIONS
	private int playerCount;;
	private int currentLevel;
	private int currentHealth;
	private int selectedGameType;

	@Override
	protected void addMenuItems() {
		int position = 0;
		currentLevel = 0;
		currentHealth = Constants.DEFAULT_HEALTH;
		selectedGameType = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.START_GAME, position++),
				new MenuItem(MenuOptions.PLAYER_COUNT, position++, Integer.toString(Constants.DEFAULT_PLAYER_COUNT)),
				new MenuItem(MenuOptions.SELECT_MAP, position++,Constants.LEVEL_LIST[currentLevel]),
				new MenuItem(MenuOptions.HEALTH, position++,Integer.toString(Constants.DEFAULT_HEALTH)),
				new MenuItem(MenuOptions.TEAMS, position++, Constants.GAME_TYPE[0]),
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
				menuItem.setData(Constants.LEVEL_LIST[currentLevel]);
				break;
			case HEALTH:
				increaseHealth();
				menuItem.setData(Integer.toString(currentHealth));
				break;
			case TEAMS:
				selectGameType();
				menuItem.setData(Constants.GAME_TYPE[selectedGameType]);
				break;
			case BACK:
				getGame().popState();
				break;
			default:
				break;
		}
	}

	private void selectGameType() {
		if(selectedGameType == Constants.GAME_TYPE.length-1) selectedGameType = 0;
		else selectedGameType++;
	}
	private void increaseHealth() {
		if(currentHealth == Constants.MAX_HEALTH) currentHealth = 100;
		else currentHealth +=100;
	}
	private void nextLevel() {
		if(currentLevel == Constants.LEVEL_LIST.length-1) currentLevel = 0;
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
