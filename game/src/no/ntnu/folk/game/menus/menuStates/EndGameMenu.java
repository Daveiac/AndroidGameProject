package no.ntnu.folk.game.menus.menuStates;

import android.graphics.Canvas;
import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.menus.MenuItem;
import no.ntnu.folk.game.menus.MenuOptions;
import no.ntnu.folk.game.states.MenuState;
import sheep.graphics.Font;

/**
 * The end game menu. It represents the winner of the game together with the time played and the option to return to main menu.
 *
 */
public class EndGameMenu extends MenuState {
	private float gameTime;
	private String winnerText;

	/**
	 * The end game menu constructor.
	 * @param gameTime		The game time at the end of the game.
	 * @param winnerText	The winner text containing who won the game.
	 */
	public EndGameMenu(float gameTime, String winnerText) {
		super("Game over");
		this.gameTime = gameTime;
		this.winnerText = winnerText;
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		canvas.drawText("Game time: " + gameTime, ProgramConstants.getWindowSize()[0] / 2, ProgramConstants.getWindowSize()[1] * 2 / 28, Font.WHITE_SANS_BOLD_20);
		canvas.drawText(winnerText, ProgramConstants.getWindowSize()[0] / 2, ProgramConstants.getWindowSize()[1] / 2, Font.WHITE_SANS_BOLD_20);
	}

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.MAIN_MENU, position++),
		};
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case MAIN_MENU:
				getGame().popState(3);
				break;
			default:
				break;
		}
	}

}
