package no.ntnu.folk.game.menus.menuStates;

import no.ntnu.folk.game.constants.GameTypes;
import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.gameplay.levels.controllers.LevelController;
import no.ntnu.folk.game.gameplay.models.GameModel;
import no.ntnu.folk.game.menus.MenuItem;
import no.ntnu.folk.game.menus.MenuOptions;
import no.ntnu.folk.game.states.GameState;
import no.ntnu.folk.game.states.MenuState;

/**
 * The option menu that will let you change options before the game starts
 *
 * @author Rune
 */
public class PreGameMenu extends MenuState {
	//OPTIONS
	private int playerCount;
	private int currentLevel;
	private int currentHealth;
	private int selectedGameType;
	private String[][] levels;
	// private GameModel gameModel; //TODO make a gameModel with the options set


	/**
	 * Make all the buttons to the menu
	 */
	@Override
	protected void addMenuItems() {
		// Initialize variables here as this method is called before the class is "made"
		playerCount = GameplayConstants.DEFAULT_PLAYER_COUNT;
		currentLevel = 0;
		currentHealth = GameplayConstants.DEFAULT_HEALTH;
		selectedGameType = 0;
		levels = LevelController.getLevels();

		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.START_GAME, position++),
				new MenuItem(MenuOptions.PLAYER_COUNT, position++, Integer.toString(GameplayConstants.DEFAULT_PLAYER_COUNT)),
				new MenuItem(MenuOptions.SELECT_MAP, position++, levels[currentLevel][0]),
				new MenuItem(MenuOptions.HEALTH, position++, Integer.toString(GameplayConstants.DEFAULT_HEALTH)),
				new MenuItem(MenuOptions.GAME_TYPE, position++, GameTypes.values()[0].toString()),
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
				getGame().pushState(new GameState(new GameModel(playerCount, currentHealth, Integer.parseInt(levels[currentLevel][1]), GameTypes.values()[selectedGameType]))); // FIXME TEMP!
				break;
			case PLAYER_COUNT:
				changePlayerCount();
				menuItem.setData(Integer.toString(playerCount));
				break;
			case SELECT_MAP:
				nextLevel();
				menuItem.setData(levels[currentLevel][0]);
				break;
			case HEALTH:
				increaseHealth();
				menuItem.setData(Integer.toString(currentHealth));
				break;
			case GAME_TYPE:
				selectGameType();
				menuItem.setData(GameTypes.values()[selectedGameType].toString());
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
		if (selectedGameType == GameTypes.values().length - 1) selectedGameType = 0;
		else selectedGameType++;
	}

	/**
	 * Increase start-health with 100 up to a max given in Constants
	 */
	private void increaseHealth() {
		if (currentHealth == GameplayConstants.MAX_HEALTH) currentHealth = 100;
		else currentHealth += 100;
	}

	/**
	 * Circles throught the available levels
	 */
	private void nextLevel() {
		if (currentLevel == levels.length - 1) currentLevel = 0;
		else currentLevel++;
	}

	/**
	 * Changes the player count to a max given in Constants
	 */
	private void changePlayerCount() {
		if (playerCount == GameplayConstants.MAX_PLAYERS) {
			playerCount = 2;
		} else {
			playerCount++;
		}
	}

}
