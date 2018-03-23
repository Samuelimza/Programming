package entity;

import entity.mob.Mob;
import graphics.Screen;
import graphics.Sprite;

public class Projectile extends Entity {

	private Sprite sprite;
	private Mob shooter;
	private double xd, yd;

	public Projectile(Mob shooter, int x, int y, double xd, double yd, Sprite sprite) {
		this.shooter = shooter;
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.xd = xd;
		this.yd = yd;
		double mag = Math.sqrt(xd * xd + yd * yd);
		this.xd /= mag * 0.5;
		this.yd /= mag * 0.5;
	}

	public Mob getShooter() {
		return shooter;
	}

	public void update() {
		if (x < -8 || y < -8 || x > level.width * 16 || y > level.height * 16) {
			remove();
			return;
		}
		if (!collision()) {
			x += xd;
			y += yd;
		}else{
			remove();
		}
	}

	private boolean collision() {
		if (level.getTile((int) (x + xd) >> 4, (int) (y + yd) >> 4).isSolid()) return true;
		return false;
	}

	public void render(Screen screen) {
		screen.renderSprite((int) x - 8, (int) y - 8, sprite);
	}

}
