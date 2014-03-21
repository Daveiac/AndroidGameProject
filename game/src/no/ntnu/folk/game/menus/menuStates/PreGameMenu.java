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
	private String[][] levels;
	private GameModel gameModel;
	private int currentLevel;

	/**
	 * Make all the buttons to the menu
	 */
	@Override
	protected void addMenuItems() {
		gameModel = new GameModel();
		levels = LevelController.getLevels();
		currentLevel = 0;
		gameModel.setLevel(0, levels[currentLevel][0]);

		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.START_GAME, position++),
				new MenuItem(MenuOptions.PLAYER_COUNT, position++, Integer.toString(gameModel.getPlayerCount()) + "players"),
				new MenuItem(MenuOptions.SELECT_MAP, position++, gameModel.getLevelName()),
				new MenuItem(MenuOptions.HEALTH, position++, Integer.toString(gameModel.getStartHealth()) + "HP"),
                new MenuItem(MenuOptions.TURN_TIMER,position++, Integer.toString(gameModel.getTurnTimer()) + "seconds"),
				new MenuItem(MenuOptions.GAME_TYPE, position++, gameModel.getGameType().toString()),
				new MenuItem(MenuOptions.BACK, position++)
		};
	}

	/**
	 * What should happen when buttons get clicked
	 */
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case START_GAME:
				getGame().pushState(new GameState(gameModel));
				break;
			case PLAYER_COUNT:
				changePlayerCount();
				menuItem.setData(Integer.toString(gameModel.getPlayerCount()) + "players");
				break;
			case SELECT_MAP:
				nextLevel();
				menuItem.setData(levels[currentLevel][0]);
				break;
			case HEALTH:
				increaseHealth();
				menuItem.setData(Integer.toString(gameModel.getStartHealth()) + "HP");
				break;
            case TURN_TIMER:
                increaseTurnTimer();
                menuItem.setData((Integer.toString(gameModel.getTurnTimer()) + "seconds"));
                break;
			case GAME_TYPE:
				selectGameType();
				menuItem.setData(gameModel.getGameType().toString());
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
		int current = gameModel.getGameType().ordinal();
		if (current == GameTypes.values().length - 1) current = 0;
		else current++;
		gameModel.setGameType(GameTypes.values()[current]);
	}

	/**
	 * Increase start-health with 100 up to a max given in Constants
	 */
	private void increaseHealth() {
		if (gameModel.getStartHealth() == GameplayConstants.MAX_HEALTH) {
			gameModel.setStartHealth(GameplayConstants.MIN_HEALTH);
		} else {
			gameModel.setStartHealth(gameModel.getStartHealth() + 50);
		}
	}

    private void increaseTurnTimer(){
        if(gameModel.getTurnTimer() == GameplayConstants.MAX_TURN_TIMER){
            gameModel.setTurnTimer(GameplayConstants.MIN_TURN_TIMER);
        }
        else{
            gameModel.setTurnTimer(gameModel.getTurnTimer() + 5);
        }
    }

	/**
	 * Circles through the available levels
	 */
	private void nextLevel() {
		if (currentLevel == levels.length - 1) currentLevel = 0;
		else currentLevel++;
		gameModel.setLevel(currentLevel, levels[currentLevel][0]);
	}

	/**
	 * Changes the player count to a max given in Constants
	 */
	private void changePlayerCount() {
		if (gameModel.getPlayerCount() == GameplayConstants.MAX_PLAYERS) {
			gameModel.setPlayerCount(2);
		} else {
			gameModel.setPlayerCount(gameModel.getPlayerCount() + 1);
		}
	}

}
