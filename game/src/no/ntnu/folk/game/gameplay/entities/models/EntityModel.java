package no.ntnu.folk.game.gameplay.entities.models;

import java.util.ArrayList;

import android.graphics.Canvas;
import no.ntnu.folk.game.gameplay.entities.views.EntityToken;
import no.ntnu.folk.game.gameplay.levels.Direction;
import sheep.game.Sprite;
import sheep.math.Vector2;

public abstract class EntityModel extends Sprite {
	private final EntityToken entityToken;

	private final String name;

	private ArrayList<Direction> collision;

	/**
	 * @param name     Name of the entityToken associated with this entityModel
	 * @param position Position of this entityModel
	 * @param image    Used when creating a token
	 */
	protected EntityModel(String name, Vector2 position, int image) {
		this.name = name;
		this.setPosition(position);
		this.entityToken = createToken(image);
	}
	/**
	 * @return Create a entityToken of the correct type
	 */
	protected abstract EntityToken createToken(int image);

	@Override
	public void update(float dt) {
		super.update(dt);
		entityToken.update(dt);
	}
	@Override
	public void draw(Canvas canvas) {
		entityToken.draw(canvas);
	}
	/**
	 * @return Name of this entityModel
	 */
	public String getName() {
		return name;
	}

	public void setCollision(ArrayList<Direction> collidesWithWall) {
		this.collision = collidesWithWall;
	}
	public ArrayList<Direction> getCollision() {
		return collision;
	}

}
