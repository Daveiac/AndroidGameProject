package no.ntnu.folk.game.gameplay.layers;

import no.ntnu.folk.game.constants.GameplayConstants;
import no.ntnu.folk.game.constants.ProgramConstants;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import no.ntnu.folk.game.gameplay.entities.models.TombStoneModel;
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
		collidesWithWall();
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

	private boolean collidesWithWall() {
		PlayerModel player = this.model.getCurrentPlayer();
		int gridSize = GameplayConstants.GRID_SIZE;
		float x = player.getX();
		float y = player.getY();
		float offX = -player.getOffset().getX();
		float offY = -player.getOffset().getY();
		int[] topLeft = {(int)(x-offX)/gridSize, (int)(y-offY)/gridSize};
		int[] topRight = {(int)(x+offX)/gridSize, (int)(y-offY)/gridSize};
		int[] bottomLeft = {(int)(x-offX)/gridSize, (int)(y+offY)/gridSize};
		int[] bottomRight = {(int)(x+offX)/gridSize, (int)(y+offY)/gridSize};
		LevelToken[][] grid = model.getCurrentLevel().getGrid();
		if(grid == null) {
			System.out.println("grid is null");
			return false;
		}
		boolean collided = false;
		//left wall
		for (int i = topLeft[1]; i < bottomLeft[1]+1; i++) {
			if(grid[i][topLeft[0]] != null) {
				collidedLeft(player);
				collided = true;
			}
		}
		//right wall
		for (int i = topRight[1]; i < bottomRight[1]+1; i++) {
			if(grid[i][topRight[0]] != null) {
				collidedRight(player);
				collided = true;
			}
		}
		//floor
		for (int i = bottomLeft[1]; i < bottomRight[1]+1; i++) {
			if(grid[bottomLeft[1]][i] != null) {
				collidedFloor(player);
				collided = true;
			}
		}
		//ceiling
		for (int i = topLeft[1]; i < topRight[1]+1; i++) {
			if(grid[topLeft[1]][i] != null) {
				collidedCeiling(player);
				collided = true;
			}
		}
		return collided;
	}
	private void collidedLeft(PlayerModel player) {
		System.out.println("player hitting left wall\n");
	}
	private void collidedRight(PlayerModel player) {
		System.out.println("player hitting right wall\n");
	}
	private void collidedFloor(PlayerModel player) {
		System.out.println("player hitting floor\n");
	}
	private void collidedCeiling(PlayerModel player) {
		System.out.println("player hitting ceiling\n");
	}

}
