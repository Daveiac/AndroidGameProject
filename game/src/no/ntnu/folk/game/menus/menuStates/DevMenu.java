package no.ntnu.folk.game.menus.menuStates;

import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.menus.MenuItem;
import no.ntnu.folk.game.menus.MenuOptions;
import no.ntnu.folk.game.states.LevelEditor;
import no.ntnu.folk.game.states.MenuState;

public class DevMenu extends MenuState {

	@Override
	protected void addMenuItems() {
		int position = 0;
		menuItems = new MenuItem[]{
				new MenuItem(MenuOptions.TOGGLE_DEBUG, position++, Boolean.toString(ProgramConstants.isDebugging())),
				new MenuItem(MenuOptions.LEVEL_EDITOR, position++),
				new MenuItem(MenuOptions.BACK, position++),
		};
	}
	@Override
	protected void clickMenuItem(MenuItem menuItem) {
		switch (menuItem.getOption()) {
			case TOGGLE_DEBUG:
				ProgramConstants.toggleDebugging();
				menuItem.setData(Boolean.toString(ProgramConstants.isDebugging()));
				break;
			case LEVEL_EDITOR:
				getGame().pushState(new LevelEditor());
				break;
			case BACK:
				getGame().popState();
				break;
			default:
				break;
		}
	}

}
