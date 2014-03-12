package no.ntnu.folk.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import no.ntnu.folk.game.constants.Globals;
import no.ntnu.folk.game.menus.menuStates.MainMenu;
import sheep.game.Game;

public class Program extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Remove window title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// Set window size in Constants
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		Globals.setWindowSize(new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels});
		// Set resources in Constants
		Globals.setResources(getResources());
		// Start game
		Game game = new Game(this, null);
		game.pushState(new MainMenu());
		setContentView(game);
	}

}
