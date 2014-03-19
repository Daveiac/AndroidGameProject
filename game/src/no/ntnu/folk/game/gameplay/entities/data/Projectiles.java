package no.ntnu.folk.game.gameplay.entities.data;

import no.ntnu.folk.game.R;

public enum Projectiles {
	HANDGUNBULLET(15, 0, 0, R.drawable.bullet_small, R.drawable.explosion),
	RIFLEBULLET(25, 0, 0, R.drawable.bullet_medium, R.drawable.explosion),
	ROCKET(35, 20, 10, R.drawable.bullet_big, R.drawable.explosion);

	private final int directDamage;
	private final int areaDamage;
	private final int areaDamageRange;
	private final int image;
	private final int explosionImage;

	/**
	 * @param directDamage    damage dealt by being hit directly by this projectile
	 * @param areaDamage      damage dealt to entities within range of where this projectile hits
	 * @param areaDamageRange range for area damage
	 * @param image           Image ID for this projectile
	 * @param explosionImage  Image ID for the explosion
	 */
	Projectiles(int directDamage, int areaDamage, int areaDamageRange, int image, int explosionImage) {
		this.directDamage = directDamage;
		this.areaDamage = areaDamage;
		this.areaDamageRange = areaDamageRange;
		this.image = image;
		this.explosionImage = explosionImage;
	}

	/**
	 * @return damage dealt by being hit directly by this projectile
	 */
	public int getDirectDamage() {
		return directDamage;
	}
	/**
	 * @return damage dealt to entities within range of where this projectile hits
	 */
	public int getAreaDamage() {
		return areaDamage;
	}
	/**
	 * @return range for area damage
	 */
	public int getAreaDamageRange() {
		return areaDamageRange;
	}

	/**
	 * @return Image id for this projectile
	 */
	public int getImage() {
		return image;
	}
	
	public int getExplosionImage(){
		return explosionImage;
	}

}
