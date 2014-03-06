package no.ntnu.folk.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import no.ntnu.folk.game.states.menus.MainMenu;
import sheep.game.Game;

public class Program extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Game game = new Game(this, null);

		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

		Constants.setWindowSize(new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels});

		game.pushState(new MainMenu());
		setContentView(game);
	}

}
