package no.ntnu.folk.game.gameplay.layers;

import java.util.ArrayList;

import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.gameplay.entities.models.EntityModel;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import no.ntnu.folk.game.gameplay.entities.models.TombStoneModel;
import no.ntnu.folk.game.gameplay.levels.Direction;
import no.ntnu.folk.game.gameplay.levels.views.LevelToken;
import no.ntnu.folk.game.gameplay.models.GameModel;
import sheep.game.Layer;
import sheep.graphics.Color;
import sheep.math.BoundingBox;
import android.graphics.Canvas;

public class GameLayer extends Layer {
	private GameModel model;
	private float offset;

	public GameLayer(GameModel model) {
		this.model = model;
		for (PlayerModel player : model.getPlayers()) {
			player.addCollisionListener(model);
		}
		offset = GameplayConstants.GRID_SIZE/4;
	}

	@Override
	public void update(float dt) {
		for (PlayerModel player : model.getPlayers()) {
			ArrayList<Direction> playerCollision = collidesWithWall(player);
			player.setCollision(playerCollision);
			checkPlayerCollsision(player, playerCollision);
		}
		for (ProjectileModel projectile : model.getProjectiles()) {
			if (!collidesWithWall(projectile).isEmpty()) {
				model.getKill().add(projectile);
			}
		}
	}

	private void checkPlayerCollsision(PlayerModel player,
			ArrayList<Direction> playerCollision) {
		boolean down = false;
		float offX = player.getOffset().getX();
		float offY = player.getOffset().getY();
		int gridSize = GameplayConstants.GRID_SIZE;
		float x;
		float y;
		for (Direction direction : playerCollision) {
			switch (direction) {
			case LEFT:
				float leftX = player.getX() - offX;
				x = ((int)(leftX / gridSize) + 1)*gridSize + offX - 1;
				player.setPosition(x, player.getY());
				break;
			case RIGHT:
				float rightX = player.getX() + offX;
				x = ((int)(rightX / gridSize))*gridSize - offX + 1;
				player.setPosition(x, player.getY());
				break;
			case UP:
				float upY = player.getY() - offY;
				y = ((int)(upY / gridSize) + 1)*gridSize + offY;
				player.setPosition(player.getX(), y);
				player.setSpeed(player.getSpeed().getX(), 0);
				break;
			case DOWN:
				float downY = player.getY() + offY;
				y = ((int)(downY / gridSize))*gridSize - offY + 1;
				player.setPosition(player.getX(), y);
				player.setAcceleration(player.getAcceleration().getX(), 0);
				player.setSpeed(player.getSpeed().getX(), 0);
				break;
			default:
				break;
			}
		}
		if(!down) {
			player.setAcceleration(player.getAcceleration().getX(), GameplayConstants.GRAVITY);
		}
	}

	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		canvas.save();
		int[] windowSize = ProgramConstants.getWindowSize();
		canvas.translate(-model.getCurrentPlayer().getX() + windowSize[0] / 2, -model.getCurrentPlayer().getY() + windowSize[1] / 2);

		drawLevel(canvas);
		drawEntities(canvas);

		canvas.restore();

		canvas.drawText(
				"Time left: " + ((int) model.playerTimeLeft()),
				windowSize[0] * 0.9f,
				windowSize[1] * 0.1f,
				Color.WHITE
		);
	}
	/**
	 * Draws the level.
	 *
	 * @param canvas The canvas the level is drawn on.
	 */
	private void drawLevel(Canvas canvas) {
		for (LevelToken levelToken : model.getLevelTokens()) {
			levelToken.draw(canvas);
		}
	}
	/**
	 * Draws entities.
	 *
	 * @param canvas The canvas the entities are drawn on.
	 */
	private void drawEntities(Canvas canvas) {
		for (TombStoneModel tombStone : model.getTombStones()) {
			tombStone.draw(canvas);
		}
		for (PlayerModel player : model.getPlayers()) {
			player.draw(canvas);
		}
		for (ProjectileModel projectile : model.getProjectiles()) {
			projectile.draw(canvas);
		}
	}

	private ArrayList<Direction> collidesWithWall(EntityModel entity) {
		int gridSize = GameplayConstants.GRID_SIZE;
		float x = entity.getX();
		float y = entity.getY();
		float offX = entity.getOffset().getX();
		float offY = entity.getOffset().getY();

		LevelToken[][] grid = model.getCurrentLevel().getGrid();
		if(grid == null) {
			System.out.println("grid is null");
			return null;
		}
		ArrayList<Direction> directions = new ArrayList<Direction>();
		//left wall
		for (int i = (int)(y-offY+ offset)/gridSize; i < (int)(y+offY- offset)/gridSize+1; i++) {
			if(grid[i][(int)(x-offX)/gridSize] != null && i > 0) {
				directions.add(Direction.LEFT);
				break;
			}
		}
		//right wall
		for (int i = (int)(y-offY+ offset)/gridSize; i < (int)(y+offY- offset)/gridSize+1; i++) {
			if(grid[i][(int)(x+offX)/gridSize] != null && i > 0) {
				directions.add(Direction.RIGHT);
				break;
			}
		}
		//floor
		for (int i = (int)(x-offX+ offset)/gridSize; i < (int)(x+offX- offset)/gridSize+1; i++) {
			if(grid[(int)(y+offY)/gridSize][i] != null && i > 0) {
				directions.add(Direction.DOWN);
				break;
			}
		}
		//ceiling
		for (int i = (int)(x-offX+ offset)/gridSize; i < (int)(x+offX- offset)/gridSize+1; i++) {
			if(grid[(int)(y-offY)/gridSize][i] != null && i > 0) {
				directions.add(Direction.UP);
				break;
			}
		}
		return directions;
	}
}
