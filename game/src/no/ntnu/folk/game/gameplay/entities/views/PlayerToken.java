package no.ntnu.folk.game.gameplay.entities.views;

import android.graphics.Canvas;
import android.graphics.Matrix;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import sheep.graphics.Color;

import java.util.Arrays;

/**
 * The player token class is a view of the player model class which it represents.
 *
 */
public class PlayerToken extends EntityToken {
	private static final float healthBarFrame = 5;

	private Matrix healthBarMatrix;

	/**
	 * @param model PlayerModel for this token
	 * @param image ID for the image
	 */
	public PlayerToken(PlayerModel model, int image) {
		super(model, image);
		this.healthBarMatrix = new Matrix();
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		drawHealthBar(canvas);
	}
	/**
	 * Draws a health bar above the player
	 *
	 * @param canvas The canvas the health bar is drawn on
	 */
	private void drawHealthBar(Canvas canvas) {
		float x = entityModel.getX();
		float y = entityModel.getY();
		float height = image.getHeight();
		float width = image.getWidth() / 2;
		healthBarMatrix.setTranslate(x, y - height);
		float health = ((PlayerModel) entityModel).getHealth() * 1.0f / ((PlayerModel) entityModel).getStartHealth();
		canvas.drawRect(x - width - healthBarFrame, y - height + healthBarFrame, x + width + healthBarFrame, y - height * (1.25f) - healthBarFrame, Color.BLACK);
		canvas.drawRect(x - width, y - height, (x - width) + width * health * 2, y - height * (1.25f), calculateHealthBarColor(health));
	}
	/**
	 * @param health Player health
	 * @return The color the health bar should be drawn
	 */
	private Color calculateHealthBarColor(float health) {
		int red = health < 0.5f ? 255 : (int) (255 - 2 * health * 255);
		int green = health > 0.5f ? 255 : (int) (2 * health * 255);
		int blue = 0;
		return new Color(red, green, blue);
	}

	@Override
	public String toString() {
		return "PlayerToken{name='" + entityModel.getName() + '\'' +
				", health=" + ((PlayerModel) entityModel).getHealth() +
				", coll: " + Arrays.toString(entityModel.getCollision().toArray()) +
				"}";
	}
	
	@Override
	public void update(float dt) {
		super.update(dt);
		entityModel.setScale(((PlayerModel) entityModel).getCurrentWeapon().getScale().getY(), 1);
	}
	
	@Override
	protected float getScaleX() {
		return entityModel.getScale().getX();
	}
	@Override
	protected float getScaleY() {
		return entityModel.getScale().getY();
	}
	/**
	 * @return 0 as the player does not rotate.
	 */
	@Override
	protected int getRotation() {
		return 0;
	}

}
