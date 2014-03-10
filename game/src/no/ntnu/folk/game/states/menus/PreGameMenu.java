package no.ntnu.folk.game.states.menus;

import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

/**
 * The option menu that will let you change options before the game starts
 * 
 * @author Rune
 *
 */
public class PreGameMenu extends MenuState {
	//OPTIONS
	private int playerCount;;
	private int currentLevel;
	private int currentHealth;
	private int selectedGameType;
	// private GameModel gameModel; //TODO make a gameModel with the options set

	
	/**
	 * Make all the buttons to the menu
	 */
	@Override
	protected void addMenuItems() {
		// Initialize variables here as this method is called before the class is "made"
		playerCount = Constants.DEFAULT_PLAYER_COUNT;
		currentLevel = 0;
		currentHealth = Constants.DEFAULT_HEALTH;
		selectedGameType = 0;
		
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.START_GAME, position++),
				new MenuItem(MenuOptions.PLAYER_COUNT, position++, Integer.toString(Constants.DEFAULT_PLAYER_COUNT)),
				new MenuItem(MenuOptions.SELECT_MAP, position++,Constants.LEVEL_LIST[currentLevel]),
				new MenuItem(MenuOptions.HEALTH, position++,Integer.toString(Constants.DEFAULT_HEALTH)),
				new MenuItem(MenuOptions.TEAMS, position++, Constants.GAME_TYPE[0]),
				new MenuItem(MenuOptions.BACK, position++),
		};
	}
	
	/**
	 * What should happen when buttons get clicked
	 */
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

	/**
	 * Just goes through the different game states defined in Constants
	 */
	private void selectGameType() {
		if(selectedGameType == Constants.GAME_TYPE.length-1) selectedGameType = 0;
		else selectedGameType++;
	}
	
	/**
	 * Increase start-health with 100 up to a max given in Constants
	 */
	private void increaseHealth() {
		if(currentHealth == Constants.MAX_HEALTH) currentHealth = 100;
		else currentHealth +=100;
	}
	
	/**
	 * Circles throught the available levels
	 */
	private void nextLevel() {
		if(currentLevel == Constants.LEVEL_LIST.length-1) currentLevel = 0;
		else currentLevel++;
	}
	
	/**
	 * Changes the player count to a max given in Constants
	 */
	private void changePlayerCount() {
		if (playerCount == Constants.MAX_PLAYERS) {
			playerCount = 2;
		} else {
			playerCount++;
		}
	}

}
