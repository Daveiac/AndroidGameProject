package no.ntnu.folk.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.menus.menuStates.MainMenu;
import sheep.game.Game;

public class Program extends Activity {
	private static View view;

	public static View getView() {
		return view;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		ProgramConstants.setWindowSize(new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels});

		Game game = new Game(this, null);
		view = game.getRootView();
		game.pushState(new MainMenu());
		setContentView(game);
	}

}
