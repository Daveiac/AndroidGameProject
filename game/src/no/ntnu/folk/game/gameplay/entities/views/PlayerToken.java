package no.ntnu.folk.game.gameplay.entities.views;

import java.util.Arrays;

import android.graphics.Canvas;
import android.graphics.Matrix;
import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import sheep.graphics.Image;

public class PlayerToken extends EntityToken {
	private Image[] healthBar;
	private float direction;

	/**
	 * @param model PlayerModel for this token
	 * @param image ID for the image
	 */
	public PlayerToken(PlayerModel model, int image) {
		super(model, image);
		direction = 1;
		healthBar = new Image[]{
				new Image(R.drawable.heathbartest),
				new Image(R.drawable.heathbartest95),
				new Image(R.drawable.heathbartest90),
				new Image(R.drawable.heathbartest85),
				new Image(R.drawable.heathbartest80),
				new Image(R.drawable.heathbartest75),
				new Image(R.drawable.heathbartest70),
				new Image(R.drawable.heathbartest65),
				new Image(R.drawable.heathbartest60),
				new Image(R.drawable.heathbartest55),
				new Image(R.drawable.heathbartest50),
				new Image(R.drawable.heathbartest45),
				new Image(R.drawable.heathbartest40),
				new Image(R.drawable.heathbartest35),
				new Image(R.drawable.heathbartest30),
				new Image(R.drawable.heathbartest25),
				new Image(R.drawable.heathbartest20),
				new Image(R.drawable.heathbartest15),
				new Image(R.drawable.heathbartest10),
				new Image(R.drawable.heathbartest5),
		};
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		Matrix matrix = new Matrix();
		matrix.postTranslate(entityModel.getX() - healthBar[0].getWidth() / 2, entityModel.getY() - image.getHeight());
		int health = 100 * ((PlayerModel) entityModel).getHealth() / ((PlayerModel) entityModel).getStartHealth();
		int count = 100;
		for (int i = 0; i < healthBar.length; i++){
			if (health == count){
				healthBar[i].draw(canvas, matrix);
			}
			count -= 5;
		}
	}

	@Override
	public String toString() {
		return "PlayerToken{name='" + entityModel.getName() + '\'' +
				", health=" + ((PlayerModel) entityModel).getHealth() +
				", coll: " + Arrays.toString(entityModel.getCollision().toArray()) +
				"}";
	}

	@Override
	protected float getScaleX() {
		if (entityModel.getSpeed().getX() != 0) {
			direction = Math.signum(entityModel.getSpeed().getX());
		}
		return direction;
	}
	@Override
	protected float getScaleY() {
		return 1;
	}
	/**
	 * @return 0 as the player does not rotate.
	 */
	@Override
	protected int getRotation() {
		return 0;
	}

}
