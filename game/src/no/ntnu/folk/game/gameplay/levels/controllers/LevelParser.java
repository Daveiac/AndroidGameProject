package no.ntnu.folk.game.gameplay.levels.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import no.ntnu.folk.game.gameplay.levels.views.LevelToken;
import no.ntnu.folk.game.gameplay.levels.views.TokenFactory;
import no.ntnu.folk.game.gameplay.models.LevelModel;
import sheep.game.Game;

/**
 * Level parser. This class parses the level corresponding the level model and creates the necessary tokens.
 *
 */
public class LevelParser {
	
	/**
	 * A level parser that parses the level corresponding the level model and creates the necessary tokens.
	 * @param lvlModel The level model of the current level.
	 */
	public static void parseLevel(LevelModel lvlModel) {
		InputStream is = Game.getInstance().getResources().openRawResource(lvlModel.getLevel());
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		int y = -1;
		try {
			LevelToken[][] grid = null;
			line = br.readLine();
			while (line != null) {
				if (line.startsWith("Size:")) {
					String[] sizeString = line.substring(5).split("x");
					int[] size = {Integer.parseInt(sizeString[0]), Integer.parseInt(sizeString[1])};
					lvlModel.setSize(size[0], size[1]);
					grid = new LevelToken[size[1]][size[0]];
					lvlModel.setGrid(grid);
				} else if (line.length() != 0 && !line.startsWith("#")) {
					y++;
					for (int x = 0; x < line.length(); x++) {
						if (line.charAt(x) == 's') {
							lvlModel.addStartPosition(x, y);
						} else {
							LevelToken levelToken = TokenFactory.createToken(line.charAt(x), x, y);
							grid[y][x] = levelToken;
							lvlModel.getLevelTokens().add(levelToken);
						}
					}
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
