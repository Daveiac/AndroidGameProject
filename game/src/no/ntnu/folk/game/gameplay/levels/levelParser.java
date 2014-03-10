package no.ntnu.folk.game.gameplay.levels;

import no.ntnu.folk.game.gameplay.entities.views.EntityToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class levelParser {


	public static ArrayList<EntityToken> parseLevel(String levelName) {

		ArrayList<EntityToken> entityTokens = new ArrayList<EntityToken>();

		String path = "res/levels/" + levelName + ".level";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			String line = br.readLine();
			while (line != null) {
				if (line.length() != 0) {
					entityTokens.add(parseLine(line));
				}
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return entityTokens;
	}

	private static EntityToken parseLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
}
