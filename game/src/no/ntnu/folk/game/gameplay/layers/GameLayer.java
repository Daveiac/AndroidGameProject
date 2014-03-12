package no.ntnu.folk.game.gameplay.layers;

import android.graphics.Canvas;
import android.view.MotionEvent;
import no.ntnu.folk.game.Constants;
import no.ntnu.folk.game.R;
import no.ntnu.folk.game.gameplay.entities.models.PlayerModel;
import no.ntnu.folk.game.gameplay.entities.models.ProjectileModel;
import no.ntnu.folk.game.gameplay.models.GameModel;
import sheep.collision.CollisionListener;
import sheep.game.Layer;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.math.BoundingBox;
import no.ntnu.folk.game.gameplay.Button;

public class GameLayer extends Layer implements CollisionListener {
	private Image aimImage = new Image(R.drawable.aim);
	private GameModel model;
	private Sprite aim = new Sprite(aimImage);
	private Button fireKey;

	public GameLayer(GameModel model) {
		this.model = model;
		float fireKeyX = Constants.getWindowSize()[0]*0.4f;
		float keyY = Constants.getWindowSize()[1] * 0.8f;
		float aimKeyX = Constants.getWindowSize()[0]*5f;
		float aimKeyY = Constants.getWindowSize()[1]*5f;
		fireKey = new Button(R.drawable.firekey, R.drawable.firekey, fireKeyX, keyY);
		aim.setPosition(aimKeyX, aimKeyY);
//		aim = new Sprite(aimImage);
		for (PlayerModel player : model.getPlayerList()) {
			player.addCollisionListener(this);
		}
	}

	@Override
	public void update(float dt) {
		if(fireKey.isPressed()){
			fireWeapon();
		}
		for (PlayerModel player : model.getPlayerList()) {
			player.update(dt);
		}
		for (ProjectileModel projectile : model.getProjectiles()) {
			projectile.update(dt);
		}
	}

	@Override
	public void draw(Canvas canvas, BoundingBox box) {
		for (PlayerModel player : model.getPlayerList()) {
			player.draw(canvas);
		}
		for (ProjectileModel projectile : model.getProjectiles()) {
			projectile.draw(canvas);
		}
		aim.draw(canvas);
		fireKey.draw(canvas);
	}
	
	public boolean onTouchDown(MotionEvent event){
		if(fireKey.contanis(event.getX(), event.getY())){
			fireKey.setPressed(true);
		}
		aim.setPosition(event.getX(), event.getY());
		return true;
	}
	
	public boolean onTouchMove(MotionEvent event){
		if(fireKey.contanis(event.getX(), event.getY())){
			fireKey.setPressed(true);
		}else{
			fireKey.setPressed(false);
		}
		aim.setPosition(event.getX(), event.getY());
		return true;
	}
	
	public boolean onTouchUp(MotionEvent event){
		fireKey.setPressed(false);
		return true;
	}

	private void fireWeapon() {
				
	}
	
	/**
	 * Called when two Sprite collide.
	 *
	 * @param a The first Sprite (the sprite being listened to).
	 * @param b The other Sprite.
	 */
	@Override
	public void collided(Sprite a, Sprite b) {
		// TODO
	}

}
