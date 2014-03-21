package no.ntnu.folk.game.states;

import android.graphics.Canvas;
import no.ntnu.folk.game.Program;
import no.ntnu.folk.game.gameplay.entities.data.Projectiles;
import no.ntnu.folk.game.gameplay.entities.models.EntityModel;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import no.ntnu.folk.game.gameplay.entities.models.TombStoneModel;
import no.ntnu.folk.game.gameplay.layers.GameLayer;
import no.ntnu.folk.game.gameplay.layers.KeyPadLayer;
import no.ntnu.folk.game.gameplay.levels.views.LevelToken;
import no.ntnu.folk.game.gameplay.models.GameModel;
import no.ntnu.folk.game.menus.menuStates.EndGameMenu;
import sheep.game.State;
import sheep.game.World;

import java.util.ArrayList;

import static android.graphics.Color.BLUE;

public class GameState extends State {
	private World gameWorld;
	private GameModel model;

	/**
	 * Create a new game.
	 *
	 * @param model
	 */
	public GameState(GameModel model) {
		this.model = model;
		gameWorld = new World();
		gameWorld.addLayer(new GameLayer(model));
		gameWorld.addLayer(new KeyPadLayer(this, model));
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		gameWorld.update(dt);
		updateModels(dt);
		checkCollisions();
		model.incrementTime(dt);
		model.decrementAvailablePlayerTime(dt);
		if (model.playerTimeUp() || model.getPlayers().indexOf(model.getCurrentPlayer()) == -1) {
			model.nextPlayer();
		}
		killEntities();
		if (model.isGameOver(model.getPlayers())) {
			Program.getGame().pushState(new EndGameMenu(model.getGameTime()));
		}
	}
	@Override
	public void draw(Canvas canvas) {
		if (canvas == null) return;
		canvas.drawColor(BLUE);

		gameWorld.draw(canvas);
	}

	private void updateModels(float dt) {
		for (PlayerModel player : model.getPlayers()) {
			player.update(dt);
		}
		for (ProjectileModel projectile : model.getProjectiles()) {
			projectile.update(dt);
		}
		for (TombStoneModel tombStone : model.getTombStones()) {
			tombStone.update(dt);
		}
	}
	private void checkCollisions() {
		for (PlayerModel player : model.getPlayers()) {
			for (LevelToken lt : model.getCurrentLevel().getLevelTokens()) {
				player.collides(lt);
			}
		}
		for (ProjectileModel projectile : model.getProjectiles()) {
			for (PlayerModel player : model.getPlayers()) {
				projectile.collides(player);
			}
		}
	}
	/**
	 * Removes killed entities
	 */
	private void killEntities() {
		for (EntityModel entity : model.getKill()) {
			if (entity instanceof ProjectileModel) {
				model.getProjectiles().remove(entity);
			} else if (entity instanceof PlayerModel) {
				model.getPlayers().remove(entity);
			}
		}
		model.getKill().clear();
		findNextPlayer();
	}
	/**
	 * Set current player to the next player if the current player dies.
	 */
	private void findNextPlayer() {
		ArrayList<PlayerModel> oldPlayers = model.getPlayers();
		int i = oldPlayers.indexOf(model.getCurrentPlayer());
		if (model.getPlayers().indexOf(model.getCurrentPlayer()) == -1) {
			while (true) {
				if ((model.getPlayers().indexOf(oldPlayers.get(++i)) != -1)) break;
			}
			model.setCurrentPlayer(model.getPlayers().get(i));
			model.getCurrentPlayer().getCurrentWeapon().setCold(true);
		}
	}

	/**
	 * Fires the weapon the current player is holding
	 */
	public void fireWeapon() {
		if (model.getCurrentPlayer().getCurrentWeapon().isCold()) {
			Projectiles projectileType = model.getCurrentPlayer().getCurrentWeapon().getProjectileType();
			ProjectileModel projectile = new ProjectileModel(projectileType, model.getCurrentPlayer());
			model.getProjectiles().add(projectile);
			projectile.addCollisionListener(model);
			projectile.setSpeed(model.getCurrentPlayer().getAim());
			model.getCurrentPlayer().getCurrentWeapon().setCold(false);
		}
	}

}
