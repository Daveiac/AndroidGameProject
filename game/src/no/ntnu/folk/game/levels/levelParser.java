package no.ntnu.folk.game.levels;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;

import sheep.game.Game;

import no.ntnu.folk.game.views.tokens.Token;

public class levelParser {

	
	public static ArrayList<Token> parseLevel(String levelName) {
		
		ArrayList<Token> tokens = new ArrayList<Token>();

		String path = "res/levels/" + levelName + ".level";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			
			String line = br.readLine();
			
			while (line != null) {
				if (line.length() != 0) {
					tokens.add(parseLine(line));
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tokens;
	}

	private static Token parseLine(String line) {
		// TODO Auto-generated method stub
		return null;
	}
}
