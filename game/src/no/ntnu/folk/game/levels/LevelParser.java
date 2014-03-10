package no.ntnu.folk.game.levels;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import sheep.game.Game;

import no.ntnu.folk.game.views.level.LevelToken;
import no.ntnu.folk.game.views.level.TokenFactory;

public class LevelParser {


	public static ArrayList<LevelToken> parseLevel(String levelName) {

		ArrayList<LevelToken> tokens = new ArrayList<LevelToken>();

		String path = "res/levels/" + levelName + ".level";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			String line = br.readLine();
			while (line != null) {
				if (!(line.length() == 0 || line.startsWith("#"))) {
					tokens.add(parseLine(line));
				}
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tokens;
	}

	private static LevelToken parseLine(String line) {
		// TODO Auto-generated method stub
		String[] tokenAttributes = line.split("[(),]");
		String tokenName = tokenAttributes[0];
		LevelToken t = TokenFactory.createToken(tokenName);
		
		for (int i = 1; i < tokenAttributes.length; i++) {
			String[] attribute = tokenAttributes[i].split("=");
			String key = attribute[0];
			String value = attribute[1];
		}
		return null;
	}
}
