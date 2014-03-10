package no.ntnu.folk.game.levels;

import no.ntnu.folk.game.views.level.LevelToken;
import no.ntnu.folk.game.views.level.TokenFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class levelParser {


	public static ArrayList<LevelToken> parseLevel(String levelName) {

		ArrayList<LevelToken> tokens = new ArrayList<LevelToken>();

		String path = "res/levels/" + levelName + ".level";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			String line = br.readLine();
			while (line != null) {
				if (line.length() != 0) {
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
		LevelToken lt = TokenFactory.createToken(tokenName);
		
		for (int i = 1; i < tokenAttributes.length; i++) {
			String[] attributes = tokenAttributes[i].split("=");
			String key = attributes[0];
			int value = Integer.valueOf(attributes[1]);
			
			lt.setParameters(key, value);
		}
		return null;
	}
}
