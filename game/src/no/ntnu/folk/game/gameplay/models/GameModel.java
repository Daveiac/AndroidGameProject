package no.ntnu.folk.game.gameplay.models;

import no.ntnu.folk.game.R;
import no.ntnu.folk.game.constants.GameTypes;
import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.gameplay.entities.data.Projectiles;
import no.ntnu.folk.game.gameplay.entities.data.Teams;
import no.ntnu.folk.game.gameplay.entities.models.EntityModel;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import no.ntnu.folk.game.gameplay.entities.models.TombStoneModel;
import no.ntnu.folk.game.gameplay.levels.views.LevelToken;
import sheep.collision.CollisionListener;
import sheep.game.Sprite;
import sheep.math.Vector2;

import java.util.ArrayList;

/**
 * Class used to keep track of the game-state as the game evolves.
 *
 * @author Rune
 */
public class GameModel implements CollisionListener {
	// Entity lists
	private ArrayList<PlayerModel> players;
	private ArrayList<ProjectileModel> projectiles;
	private ArrayList<TombStoneModel> tombStones;
	private ArrayList<EntityModel> kill;

	// Players
	private int playerCount;
	private int startHealth;
	private PlayerModel currentPlayer;

	// Map
	private LevelModel currentLevel;
	private GameTypes gameType;

	// Game time
	private float gameTime;
	private float availablePlayerTime;
	private boolean paused;

	/**
	 *
	 */
	public GameModel() {
		this.playerCount = GameplayConstants.DEFAULT_PLAYER_COUNT;
		this.startHealth = GameplayConstants.DEFAULT_HEALTH;
		this.currentLevel = new LevelModel(0);
		this.gameType = GameTypes.FFA;
		createPlayers();
		currentPlayer = players.get(0);
		tombStones = new ArrayList<TombStoneModel>();
		kill = new ArrayList<EntityModel>();
		projectiles = new ArrayList<ProjectileModel>();

		// Init time variables
		gameTime = 0;
		availablePlayerTime = GameplayConstants.TURN_TIME;

		for (LevelToken lt : currentLevel.getLevelTokens()) {
			lt.addCollisionListener(this);
		}
	}

	/**
	 * Create the number of players
	 */
	private void createPlayers() {
		players = new ArrayList<PlayerModel>(playerCount);
		ArrayList<int[]> startPos = currentLevel.getStartPositions();
		for (int i = 0; i < playerCount; i++) {
			String name = "Player " + i;
			Vector2 position = new Vector2(startPos.get(i)[0], startPos.get(i)[1]);
			Teams team;
			if (gameType.equals(GameTypes.FFA)) {
				team = Teams.getTeamFromOrdinal(i);
			} else {
				team = i < playerCount / 2 ? Teams.RED : Teams.BLUE;
			}
			PlayerModel player = new PlayerModel(name, position, team, startHealth);
			players.add(player);
		}
	}

	/**
	 * @return The list of players in this game
	 */
	public ArrayList<PlayerModel> getPlayers() {
		return this.players;
	}

	/**
	 * @return The list of projectiles in this game
	 */
	public ArrayList<ProjectileModel> getProjectiles() {
		return projectiles;
	}

	/**
	 * @return The list of levelTokens in this game
	 */
	public ArrayList<LevelToken> getLevelTokens() {
		return currentLevel.getLevelTokens();
	}

	/**
	 * Update timer
	 *
	 * @param dt time since last update
	 */
	public void update(float dt) {
		updateModels(dt);
		checkCollisions();
		gameTime += dt;
		availablePlayerTime -= dt;
		if (playerTimeUp() || players.indexOf(currentPlayer) == -1) {
			nextPlayer();
		}
		killEntities();
		if (isGameOver(players)) {
//			System.out.println("GAME OVER");
			// TODO end the game here
		}
	}
	private void updateModels(float dt) {
		for (PlayerModel player : players) {
			player.update(dt);
		}
		for (ProjectileModel projectile : projectiles) {
			projectile.update(dt);
		}
		for (TombStoneModel tombStone : tombStones) {
			tombStone.update(dt);
		}
	}
	private void checkCollisions() {
		for (PlayerModel player : players) {
			for (LevelToken lt : currentLevel.getLevelTokens()) {
				player.collides(lt);
			}
		}
		for (ProjectileModel projectile : projectiles) {
			for (PlayerModel player : players) {
				projectile.collides(player);
			}
		}
	}
	private void killEntities() {
		ArrayList<PlayerModel> oldPlayers = players;
		for (EntityModel entity : kill) {
			if (entity instanceof ProjectileModel) {
				projectiles.remove(entity);
			} else if (entity instanceof PlayerModel) {
				players.remove(entity);
			}
		}
		int i = oldPlayers.indexOf(currentPlayer);
		if (players.indexOf(currentPlayer) == -1) {
			while (true) {
				if ((players.indexOf(oldPlayers.get(++i)) != -1)) break;
			}
			currentPlayer = players.get(i);
		}
	}

	/**
	 * Set currentPlayer to the next player. Set to first player if a the end of
	 * the list.
	 */
	public void nextPlayer() {
		getCurrentPlayer().setSpeed(0, 0);
		availablePlayerTime = GameplayConstants.TURN_TIME;
		int playerNumber = players.indexOf(currentPlayer);
		if (playerNumber == players.size() - 1) {
			playerNumber = 0;
		} else {
			playerNumber++;
		}
		currentPlayer = players.get(playerNumber);
	}

	/**
	 * @return Current player
	 */
	public PlayerModel getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @return True if the time is up for current player
	 */
	public boolean playerTimeUp() {
		return availablePlayerTime <= 0;
	}

	/**
	 * @return time left of this turn for the current player
	 */
	public float playerTimeLeft() {
		return this.availablePlayerTime;
	}

	/**
	 * Fires the weapon the current player is holding
	 */
	public void fireWeapon() {
		if (getCurrentPlayer().getCurrentWeapon().isCool()) {
			Projectiles projectileType = getCurrentPlayer().getCurrentWeapon().getProjectileType();
			ProjectileModel projectile = new ProjectileModel(projectileType, getCurrentPlayer());
			projectiles.add(projectile);
			projectile.addCollisionListener(this);
			projectile.setSpeed(getCurrentPlayer().getAim());
			getCurrentPlayer().getCurrentWeapon().setCool(true);
		}
	}
	/**
	 * @return true if the game is paused
	 */
	public boolean isPaused() {
		return this.paused;
	}
	/**
	 * @param paused Set whether or not the game is paused
	 */
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	/**
	 * Called when two Sprite collide.
	 *
	 * @param a The first Sprite (the sprite being listened to).
	 * @param b The other Sprite.
	 */
	@Override
	public void collided(Sprite a, Sprite b) {
		if (a instanceof ProjectileModel) {
			if (b instanceof PlayerModel) {
				kill.add((EntityModel) a);
				attack((PlayerModel) b, (ProjectileModel) a);
			}
		}
		if (a instanceof PlayerModel) {
			if (b instanceof LevelToken) {
				a.setSpeed(a.getSpeed().getX(), 0);
			}
		}
	}
	private void attack(PlayerModel player, ProjectileModel projectile) {
		player.attacked(projectile.getDirectDamage());
		if (player.getHealth() <= 0) {
			kill.add(player);
			tombStones.add(new TombStoneModel(player.getName(), player.getPosition(), R.drawable.tombstone));
		}
	}

	public void setGameTime(int time) {
		this.availablePlayerTime = time;
	}

	public boolean isGameOver(ArrayList<PlayerModel> playerList) {
		int numberOfPlayerLeft = 0;
		Teams team = playerList.get(0).getTeam();
		switch (gameType) {
			case FFA:
				return numberOfPlayerLeft <= 1;
			case TEAMS:
				for (PlayerModel p : playerList) {
					if (!team.equals(p.getTeam())) {
						return false;
					}
				}
				return true;
			default:
				return false;
		}
	}

	public int getPlayerCount() {
		return players.size();
	}
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
		createPlayers();
	}
	public int getStartHealth() {
		return startHealth;
	}
	public void setStartHealth(int startHealth) {
		this.startHealth = startHealth;
		createPlayers();
	}
	public GameTypes getGameType() {
		return gameType;
	}
	public void setGameType(GameTypes gameType) {
		this.gameType = gameType;
		createPlayers();
	}

	public ArrayList<TombStoneModel> getTombStones() {
		return tombStones;
	}
}
