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
	private static Game game;

	/**
	 * @return The activity's view
	 */
	public static View getView() {
		return view;
	}

	/**
	 * @return The game instance
	 */
	public static Game getGame() {
		return game;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		storeWindowSize();
		initializeGame();
	}

	/**
	 * Saves the windowSize to ProgramConstants
	 */
	private void storeWindowSize() {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		ProgramConstants.setWindowSize(new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels});
	}

	/**
	 * Initialize the game and set the view
	 */
	private void initializeGame() {
		game = new Game(this, null);
		view = game.getRootView();
		game.pushState(new MainMenu());
		setContentView(game);
	}

	@Override
	public void finish() {
		super.finish();
		if (isFinishing()) {
			System.exit(0);
		}
	}

}
