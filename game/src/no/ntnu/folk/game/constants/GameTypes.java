package no.ntnu.folk.game.constants;

public enum GameTypes {
	FFA("Free For All"), TEAMS("Teams");
	private String text;

	GameTypes(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return text;
	}
}
