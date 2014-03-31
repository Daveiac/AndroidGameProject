package no.ntnu.folk.game.states;

import android.graphics.Canvas;
import no.ntnu.folk.game.Program;
import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.gameplay.entities.data.Projectiles;
import no.ntnu.folk.game.gameplay.entities.data.Weapons;
import no.ntnu.folk.game.gameplay.entities.models.EntityModel;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import no.ntnu.folk.game.gameplay.entities.models.TombStoneModel;
import no.ntnu.folk.game.gameplay.layers.GameLayer;
import no.ntnu.folk.game.gameplay.layers.KeyPadLayer;
import no.ntnu.folk.game.gameplay.models.GameModel;
import no.ntnu.folk.game.menus.menuStates.EndGameMenu;
import sheep.game.State;
import sheep.game.World;
import sheep.math.Vector2;

import java.util.ArrayList;

import static android.graphics.Color.BLACK;

/**
 * The game state. This state is activated when the game starts after the pre game menu. It contains the game world together with its layers and game model.
 * The methods do the mechanics when a shot is fired and the consequences of it.
 *
 */
public class GameState extends State {
	private World gameWorld;
	private GameModel model;
	private GameLayer gameLayer;

	/**
	 * Create a new game.
	 *
	 * @param model The GameModel for this game
	 */
	public GameState(GameModel model) {
		this.model = model;
		gameWorld = new World();
		gameLayer = new GameLayer(model);
		gameWorld.addLayer(gameLayer);
		gameWorld.addLayer(new KeyPadLayer(this, model));
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		gameWorld.update(dt);
		updateModels(dt);
		model.incrementTime(dt);
		model.decrementAvailablePlayerTime(dt);
		killEntities();
		if (model.isGameOver()) {
			Program.getGame().pushState(new EndGameMenu(model.getGameTime(), model.getWinnerText()));
		} else {
			findNextPlayer();
		}
	}
	@Override
	public void draw(Canvas canvas) {
		if (canvas == null) return;
		canvas.drawColor(BLACK);

		gameWorld.draw(canvas);
	}

	/**
	 * Updates the corresponding models.
	 * @param dt The change in time.
	 */
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
			model.getCurrentPlayer().setCold(true);
		}
	}

	/**
	 * Fires the weapon the current player is holding
	 */
	public void fireWeapon() {
		if ((model.getCurrentPlayer().isCold() && model.getCurrentPlayer().getCurrentWeapon().hasAmmo()) || ProgramConstants.isUnlimitedFire()) {
			model.getCurrentPlayer().setFiredWeapon(true);
			if(!ProgramConstants.isUnlimitedFire()) {
				model.getCurrentPlayer().getCurrentWeapon().reduceAmmo();
			}
			if(model.getPlayerTimeLeft() > 5){
				model.setAvailablePlayerTime(5);
			}
			ProjectileModel projectile = makeProjectile();
			model.getProjectiles().add(projectile);
			projectile.addCollisionListener(gameLayer);

			Vector2 aim = setAimMagnitude();

			projectile.setSpeed(aim);
			projectile.setAcceleration(0, 50);
			model.getCurrentPlayer().setCold(false);
		}
	}

	/**
	 * @return A new projectile based on current player
	 */
	private ProjectileModel makeProjectile() {
		Projectiles projectileType = model.getCurrentPlayer().getCurrentWeapon().getProjectileType();
		return new ProjectileModel(projectileType, model.getCurrentPlayer());
	}

	/**
	 * @return Aim vector with correct magnitude
	 */
	private Vector2 setAimMagnitude() {
		Vector2 aim = model.getCurrentPlayer().getAim();
		double v_aim = Math.sqrt(Math.pow(aim.getX(), 2) + Math.pow(aim.getY(), 2));
		double v = model.getCurrentPlayer().getCurrentWeapon().getProjectileType().getMuzzleVelocity();
		float ratio = (float) (v / v_aim);
		return aim.getMultiplied(ratio);
	}

}
