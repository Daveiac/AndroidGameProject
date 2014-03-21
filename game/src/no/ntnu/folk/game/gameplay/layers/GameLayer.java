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

	public GameLayer(GameModel model) {
		this.model = model;
		for (PlayerModel player : model.getPlayers()) {
			player.addCollisionListener(model);
		}
	}

	@Override
	public void update(float dt) {
		PlayerModel player = model.getCurrentPlayer();
		ArrayList<Direction> playerCollision = collidesWithWall(player);
		player.setCollision(playerCollision);
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
		float offX = -entity.getOffset().getX();
		float offY = -entity.getOffset().getY();

		LevelToken[][] grid = model.getCurrentLevel().getGrid();
		if(grid == null) {
			System.out.println("grid is null");
			return null;
		}
		ArrayList<Direction> directions = new ArrayList<Direction>();
		//left wall
		int offset = 5;
		for (int i = (int)(y-offY+ offset)/gridSize; i < (int)(y+offY- offset)/gridSize+1; i++) {
			if(grid[i][(int)(x-offX)/gridSize] != null && i > 0) {
				directions.add(Direction.LEFT);
				System.out.println("Player collides with: LEFT");
			}
		}
		//right wall
		for (int i = (int)(y-offY+ offset)/gridSize; i < (int)(y+offY- offset)/gridSize+1; i++) {
			if(grid[i][(int)(x+offX)/gridSize] != null && i > 0) {
				System.out.println("Player collides with: RIGHT");
				directions.add(Direction.RIGHT);
			}
		}
		//floor
		for (int i = (int)(x-offX+ offset)/gridSize; i < (int)(x+offX- offset)/gridSize+1; i++) {
			if(grid[(int)(y+offY)/gridSize][i] != null && i > 0) {
				System.out.println("Player collides with: DOWN");
				directions.add(Direction.DOWN);
			}
		}
		//ceiling
		for (int i = (int)(x-offX+ offset)/gridSize; i < (int)(x+offX- offset)/gridSize+1; i++) {
			if(grid[(int)(y-offY)/gridSize][i] != null && i > 0) {
				System.out.println("Player collides with: UP");
				directions.add(Direction.UP);
			}
		}
		return directions;
	}
}
