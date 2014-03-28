package no.ntnu.folk.game.gameplay.layers;

import android.graphics.Canvas;
import android.graphics.Rect;
import no.ntnu.folk.game.Program;
import no.ntnu.folk.game.R;
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
import sheep.graphics.Image;
import sheep.math.BoundingBox;

import java.util.ArrayList;

public class GameLayer extends Layer {
	private GameModel model;
	private ArrayList<Integer[]> lastingImageArrayList;

	public GameLayer(GameModel model) {
		this.model = model;
		lastingImageArrayList = new ArrayList<Integer[]>();
		for (PlayerModel player : model.getPlayers()) {
			player.addCollisionListener(model);
		}
	}

	@Override
	public void update(float dt) {
		for (PlayerModel player : model.getPlayers()) {
			ArrayList<Direction> playerCollision = collidesWithWall(player);
			player.setCollision(playerCollision);
			correctPosition(player, playerCollision);
			if (!this.model.getExplosions().isEmpty()) {
				for (ProjectileModel pm : this.model.getExplosions()) {
					float exploLeft = pm.getPosition().getX() - pm.getAreaDamageRange();
					float exploTop = pm.getPosition().getY() - pm.getAreaDamageRange();
					float exploRight = pm.getPosition().getX() + pm.getAreaDamageRange();
					float exploBottom = pm.getPosition().getY() + pm.getAreaDamageRange();
					BoundingBox aoeDamage = new BoundingBox(new Rect((int) exploLeft, (int) exploTop, (int) exploRight, (int) exploBottom));
					if (aoeDamage.contains(player.getX()-player.getOffset().getX(), player.getY()-player.getOffset().getY())) {
						player.attacked(pm.getAreaDamage());
					}
				}
			}
		}
		model.getExplosions().clear();
		for (ProjectileModel projectile : model.getProjectiles()) {
			if (!collidesWithWall(projectile).isEmpty()) {
				model.addExplosion(projectile);
				model.getKill().add(projectile);
			}
		}
	}

	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		canvas.save();
		int[] windowSize = ProgramConstants.getWindowSize();
		canvas.translate(-model.getCurrentPlayer().getX() + windowSize[0] / 2, -model.getCurrentPlayer().getY() + windowSize[1] / 2);

		drawLevel(canvas);
		drawEntities(canvas);
		drawLastingImages(canvas);
        drawTimer(canvas);

        canvas.restore();
    }

    private void drawTimer(Canvas canvas){
        int timeLeft = (int)model.playerTimeLeft();
        if (timeLeft > 6) {
            canvas.drawText(
                    ""+timeLeft,
                    model.getCurrentPlayer().getX(),
                    model.getCurrentPlayer().getY() - ProgramConstants.getWindowSize()[0] * 0.1f,
                    Color.WHITE
            );
        }
        if(timeLeft < 6){
            Image i;
            float x = model.getCurrentPlayer().getX();
            float y = model.getCurrentPlayer().getY() - ProgramConstants.getWindowSize()[0] * 0.1f;
            switch (timeLeft){
                case 5:
                    i = new Image(R.drawable.five);
                    i.draw(canvas, x - i.getWidth()/2, y);
                    break;
                case 4:
                    i = new Image(R.drawable.four);
                    i.draw(canvas, x - i.getWidth()/2, y);
                    break;
                case 3:
                    i = new Image(R.drawable.three);
                    i.draw(canvas, x - i.getWidth()/2, y);
                    break;
                case 2:
                    i = new Image(R.drawable.two);
                    i.draw(canvas, x - i.getWidth()/2, y);
                    break;
                case 1:
                    i = new Image(R.drawable.one);
                    i.draw(canvas, x - i.getWidth()/2, y);
                    break;
            }
        }
    }

	private void drawLastingImages(Canvas canvas) {
		for (Integer[] o : lastingImageArrayList) {
			Image i = new Image(o[0]);
			i.draw(canvas, o[1], o[2]);
			o[3]--;
			if (o[3] < 0) {
				lastingImageArrayList.remove(o);
			}
		}
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
		if (!this.model.getExplosions().isEmpty()) {
			for (ProjectileModel model : this.model.getExplosions()) {
				Image i = new Image(model.getExplosion());
				float x = model.getX() - i.getWidth() / 2;
				float y = model.getY() - i.getHeight() / 2;
				Integer[] o = new Integer[4];
				o[0] = model.getExplosion();
				o[1] = (int) x;
				o[2] = (int) y;
				o[3] = GameplayConstants.EXPLOSION_LENGTH;
				lastingImageArrayList.add(o);
				i.draw(canvas, x, y);
			}
		}
	}

	private void correctPosition(EntityModel entity, ArrayList<Direction> collision) {
		boolean down = false;
		float offX = entity.getOffset().getX();
		float offY = entity.getOffset().getY();
		int gridSize = GameplayConstants.GRID_SIZE;
		float x, y;
		for (Direction direction : collision) {
			switch (direction) {
				case LEFT:
					x = ((int) ((entity.getX() - offX) / gridSize) + 1) * gridSize + offX - 1;
					entity.setPosition(x, entity.getY());
					break;
				case RIGHT:
					x = ((int) ((entity.getX() + offX) / gridSize)) * gridSize - offX + 1;
					entity.setPosition(x, entity.getY());
					break;
				case UP:
					y = ((int) ((entity.getY() - offY) / gridSize) + 1) * gridSize + offY;
					entity.setPosition(entity.getX(), y);
					entity.setSpeed(entity.getSpeed().getX(), 0);
					break;
				case DOWN:
					down = true;
					y = ((int) ((entity.getY() + offY) / gridSize)) * gridSize - offY + 1;
					entity.setPosition(entity.getX(), y);
					entity.setAcceleration(entity.getAcceleration().getX(), 0);
					entity.setSpeed(entity.getSpeed().getX(), 0);
					break;
				default:
					break;
			}
		}
		if (!down) {
			entity.setAcceleration(entity.getAcceleration().getX(), GameplayConstants.GRAVITY);
		}
	}

	private ArrayList<Direction> collidesWithWall(EntityModel entity) {
		int gridSize = GameplayConstants.GRID_SIZE;
		float offset = ((entity instanceof PlayerModel) ? (GameplayConstants.GRID_SIZE / 4) : 0);
		float x = entity.getX();
		float y = entity.getY();
		float offX = entity.getOffset().getX();
		float offY = entity.getOffset().getY();

		LevelToken[][] grid = model.getCurrentLevel().getGrid();
		if (grid == null) {
			System.out.println("grid is null");
			return null;
		}
		ArrayList<Direction> directions = new ArrayList<Direction>();
		//left wall
		for (int i = (int) (y - offY + offset) / gridSize; i < (int) (y + offY - offset) / gridSize + 1; i++) {
			if (grid[i][(int) (x - offX) / gridSize] != null && i > 0) {
				directions.add(Direction.LEFT);
				break;
			}
		}
		//right wall
		for (int i = (int) (y - offY + offset) / gridSize; i < (int) (y + offY - offset) / gridSize + 1; i++) {
			if (grid[i][(int) (x + offX) / gridSize] != null && i > 0) {
				directions.add(Direction.RIGHT);
				break;
			}
		}
		//floor
		for (int i = (int) (x - offX + offset) / gridSize; i < (int) (x + offX - offset) / gridSize + 1; i++) {
			if (grid[(int) (y + offY) / gridSize][i] != null && i > 0) {
				directions.add(Direction.DOWN);
				break;
			}
		}
		//ceiling
		for (int i = (int) (x - offX + offset) / gridSize; i < (int) (x + offX - offset) / gridSize + 1; i++) {
			if (grid[(int) (y - offY) / gridSize][i] != null && i > 0) {
				directions.add(Direction.UP);
				break;
			}
		}
		return directions;
	}
}
