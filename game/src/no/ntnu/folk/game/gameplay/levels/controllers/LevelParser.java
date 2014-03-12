package no.ntnu.folk.game.gameplay.levels.controllers;


import no.ntnu.folk.game.gameplay.levels.views.LevelToken;
import no.ntnu.folk.game.gameplay.levels.views.TokenFactory;
import no.ntnu.folk.game.gameplay.models.LevelModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LevelParser {

	public static void parseLevel(LevelModel lm) {

		String path = "res/levels/" + lm.getLevel() + ".level";

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			String line = br.readLine();
			while (line != null) {
				if (line.length() != 0 || line.startsWith("#")); {
					String[] token = line.split("[(),]");
					String tokenName = token[0];

					// if an instance
					if (tokenName.equals("Size") || tokenName.equals("StartPosition")) {
						int x = Integer.parseInt(token[1].substring(2));
						int y = Integer.parseInt(token[2].substring(2));
						if (tokenName.equals("Size")) {
							lm.setSize(x, y);
						}
						else if(tokenName.equals("StartPosition")) {
							lm.addStartPosition(x, y);
						}
					}

					// if a token
					else {
						lm.addToken(parseToken(token));
					}
				}
				line = br.readLine();
			}

			// close reader
			br.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static LevelToken parseToken(String[] token) {
		// TODO Auto-generated method stub
		String tokenName = token[0];
		LevelToken lt = TokenFactory.createToken(tokenName);

		for (int i = 1; i < token.length; i++) {
			String[] attributes = token[i].split("=");
			String key = attributes[0];
			int value = Integer.valueOf(attributes[1]);

			lt.setParameters(key, value);
		}
		return lt;
	}
}
